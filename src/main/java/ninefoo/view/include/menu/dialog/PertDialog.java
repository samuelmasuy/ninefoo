package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ninefoo.config.Session;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

public class PertDialog extends CenterScrollSouthButtonDialog{
	private PMButton closeButton;
	private Project project;
	
	public PertDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
        this.closeButton = new PMButton("CLOSE");
		toolsListener.loadProjectForPert(this, Session.getInstance().getProjectId());
        setCenterPanel(new FormDialog() {
			
			@Override
			public void placeForm() {
				this.table.put(new JLabel("Activity Label"));
				this.table.put(new JLabel("Precedents"));
				this.table.put(new JLabel("Optimistic"));
				this.table.put(new JLabel("Most likely"));
				this.table.put(new JLabel("Pessimistic"));
				this.table.put(new JLabel("Expected"));
				this.table.put(new JLabel("Standard deviation"));
				
				for(Activity current : project.getAcitivies()){
					this.table.newRow();
					this.table.put(new JLabel(current.getActivityLabel()));
					this.table.put(new JLabel(current.getPrerequisitesAsString()));
					this.table.put(new JLabel(current.getOptimisticDuration()+""));
					this.table.put(new JLabel(current.getLikelyDuration()+""));
					this.table.put(new JLabel(current.getPessimisticDuration() +""));
					this.table.put(new JLabel(String.format("%.2f", current.getExpectedDuration())));
					this.table.put(new JLabel(String.format("%.2f", current.getStandardDeviation())));
				}
			}
		});
        
        this.southPanel.add(closeButton);
        
		// Configure dialog
        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
	}
	
	public void setProject(Project project){
		this.project = project;
	}
}
