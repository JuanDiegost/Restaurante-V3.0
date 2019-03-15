package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.entity.ManagerRestaurant;
import model.entity.RestaurantTable;
import model.entity.Waiter;
import model.persistence.Persistence;

/***
 * Clase encargada de gestionar los meseros del restaurante
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class DaoWaiter {
	// ------------------------------Attributes---------------------------
	/**
	 * lista de camareros
	 */
	private ArrayList<Waiter> waitersList;

	// ------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 */
	public DaoWaiter() {
		this.waitersList = new ArrayList<Waiter>();
		/* Cargamos los camareros por default */
		loadWaiters();
	}

	// --------------------------------Methods----------------------------
	/**
	 * Método que agrega un mesero a la lista
	 * @param id Identificador del mesero
	 */
	public void addWaiter(int id, List<RestaurantTable> tables, int maxNumerOrders, ManagerRestaurant managerRestaurant,
			Persistence persistence) {
		/*Agregamos un camarero a la lista de camareros*/
		this.waitersList.add(new Waiter(id, tables, maxNumerOrders, managerRestaurant, persistence));
	}

	/**
	 * M�todo para cargar los meseros por defecto
	 */
	private void loadWaiters() {
		/* Agregamos los 2 camareros a la lista de camareros */
		this.waitersList.add(new Waiter(1));
		this.waitersList.add(new Waiter(2));
	}

	// --------------------------------Getters----------------------------
	public ArrayList<Waiter> getWaitersList() {
		return waitersList;
	}
	// --------------------------------Setters----------------------------
}