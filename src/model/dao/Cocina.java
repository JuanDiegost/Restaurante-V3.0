package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.entity.Chef;
import model.entity.Order;
import model.entity.TypePlate;

public class Cocina extends Thread {

	/**
	 * Lista de cosineros
	 */
	private List<Chef> chefList;
	private List<Order> orders;
	private List<Order> ordersComplete;

	public Cocina() {
		this.chefList = new ArrayList<>();
		this.orders = new ArrayList<>();
		this.ordersComplete = new ArrayList<>();
		loadCheft();
	}

	/**
	 * Crea los cosineros
	 */
	private void loadCheft() {
		// Creación cosinero 1, puede prepara entradas, platos fuertes y postres; puede
		// preparar dos postres al mismo tiempo
		Chef chef = new Chef(1, "JOSE", TypePlate.POSTRE);
		chef.addTypePlate(TypePlate.ENTRADA);
		chef.addTypePlate(TypePlate.PLATO_FUERTE);
		chef.addTypePlate(TypePlate.POSTRE);

		this.chefList.add(chef);

		// Creación cosinero 2, puede prepara entradas y platos fuertes; puede preparar
		// dos entradas al mismo tiempo
		Chef chef2 = new Chef(2, "PEPE", TypePlate.ENTRADA);
		chef2.addTypePlate(TypePlate.ENTRADA);
		chef2.addTypePlate(TypePlate.PLATO_FUERTE);
		this.chefList.add(chef2);
	}

	/**
	 * Agrega una nueva orden a la cosina para que esta sea preparada
	 * 
	 * @param order
	 */
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	/**
	 * Retorna la lista de los siguientes productos que deve preparar, retorna 2, si e la especilidad del chef
	 * @param typePlate el tipo de plato que puede cocinar el chef al mismo tiempo
	 */
	public void getNextOrder(TypePlate specialty) {
		for (Order order : orders) {
			
		}
	}

	/**
	 * Revisa la lista de los platos que esta preparando cada cosinero, para saber
	 * cual plato ya esta listo
	 * 
	 * @param chef
	 * @return
	 */
	private void getOrdersCompleteByChef(Chef chef) {
		if (chef.isCompleteAllPlats()) {
			chef.getPlateTypeList().clear();
		}
	}

	@Override
	public void run() {
		super.run();
		//Revisa que chefs ya acabaron de preparar el producto
		for (Chef chef : chefList) {
			getOrdersCompleteByChef(chef);
		}
		
	}

}
