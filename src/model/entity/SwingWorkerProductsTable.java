package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

import model.persistence.Persistence;
import view.PrincipalWindow;
/**
 * Clase encargada de manejar el proceso del modelo ya actualizar la vista paralelamente
 *  @author Andres Torres y Lina Melo
 *
 */
public class SwingWorkerProductsTable extends SwingWorker<Void, ArrayList<Product>>{
	//------------------------------Attributes---------------------------
	/**
	 * Instancia del administrador del restaurante
	 */
	private ManagerRestaurant manager;
	/**
	 * Comensales a atender
	 */
	private int diners;
	/**
	 * Instancia de la persistencia
	 */
	private Persistence persistence;
	/**
	 * Instancia de la vista
	 */
	private PrincipalWindow window;
	//------------------------------Constructor--------------------------
	/**
	 * Constrolador por defecto
	 * @param manager Administrador del restaurante
	 */
	public SwingWorkerProductsTable(ManagerRestaurant manager, int diners,Persistence persistence,PrincipalWindow window) {
		this.manager = manager;
		this.diners = diners;
		this.persistence = persistence;
		this.window = window;
	}
	//--------------------------------Methods----------------------------
	@Override
	protected Void doInBackground() throws Exception {
		/*Creamos un executor service que maneje los hilos que en este caso serán la cantidad de meseros*/
		ExecutorService executor = Executors.newFixedThreadPool(manager.getDaoWaiter().getWaitersList().size());
		/*Hacemos un ciclo para la cantidad de ordenes*/
		RestaurantTable table = null;
		int aux = 0;int attendedDiners = 0;
		while(attendedDiners < diners) {
			/*Generamos un número random para buscar el la lista de mesas a ver en cual ubicar la orden*/
			aux = (int)Math.random()*999;
			/*Seleeccionamos la mesa pseudoaleatoriamente*/
			table =  manager.getDaoRestaurantTable().determinateTablePseudoRandomly(persistence.getRestaurantTablesList().get(aux));
			/*Determinamos la cantidad de comensales por mesa*/
			aux = (int) (Math.random() * 3) + 1;
			if(aux > diners - attendedDiners) {
				aux = diners - attendedDiners;
			}
			/*Creamos una orden*/
			manager.getDaoOrder().addOrder(table.getId(),persistence, manager.getDaoProduct(),aux,persistence.getAttentioTimeList().get(aux),manager.getCashier(),manager.getCocina());
			attendedDiners+= aux;
			/*Lanzamos el hilo al ejecutor para que se ponga en la cola de ejecución*/
			executor.execute(manager.getDaoOrder().getOrderslist().get(manager.getDaoOrder().getOrderslist().size()-1));
		}
		/*Termina el ejecutor de tareas para que no siga esperando*/
		executor.shutdown();
		/*Tiempo de espera hasta que terminen todos los procesos*/
		while (!executor.isTerminated()) {
			publish(manager.getDaoProduct().getProductList()); /*Supone que lo manda a vista*/
		}
		return null;
	}
	
	@Override
	protected void process(List<ArrayList<Product>> chunks) {
		/*Pinto la vista con lo que se supone que me esta llegando del publish*/
		window.getPanelTable().revalidateTableWithSpecificItems(chunks.get(0));
		/*Buscamos el producto con mayores utilidades*/
		Product productAux = manager.getDaoProduct().searchProductWithBestUtilities(chunks.get(0));
		/*Cargamos los datos del mejor producto del restaurante a la vista*/
		window.setValuesOfBestProduct(productAux.getName(), (productAux.getPrice()*productAux.getQuantityQualifications())*0.025);
	}
}