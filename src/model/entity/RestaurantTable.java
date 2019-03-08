package model.entity;

/**
 * Esta clase es la encargada de una mesa en el resturante
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class RestaurantTable {
	/**
	 * Identificar de la mesa
	 */
	private int id;
	private boolean empty;
	private Order order;

	// -----------------------------Constructor-------------------------
	/**
	 * Constructor por defecto
	 * 
	 * @param id
	 *            Identificador de la mesa
	 */
	public RestaurantTable(int id) {
		this.id = id;
		this.empty = false;
	}

	// ------------------------------Getters-----------------------------
	public int getId() {
		return id;
	}
	public boolean isEmpty() {
		return empty;
	}
	public Order getOrder() {
		return order;
	}

	// ------------------------------Setters-------------------------------
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}