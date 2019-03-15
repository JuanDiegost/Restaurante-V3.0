package model.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.entity.Chef;
import model.entity.Consumption;
import model.entity.Order;
import model.entity.StateConsumption;
import model.entity.TypePlate;

public class Cocina {

	/**
	 * Lista de cosineros
	 */
	private List<Chef> chefList;
	/**
	 * Cola de ordenes para preparar
	 */
	private Queue<Order> orders;
	/**
	 * Lista de ordenes completadas
	 */
	private List<Order> ordersPrepared;

	public Cocina() {
		this.chefList = new ArrayList<>();
		this.orders = new LinkedList<Order>();
		this.ordersPrepared = new ArrayList<>();
		loadCheft();
	}

	/**
	 * Indica que el chef debe empesar a cosinar, se llama desde el chef para
	 * indicar que ya terino de cosinar y que esta disponible
	 * 
	 * @param chef
	 */
	public void startCook(Chef chef) {
		if (!orders.isEmpty()) {
			isOrderComplete();
			try {
				chef.setOrderToPrepared(getNextOrder(chef, orders.peek()));
			} catch (Exception e) {
			}

		}
	}

	/**
	 * Evalua si se completo la orden, si es asi se quita de la cola de preparación,
	 * y se ingresa en la lista de ordenes para llevar a la mesa
	 * 
	 */
	private void isOrderComplete() {
		if (!orders.isEmpty()) {
			if (orders.peek().isEndPrepared()) {
				ordersPrepared.add(orders.poll());
			}
		}
	}

	/**
	 * Retorna la lista de ordenes que ya se han preparado , por mesero
	 * 
	 * @param idWaiter
	 *            el id de del mesero
	 * @return la lista de ordenes completas
	 */
	public List<Order> getOrdersPreared(int idWaiter) {
		List<Order> orders = new ArrayList<>();
		for (Order order : orders) {
			if (order.getIdWaiter() == idWaiter && order.isEndPrepared()) {
				orders.add(order);
			}
		}
		return orders;
	}

	/**
	 * Crea los cosineros
	 */
	private void loadCheft() {
		// Creación cosinero 1, puede prepara entradas, platos fuertes y postres; puede
		// preparar dos postres al mismo tiempo
		Chef chef = new Chef(1, "JOSE", TypePlate.POSTRE, this);
		chef.addTypePlate(TypePlate.ENTRADA);
		chef.addTypePlate(TypePlate.PLATO_FUERTE);
		chef.addTypePlate(TypePlate.POSTRE);

		this.chefList.add(chef);
		chef.start();

		// Creación cosinero 2, puede prepara entradas y platos fuertes; puede preparar
		// dos entradas al mismo tiempo
		Chef chef2 = new Chef(2, "PEPE", TypePlate.ENTRADA, this);
		chef2.addTypePlate(TypePlate.ENTRADA);
		chef2.addTypePlate(TypePlate.PLATO_FUERTE);
		this.chefList.add(chef2);

		chef2.start();
	}

	/**
	 * Agrega una nueva orden a la cosina para que esta sea preparada
	 * 
	 * @param order
	 */
	public void addOrder(Order order) {
		this.orders.add(order);
		for (Chef chef : chefList) {
			if (!chef.isCompleteAllPlats()) {
				startCook(chef);
			}
		}
	}

	/**
	 * Agrega una todas las ordenes, a la cola de preparación
	 * 
	 * @param order
	 */
	public void addAllOrders(List<Order> orders) {
		this.orders.addAll(orders);
	}

	public void serachChefFree() {
		for (Chef chef : chefList) {
			if (chef.isCompleteAllPlats()) {
				startCook(chef);
			}
		}
	}

	/**
	 * Retorna la lista de los siguientes productos que deve preparar, retorna 2, si
	 * es la especilidad del chef
	 * 
	 * @param chef
	 * @param order
	 * @return
	 */
	public List<Consumption> getNextOrder(Chef chef, Order order) {
		List<Consumption> consumptions = new ArrayList<>();
		for (Consumption consumption : order.getListProductToPrepared()) {
			if (consumptions.isEmpty()) {
				// Evalua si el plato todavia no se ha cocinado
				if (consumption.getConsumption().equals(StateConsumption.ORDER)
						&& chefCanCookPlate(chef, consumption.getProduct().getTypePlate())) {
					consumption.setAsigned();
					consumptions.add(consumption);
					// si no es la especialidad del chef solo retorna un producto si no se va por el
					// else en las siguintes iteraciones
					if (!consumption.getProduct().getTypePlate().equals(chef.getSpecialty())) {
						return consumptions;
					}
				}
			} else {
				// si encuentra otra especialidad la agrega a la lista y termina, si no pues nno
				// hace nada
				if (consumption.getConsumption().equals(StateConsumption.ORDER)
						&& consumption.getProduct().getTypePlate().equals(chef.getSpecialty())) {
					consumption.setAsigned();
					consumptions.add(consumption);
					return consumptions;
				}
			}
		}
		return consumptions;
	}

	private boolean chefCanCookPlate(Chef chef, TypePlate plate) {
		for (TypePlate typePlate : chef.getPlateTypeList()) {
			if (typePlate.equals(plate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Revisa la lista de los platos que esta preparando cada cosinero, para saber
	 * cual plato ya esta listo, si esta listo los quita de la lista de preparación
	 * 
	 * @param chef
	 * @return
	 */
	public void getOrdersCompleteByChef(Chef chef) {
		if (chef.isCompleteAllPlats()) {
			chef.getPlateTypeList().clear();
		}
	}

}
