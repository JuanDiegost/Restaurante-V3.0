package model.entity;
/**
 * Esta clase contiene las constantes del modelo de la palciación
 * @author Andres Torres y Lina Melo
 *
 */
public class GlobalConstant {
	/**
	 * Horas minimas a simular
	 */
	public static final int HOURS_TO_SIMULATE = 150;
	/**
	 * Numero de mesas en el restaurante
	 */
	public static final int NUMBER_RESTAURANT_TABLE = 20;
	/**
	 * Numero minimo de netradas que se pueden ordenar
	 */
	public static final int MIN_NUM_ENTRACE=0;

	/**
	 * Numero minimo de postres que se pueden ordenar
	 */
	public static final int MIN_NUM_DESSERT=0;

	/**
	 * Numero minimo de postres que se pueden ordenar
	 */
	public static final int MIN_NUM_MAIN_COURSE=1;
	
	/**
	 * Numero maximo de netradas que se pueden ordenar
	 */
	public static final int MAX_NUM_ENTRACE=2;
	
	/**
	 * Numero maximo de postres que se pueden ordenar
	 */
	public static final int MAX_NUM_DESSERT=2;


	/**
	 * Numero maximo de postres que se pueden ordenar
	 */
	public static final int MAX_NUM_MAIN_COURSE=1;
	
	/**
	 * Velocidad en que se ejecuta los hilos del sistema 
	 */
	public static int SPEED_SYSTEM=100;
	
	
	/**
	 * Timpo que tarda el mesero para menecer una calificaion menos de 10
	 */
	public static int TIME_CALIFICATION_LESS_10=2000;
	
	/**
	 * Timpo que tarda el mesero para menecer una calificaion menos de 20
	 */
	public static int TIME_CALIFICATION_LESS_20=1000;
	
	/**
	 * Timpo que tarda el mesero para menecer una calificaion menos de 30
	 */
	public static int TIME_CALIFICATION_LESS_30=500;
	
	/**
	 * Timpo que tarda el mesero para menecer una calificaion menos de 40
	 */
	public static int TIME_CALIFICATION_LESS_40=200;
	
}
