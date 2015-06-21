package ninefoo.view.project.dialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ninefoo.helper.LayoutHelper;
import ninefoo.lib.form.FormDialog;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Project;
import ninefoo.view.project.listener.TabularDataListener;

/**
 * Pop up dialog showing the dependency form.
 * @author Amir El Bawab
 */
public class ActivityDependencyDialog extends JDialog{

	private static final long serialVersionUID = 8742892025528089478L;

	// Define components
	private JButton linkButton;
	private JComboBox<Integer> dependsOnBox;
	private int activityId;
	
	/**
	 * Constructor
	 */
	public ActivityDependencyDialog(JPanel parentPanel, final int activityId, final TabularDataListener tabularDataListener, Project project, final int row) {
		
		// Set title
		this.setTitle("Define dependency");
		
		// Set layout
		this.setLayout(new BorderLayout());
		
		// Set activities
		Integer[] activitiesObj = new Integer[project.getAcitivies().size()-1];
		int realIndex = 0;
		for(int i=0; i < project.getAcitivies().size(); i++){
			
			// If different id
			if(project.getAcitivies().get(i).getActivityId() != activityId)
				activitiesObj[realIndex++] = project.getAcitivies().get(i).getActivityId();
		}
		// Set activity id
		this.activityId = activityId;
		
		// Initialize components
		this.dependsOnBox = new JComboBox<>(activitiesObj);
		this.linkButton = new JButton("Link");
		
		// Create components
		JPanel buttonContainer = new JPanel();
		DependencyPanel dependencyPanel	= new DependencyPanel();
		
		// Add button listener
		this.linkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Notify constructor
				tabularDataListener.dependencyLink(ActivityDependencyDialog.this, activityId, Integer.parseInt(dependsOnBox.getSelectedItem().toString()), row);
			}
		});
		
		// Add components to panels
		buttonContainer.add(linkButton);
		
		// Add components
		this.add(dependencyPanel, BorderLayout.CENTER);
		this.add(buttonContainer, BorderLayout.SOUTH);
		
		// Configure dialog
		this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		this.setSize(new Dimension(270,170));
		this.setLocationRelativeTo(parentPanel);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	/**
	 * Private class for the dependency form
	 * @see FormDialog
	 */
	private class DependencyPanel extends FormDialog{

		private static final long serialVersionUID = -8662105739137494017L;

		/**
		 * Constructor
		 */
		public DependencyPanel() {
			
			// Set border title
			this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));
			
			// Add components
			int row = 0;
			LayoutHelper.gcGrid(gc, row, 0, 1);
			this.fixedPanel.add(new JLabel(String.format("Activity '%s' depends on ", activityId)), gc);
			
			LayoutHelper.gcGrid(gc, row, 1, 1);
			this.fixedPanel.add(dependsOnBox, gc);
			
			// Add panel
			this.add(fixedPanel);
			this.setPreferredSize(new Dimension(300, 100));
		}
	}
}
