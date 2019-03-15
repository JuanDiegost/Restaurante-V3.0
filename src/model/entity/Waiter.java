package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.persistence.Persistence;

/**
 * Clase encargada de la estructura del mesero
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class Waiter extends Thread {
	// -------------------------------Attributes-------------------------
	/**
	 * Identificador del mesero
	 */
	private int idWaiter;

	/**
	 * Lista de las mesas que atiende el mesero
	 */
	private List<RestaurantTable> tables;

	/**
	 * Lista de ordenes que tiene el mesero
	 */
	private List<Order> orders;

	/**
	 * Lista de ordenes que tiene el mesero
	 */
	private List<Order> ordersInKitchen;

	/**
	 * Maximo numero de ordenes que puede atender un mesero
	 */
	private int maxNumerOrders;

	/**
	 * Propina que recibido el mesero
	 */
	private double tip;

	/**
	 * suma de todas las calificaciones dadas
	 */
	private int calification;

	/**
	 * Numero de clientes atendidos
	 */
	private int numerClientAttend;

	/**
	 * Estado que indica si el mesero esta tomando ordenes, llevandolas, en la
	 * cocina o esta libre
	 */
	private StateWaiter stateWaiter;

	private ManagerRestaurant managerRestaurant;
	private Persistence persistence;

	// ------------------------------Constructor-------------------------
	public Waiter(int id, List<RestaurantTable> tables, int maxNumerOrders, ManagerRestaurant managerRestaurant,
			Persistence persistence) {
		this.idWaiter = id;
		this.maxNumerOrders = maxNumerOrders;
		this.tables = tables;
		this.orders = new ArrayList<>();
		this.ordersInKitchen = new ArrayList<>();
		this.tip = 0;
		this.calification = 0;
		this.numerClientAttend = 0;
		this.managerRestaurant = managerRestaurant;
		this.persistence = persistence;
		this.stateWaiter = StateWaiter.SEARCH_TABLES;
	}

	public Waiter(int id) {
		this.idWaiter = id;
		this.tables = new ArrayList<>();

	}

	// ---------------------------------Methods--------------------------

	@Override
	public void run() {
		super.run();
		while (true) {
			attendTables();
			goToKitchen();
			distributeOrders();
		}
	}

	/**
	 * Recorre la lista de todas las ordenes en la cocina, y si ya están listas las
	 * reparte
	 */
	private void distributeOrders() {
		try {
			Thread.sleep(1000);// TODO Tiempo que tarda el mesero en ir de cocina a las mesas o lo contrario
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (ordersInKitchen != null) {
			try {
				System.out.println("Reviso ordenes listas");
				for (Order order : ordersInKitchen) {
					if (order.isAllOrderPrepated()) {
						// Empizan a consumir los platos (el else)
						order.start();
						ordersInKitchen.remove(order);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		stateWaiter = StateWaiter.SEARCH_TABLES;
	}

	/**
	 * Recorre la lista de mesas, y si estan ocupadasm y no han ordenado, les toma
	 */
	private void attendTables() {
		int numberOrders = 0;
		this.stateWaiter = StateWaiter.SEARCH_TABLES;
		while (this.stateWaiter.equals(StateWaiter.SEARCH_TABLES)) {
			for (RestaurantTable restaurantTable : ManagerRestaurant.getManagerRestaurant().getDaoRestaurantTable().getRestaurantTablesList()) {
				if (restaurantTable.isEmpty() && !stateWaiter.equals(StateWaiter.ATTEND_TABLE)
						&& restaurantTable.getOrder() == null) {
					stateWaiter = StateWaiter.ATTEND_TABLE;
					Order order = restaurantTable.startAtention(managerRestaurant, persistence, this);
					if (orders == null) {
						orders = new ArrayList<>();
					}
					if (ordersInKitchen == null) {
						ordersInKitchen = new ArrayList<>();
					}
					this.orders.add(order);
					restaurantTable.setOrder(order);
					
					
					order.attendOrder(this);
					System.out.println(order.getIdOrder());
					
					
					while (stateWaiter.equals(StateWaiter.ATTEND_TABLE)) {
					}

					numberOrders++;
					if (numberOrders >= 3) {
						this.stateWaiter = StateWaiter.SEND_ORDER_TO_KITCHEN;
						return;
					}
				}

			}
			this.stateWaiter = StateWaiter.SEND_ORDER_TO_KITCHEN;
		}
	}

	/**
	 * Va a la cosina, se tarda un tiempo, y si tiene ordenes de clientes, las deja
	 * en la cola de preparación.
	 */
	private void goToKitchen() {
		try {
			Thread.sleep(100);// TODO Tiempo que tarda el mesero en ir de cocina a las mesas o lo contrario
			this.stateWaiter = StateWaiter.IN_KITCHEN;
			if (orders != null && ordersInKitchen != null) {
				ordersInKitchen.addAll(orders);
				ManagerRestaurant.getManagerRestaurant().getCocina().addAllOrders(orders);
			}
			ManagerRestaurant.getManagerRestaurant().getCocina().serachChefFree();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void addTable(RestaurantTable restaurantTable) {
		this.tables.add(restaurantTable);
	}

	public void addOrder(Order order) {
		this.orders.add(order);
	}

	public void removeOrder(Order order) {
		this.orders.remove(order);
	}

	public void addTip(double tip) {
		this.tip += tip;
	}

	public void addCalification(int calification) {
		this.calification += calification;
		this.numerClientAttend++;
	}

	// ---------------------------------Getters--------------------------
	public int getIdWaiter() {
		return idWaiter;
	}

	public int getMaxNumerOrders() {
		return maxNumerOrders;
	}

	public double getTip() {
		return tip;
	}

	public double getCalification() {
		return this.calification / this.numerClientAttend;
	}

	public Persistence getPersistence() {
		return persistence;
	}
	// ---------------------------------Setters--------------------------

	public void setStateWaiter(StateWaiter stateWaiter) {
		this.stateWaiter = stateWaiter;
	}
}