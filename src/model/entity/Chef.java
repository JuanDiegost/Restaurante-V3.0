package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Chef extends Thread {

	/**
	 * Identificador del cheft
	 */
	private int id;

	/**
	 * Lista de los tipos de platos(entradas, platos fuertes y postres) que puede
	 * cosinar el cheft
	 */
	private List<TypePlate> plateTypeList;

	/**
	 * Lista de los tipos de platos que se deven preparar
	 */
	private List<Consumption> orderToPrepared;

	/**
	 * Tipo de plato que puede preparar dos al mismo tiempo
	 */
	private TypePlate specialty;

	/**
	 * Nombre del chef
	 */
	private String name;

	// -----------CONSTRUCTOR------------------------------

	public Chef(int id, String name, TypePlate specialty) {
		super();
		this.id = id;
		this.plateTypeList = new ArrayList<>();
		this.name = name;
		this.specialty = specialty;
		this.orderToPrepared = new ArrayList<>();
	}

	/**
	 * Agrega un nuevo tipo de plato que puede preparar el chef
	 * 
	 * @param typePlate
	 *            el nuevo tipo de plato que puede preparar
	 */
	public void addTypePlate(TypePlate typePlate) {
		this.plateTypeList.add(typePlate);
	}

	/**
	 * Agrega un nuevo producto a la lista de ordenes por preparar
	 * 
	 * @param order
	 */
	public void addProductToPrepared(Consumption consumption) {
		this.orderToPrepared.add(consumption);
	}

	@Override
	public void run() {
		int maxTimeToPrepared = 0;
		for (Consumption consumption : orderToPrepared) {
			consumption.setPreparing();
			maxTimeToPrepared = consumption.getTimeToPrepared() > maxTimeToPrepared ? consumption.getTimeToPrepared()
					: maxTimeToPrepared;
		}
		try {
			Thread.sleep(maxTimeToPrepared * GlobalConstant.SPEED_SYSTEM);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Consumption consumption : orderToPrepared) {
			consumption.setPrepared();
		}
	}

	// -----------------GETTERS---------------------------
	public int getIdChef() {
		return id;
	}

	public List<TypePlate> getPlateTypeList() {
		return plateTypeList;
	}

	public String getNameChef() {
		return name;
	}

	public TypePlate getSpecialty() {
		return specialty;
	}
	
	public boolean isCompleteAllPlats() {
		for (Consumption consumption : orderToPrepared) {
			if(consumption.getConsumption().equals(StateConsumption.PREPARING)) {
				return false;
			}
		}
		return true;
	}

}