package ninefoo.view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import ninefoo.helper.StringHelper;
import ninefoo.model.Activity;
import ninefoo.model.Project;
import ninefoo.view.project.listener.TabularDataListener;

public class TabularData_view extends JPanel {
	
	private static final long serialVersionUID = 6595729954886810500L;

	// Constants
	private final Object[] dataTableHeader = {"", "Activity ID", "Activity Name", "Start", "Finish", "Duration", "Activity % Complete", "Total Float"};
	private final int COUNTER_INDEX = 0;
	private final int ID_INDEX = COUNTER_INDEX + 1;
	private final int ACTIVITY_NAME_INDEX = ID_INDEX + 1;
	private final int START_DATE_INDEX = ACTIVITY_NAME_INDEX + 1;
	private final int FINISH_DATE_INDEX = START_DATE_INDEX + 1;
	private final int DURATION_INDEX = FINISH_DATE_INDEX + 1;
	private final int COMPLETION_INDEX = DURATION_INDEX + 1;
	private final int FLOAT_INDEX = COMPLETION_INDEX + 1;
	private int counter = 0;
	private Project project;
	
	// Static variables
	public static final String PRE_CREATED_ID = "  ?";
	
	// Declare components
	private JTable dataTable;
	private DefaultTableModel dataTableModel;
	private JScrollPane dataTableScrollPane;
	
	// Define listener
	private TableModelListener tableModelListener;
	private TabularDataListener tabularDataListener;
	
	// Constructor
	public TabularData_view() {
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Initialize listener
		tableModelListener = new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				
				// Pass it
				if(tabularDataListener != null){
					
					int row = e.getFirstRow();
					String actId = StringHelper.stringOrEmpty(dataTable.getValueAt(row, ID_INDEX));
					String actName = StringHelper.stringOrEmpty(dataTable.getValueAt(row, ACTIVITY_NAME_INDEX));
					String actStart = StringHelper.stringOrEmpty(dataTable.getValueAt(row, START_DATE_INDEX));
					String actFinish = StringHelper.stringOrEmpty(dataTable.getValueAt(row, FINISH_DATE_INDEX));
					String actCompleted = StringHelper.stringOrEmpty(dataTable.getValueAt(row, COMPLETION_INDEX));
					String actDurtation = StringHelper.stringOrEmpty(dataTable.getValueAt(row, DURATION_INDEX));
					tabularDataListener.tableUpdated(row, project, actId, actName, actStart, actFinish, actDurtation, actCompleted);
				}
			}
		};
		
		// Initialize data
		this.dataTableModel = new DefaultTableModel(null, dataTableHeader){
			
			private static final long serialVersionUID = -1038079031800784743L;

			@Override
			public boolean isCellEditable(int row, int column) {
				
				if(column == ID_INDEX || column == FLOAT_INDEX || column == COUNTER_INDEX) 	
					return false;
				return true;
			}
		};
		this.dataTable = new JTable(dataTableModel){
			private static final long serialVersionUID = 7608473620850578557L;
			public boolean getScrollableTracksViewportWidth(){
                return getPreferredSize().width < getParent().getWidth();
            }
		};
		this.dataTableScrollPane = new JScrollPane(dataTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Add listener
		addListenerToDataModel();
		
		// Customize the Table
		this.dataTable.getTableHeader().setReorderingAllowed(false); // Disable column drag
		this.dataTable.getColumnModel().getColumn(COUNTER_INDEX).setPreferredWidth(30);
		this.dataTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		// Customize the scroll table
		this.dataTableScrollPane.setPreferredSize(new Dimension(300, 0));
		
		// Add components
		this.add(dataTableScrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Set tabular listener
	 * @param tabularDataListener
	 */
	public void setTabularDataListener(TabularDataListener tabularDataListener){
		this.tabularDataListener = tabularDataListener;
	}
	
	/**
	 * Set project
	 * @param project
	 */
	public void setProject(Project project){
		this.project = project;
	}
	
	/**
	 * Update activity at a specific row
	 * @param row
	 * @param activity
	 */
	public void updateRow(int row, Activity activity){
		
		// Remove listener to avoid recursive call
		this.dataTableModel.removeTableModelListener(this.tableModelListener);
		
		// Populate
		String id = StringHelper.stringOrEmpty(activity.getActivityId());
		id = id.isEmpty() ? PRE_CREATED_ID : id;
		this.dataTableModel.setValueAt(id, row, ID_INDEX);
		this.dataTableModel.setValueAt(StringHelper.stringOrEmpty(activity.getActivityLabel()), row, ACTIVITY_NAME_INDEX);
		this.dataTableModel.setValueAt(StringHelper.stringOrEmpty(activity.getStartDate()), row, START_DATE_INDEX);
		this.dataTableModel.setValueAt(StringHelper.stringOrEmpty(activity.getFinishDate()), row, FINISH_DATE_INDEX);
		this.dataTableModel.setValueAt(StringHelper.stringOrEmpty(activity.getDuration()), row, DURATION_INDEX);
		
		// TODO Add activity completion
//		this.dataTableModel.setValueAt(  , row, COMPLETION_INDEX);
		
		// Re-add listener
		addListenerToDataModel();
	}
	
	/**
	 * Add empty row
	 */
	public void addEmptyRow(){
		dataTableModel.addRow(new Object[]{++counter, PRE_CREATED_ID, "", "", "", "", "", ""});
		dataTable.changeSelection(dataTableModel.getRowCount()-1, 0, false, false);
	}
	
	/**
	 * Add listener to data model
	 */
	public void addListenerToDataModel(){
		
		// Add listener to table model
		this.dataTable.getModel().addTableModelListener(this.tableModelListener);
	}
}
