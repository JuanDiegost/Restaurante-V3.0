package view;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
/**
 * Esta clase es la encargada de la ventana principal del programa
 * @author Andres Torres y Lina Melo
 *
 */
public class PrincipalWindow extends JFrame{
	//------------------------------Attributes---------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6553539283470740308L;
	
	/**
	 * Panel de estadisticas
	 */
	private PanelTableProductStadistics panelTable;
	/**
	 * Panel del mejor producto
	 */
	private PanelBestProduct panelBestProduct;
	/**
	 * Panel de inicio
	 */
	private PanelStart panelStart;
	//------------------------------Constructor--------------------------
	/**
	 * Create the application.
	 */
	public PrincipalWindow(ActionListener actionListener) {
		/*Crarga los componentes del frame*/
		initialize(actionListener);
	}
	//--------------------------------Methods----------------------------
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ActionListener actionListener) {
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("./res/img/logo1.png"));
		this.setTitle("Restaurante Pollito's");
		this.setBounds(100, 100, 1000, 320);
		this.setBackground(new Color(244,244,244));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		loadPanels(actionListener);
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(542, 10, 1, 76);
		getContentPane().add(separator);
		repaint();
		this.setVisible(true);
	}
	/**
	 * Carga los paneles al frame principal
	 * @param actionListener escucha de los botones
	 */
	private void loadPanels(ActionListener actionListener) {
		this.panelTable = new PanelTableProductStadistics();
		panelTable.setLocation(10, 94);
		getContentPane().add(panelTable);
		
		this.panelStart = new PanelStart(actionListener);
		panelStart.setLocation(10, 10);
		getContentPane().add(panelStart);
		
		this.panelBestProduct = new PanelBestProduct();
		getContentPane().add(panelBestProduct);
	}
	/**
	 * Carga los valores de días y horas a sus respectivos textfield
	 * @param values: Arreglo de 2 valores, [0] días y [1] horas
	 */
	public void setValuesOfDaysHoursAndDiners(float[] values, int diners) {
		this.panelStart.setTextFieldHoursToSimulate((int)values[0]);
		this.panelStart.setTextFieldDayToSimulate((int)values[1]);
		this.panelStart.setTextFieldDiners(diners);
	}
	
	public void setValuesOfBestProduct(String name, double utility) {
		this.panelBestProduct.setTextFieldNameBestProduct(name);
		this.panelBestProduct.setTextFieldUtility(utility);
	}
	//--------------------------------Getters----------------------------
	public PanelTableProductStadistics getPanelTable() {
		return panelTable;
	}
}