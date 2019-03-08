package view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
/**
 * 
 * @author @author Andres Torres y Lina Melo
 *
 */
public class PanelStart extends JPanel {
	//------------------------------Attributes---------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -3272093002162678998L;
	private JTextField textFieldDayToSimulate;
	private JTextField textFieldDiners;
	private JTextField textFieldHoursToSimulate;
	
	//------------------------------Constructor--------------------------
	public PanelStart(ActionListener listener) {
		initialize(listener);
		this.setBackground(new Color(246,246,246));
	}
	//--------------------------------Methods----------------------------
	private void initialize(ActionListener listener) {
		this.setBounds(10,10, GlobalConstant.WIDHT_PANEL_START, GlobalConstant.HEIGHT_PANEL_START);
		this.setLayout(null);
		
		JButton btnSimular = new JButton("<html><center>Empezar<br>simulación</center></html>\n");
		btnSimular.setForeground(Color.BLACK);
		btnSimular.setFont(new Font("Bookman Old Style", Font.BOLD, 14));
		btnSimular.setBackground(new Color(240,240,240));
		btnSimular.setBounds(5, 8, 100, 60);
		btnSimular.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GREEN, 1),
				BorderFactory.createEtchedBorder(EtchedBorder.RAISED)));
		btnSimular.addActionListener(listener);
		btnSimular.setActionCommand("GO_TO_SIMULATE");
		this.add(btnSimular);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("./res/img/arrow_opt.jpg"));
		label_1.setBounds(120, 5, 90, 66);
		this.add(label_1);
		
		JLabel lblDaysToSimulate = new JLabel("Días a simular");
		lblDaysToSimulate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDaysToSimulate.setBounds(207, 49, 95, 20);
		this.add(lblDaysToSimulate);
		
		textFieldDayToSimulate = new JTextField();
		textFieldDayToSimulate.setBounds(293, 50, 51, 19);
		textFieldDayToSimulate.setEditable(false);
		textFieldDayToSimulate.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(textFieldDayToSimulate);
		textFieldDayToSimulate.setColumns(10);
		
		JLabel lblDiners = new JLabel("Comensales");
		lblDiners.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiners.setBounds(360, 10, 65, 13);
		this.add(lblDiners);
		
		textFieldDiners = new JTextField();
		textFieldDiners.setBounds(435, 7, 51, 19);
		textFieldDiners.setEditable(false);
		textFieldDiners.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(textFieldDiners);
		textFieldDiners.setColumns(10);
		
		JLabel lblHoursToSimulate = new JLabel("Horas a simular");
		lblHoursToSimulate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHoursToSimulate.setBounds(207, 10, 95, 20);
		this.add(lblHoursToSimulate);
		
		textFieldHoursToSimulate = new JTextField();
		textFieldHoursToSimulate.setBounds(293, 7, 51, 19);
		textFieldHoursToSimulate.setEditable(false);
		textFieldHoursToSimulate.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(textFieldHoursToSimulate);
		textFieldHoursToSimulate.setColumns(10);
	}
	//--------------------------------Setters----------------------------
	public void setTextFieldDayToSimulate(int textFieldDayToSimulate) {
		this.textFieldDayToSimulate.setText(String.valueOf(textFieldDayToSimulate));
	}
	public void setTextFieldDiners(int textFieldDiners) {
		this.textFieldDiners.setText(String.valueOf(textFieldDiners));
	}
	public void setTextFieldHoursToSimulate(int textFieldHoursToSimulate) {
		this.textFieldHoursToSimulate.setText(String.valueOf(textFieldHoursToSimulate));
	}
}