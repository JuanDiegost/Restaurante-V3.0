package model.dao;
/**
 * Clase encargada de gestionar las mesas del resturante
 * @author Andres Torres y Lina Melo
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.entity.GlobalConstant;
import model.entity.RestaurantTable;

public class DaoRestauranTable {

	//-------------------------------Attributes--------------------------
	/**
	 * Lista de mesas
	 */
	private ArrayList<RestaurantTable> restaurantTablesList;
	//------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 */
	public DaoRestauranTable() {
		this.restaurantTablesList = new ArrayList<RestaurantTable>();
		/*Carga la mesas por default*/
//		loadDefaultRestaurantTables();
	}
	//--------------------------------Methods----------------------------
	/**
	 * M�todo que agrega mesa a la lista
	 */
	public void addRestaurantTable(int id) {
		/*Agrega una mesa a la lista */
		this.restaurantTablesList.add(new RestaurantTable(id));
	}

	
	/**
	 * M�todo que determino el id de la mesa dependiendo de un n�mero pseudoaleatorio
	 * @param numberPseudoaleatory
	 * @return Id del plato correspondiente
	 */
	public RestaurantTable determinateTablePseudoRandomly(double numberPseudoaleatory) {
		/*Devuelve la parte entera del número a evaluar como resultado*/
		if(numberPseudoaleatory >= 0 && numberPseudoaleatory < 1) {
			return restaurantTablesList.get(0);
		} else if(numberPseudoaleatory >= 1 && numberPseudoaleatory < 2) {
			return restaurantTablesList.get(1);
		} else if(numberPseudoaleatory >= 2 && numberPseudoaleatory < 3) {
			return restaurantTablesList.get(2);
		} else if(numberPseudoaleatory >= 3 && numberPseudoaleatory < 4) {
			return restaurantTablesList.get(3);
		} else {
			return restaurantTablesList.get(4);
		}
	}
	
	/**
	 * Retorna la lista de mesas del restaurante vacias
	 * @return
	 */
	public List<RestaurantTable> getRestaurantTableEmpTy(){
		List<RestaurantTable> list=new ArrayList<>();
		for (RestaurantTable restaurantTable :restaurantTablesList) {
			if(restaurantTable.isEmpty())
				list.add(restaurantTable);
		}
		return list;
			
	}
	
	/**
	 * Retorna verdadero si el restaurante se encuentra vacio
	 * @return
	 */
	public boolean isEmptyRestaurant() {
		for (RestaurantTable restaurantTable : restaurantTablesList) {
			if(!restaurantTable.isEmpty()) return false;
		}
		return true;
	}
	
	//---------------------------------Getters---------------------------
	public ArrayList<RestaurantTable> getRestaurantTablesList() {
		return restaurantTablesList;
	}

}