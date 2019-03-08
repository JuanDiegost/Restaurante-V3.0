package model.error;
/**
 * Esta clase es la encargada de manejar el error en caso que
 * no se encuentre una mesa
 * @author Andres Torres y Lina Melo
 *
 */
public class ErrorTableNotFound extends Exception{
	//------------------------------Attributes---------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -3123958985914296634L;
	/**
	 * Mensaje de error
	 */
	public static final String ERROR_TABLE_NOT_FOUND = "Mesa no encontrada";
	//------------------------------Constructor--------------------------
	public ErrorTableNotFound() {
		super(ERROR_TABLE_NOT_FOUND);
	}
}
