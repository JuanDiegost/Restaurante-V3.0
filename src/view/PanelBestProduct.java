package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 *Esta clase contiene el panel para el mejor producto en la parte de la vista
 * @author Andres Torres y Lina Melo
 *
 */
public class PanelBestProduct extends JPanel {
	//------------------------------Attributes---------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6534297126890209144L;
	/**
	 * Textfield con el nombre del mejor producto
	 */
	private JTextField textFieldNameBestProduct;
	/**
	 * Textfield con la utilidad del mejor producto
	 */
	private JTextField textFieldUtilityBestProduct;
	//------------------------------Constructor--------------------------
	/**
	 * Constructor por defecto
	 */
	public PanelBestProduct() {
		initialize();
	}
	//--------------------------------Methods----------------------------
	/**
	 * Carga los componentes del panel
	 */
	private void initialize() {
		this.setBounds(553, 10, 423, 76);
		this.setLayout(null);
		this.setBackground(new Color(249,249,249));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("./res/img/stadistics_opt.png"));
		label.setBounds(10, 6, 70, 67);
		this.add(label);
		
		JLabel lblProductoDestacado = new JLabel("Producto destacado");
		lblProductoDestacado.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductoDestacado.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblProductoDestacado.setBounds(138, 5, 160, 23);
		this.add(lblProductoDestacado);
		
		JLabel lblBestProduct = new JLabel("Nombre:");
		lblBestProduct.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBestProduct.setBounds(92, 38, 65, 24);
		this.add(lblBestProduct);
		
		textFieldNameBestProduct = new JTextField();
		textFieldNameBestProduct.setEditable(false);
		textFieldNameBestProduct.setBounds(145, 38, 98, 24);
		textFieldNameBestProduct.setFont(new Font("Tahoma", Font.PLAIN, 9));
		this.add(textFieldNameBestProduct);
		textFieldNameBestProduct.setColumns(10);
		
		JLabel lblUtility = new JLabel("Utilidad total:");
		lblUtility.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUtility.setBounds(250, 38, 75, 24);
		this.add(lblUtility);
		
		textFieldUtilityBestProduct = new JTextField();
		textFieldUtilityBestProduct.setEditable(false);
		textFieldUtilityBestProduct.setBounds(318, 38, 75, 24);
		this.add(textFieldUtilityBestProduct);
		textFieldUtilityBestProduct.setColumns(10);
	}
	//--------------------------------Getters----------------------------
	//--------------------------------Setters----------------------------
	public void setTextFieldUtility(double textFieldUtility) {
		this.textFieldUtilityBestProduct.setText(String.valueOf(textFieldUtility));
	}
	public void setTextFieldNameBestProduct(String textFieldNameBestProduct) {
		this.textFieldNameBestProduct.setText(textFieldNameBestProduct);
	}
}