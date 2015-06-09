package ninefoo.view.project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;
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
import ninefoo.view.project.dialog.ActivityDependencyDialog;
import ninefoo.view.project.listener.TabularDataListener;

public class TabularData_view extends JPanel {
	
	private static final long serialVersionUID = 6595729954886810500L;

	// Constants
	private final Object[] dataTableHeader = {"", "Activity ID", "Activity Name", "Start", "Finish", "Duration", "Activity % Complete", "Total Float", "Dependency"};
	private final int COUNTER_INDEX = 0;
	private final int ID_INDEX = COUNTER_INDEX + 1;
	private final int ACTIVITY_NAME_INDEX = ID_INDEX + 1;
	private final int START_DATE_INDEX = ACTIVITY_NAME_INDEX + 1;
	private final int FINISH_DATE_INDEX = START_DATE_INDEX + 1;
	private final int DURATION_INDEX = FINISH_DATE_INDEX + 1;
	private final int COMPLETION_INDEX = DURATION_INDEX + 1;
	private final int FLOAT_INDEX = COMPLETION_INDEX + 1;
	private final int DEPENDENCY_INDEX = FLOAT_INDEX + 1;
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
	public TabularData_view(final JPanel parentPanel) {
		
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
				
				if(column == ID_INDEX || column == FLOAT_INDEX || column == COUNTER_INDEX || column == DEPENDENCY_INDEX) 	
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
		
		// Add model listener
		addListenerToDataModel();
		
		// Add table listener
		this.dataTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// On double click
				if (e.getClickCount() == 2) {
			      JTable target = (JTable)e.getSource();
			      int row = target.getSelectedRow();
			      int column = target.getSelectedColumn();
			      
			      // If dependency column
			      if(column == DEPENDENCY_INDEX){
			    	  
			    	  // If row have an id
			    	  if(!dataTableModel.getValueAt(row, ID_INDEX).toString().equals(PRE_CREATED_ID)){
			    		
			    		  // Count the real activities
			    		  int i;
			    		  for(i=0; i < dataTableModel.getRowCount(); i++)
			    			  if(i != row && !dataTableModel.getValueAt(i, ID_INDEX).equals(PRE_CREATED_ID))
			    				  break;
			    		  
			    		  // If no real activities found
			    		  if(i == dataTableModel.getRowCount()){
	
			    			  // Display error
			    			  JOptionPane.showMessageDialog(TabularData_view.this, "Please add more activities to be able to add dependecies", "Operation failed", JOptionPane.ERROR_MESSAGE);
			    		  
			    		  // If at least 2 real activities found
			    		  }else{

				    		  // If more than one row
				    		  new ActivityDependencyDialog(parentPanel, Integer.parseInt(dataTableModel.getValueAt(row, ID_INDEX).toString()), tabularDataListener, project, row); 
			    		  }
			    		  
			    	  }
			      }
			    }
				
			}
		});
		
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
		this.dataTableModel.setValueAt(StringHelper.stringOrEmpty(activity.getPrerequisitesAsString()), row, DEPENDENCY_INDEX);
		
		// TODO Add activity completion
//		this.dataTableModel.setValueAt(  , row, COMPLETION_INDEX);
		
		// Re-add listener
		addListenerToDataModel();
	}
	
	/**
	 * Add empty row
	 */
	public void addEmptyRow(){
		Object[] object = new Object[dataTableHeader.length];
	
		// Set everything to empty
		for(int i = 0; i < object.length; i++)
			object[i] = "";
		
		// Special cases	
		object[COUNTER_INDEX] = ++counter;
		object[ID_INDEX] = PRE_CREATED_ID;
		
		// Add row
		dataTableModel.addRow(object);
		
		// Auto scroll down
		dataTable.changeSelection(dataTableModel.getRowCount()-1, 0, false, false);
	}
	
	/**
	 * Add listener to data model
	 */
	public void addListenerToDataModel(){
		
		// Add listener to table model
		this.dataTable.getModel().addTableModelListener(this.tableModelListener);
	}
	
	/**
	 * Populate activities
	 */
	public void populateActivities(){
		
		// Remove listener to avoid recursive call
		this.dataTableModel.removeTableModelListener(this.tableModelListener);
		
		List<Activity> activities = this.project.getAcitivies();
		for(Activity activity : activities){
			Object[] object = new Object[dataTableHeader.length];
			object[COUNTER_INDEX] = ++counter;
			object[ID_INDEX] = activity.getActivityId();
			object[ACTIVITY_NAME_INDEX] = activity.getActivityLabel();
			object[START_DATE_INDEX] = activity.getStartDate();
			object[FINISH_DATE_INDEX] = activity.getFinishDate();
			object[DURATION_INDEX] = activity.getDuration();
			this.dataTableModel.addRow(object);
		}
		
		// Re-add listener
		addListenerToDataModel();
	}
	
	/**
	 * Reset table
	 */
	public void resetTable(){
		
		// Remove listener to avoid recursive call
		this.dataTableModel.removeTableModelListener(this.tableModelListener);
				
		// Delete row by row
		while(this.dataTableModel.getRowCount() > 0){
			System.out.println(this.dataTableModel.getRowCount());
			this.dataTableModel.removeRow(0);
		}
		
		// Reset counter
		this.counter = 0;
		
		// Re-add listener
		addListenerToDataModel();
	}
}
