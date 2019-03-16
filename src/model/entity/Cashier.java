package model.entity;

import java.util.LinkedList;
import java.util.Queue;

public class Cashier extends Thread{

	/**
	 * Identificador del cajero
	 */
	private int id;
	
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
	
	/**
	 * Cola de clientes paara pagar en efectivo.
	 */
	private Queue<Client> clientsCash;
	
	/**
	 * Cola de clientes paara pagar con targeta
	 */
	private Queue<Client> clientsCreditCard;
	
	//-------------------CONSTRUCTOR---------------------------------------
	public Cashier(int id) {
		super();
		this.id = id;
		this.totalPaidCash=0;
		this.totalPaidCreditCard=0;
		this.numberPaymentCash=0;
		this.numberPaymentCreditCard=0;
		this.numberPaymentAmerican=0;
		this.numberPaymentSingle=0;
		this.numberPaymentDivided=0;
		clientsCash=new LinkedList<>();
		clientsCreditCard=new LinkedList<>();
	}

	//---------------Methods--------------------------------------
	
	@Override
	public void run() {
		super.run();
		while (true) {
			while (!clientsCash.isEmpty()) {
				try {
					Thread.sleep(100);//TODO COLOCAR TIEMPO DE LA DISTRIBUCION QUE TARDA EN PROCESAR UN PAGO EN EFECTIVO
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Client cliente=clientsCash.poll();
				System.out.println("Cliente "+cliente.getIdClient() +" Termina de pagar");
			}
			while (!clientsCreditCard.isEmpty()) {
				try {
					Thread.sleep(100);//TODO COLOCAR TIEMPO DE LA DISTRIBUCION QUE TARDA EN PROCESAR UN PAGO EN TARGETA DE CREDITO
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clientsCreditCard.poll();
			}
		}
	}
	
	/**
	 * Agrega un nuevo pago con targeta de credito
	 * @param payment cantidad a pagar con la targeta de credito
	 */
	public void addPaymentCreditCard(Client client,double payment) {
		System.out.println("Cliente "+client.getIdClient()+" paga $"+payment+" con targeta");
		clientsCreditCard.add(client);
		this.totalPaidCreditCard+=payment;
		this.numberPaymentCreditCard++;
		System.out.println("-------------------"+total()+"-----------------------------------");
	}
	
	/**
	 * Agrega un nuevo pago con targeta en efectivo
	 * @param payment cantidad a pagar en efectivo
	 */
	public void addPaymentCash(Client client,double payment) {
		System.out.println("Cliente "+client.getIdClient()+" paga $"+payment+" en efetico");
		clientsCash.add(client);
		this.totalPaidCash+=payment;
		this.numberPaymentCash++;
		//System.out.println("-------------------"+total()+"-----------------------------------");
	}
	
	/**
	 * Agrega un tipo de pago dividido
	 */
	public void addPaymetDivided() {
		System.out.println("Pago dividido");
		this.numberPaymentDivided++;
	}
	
	/**
	 * Agrega un tipo de pago americano
	 */
	public void addPaymetAmerican() {
		System.out.println("Pago americano");
		this.numberPaymentAmerican++;
	}
	
	/**
	 * Agrega un tipo de pago unico
	 */
	public void addPaymetSingle() {
		System.out.println("Pago uno");
		this.numberPaymentSingle++;
	}
	
	/**
	 * Ingresos en bruto del restaurant
	 * @return 
	 */
	public double total() {
		return this.totalPaidCash+this.totalPaidCreditCard;
	}

	//----------------Getters---------------------------------------------
	public int getIdCachier() {
		return id;
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