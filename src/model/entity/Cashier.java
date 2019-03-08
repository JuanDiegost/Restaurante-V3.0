package model.entity;

public class Cashier {

	/**
	 * Identificador del cajero
	 */
	private int id;
	
	/**
	 * Tiempo que tarda en registrar un pago en efectivo
	 */
	private int timePaymetInCash;
	
	/**
	 * Tiempo que tarda en registrar un pago con targeta de credito
	 */
	private int timePaymetCreditCard;
	
	/**
	 * Tiempo que tarda en desplazarse de la caja a cualquier mesa del restaurante
	 */
	private int timetoFlushAtRestaurantTable;
	
	/**
	 * Total pagado en efectivo
	 */
	private double totalPaidCash;
	
	/**
	 * Numero de veces que se paga en efectivo
	 */
	private int numberPaymentCash;
	
	/**
	 * Total Pagado con targeta de credito
	 */
	private double totalPaidCreditCard;
	/**
	 * Numero de veces que se paga con targeta de credito
	 */
	private int numberPaymentCreditCard;
	
	/**
	 * Numero de veces que se paga con modalidad de pago unico
	 */
	private int numberPaymentSingle;
	
	/**
	 * Numero de veces que se paga con modalidad de pago americano
	 */
	private int numberPaymentAmerican;
	
	
	/**
	 * Numero de veces que se paga con modalidad de pago dividido
	 */
	private int numberPaymentDivided;


	//-------------------CONSTRUCTOR---------------------------------------
	public Cashier(int id, int timePaymetInCash, int timePaymetCreditCard, int timetoFlushAtRestaurantTable) {
		super();
		this.id = id;
		this.timePaymetInCash = timePaymetInCash;
		this.timePaymetCreditCard = timePaymetCreditCard;
		this.timetoFlushAtRestaurantTable = timetoFlushAtRestaurantTable;
		this.totalPaidCash=0;
		this.totalPaidCreditCard=0;
		this.numberPaymentCash=0;
		this.numberPaymentCreditCard=0;
		this.numberPaymentAmerican=0;
		this.numberPaymentSingle=0;
		this.numberPaymentDivided=0;
	}

	//---------------Methods--------------------------------------
	
	/**
	 * Agrega un nuevo pago con targeta de credito
	 * @param payment cantidad a pagar con la targeta de credito
	 */
	public void addPaymentCreditCard(double payment) {
		this.totalPaidCreditCard+=payment;
		this.numberPaymentCreditCard++;
	}
	
	/**
	 * Agrega un nuevo pago con targeta en efectivo
	 * @param payment cantidad a pagar en efectivo
	 */
	public void addPaymentCash(double payment) {
		this.totalPaidCash+=payment;
		this.numberPaymentCash++;
	}
	
	/**
	 * Agrega un tipo de pago dividido
	 */
	public void addPaymetDivided() {
		this.numberPaymentDivided++;
	}
	
	/**
	 * Agrega un tipo de pago americano
	 */
	public void addPaymetAmerican() {
		this.numberPaymentAmerican++;
	}
	
	/**
	 * Agrega un tipo de pago unico
	 */
	public void addPaymetSingle() {
		this.numberPaymentSingle++;
	}

	//----------------Getters---------------------------------------------
	public int getId() {
		return id;
	}


	public int getTimePaymetInCash() {
		return timePaymetInCash;
	}


	public int getTimePaymetCreditCard() {
		return timePaymetCreditCard;
	}


	public int getTimetoFlushAtRestaurantTable() {
		return timetoFlushAtRestaurantTable;
	}
	public int getNumberPaymentAmerican() {
		return numberPaymentAmerican;
	}
	public int getNumberPaymentCreditCard() {
		return numberPaymentCreditCard;
	}
	public int getNumberPaymentCash() {
		return numberPaymentCash;
	}
	public int getNumberPaymentDivided() {
		return numberPaymentDivided;
	}
	public int getNumberPaymentSingle() {
		return numberPaymentSingle;
	}
	public double getTotalPaidCash() {
		return totalPaidCash;
	}
	public double getTotalPaidCreditCard() {
		return totalPaidCreditCard;
	}
	
	
	
}