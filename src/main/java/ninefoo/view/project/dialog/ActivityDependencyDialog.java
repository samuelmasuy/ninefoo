package ninefoo.view.project.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Project;
import ninefoo.view.project.table.listener.TabularDataListener;

/**
 * Pop up dialog showing the dependency form.
 * @author Amir El Bawab
 */
public class ActivityDependencyDialog extends CenterFormSouthButtonDialog{

	private static final long serialVersionUID = 8742892025528089478L;

	// Define components
	private JButton linkButton;
	private JComboBox<Integer> dependsOnBox;
	
	/**
	 * Constructor
	 */
	public ActivityDependencyDialog(JPanel parentPanel, final int activityId, final TabularDataListener tabularDataListener, Project project, final int row) {
		
		// Set title
		this.setTitle(LanguageText.getConstant("NEW_DEPENDENCY_ACT"));
		
		// Set activities
		Integer[] activitiesObj = new Integer[project.getAcitivies().size()-1];
		int realIndex = 0;
		for(int i=0; i < project.getAcitivies().size(); i++){
			
			// If different id
			if(project.getAcitivies().get(i).getActivityId() != activityId)
				activitiesObj[realIndex++] = project.getAcitivies().get(i).getActivityId();
		}
		
		// Initialize components
		this.dependsOnBox = new JComboBox<>(activitiesObj);
		this.linkButton = new JButton("Link");
		
		// Create components
		this.setCenterPanel(new FormDialog() {
			
			private static final long serialVersionUID = 2887864663030389211L;

			@Override
			public void placeForm() {
				
				// Set border title
				this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));
				
				// Add components
				this.table.put(new JLabel(String.format(LanguageText.getConstant("ACTIVITY_DEPENDS_ACT"), activityId)));
				this.table.put(dependsOnBox);
				
				// Set size
				this.setPreferredSize(new Dimension(300, 100));
			}
		});
		
		// Add button listener
		this.linkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Notify constructor
				tabularDataListener.dependencyLink(ActivityDependencyDialog.this, activityId, Integer.parseInt(dependsOnBox.getSelectedItem().toString()), row);
			}
		});
		
		// Add components to panels
		this.southPanel.add(linkButton);
		
		// Configure dialog
		this.setSize(new Dimension(270,170));
		this.setLocationRelativeTo(parentPanel);
		this.setResizable(false);
		this.setVisible(true);
	}
}
