package ninefoo.lib.excelTable;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * JTable with number appended to the left for each row.
 * Also this class contains default configuration and allows cells to be editable
 * @author Amir El Bawab
 */
public class NumberedExcelTable extends JTable {

	private static final long serialVersionUID = -3795282371155403958L;

	private int counter;
	private DefaultTableModel model;
	private boolean editable = false;
	
	/**
	 * Constructor
	 * @param header
	 */
	public NumberedExcelTable(Object[] header){
		this();
		this.setHeader(header);
	}
	
	/**
	 * Constructor
	 */
	public NumberedExcelTable() {
		
		// Initialize
		this.counter = 0;
		this.model = new DefaultTableModel(){
			
			private static final long serialVersionUID = 8101398071135360727L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return editable;
			}
		};
		
		// Configure table
		this.setModel(this.model);
		getTableHeader().setReorderingAllowed(false); // Disable column drag
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // One select at a time
	}
	
	/**
	 * Scroll to the newly added row
	 */
	public boolean getScrollableTracksViewportWidth(){
        return getPreferredSize().width < getParent().getWidth();
    }
	
	/**
	 * Get the table in a JScrollPane
	 * @return table inside a scroll pane
	 */
	public JScrollPane getJScrollPane(){
		return new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
	
	/**
	 * Set cells editable
	 * @param editable
	 */
	public void setEditable(boolean editable){
		this.editable = editable;
	}
	
	/**
	 * Set the header titles
	 * @param header
	 */
	public void setHeader(Object ... header){
		
		// Add the row number at the beginning
		Object[] newHeader = new Object[header.length + 1];
		System.arraycopy(new Object[]{""}, 0, newHeader, 0, 1);
		System.arraycopy(header, 0, newHeader, 1, header.length);
		this.model.setColumnIdentifiers(newHeader);
		
		// Resize the number column
		this.getColumnModel().getColumn(0).setPreferredWidth(30);
		
		// Center of the cell
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		this.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		
	}
	
	/**
	 * Add one row of data
	 * @param data
	 */
	public void addRow(Object ... data){
		
		// Add the row number at the beginning
		Object[] newData = new Object[data.length + 1];
		System.arraycopy(new Object[]{String.valueOf(++counter)}, 0, newData, 0, 1);
		System.arraycopy(data, 0, newData, 1, data.length);
		this.model.addRow(newData);
	}
	
	/**
	 * Empty the table
	 */
	public void empty(){
		this.counter = 0;
		this.removeAll();
	}
}
