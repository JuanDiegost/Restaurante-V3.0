package model.entity;

import model.dao.Cocina;
import model.dao.DaoOrder;
import model.dao.DaoProduct;
import model.dao.DaoRestauranTable;
import model.dao.DaoWaiter;
import model.persistence.Persistence;
/**
 * Clase encargada de la estructura de el adminitrador del restaurante
 * @author Andres Torres y Lina Melo
 *
 */
public class ManagerRestaurant {
	//------------------------------Attributes---------------------------
	/**
	 * Instancia del dao productos
	 */
	private DaoProduct daoProduct;
	/**
	 * Instancia del dao meseros
	 */
	private DaoWaiter daoWaiter;
	/**
	 * Instancia del dao pedidos
	 */
	private DaoOrder daoOrder;
	/**
	 * Instancia del dao mesas del restaurante
	 */
	private DaoRestauranTable daoRestaurantTable;
	
	/**
	 * Instancia del dao chef
	 */
	private Cocina cocina;
	
	/**
	 * Instancia de la caja.
	 */
	private Cashier cashier;
	/**
	 * Instancia de la persistencia
	 */
	private Persistence persistence;
	//------------------------------Constructor--------------------------
	public ManagerRestaurant(Persistence persistence) {
		this.persistence = persistence;
		this.daoOrder = new DaoOrder();
		this.daoProduct = new DaoProduct();
		this.daoWaiter = new DaoWaiter();
		this.daoRestaurantTable = new DaoRestauranTable();
		this.cocina=new Cocina();
		this.cashier=new Cashier(0);
	}
	//--------------------------------Methods----------------------------
	/**
	 * Calculamos la cantidad de  dias a simular
	 * @return arreglo 2 posiciones: [0] horas [1] días
	 */
	public float[] calculateHoursAndDaysToSimulate() {
		/*Definimos un arreglo para guardar los 2 valores*/
		float[] values = {0,0};
		/*Definimos un número random para ver la posición del numero en la lista de horas generadas pseudo-aleatoriamente*/
		int numberRandom;
		/*Generamos un ciclo hasta que se cumpla minimo 100 horas*/
		while(values[0] <= GlobalConstant.HOURS_TO_SIMULATE) {
			/*Asignamos a la variable la posición de lista de horas pseudo-aleatorias*/
			numberRandom =(int)(Math.random()*999);
			/*Acumulamos el valor de horas*/
			values[0] += persistence.getHoursList().get(numberRandom);
			/*Sumamos 1 a los días a simular*/
			values[1]++;
		}
		/*Retornamos el arreglo con la informaci�n*/
		return values;
	}
	/**
	 * Método que calcula los comensales dependiendo de los días a simular
	 * @return
	 */
	public int calculateDinersDependsDaysToSimulate(int days) {
		/*Definimos una variable para contar la cantidad de comensales*/
		int diners = 0;
		/*Generamos un n�mero random para seleccionar de la lista de n�mero ya validados */
		int numberRandomToInitOnList =(int)(Math.random()*(999 - days));
		/*Recorremos la lista desde la posici�n que obtuvimos hasta la cantidad de d�as a simular*/
		for (int i = numberRandomToInitOnList; i < numberRandomToInitOnList+days; i++) {
			/*Acumulamos esa cantidad de comensales*/
			diners += persistence.getDinersList().get(i);
		}
		/*Retornamos la cantidad total de comensales calculados*/
		return diners;
	}
	
	
	//--------------------------------Getters----------------------------
	public DaoProduct getDaoProduct() {
		return daoProduct;
	}
	public DaoWaiter getDaoWaiter() {
		return daoWaiter;
	}
	public DaoOrder getDaoOrder() {
		return daoOrder;
	}
	public DaoRestauranTable getDaoRestaurantTable() {
		return daoRestaurantTable;
	}
	
	public Cocina getCocina() {
		return cocina;
	}
	
	public Cashier getCashier() {
		return cashier;
	}
}