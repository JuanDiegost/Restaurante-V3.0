package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoProduct;
import utils.Utils;

public class Client extends Thread{

	private static int ID_COUNT = 0;
	private int id;
	private int timeToConsume;

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

	private void generateListProduct() {
		addEntrace();
		addDesert();
		addMainPlate();
	}

	private void addEntrace() {
		int numEntrace = Utils.generateRandom(GlobalConstant.MIN_NUM_ENTRACE, GlobalConstant.MAX_NUM_ENTRACE);
		for (int i = 0; i < numEntrace; i++) {
			consumptions.add(new Consumption(DaoProduct.getRandomEntrece()));
		}
	}

	private void addMainPlate() {
		int numMainPLate = Utils.generateRandom(GlobalConstant.MIN_NUM_MAIN_COURSE, GlobalConstant.MAX_NUM_MAIN_COURSE);
		for (int i = 0; i < numMainPLate; i++) {
			consumptions.add(new Consumption(DaoProduct.getRandomMain()));
		}
	}

	private void addDesert() {
		int numDessert = Utils.generateRandom(GlobalConstant.MIN_NUM_DESSERT, GlobalConstant.MAX_NUM_DESSERT);
		for (int i = 0; i < numDessert; i++) {
			consumptions.add(new Consumption(DaoProduct.getRandomDessert()));
		}
	}

	public int getIdClient() {
		return id;
	}

	public List<Consumption> getConsumptions() {
		return consumptions;
	}
	
	public boolean isEndPrepared() {
		for (Consumption consumption : consumptions) {
			if(!consumption.getConsumption().equals(StateConsumption.PREPARED)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isEndEat() {
		for (Consumption consumption : consumptions) {
			if(!consumption.getConsumption().equals(StateConsumption.END)) {
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
			
			Thread.sleep(timeToConsume*GlobalConstant.SPEED_SYSTEM);
			
			for (Consumption consumption : consumptions) {
				if (consumption.getConsumption().equals(StateConsumption.EATING)) {
					consumption.setEnd();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}