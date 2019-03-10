package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoProduct;
import utils.Utils;

public class Client extends Thread {

	private static int ID_COUNT = 0;
	private int id;
	private int timeToConsume;
	private double calificationWaiter;

	/**
	 * Lista de productos que el cliente desea consumir
	 */
	private List<Consumption> consumptions;

	public Client(int timeToConsume) {
		this.id = ID_COUNT;
		this.timeToConsume = timeToConsume;
		ID_COUNT++;
		consumptions = new ArrayList<>();
		generateListProduct();
	}

	/**
	 * Marca todos los platos, como consumidos.
	 */
	public void endEat() {
		for (Consumption consumption : consumptions) {
			consumption.setEnd();
		}
	}

	/**
	 * Genera la lista de de plaos que va aconsumir el cliente, postres, entradas,
	 * plato fuerte
	 */
	private void generateListProduct() {
		addEntrace();
		addDesert();
		addMainPlate();
	}

	/**
	 * Agrega un numero aleatorio de entradas de 0 a 1
	 */
	private void addEntrace() {
		int numEntrace = Utils.generateRandom(GlobalConstant.MIN_NUM_ENTRACE, GlobalConstant.MAX_NUM_ENTRACE);
		for (int i = 0; i < numEntrace; i++) {
			consumptions.add(new Consumption(DaoProduct.getRandomEntrece()));
		}
	}

	/**
	 * Agrega un 1 plato fuerte al pedido, pero se puede cambiar s
	 */
	private void addMainPlate() {
		int numMainPLate = Utils.generateRandom(GlobalConstant.MIN_NUM_MAIN_COURSE, GlobalConstant.MAX_NUM_MAIN_COURSE);
		for (int i = 0; i < numMainPLate; i++) {
			consumptions.add(new Consumption(DaoProduct.getRandomMain()));
		}
	}

	/**
	 * Agrega un numero aleatorio de postres, al pedido, de 0 a 2
	 */
	private void addDesert() {
		int numDessert = Utils.generateRandom(GlobalConstant.MIN_NUM_DESSERT, GlobalConstant.MAX_NUM_DESSERT);
		for (int i = 0; i < numDessert; i++) {
			consumptions.add(new Consumption(DaoProduct.getRandomDessert()));
		}
	}

	/**
	 * Retorna true, si todos los pedidos del clinete ya han sido preparados
	 * 
	 * @return
	 */
	public boolean isEndPrepared() {
		for (Consumption consumption : consumptions) {
			if (!consumption.getConsumption().equals(StateConsumption.PREPARED)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retorna true si el cliente ya acabado de consumir su pedido si no false
	 * 
	 * @return
	 */
	public boolean isEndEat() {
		for (Consumption consumption : consumptions) {
			if (!consumption.getConsumption().equals(StateConsumption.END)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void run() {
		super.run();
		try {
			for (Consumption consumption : consumptions) {
				if (consumption.getConsumption().equals(StateConsumption.PREPARED)) {
					consumption.setEating();
				}
			}

			Thread.sleep(timeToConsume * GlobalConstant.SPEED_SYSTEM);

			for (Consumption consumption : consumptions) {
				if (consumption.getConsumption().equals(StateConsumption.EATING)) {
					consumption.setEnd();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// -----------GETTERS-----------------
	/**
	 * Retorna el tiempo que el cliente tarda en consumir su pedido
	 * 
	 * @return
	 */
	public int getTimeToConsume() {
		return timeToConsume;
	}

	
	public int getIdClient() {
		return this.id;
	}

	public List<Consumption> getConsumptions() {
		return consumptions;
	}

	public double getCalificationWaiter() {
		return calificationWaiter;
	}

	// ---------Setters-------------------
	public void setCalificationWaiter(double calificationWaiter) {
		this.calificationWaiter = calificationWaiter;
	}

	public void calificatePlats() {
		for (Consumption consumption : consumptions) {
			consumption.setCalification(Math.random() * 5);
		}
	}
}