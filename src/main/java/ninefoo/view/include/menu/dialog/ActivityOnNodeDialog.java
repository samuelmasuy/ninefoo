package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.config.Session;
import ninefoo.lib.component.PMButton;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterScrollSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.lib.pert.Panel.PertPanel;
import ninefoo.lib.pert.Shape.PertShape;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

public class ActivityOnNodeDialog extends CenterScrollSouthButtonDialog{
	private PMButton closeButton;
	private FormDialog formPanel;
	private Project project;
	private PertPanel activityOnNodePanel;
	
	public ActivityOnNodeDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
        this.closeButton = new PMButton("CLOSE");
		
        this.setTitle("Activity On Node");
        
        // Load project
        toolsListener.loadProject(this, Session.getInstance().getProjectId());
        
        formPanel = new FormDialog() {
			
			@Override
			public void placeForm() {
				activityOnNodePanel = new PertPanel(project.getAcitivies().size());
				this.table.put(activityOnNodePanel);
			}
		};
		
		this.setCenterPanel(formPanel);
		
        this.southPanel.add(closeButton);
        generateGraph();
		// Configure dialog
        this.setSize(new Dimension(800, 500));
        this.setLocationRelativeTo(parentFrame);
        this.setVisible(true);
	}
	
	public void generateGraph(){
		
		// List of shapes
		List<PertShape> shapes = new ArrayList<>(); 
		
		// Add nodes
		for(Activity current : project.getAcitivies()){
			PertShape shape = activityOnNodePanel.addNode();
			shape.id = current.getActivityId();
			shapes.add(shape);
		}
		
		// Add edges
		for(Activity current : project.getAcitivies()){
			PertShape to = getShapeById(shapes, current.getActivityId());
			for(Activity currentPrereq : current.getPrerequisites()){
				PertShape from = getShapeById(shapes, currentPrereq.getActivityId());
				activityOnNodePanel.addEdge(from, to);
			}
		}
		
		activityOnNodePanel.refeshValues();
		activityOnNodePanel.prettify();
	}
	
	private PertShape getShapeById(List<PertShape> shapes, int id){
		for(PertShape shape : shapes)
			if(id == shape.id)
				return shape;
		return null;
	}
	
	public void setProject(Project project){
		this.project = project;
	}
}
