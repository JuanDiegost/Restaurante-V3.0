package view;

import java.awt.Panel;


public class PanelStage extends Panel {
	// ------------------------------Attributes---------------------------
	/**
	 * Serial
	 */
	private static final long serialVersionUID = -6553539283470740308L;

	/**
	 * Panel de estadisticas
	 */
	private PanelTableProductStadistics panelTable;

	/**
	 * Panel de estadisticas
	 */
	private PanelTableWaiterStadistics panelTableWaiter;

	private PanelTableCashier panelTableCashier;
	
	private String title;
	
	/**
	 * Panel del mejor producto
	 */

	// --------------------------------Methods----------------------------
	/**
	 * Initialize the contents of the frame.
	 */
	public PanelStage(String title) {
		this.title=title;

		this.setBounds(20, 20, 1200, 700);
    	this.setLayout(null);
		loadPanels();
	}

	/**
	 * Carga los paneles al frame principal
	 * 
	 * @param actionListener
	 *            escucha de los botones
	 */
	private void loadPanels() {

		this.panelTable = new PanelTableProductStadistics(title);
		panelTable.setLocation(0, 10);
		add(panelTable);

		this.panelTableWaiter = new PanelTableWaiterStadistics(title);
		panelTableWaiter.setLocation(600, 10);
		add(panelTableWaiter);

		this.panelTableCashier = new PanelTableCashier(title);
		panelTableCashier.setLocation(600, 210);
		add(panelTableCashier);
		
		this.setVisible(true);
	}
	// --------------------------------Getters----------------------------
	public PanelTableProductStadistics getPanelTable() {
		return panelTable;
	}

	public PanelTableWaiterStadistics getPanelTableWaiter() {
		return panelTableWaiter;
	}

	public PanelTableCashier getPanelTableCashier() {
		return panelTableCashier;
	}
}