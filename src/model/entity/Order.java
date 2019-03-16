package model.entity;

import java.util.ArrayList;
import java.util.List;

import model.dao.Cocina;
import model.dao.DaoProduct;
import model.persistence.Persistence;
import utils.Utils;

/**
 * Clase encagada de la estructura de un pedido
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class Order extends Thread {
	// ------------------------------Attributes---------------------------

	/**
	 * 
	 */
	private static int COUNT_ID = 0;
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
	private List<Client> clients;
	/**
	 * Persitencia de las lista de números pseudoaleatorios ya validados
	 */
	private Persistence persistence;
	/**
	 * Instancia de daoProductos
	 */
	private DaoProduct daoProduct;

	private double baksheesh;

	/**
	 * Modo de pago para esa mesa, americano, dividido, un persona
	 */
	private ModeOfPaymet modeOfPaymet;

	/**
	 * Tiempo de atencion de la orden por parte del mesero
	 */
	private double atentionTime;

	/**
	 * Objeto que representa la caja
	 */
	private Cashier cashier;
	/**
	 * Objeto que representa al mesero
	 */
	private Waiter waiter;

	/**
	 * Cantidad de clientes en la mesa
	 */
	private int quantityOfDiners;

	/**
	 * La mesa en la que se encuentran los clientes
	 */
	private RestaurantTable restaurantTable;

	// ------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 * 
	 * @param idOrder
	 *            Identificador de la orden
	 * @param idTable
	 *            Identificador de la mesa
	 * @param persistence
	 *            Peristencia de listas de números pseudoaleatorios
	 */
	public Order(int idTable, Persistence persistence, DaoProduct daoProduct, int quantityOfDiners, double atentionTime,
			Cashier cashier, Waiter waiter, RestaurantTable restaurantTable) {
		this.idOrder = COUNT_ID;
		COUNT_ID++;
		this.idTable = idTable;
		this.persistence = persistence;
		this.clients = new ArrayList<>();
		this.daoProduct = daoProduct;
		this.atentionTime = atentionTime;
		this.cashier = cashier;
		this.quantityOfDiners = quantityOfDiners;
		this.restaurantTable = restaurantTable;
	}
	// --------------------------------Methods----------------------------

	/**
	 * Método que determina que calificación es la respectiva según un número
	 * pseudo-aleatorio
	 * 
	 * @param numberPseudoaleatory
	 *            Número pseudo-aleatorio base
	 * @return Calificaciún
	 */
	public int determinateQualificationPseudoAleatory(double numberPseudoaleatory) {
		/* Devuelve la parte entera del número a evaluar como resultado */
		if (numberPseudoaleatory >= 0 && numberPseudoaleatory < 1) {
			return 0;
		} else if (numberPseudoaleatory >= 1 && numberPseudoaleatory < 2) {
			return 1;
		} else if (numberPseudoaleatory >= 2 && numberPseudoaleatory < 3) {
			return 2;
		} else if (numberPseudoaleatory >= 3 && numberPseudoaleatory < 4) {
			return 3;
		} else if (numberPseudoaleatory >= 4 && numberPseudoaleatory < 5) {
			return 4;
		} else {
			return 5;
		}
	}

	/**
	 * Método que da el plazo de tiempo en que el mesero espera para tomar el pedido
	 * (3.5 min) Se maneja la siguiente escala 30 milisegunods = 3 minutos 40
	 * milisegunods = 4 minutos 50 milisegunods = 5 minutos
	 * 
	 * @param
	 */
	private void waitOfWaiter(int i,Waiter waiter) {
		try {
			/* Espera del mesero en la mesa */
			waiter.setStateWaiter(StateWaiter.ATTEND_TABLE);
			Thread.sleep(10);
			waiter.setStateWaiter(StateWaiter.SEARCH_TABLES);
		} catch (InterruptedException e) {
			System.out.println("Error en la espera del mesero " + idWaiter + " en la mesa: " + idTable);
		}
	}

	public void attendOrder(Waiter waiter) {
		this.waiter=waiter;
		for (int i = 0; i < this.quantityOfDiners; i++) {
			// Creo el numerode clientes indicado
			// TODO aqui se deve madar un valor de la distribucion del tiempo de consumo al
			// azar
			double timeConsume = persistence.getConsumeTime()
					.get(Utils.generateRandom(0, persistence.getConsumeTime().size() - 1));
			clients.add(new Client((int) 10, daoProduct));

		}
		// Definimos un número entre 3-5 para la espera del mesero
		// int aux = (int) ((Math.random() * 3 - 5) + 1) + 5;
		int aux = (int) this.atentionTime;
		//this.idWaiter = Integer.parseInt(Thread.currentThread().getName().substring(14));
		// Simulo el tiempo de pedido
		waitOfWaiter(aux,waiter);
		waiter.setStateWaiter(StateWaiter.SEARCH_TABLES);
	}

	/**
	 * Hilo
	 */
	@Override
	public void run() {
		// Si no hay clientes en la mesa significa que no han ordenado
		if (clients.isEmpty()) {

		} else {
			// Simulo el tiempo que demora en traer la orden
			eat();
			for (Client client : clients) {
				// calificación mesero
				waiter.addCalification(generateCalificationWaiter());
				// Calificación platos
				client.calificatePlats();
				client.endEat();
			}

			// Evalua si se le da una propina al mesero
			if (Utils.generateRandom(0, 2) == 1) {
				waiter.addTip(getTotalCost() * 0.1);
			}
			this.modeOfPaymet = getModePaymentRandom();
			pay();
			restaurantTable.setOrder(null);
			restaurantTable.setEmpty(true);
			// Quita todos los clientes de la mesa

		}
	}

	/**
	 * Espera el tempio que tardan el maximo tiempo de consumo de losclientes
	 */
	private void eat() {
		int maxTime = 0;
		for (Client client : clients) {
			maxTime = client.getTimeToConsume() > maxTime ? client.getTimeToConsume() : maxTime;
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que llama a la caja para realizar el pago
	 */
	private void pay() {
		if (modeOfPaymet.equals(ModeOfPaymet.AMERICAN)) {
			cashier.addPaymetAmerican();
			paymentAllClientAmerican();
		} else if (modeOfPaymet.equals(ModeOfPaymet.DIVIDED)) {
			cashier.addPaymetDivided();
			paymentAllClientDivided();
		} else {
			cashier.addPaymetSingle();
			paymetOneClient(clients.get(Utils.generateRandom(0, clients.size()-1)), getTotalCost());
		}
	}

	/**
	 * Envia a todas las personas a pagar, en pago dividido
	 */
	private void paymentAllClientDivided() {
		double paymetDivided = getCostDivided();
		for (Client client : clients) {
			paymetOneClient(client, paymetDivided);
		}
	}

	/**
	 * Envia a todas las personas a en pago americanp
	 */
	private void paymentAllClientAmerican() {
		for (Client client : clients) {
			paymetOneClient(client, getTotalCostByClient(client));
		}
	}

	/**
	 * Envia a un cliente a pagar a la caja (targeta o cash)
	 * 
	 * @param client
	 *            el cliente que va a efectuar el pago
	 * @param totalToPay,
	 *            el total que pagara ese cliente
	 */
	private void paymetOneClient(Client client, double totalToPay) {
		int num = Utils.generateRandom(0, 2);
		if (num == 1) {
			cashier.addPaymentCash(client, totalToPay);
		} else {
			cashier.addPaymentCreditCard(client, totalToPay);
		}
	}

	/**
	 * Retorna un modod de pago aleatorio.
	 * 
	 * @return
	 */
	public ModeOfPaymet getModePaymentRandom() {
		int num = Utils.generateRandom(0, 3);
		return num == 0 ? ModeOfPaymet.AMERICAN : num == 1 ? ModeOfPaymet.DIVIDED : ModeOfPaymet.ONE_PERSON;
	}

	/**
	 * Retorna la cuenta total de la mesa
	 * 
	 * @return
	 */
	public double getTotalCost() {
		double total = 0;
		for (Client client : clients) {
			total += getTotalCostByClient(client);
		}
		return total;
	}

	/**
	 * Retorna la cuenta total del cliente
	 * 
	 * @return
	 */
	public double getTotalCostByClient(Client client) {
		double total = 0;
		for (Consumption consumption : client.getConsumptions()) {
			total += consumption.getProduct().getPrice();
		}
		return total;
	}

	/**
	 * Retorna el valor que debe pagar cada cliente
	 * 
	 * @return
	 */
	public double getCostDivided() {
		return getTotalCost() / clients.size();
	}

	/**
	 * Genera una calificacion para el mesero
	 * 
	 * @return
	 */
	private int generateCalificationWaiter() {
		if (atentionTime < GlobalConstant.TIME_CALIFICATION_LESS_40) {
			return (int) (Math.random() * 6) ;
		} else if (atentionTime < GlobalConstant.TIME_CALIFICATION_LESS_30) {
			return (int) (Math.random() * 5) ;
		} else if (atentionTime < GlobalConstant.TIME_CALIFICATION_LESS_20) {
			return (int) (Math.random() * 4);
		} else if (atentionTime < GlobalConstant.TIME_CALIFICATION_LESS_10) {
			return (int) (Math.random() * 3);
		}
		return (int) (Math.random() * 2);
	}

	/**
	 * Retorna true si todos los platos han sido, preparados
	 * 
	 * @return
	 */
	public boolean isEndPrepared() {
		for (Client client : clients) {
			if (!client.isEndPrepared()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Retorna true si todos los platos han sido, preparados
	 * 
	 * @return
	 */
	public boolean isAllOrderPrepated() {
		for (Client client : clients) {
			if (!client.isAllOrderPrepared()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Retorna si todos los clientas an terminado de comer.
	 * 
	 * @return
	 */
	public boolean isEndEat() {
		for (Client client : clients) {
			if (!client.isEndEat()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Optiene la lista de pedidos de todos los clientes de la mesa
	 * 
	 * @return
	 */
	public List<Consumption> getListProductToPrepared() {
		List<Consumption> consumptions = new ArrayList<>();
		for (Client client : clients) {
			consumptions.addAll(client.getConsumptions());
		}
		return consumptions;
	}

	/**
	 * Retorna el tiempo del cliente que más, se demora consumiendo su pedido
	 * 
	 * @return
	 */
	public int maxTimeToConsumeOrder() {
		int max = 0;
		for (Client client : clients) {
			max = client.getTimeToConsume() > max ? client.getTimeToConsume() : max;
		}
		return max;
	}

	// --------------------------------Getters----------------------------
	public int getIdOrder() {
		return idOrder;
	}

	public int getIdTable() {
		return idTable;
	}

	public int getIdWaiter() {
		return idWaiter;
	}

	public List<Client> getClients() {
		return clients;
	}

	public double getAtentioTime() {
		return atentionTime;
	}

	public double getBaksheesh() {
		return baksheesh;
	}

	// --------------------------------Setters----------------------------
	public void setPersistence(Persistence persistence) {
		this.persistence = persistence;
	}
}