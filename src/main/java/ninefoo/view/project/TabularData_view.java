package ninefoo.view.project;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TabularData_view extends JPanel {
	
	private static final long serialVersionUID = 6595729954886810500L;

	// Constants
	private final Object[] dataTableHeader = {"Activity ID", "Activity Name", "Start", "Finish", "Activity % Complete", "Total Float"};
	
	// Declare components
	private JTable dataTable;
	private DefaultTableModel dataTableModel;
	private JScrollPane dataTableScrollPane;
	
	// Constructor
	public TabularData_view() {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Set border
		this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		
		// Initialize data
		this.dataTableModel = new DefaultTableModel(null, dataTableHeader);
		this.dataTable = new JTable(dataTableModel);
		this.dataTableScrollPane = new JScrollPane(dataTable);
		
		// Customize the Table
		dataTable.getTableHeader().setReorderingAllowed(false); // Disable column drag
		
		// Populate data with random data - For test only
		for(int i=0; i < 100; i++)
			dataTableModel.addRow(new Object[]{"EC" + i, "Activity Name " + i, String.format("%d-Sep-10", i%30+1), String.format("%d-Nov-10", i%30+1), "100%", i+1+"d"});
		
		// Add components
		this.add(dataTableScrollPane, BorderLayout.CENTER);
		
		// Configure JPanel
		this.setVisible(false);
	}
}
