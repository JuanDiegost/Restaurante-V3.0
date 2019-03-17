package model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.SwingWorker;

import model.persistence.Persistence;
import utils.Utils;
import view.PrincipalWindow;

/**
 * Clase encargada de manejar el proceso del modelo ya actualizar la vista
 * paralelamente
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class SwingWorkerProductsTable extends SwingWorker<Void, ArrayList<Product>> {
	// ------------------------------Attributes---------------------------
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

	private view.PanelStage panelStage;


	// ------------------------------Constructor--------------------------
	/**
	 * Constrolador por defecto
	 * 
	 * @param manager
	 *            Administrador del restaurante
	 */
	public SwingWorkerProductsTable(ManagerRestaurant manager, int diners, Persistence persistence,
			PrincipalWindow window, view.PanelStage stage) {
		this.manager = manager;
		this.diners = diners;
		this.persistence = persistence;
		this.window = window;
		this.panelStage = stage;
	}

	// --------------------------------Methods----------------------------
	@Override
	protected Void doInBackground() throws Exception {
		/*
		 * Creamos un executor service que maneje los hilos que en este caso serán la
		 * cantidad de meseros
		 */
		ExecutorService executor = Executors.newFixedThreadPool(1);
		/* Hacemos un ciclo para la cantidad de ordenes */
		RestaurantTable table = null;
		int aux = 0;
		int attendedDiners = 0;
		manager.startAtention();
		while (attendedDiners < 5) {
			attendedDiners += 1;
			// /*Lanzamos el hilo al ejecutor para que se ponga en la cola de ejecución*/
			executor.execute(manager);
		}

		/* Termina el ejecutor de tareas para que no siga esperando */
		executor.shutdown();
		/* Tiempo de espera hasta que terminen todos los procesos */
		while (!executor.isTerminated()) {
			publish(manager.getDaoProduct().getProductList()); /* Supone que lo manda a vista */

		}
		while (!manager.getDaoWaiter().getWaitersList().get(0).isEnd()
				&& !manager.getDaoWaiter().getWaitersList().get(1).isEnd()) {
			manager.getDaoWaiter().getWaitersList().get(0).isWaiterEnd();
			manager.getDaoWaiter().getWaitersList().get(1).isWaiterEnd();
			publish(manager.getDaoProduct().getProductList()); /* Supone que lo manda a vista */
		}
		return null;
	}

	@Override
	protected void process(List<ArrayList<Product>> chunks) {
		/* Pinto la vista con lo que se supone que me esta llegando del publish */
		ArrayList<Product> list = new ArrayList<>();
		list.addAll(ManagerRestaurant.getManagerRestaurant().getDaoProduct().getProductList());
		list.addAll(ManagerRestaurant.getManagerRestaurant().getDaoProduct().getProducDessertList());
		list.addAll(ManagerRestaurant.getManagerRestaurant().getDaoProduct().getProducEntraceList());

		panelStage.getPanelTable().revalidateTableWithSpecificItems(list);
		panelStage.getPanelTableWaiter().revalidateTableWithSpecificItems(
				ManagerRestaurant.getManagerRestaurant().getDaoWaiter().getWaitersList());
		Cashier cashier = ManagerRestaurant.getManagerRestaurant().getCashier();

		int[] l = { cashier.getNumberPaymentCash(), cashier.getNumberPaymentCreditCard(),
				cashier.getNumberPaymentDivided(), cashier.getNumberPaymentAmerican(),
				cashier.getNumberPaymentSingle() };
		panelStage.getPanelTableCashier().revalidateTableWithSpecificItems(l);
		/* Buscamos el producto con mayores utilidades */
		// Product productAux =
		// manager.getDaoProduct().searchProductWithBestUtilities(chunks.get(0));
		/* Cargamos los datos del mejor producto del restaurante a la vista */
		// window.setValuesOfBestProduct(productAux.getName(),
		// (productAux.getPrice()*productAux.getQuantityQualifications())*0.025);
	}

	@Override
	protected void done() {

		for (Product product : manager.getDaoProduct().getProductList()) {
			System.out.println(product.getName() + " : " + product.getTotalQualification());
		}
		System.out.println("Termine ya del todo");
		for (Waiter waiter : ManagerRestaurant.getManagerRestaurant().getDaoWaiter().getWaitersList()) {
			waiter.setEnd(true);
		}
		
		// window.getPanelTable().revalidateTableWithSpecificItems(manager.getDaoProduct().getProductList());

	}

	public void setManager(ManagerRestaurant manager) {
		this.manager = manager;
	}
}