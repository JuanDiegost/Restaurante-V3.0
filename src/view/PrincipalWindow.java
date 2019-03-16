package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	 * Panel de estadisticas face 1
	 */
	private PanelStage stage1;
	
	/**
	 * Panel de estadisticas face 2
	 */
	private PanelStage stage2;
	
	/**
	 * Panel de estadisticas face 3
	 */
	private PanelStage stage3;
	
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
		ScrollPane pane=new ScrollPane();
		pane.setBounds(90, 90, 1200, 500);
		
		JPanel jPanel=new JPanel(new GridLayout(1, 3));
		pane.add(jPanel);
		
		this.stage1 = new PanelStage("Ventana 1");
		stage1.setLocation(0, 800);
		jPanel.add(stage1);
		
		this.stage2 = new PanelStage("Ventana 2");
		//stage2.setLocation(800, 800);
		jPanel.add(stage2);
		
		this.stage3 = new PanelStage("Ventana 3");
		//stage3.setLocation(1600, 800);
		jPanel.add(stage3);
		
		getContentPane().add(pane);

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
	
	public PanelStage getStage1() {
		return stage1;
	}
	
	public PanelStage getStage2() {
		return stage2;
	}
	
	public PanelStage getStage3() {
		return stage3;
	}
}