package view;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Andres Torres
 */
public class TableCallRender  extends  DefaultTableCellRenderer{
    /**
	 * Serial
	 */
	private static final long serialVersionUID = -7945278162496170788L;
    
    //----------------------------Methods------------------------------
    /**
     * Modifica el contenido de la celda
     * @param table
     * @param value
     * @param isSelected
     * @param hasFocus
     * @param row
     * @param column
     * @return 
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        this.setBorder(BorderFactory.createLineBorder(new Color(255, 204, 59)));
        comp.setFont(new Font("Bookman Old Style", 0, 10));
        return comp;
    }
}