package model.dao;

import java.util.ArrayList;

import model.entity.Cashier;
import model.entity.Order;
import model.entity.RestaurantTable;
import model.entity.Waiter;
import model.persistence.Persistence;

/**
 * Clase encargada de la gestion de los pedidos del restaurante
 * @author Andres Torres y Lina Melo
 *
 */
public class DaoOrder {
	//------------------------------Attributes---------------------------
	/**
	 * Identificador incremental para los pedidos
	 */
	private int idIncremental;
	/**
	 * Lista de ordenes
	 */
	private ArrayList<Order> orderslist;
	//------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 */
	public DaoOrder() {
		this.idIncremental = 1;
		this.orderslist = new ArrayList<Order>();
	}
	//--------------------------------Methods----------------------------
	/**
	 * Método que agrega un pedido a la lista de pedidos
	 * @param idTablet: Identificador de la mesa donde se realizo el pedido
	 * @param persistence: Instancia de Persistencia donde se encuentran los números pseudo-aleatorios
	 * @param daoProduct: Gestor de los productos del restaurante
	 * @param quantityDiners: Cantidad de comensales del pedido
	 */
	public void addOrder( int idTablet, Persistence persistence, DaoProduct daoProduct,int quantityDiners,double attentioTime,Cashier cashier,Waiter waiter,RestaurantTable restaurantTable) {
		/*Agregamos un pedido a la lista de pedidos*/
		this.orderslist.add(new Order(idTablet, persistence, daoProduct,quantityDiners,attentioTime,cashier,waiter,restaurantTable));
		/*Incrementamos automaticamente el valor del identificador de los pedidos*/
		this.idIncremental++;
	}
	//--------------------------------Getters----------------------------
	public ArrayList<Order> getOrderslist() {
		return orderslist;
	}
}