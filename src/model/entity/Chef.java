package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.dao.Cocina;

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

	private Cocina cocina;

	// -----------CONSTRUCTOR------------------------------

	public Chef(int id, String name, TypePlate specialty, Cocina cocina) {
		super();
		this.id = id;
		this.plateTypeList = new ArrayList<>();
		this.name = name;
		this.specialty = specialty;
		this.orderToPrepared = new ArrayList<>();
		this.cocina = cocina;
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
		while (true) {
			int maxTimeToPrepared = 0;
			// Calcula el maximo tiempo de preparacion en caso de prepara dos ala vez si no
			// pues el tiempo del producto
			for (Consumption consumption : orderToPrepared) {
				if (consumption.getConsumption().equals(StateConsumption.ASIGNED)) {
					consumption.setPreparing();
					System.out.println("Chef "+id +" prepara " + consumption.getProduct().getName());
					maxTimeToPrepared = consumption.getTimeToPrepared() > maxTimeToPrepared
							? consumption.getTimeToPrepared()
							: maxTimeToPrepared;
				consumption.setPrepared();
				}
			}
			try {
				// Espera el tiempo en preparar ese plato
				//Thread.sleep(maxTimeToPrepared * GlobalConstant.SPEED_SYSTEM);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Marca todos los productos como preparados
			//ManagerRestaurant.getManagerRestaurant().getCocina().startCook(this);
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
			if (consumption.getConsumption().equals(StateConsumption.PREPARING)) {
				return false;
			}
		}
		return true;
	}

	public void setOrderToPrepared(List<Consumption> orderToPrepared) {
		this.orderToPrepared = orderToPrepared;
	}

}