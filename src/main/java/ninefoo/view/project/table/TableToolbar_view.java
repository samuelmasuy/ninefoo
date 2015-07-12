package ninefoo.view.project.table;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TableToolbar_view extends JPanel {
	private static final long serialVersionUID = -7124432397140089151L;

	// Declare components
	private JButton addActivity, editActivity, deleteActivity, viewActivity;
	
	public TableToolbar_view() {
		// Set layout
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		// Initialize compoentns
		this.viewActivity = new JButton(new ImageIcon(getClass().getResource("/images/buttons/general/eye.png")));
		this.addActivity = new JButton(new ImageIcon(getClass().getResource("/images/buttons/general/add.png")));
		this.editActivity = new JButton(new ImageIcon(getClass().getResource("/images/buttons/general/edit.png")));
		this.deleteActivity = new JButton(new ImageIcon(getClass().getResource("/images/buttons/general/remove.png")));
		
		// Configure buttons
		this.viewActivity.setBorderPainted(false); 
		this.viewActivity.setContentAreaFilled(false); 
		this.viewActivity.setFocusPainted(false); 
		this.viewActivity.setOpaque(false);
		this.viewActivity.setText("View details");
		
		this.addActivity.setBorderPainted(false); 
		this.addActivity.setContentAreaFilled(false); 
		this.addActivity.setFocusPainted(false); 
		this.addActivity.setOpaque(false);
		this.addActivity.setText("Add");
		
		this.editActivity.setBorderPainted(false); 
		this.editActivity.setContentAreaFilled(false); 
		this.editActivity.setFocusPainted(false); 
		this.editActivity.setOpaque(false);
		this.editActivity.setText("Edit");
		
		this.deleteActivity.setBorderPainted(false); 
		this.deleteActivity.setContentAreaFilled(false); 
		this.deleteActivity.setFocusPainted(false); 
		this.deleteActivity.setOpaque(false);
		this.deleteActivity.setText("Remove");
		
		// Add component
		this.add(addActivity);
		this.add(editActivity);
		this.add(deleteActivity);
		this.add(viewActivity);
	}
	
}
