package model.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de la estructura del mesero
 * @author Andres Torres y Lina Melo
 *
 */
public class Waiter {
	//-------------------------------Attributes-------------------------
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
	
	//------------------------------Constructor-------------------------
	public Waiter(int id,List<RestaurantTable> tables,int maxNumerOrders) {
		this.idWaiter = id;
		this.maxNumerOrders=maxNumerOrders;
		this.tables=tables;
		this.orders=new ArrayList<>();
		this.tip=0;
		this.calification=0;
		this.numerClientAttend=0;
	}
	
	public Waiter(int id) {
		this.idWaiter=id;
		this.tables=new ArrayList<>();
	}
	
	//---------------------------------Methods--------------------------
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
		this.tip+=tip;
	}
	public void addCalification(int calification) {
		this.calification+=calification;
		this.numerClientAttend++;
	}
	//---------------------------------Getters--------------------------
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
		return this.calification/this.numerClientAttend;
	}
}