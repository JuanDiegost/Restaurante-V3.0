package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.entity.Product;
/**
 * 
 * @author Andres Torres, Lina Melo, Juan Diego Molina
 *
 */
public class PanelTableProductStadistics extends JPanel{
	//-------------------------------Attributes---------------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 6008374415820834777L;
	/**
	 * Identificadores de columnas
	 */
	private static final String[] TITTLE_COLUMNS = {
			"Nombre",
			"N° Pedidos",
			"Prom Calific", 
			"Total ventas"};
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
    private String title;
    
    //------------------------------Constructor--------------------------
    public PanelTableProductStadistics(String title) {
    	this.title=title;
    	this.setBounds(549, 38, GlobalConstant.WIDHT_PANEL_TABLE, GlobalConstant.HEIGHT_PANEL_TABLE);
    	this.setLayout(null);
		loadTable();
		loadScroll();
    }
    //--------------------------------Methods----------------------------
    /**
     * Carga el scroll de la tabla
     */
    private void loadScroll() {
    	scrollTableNumbers = new JScrollPane(tableNumbers);
    	scrollTableNumbers.setBorder(new LineBorder(Color.ORANGE));
    	scrollTableNumbers.setSize(new Dimension( GlobalConstant.WIDHT_PANEL_TABLE, GlobalConstant.HEIGHT_PANEL_TABLE));
    	scrollTableNumbers.getViewport().setBackground(Color.WHITE);
    	scrollTableNumbers.setBorder(BorderFactory.createTitledBorder(null, "Todas las estadisticas de la "+title, SwingConstants.CENTER, TitledBorder.TOP, new Font("Bookman Old Style", 0, 13)));
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
        tableNumbers.getTableHeader().setReorderingAllowed(false) ;
        tableNumbers.getColumnModel().getColumn(0).setPreferredWidth(30);
        tableNumbers.getColumnModel().getColumn(1).setPreferredWidth(10);
        tableNumbers.getColumnModel().getColumn(2).setPreferredWidth(10);
        tableNumbers.getColumnModel().getColumn(3).setPreferredWidth(10);
        /*tableNumbers.getColumnModel().getColumn(4).setPreferredWidth(80);
        tableNumbers.getColumnModel().getColumn(5).setPreferredWidth(80);
        tableNumbers.getColumnModel().getColumn(6).setPreferredWidth(100);
        tableNumbers.getColumnModel().getColumn(7).setPreferredWidth(100);*/
        tableNumbers.setRowHeight(30);
        tableNumbers.getTableHeader().setFont(new Font("Bookman Old Style", 1, 12));
        tableNumbers.getTableHeader().setBackground(new Color(251,255,168));
        tableNumbers.getTableHeader().setForeground(Color.BLACK);
        tableNumbers.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(255,204,59)));
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
     * @param list: Lista de productos
     */
    public void revalidateTableWithSpecificItems(ArrayList<Product> list) {
        model.getDataVector().removeAllElements();
        for (int i = 0; i < list.size(); i++) {
            model.addRow(new Object[]{
            		list.get(i).getName(),
            		list.get(i).getQuantityQualifications(),
            		list.get(i).calculateAveragueQualification(),
            		list.get(i).getQuantityQualifications()*list.get(i).getPrice()});
        }
        this.revalidate();
        this.repaint();
    }
}