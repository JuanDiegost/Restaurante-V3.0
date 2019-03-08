package model.dao;

import java.util.ArrayList;

import model.entity.Product;
import model.entity.TypePlate;
import utils.Utils;
/**
 * Clase encargada de la gestion de productos
 * @author Andres Torres y Lina Melo
 */
public class DaoProduct {
	//-------------------------------Attributes---------------------------
	/**
	 * Lista de prodcutos, plato fuerte
	 */
	private static ArrayList<Product> productList;
	
	/**
	 * Lista de prodcutos, Entradas
	 */
	private static ArrayList<Product> producEntraceList;
	
	/**
	 * Lista de prodcutos, Postres
	 */
	private static ArrayList<Product> producDessertList;
	//------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 */
	public DaoProduct() {
		this.productList = new ArrayList<Product>();
		this.producDessertList=new ArrayList<>();
		this.producEntraceList=new ArrayList<>();
		loadDefaultProduct();
	}
	//--------------------------------Methods---------------------------
	/**
	 * Carga los productos iniciales
	 */
	private void loadDefaultProduct() {
		this.productList.add(new Product(1, "Bandeja Paisa",18000,600,1200,TypePlate.PLATO_FUERTE));
		this.productList.add(new Product(2, "Cuchuco de Trigo con Espinazo", 12000,600,1200,TypePlate.PLATO_FUERTE));
		this.productList.add(new Product(3, "Paella a la Valenciana", 20000,600,1200,TypePlate.PLATO_FUERTE));
		this.productList.add(new Product(4, "Arroz con Pollo", 17000,600,1200,TypePlate.PLATO_FUERTE));
	}
	/**
	 * M�todo que busca un producto mediante su ID
	 * @param id Id del producto a buscar
	 * @return producto correspondiente
	 */
	public Product searchProductById(int id) {
		/*Creamos un producto auxiliar*/
		Product product = null;
		/*Recorre la lista de productos*/
		for (int i = 0; i < this.productList.size(); i++) {
			/*Compara los Id*/
			if(this.productList.get(i).getId() == id) {
				/*Si es ese id asigna ese producto al auxuliar*/
				product = this.productList.get(i);
				/*Terminamos el for*/
				i = this.productList.size();
			}
		}
		/*Retornamos el producto hallado*/
		return product;
	}
	/**
	 * Método que obtiene la lista de productos descartando 1
	 */
	public ArrayList<Product> obtainAuxiliarProducts(Product product){
		/*Realizamos una copia de la lista original de productos*/
		@SuppressWarnings("unchecked")
		ArrayList<Product> auxList = (ArrayList<Product>) this.productList.clone();
		/*A la lista auxiliar le eliminamos un producto en especifico*/
		auxList.remove(product);
		/*Retornamos esa lista*/
		return auxList;
	}
	/**
	 * Asigna una calificación a un producto
	 * @param product Producto a calificar
	 * @param qualification Calificación otorgada
	 */
	public void qualifyProduct(Product product, int qualification) {
		/*Recorremos la lista de productos*/
		for (int i = 0; i < this.productList.size(); i++) {
			/*Comparamos los ID*/
			if(product.getId() == this.productList.get(i).getId()) {
				/*Si es ese producto le agrega la cailifación*/
				this.productList.get(i).addQualification(qualification);
				/*Terminamos el for*/
				i = this.productList.size();
			}
		}
	}
	/**
	 * Método que determino el id del plato dependiendo de un n�mero pseudoaleatorio
	 * @param numberPseudoaleatory
	 * @return Id del plato correspondiente
	 */
	public int determinateIdProductPseudoAleatory(double numberPseudoaleatory) {
		if(numberPseudoaleatory >= 0 && numberPseudoaleatory < 1) {
			return 1;
		} else if(numberPseudoaleatory >= 1 && numberPseudoaleatory < 2) {
			return 2;
		} else if(numberPseudoaleatory >= 2 && numberPseudoaleatory < 3) {
			return 3;
		} else {
			return 4;
		}
	}
	
	/**
	 * M�todo que busca el producto que m�s utilidades dejo
	 * @return
	 */
	public Product searchProductWithBestUtilities(ArrayList<Product> list) {
		Product aux = list.get(0);
		for (int i = 1; i < list.size(); i++) {
			if((list.get(i).getQuantityQualifications() * list.get(i).getPrice())*0.25 
					> (aux.getQuantityQualifications() * aux.getPrice())*0.25) {
				aux = list.get(i);
			}
		}
		return aux;
	}
	
	/**
	 * Retorna una entrada aleatoria
	 * @return un producto tio plato fuerte
	 */
	public static Product getRandomEntrece() {
		return producEntraceList.get(Utils.generateRandom(0, producEntraceList.size()-2));
	}
	
	/**
	 * Retorna un plato fuerte aleatorio
	 * @return un producto tipo plato fuerte
	 */
	public static Product getRandomMain() {
		return productList.get(Utils.generateRandom(0, productList.size()-2));
	}
	
	/**
	 * Retorna un postre aleatorio
	 * @return unproducto tipo postre
	 */
	public static Product getRandomDessert() {
		return producDessertList.get(Utils.generateRandom(0, producDessertList.size()-2));
	}
	
	//---------------------------------Getters----------------------------
	public static ArrayList<Product> getProductList() {
		return productList;
	}
	
	public static ArrayList<Product> getProducDessertList() {
		return producDessertList;
	}
	
	public static ArrayList<Product> getProducEntraceList() {
		return producEntraceList;
	}
	

}