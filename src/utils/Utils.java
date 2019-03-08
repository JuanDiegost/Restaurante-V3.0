package utils;


public class Utils {

	/**
	 * Genera un numero aleatorio entre dos rangos 
	 * @param of limite inferior  <=
	 * @param to limite superiror >=
	 * @return
	 */
	public static int generateRandom(int of,int to) {
		return (int) (Math.random() * (to+1)) + of;
	}
}