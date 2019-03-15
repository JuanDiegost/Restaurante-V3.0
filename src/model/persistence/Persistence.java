package model.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Clase encargada de la persistencia de los números ya validados para todas las
 * variables
 * 
 * @author Andres Torres y Lina Melo
 *
 */
public class Persistence {
	// ------------------------------Attributes---------------------------
	/**
	 * Lista de horas de trabajo
	 */
	private ArrayList<Double> hoursList;
	/**
	 * Lista de cantidad de comensales;
	 */
	private ArrayList<Double> dinersList;
	/**
	 * Lista de calificaciones
	 */
	private ArrayList<Double> qualificationList;
	/**
	 * Lista de productos
	 */
	private ArrayList<Double> productList;
	/**
	 * Lista de producto condicionados por primera orden
	 */
	private ArrayList<Double> auxProductList;
	/**
	 * Lista de producto condicionados por primera orden
	 */
	private ArrayList<Double> restaurantTablesList;
	/**
	 * Lista de tiempos de atecnion
	 */
	private ArrayList<Double> attentioTimeList;

	/**
	 * Lista de los tiempos de cosumo
	 */
	private ArrayList<Double> consumeTime;

	private static Persistence INSTANCE;

	// ------------------------------Constructor--------------------------
	private Persistence() {
	}

	public static Persistence getINSTANCE() {
		if (INSTANCE == null) {
			INSTANCE = new Persistence(1);
		}
		return INSTANCE;
	}

	private Persistence(int n) {
		this.hoursList = new ArrayList<Double>();
		this.dinersList = new ArrayList<Double>();
		this.qualificationList = new ArrayList<Double>();
		this.productList = new ArrayList<Double>();
		this.auxProductList = new ArrayList<Double>();
		this.restaurantTablesList = new ArrayList<Double>();
		this.attentioTimeList = new ArrayList<Double>();
		this.consumeTime = new ArrayList<Double>();
		loadListToMemory();
	}

	// --------------------------------Methods----------------------------
	/**
	 * Carga a memoria todos las listas con los números pseudoaleatorios ya
	 * validados
	 */
	private void loadListToMemory() {
		try {
			readCSV(hoursList, "./res/NumerosValidados/NumerosHoras.csv");
			readCSV(dinersList, "./res/NumerosValidados/NumerosComensales.csv");
			readCSV(qualificationList, "./res/NumerosValidados/NumerosCalificaciones.csv");
			readCSV(productList, "./res/NumerosValidados/NumerosPlatosInicial.csv");
			readCSV(auxProductList, "./res/NumerosValidados/NumerosPlatoCondicionadosPorPrimeraOrden.csv");
			readCSV(restaurantTablesList, "./res/NumerosValidados/NumerosHoras.csv");
			readCSV(attentioTimeList, "./res/NumerosValidados/NumerosHoras.csv");
			readCSV(consumeTime, "./res/NumerosValidados/NumerosHoras.csv");

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error cargando los archivos de persistencia", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Lee un CSV que no contiene el mismo caracter que el separador en su texto y
	 * sin comillas que delimiten los campos
	 * 
	 * @param path
	 *            Ruta donde está el archivo
	 * @throws IOException
	 */
	public void readCSV(ArrayList<Double> numbers, String path) throws IOException {
		// Abro el .csv en buffer de lectura
		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		// Leo una línea del archivo
		String linea = bufferLectura.readLine();
		while (linea != null) {
			String lineWithReplace = linea.replaceAll(",", ".");
			// Separa la línea leída con el separador definido previamente
			String[] campos = lineWithReplace.split(String.valueOf(";"));
			for (int i = 0; i < campos.length; i++) {
				numbers.add(Double.valueOf(campos[i]));
			}
			linea = bufferLectura.readLine();
		}
		// CIerro el buffer de lectura
		if (bufferLectura != null) {
			bufferLectura.close();
		}
	}

	// --------------------------------Getters----------------------------
	public ArrayList<Double> getHoursList() {
		return hoursList;
	}

	public ArrayList<Double> getDinersList() {
		return dinersList;
	}

	public ArrayList<Double> getQualificationList() {
		return qualificationList;
	}

	public ArrayList<Double> getProductList() {
		return productList;
	}

	public ArrayList<Double> getAuxProductList() {
		return auxProductList;
	}

	public ArrayList<Double> getRestaurantTablesList() {
		return restaurantTablesList;
	}

	public ArrayList<Double> getAttentioTimeList() {
		return attentioTimeList;
	}

	public ArrayList<Double> getConsumeTime() {
		return consumeTime;
	}
}