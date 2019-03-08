package model.entity;

import java.util.ArrayList;
import model.dao.DaoProduct;
import model.persistence.Persistence;

/**
 * Clase encagada de la estructura de un pedido
 *@author Andres Torres y Lina Melo
 *
 */
public class Order extends Thread{
	//------------------------------Attributes---------------------------
	/**
 	* Identificador de la orden
 	*/
	private int idOrder;
	/**
	 * Identificador de la mesa de la orden
	 */
	private int idTable;
	/**
	 * Identificador del mesero que atendio la mesa
	 */
	private int idWaiter;
	/**
	 * Lista de productos pedidos
	 */
	private Client[] clients;
	/**
	 * Persitencia de las lista de números pseudoaleatorios ya validados
	 */
	private Persistence persistence;
	/**
	 * Instancia de daoProductos
	 */
	private DaoProduct daoProduct;
	
	/**
	 * Tiempo de atencion de la orden por parte del mesero
	 */
	private double atentionTime;
	//------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 * @param idOrder Identificador de la orden
	 * @param idTable Identificador de la mesa
	 * @param persistence Peristencia de listas de números pseudoaleatorios
	 */
	public Order(int idOrder,int idTable, Persistence persistence, DaoProduct daoProduct,int quantityOfDiners,double atentionTime) {
		this.idOrder = idOrder;
		this.idTable = idTable;
		this.persistence = persistence;
		this.clients = new Client[quantityOfDiners];
		this.daoProduct = daoProduct;
		this.atentionTime=atentionTime;
	}
	//--------------------------------Methods----------------------------
	
	public ArrayList<Product> filterProductByType(TypePlate typePlate){
	//TODO  FILTRAR POR TIPO DE PLATO
		return null;
	}
	
	/**
	 * Método que determina que calificación es la respectiva según un número pseudo-aleatorio
	 * @param numberPseudoaleatory Número pseudo-aleatorio base
	 * @return Calificaciún
	 */
	public int determinateQualificationPseudoAleatory(double numberPseudoaleatory) {
		/*Devuelve la parte entera del número a evaluar como resultado*/
		if(numberPseudoaleatory >= 0 && numberPseudoaleatory < 1) {
			return 0;
		} else if(numberPseudoaleatory >= 1 && numberPseudoaleatory < 2) {
			return 1;
		} else if(numberPseudoaleatory >= 2 && numberPseudoaleatory < 3) {
			return 2;
		}else if(numberPseudoaleatory >= 3 && numberPseudoaleatory < 4) {
			return 3;
		} else if(numberPseudoaleatory >= 4 && numberPseudoaleatory < 5) {
			return 4;
		} else {
			return 5;
		}
	}
	/**
	 * Método que da el plazo de tiempo en que el mesero espera para tomar el pedido (3.5 min)
	 * Se maneja la siguiente escala 
	 * 30 milisegunods = 3 minutos
	 * 40 milisegunods = 4 minutos
	 * 50 milisegunods = 5 minutos
	 * @param 
	 */
	private void waitOfWaiter(int i) {
		try {
			/*Espera del mesero en la mesa*/
			Thread.sleep(i * 10);
		} catch (InterruptedException e) {
			System.out.println("Error en la espera del mesero " + idWaiter + " en la mesa: " + idTable);
		}
	}
	/**
	 * Hilo
	 */
	@Override
	public void run() {
		/* Definimos un número entre 3-5 para la espera del mesero */
		//int aux = (int) ((Math.random() * 3 - 5) + 1) + 5;
		int aux=(int) this.atentionTime;
		this.idWaiter = Integer.parseInt(Thread.currentThread().getName().substring(14));
		/* Simulo la espera del mesero */
		waitOfWaiter(aux);
		for (int i = 0; i < productsList.length; i++) {
			/*  Preguntamos si es el primer comensal ya que este es el unico que puede pedir los 4 platos* /
			/* El caso en que sea el primer comensal entonces */
			if (i == 0) {
				/* Definimos un número para la lista de productos */
				aux = (int) (Math.random() * 999);
				/*Obtenemos el producto a agregar, el primero es el unico que puede escoger entre los 4 platos y los agregamos al pedido*/
				productsList[i] = daoProduct.searchProductById(
						daoProduct.determinateIdProductPseudoAleatory(persistence.getProductList().get(aux)));;
			/* Si no es el primer comensal entonces */
			} else {
				/* Definimos un numero de los 3 platos restantes */
				aux = (int) (Math.random() * 3);
				/* Obtenemos la lista de platos resultantes */
				ArrayList<Product> auxList = daoProduct.obtainAuxiliarProducts(productsList[i - 1]);
				/* Asignamos uno de los platos resultantes al pedido */
				productsList[i] = auxList.get(aux);
			}
			if(productsList[i] == null) {
				System.out.println("se pierde");
			}
			/* Definimos un número para la lista de calificaciones */
			aux = (int) (Math.random() * 999);
			/*Calificamos el producto*/
			daoProduct.qualifyProduct(productsList[i], determinateQualificationPseudoAleatory(persistence.getQualificationList().get(aux)));
		}
	}
	
	public boolean isEndPrepared() {
		for (Client client : clients) {
			if (!client.isEndPrepared()) {
				return false; 
			}
		}
		return true;
	}
	
	public boolean isEndEat() {
		for (Client client : clients) {
			if (!client.isEndEat()) {
				return false; 
			}
		}
		return true;
	}
	
	public Consumption getNextProductToPrepared() {
		for (Client client : clients) {
			if (!client.isEndPrepared()) {
				return client.getConsumpti; 
			}
		}
		return ;
	}
	
	//--------------------------------Getters----------------------------
	public int getIdOrder() {
		return idOrder;
	}
	public int getIdTable() {
		return idTable;
	}
	public int getIdWaiter() {
		return idWaiter;
	}
	public Client[] getClients() {
		return clients;
	}
	public double getAtentioTime() {
		return atentionTime;
	}
	//--------------------------------Setters----------------------------
	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}
}