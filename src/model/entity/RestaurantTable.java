package model.entity;

import model.persistence.Persistence;
import utils.Utils;

/**
 * Esta clase es la encargada de una mesa en el resturante
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class RestaurantTable extends Thread {
	/**
	 * Identificar de la mesa
	 */
	private int id;
	private boolean empty;
	private Order order;
	public int quantityOfDiners;
	/**
	 * Tiempo que tarda el mesero en venir a tomar la orden.
	 */
	private int timeToAtention;

	// -----------------------------Constructor-------------------------
	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 *            Identificador de la mesa
	 */
	public RestaurantTable(int id) {
		this.id = id;
		this.empty = true;
		this.timeToAtention = 0;
	}

	public void fillTable() {
		this.empty = false;
		try {
			this.start();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * Empieza la atencion del mesero en la mesa
	 * 
	 * @param managerRestaurant
	 * @param persistence
	 * @param waiter
	 * @return
	 */
	public Order startAtention(ManagerRestaurant managerRestaurant, Persistence persistence, Waiter waiter) {
		// Calculamos la cantidad e comenzales por mesa de 1 a 4
		int quantityOfDiners = Utils.generateRandom(1, 4);
		double randomAttTime = Persistence.getINSTANCE().getAttentioTimeList()
				.get(Utils.generateRandom(0, Persistence.getINSTANCE().getAttentioTimeList().size() - 1));
		this.order = new Order(id, Persistence.getINSTANCE(), ManagerRestaurant.getManagerRestaurant().getDaoProduct(),
				quantityOfDiners, randomAttTime, ManagerRestaurant.getManagerRestaurant().getCashier(), waiter, this);
		this.empty=false;
		return order;
	}

	@Override
	public void run() {

		if (order == null) {
			try {
				Thread.sleep(GlobalConstant.SPEED_SYSTEM);
				// Contador para determinar cuato tarda el mesero en venir a tomar la orden
				this.timeToAtention++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			order.start();
		}

	}

	// ------------------------------Getters-----------------------------
	public int getIdRestauratTable() {
		return id;
	}

	public boolean isEmpty() {
		return empty;
	}

	public Order getOrder() {
		return order;
	}

	public int getTimeToAtention() {
		return timeToAtention;
	}

	// ------------------------------Setters-------------------------------
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}