package model.entity;
/**
 * Esta clase es la encargada de la estructura de un producto
 *@author Andres Torres y Lina Melo
 *
 */
public class Product {
	//-------------------------------Attributes---------------------------
	/**
	 * Identificador del prodcuto
	 */
	private int idProduct;
	/**
	 * Nombre del producto
	 */
	private String name;
	/**
	 * Precio del productor
	 */
	private int price;
	/**
	 * Calificaci�n total
	 */
	private int totalQualification;
	/**
	 * Cantidad de veces calificado
	 */
	private int quantityQualifications;
	
	/**
	 * Tiempo de preparacion tecnico
	 */
	private int preparationTime;
	
	/**
	 * Timepo de comer el producto 
	 */
	private int timeToEat;
	
	/**
	 * Tipo de plato de este producto (plato fuerte, entrada, postre)
	 */
	private TypePlate typePlate;
	
	//------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 * @param id: Identificador 
	 * @param name: Nombre
	 * @param price: Precio
	 */
	public Product(int id, String name, int price,int preparationTime,int timeToEat,TypePlate typePlate) {
		this.idProduct = id;
		this.name = name;
		this.price = price;
		this.preparationTime=preparationTime;
		this.timeToEat=timeToEat;
		this.typePlate=typePlate;
		this.quantityQualifications = 0;
		this.totalQualification = 0;
	}
	//--------------------------------Methods----------------------------
	/**
	 * M�todo que acumula las calificaiones obtenidas
	 * @param qualification
	 */
	public void addQualification(int qualification) {
		/*Incrementamos en 1 la cantidad de veces que el producto es calificado*/
		this.quantityQualifications++;
		/*Agregamos la calificación al total*/
		this.totalQualification += qualification;
	}
	/**
	 * M�todo que obtiene la calificaci�n promedio del plato
	 * @return Calificaci�n promedio
	 */
	public double calculateAveragueQualification() {
		/*Dividimos la calificación total obtenida entre la cantidad de veces que fue pedido el producto*/
		return totalQualification / quantityQualifications;
	}
	//--------------------------------Getters----------------------------
	public int getId() {
		return idProduct;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getTotalQualification() {
		return totalQualification;
	}
	public int getQuantityQualifications() {
		return quantityQualifications;
	}
	public int getPreparationTime() {
		return preparationTime;
	}
	public int getTimeToEat() {
		return timeToEat;
	}
	public TypePlate getTypePlate() {
		return typePlate;
	}
	
}