package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class PanelTableCashier extends Panel {

	// -------------------------------Attributes---------------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6008374415820834777L;
	/**
	 * Identificadores de columnas
	 */
	private static final String[] TITTLE_COLUMNS = { "Efectivo", "Tarjeta", "Dividido", "Americano", "Una persona" };
	/**
	 * Tabla historial de bacterias
	 */
	private JTable tableNumbers;
	/**
	 * Modelo de la tabla
	 */
	private DefaultTableModel model;
	/**
	 * Scrool de la tabla
	 */
	private JScrollPane scrollTableNumbers;

	// ------------------------------Constructor--------------------------
	public PanelTableCashier() {
		this.setBounds(549, 38, GlobalConstant.WIDHT_PANEL_TABLE, GlobalConstant.HEIGHT_PANEL_TABLE);
		this.setLayout(null);
		loadTable();
		loadScroll();
	}

	// --------------------------------Methods----------------------------
	/**
	 * Carga el scroll de la tabla
	 */
	private void loadScroll() {
		scrollTableNumbers = new JScrollPane(tableNumbers);
		scrollTableNumbers.setBorder(new LineBorder(Color.ORANGE));
		scrollTableNumbers.setSize(new Dimension(GlobalConstant.WIDHT_PANEL_TABLE, 150));
		scrollTableNumbers.getViewport().setBackground(Color.WHITE);
		scrollTableNumbers.setBorder(BorderFactory.createTitledBorder(null, "Tipos de pagos",
				SwingConstants.CENTER, TitledBorder.TOP, new Font("Bookman Old Style", 0, 13)));
		add(scrollTableNumbers);
	}

	/**
	 * Inicializa todos los componentes de la tabla
	 */
	private void loadTable() {
		loadModel();
		model.setColumnIdentifiers(TITTLE_COLUMNS);
		tableNumbers = new JTable();
		tableNumbers.setModel(model);
		tableNumbers.getTableHeader().setReorderingAllowed(false);
		tableNumbers.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableNumbers.getColumnModel().getColumn(1).setPreferredWidth(50);
		tableNumbers.getColumnModel().getColumn(2).setPreferredWidth(50);

		tableNumbers.setRowHeight(30);
		tableNumbers.getTableHeader().setFont(new Font("Bookman Old Style", 1, 12));
		tableNumbers.getTableHeader().setBackground(new Color(251, 255, 168));
		tableNumbers.getTableHeader().setForeground(Color.BLACK);
		tableNumbers.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(255, 204, 59)));
		tableNumbers.setOpaque(false);
		for (int i = 0; i < TITTLE_COLUMNS.length; i++) {
			tableNumbers.getColumnModel().getColumn(i).setCellRenderer(new TableCallRender());
		}
	}

	/**
	 * Cargar el model de la tabla
	 */
	private void loadModel() {
		model = new DefaultTableModel() {
			/**
			 * Serial
			 */
			private static final long serialVersionUID = -2474742434873502448L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	/**
	 * Metodo que limpia la tabla
	 */
	public void cleanTable() {
		for (int i = 0; i < tableNumbers.getRowCount(); i++) {
			model.removeRow(i);
			i -= 1;
		}
	}

	/**
	 * Llena la tabla con los respectivos datos de los productos
	 * 
	 * @param list:
	 *            Lista de productos
	 */
	public void revalidateTableWithSpecificItems(int[] list) {
		model.getDataVector().removeAllElements();
		model.addRow(new Object[] { list[0], list[1], list[2],list[3],list[4]});

		this.revalidate();
	}
}
