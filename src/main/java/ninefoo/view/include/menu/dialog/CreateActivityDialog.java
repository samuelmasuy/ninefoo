package ninefoo.view.include.menu.dialog;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ninefoo.lib.datePicker.DatePicker;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.view.include.menu.listener.ToolsListener;

public class CreateActivityDialog extends CenterFormSouthButtonDialog {
	
	// Define components
	private JButton createButton;
	private JTextField activityLabel;
	private JTextArea description;
	private JTextField duration;
	private JTextField optimisticDuration;
	private JTextField likelyDuration;
	private JTextField pessimisticDuration;
	private DatePicker startDate;
	private DatePicker finishDate;
	private String[] member_data = new String [] {};
	private String[] prerequisite_data = new String [] {};
	
	/** 
	 *  Constructor
	 */
	public CreateActivityDialog(JFrame parentFrame, final ToolsListener toolsListener) {
		
		// Initialize components
		this.createButton = new JButton(LanguageText.getConstant("CREATE"));
		
		this.setTitle(LanguageText.getConstant(""));
		
		// Add button listener
		this.createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolsListener != null)
					toolsListener.createActivity();
			}
		});
		
		// Add center form
		this.setCenterPanel(new FormDialog() {
			
			@Override
			public void placeForm() {
				// Set border title
				this.titledBorder.setTitle(String.format(LanguageText.getConstant("")));
				
				// Set input border
			
				// Add components
				this.table.put(new JLabel(LanguageText.getConstant("")));
				
				this.table.newRow();
				this.table.put(new JLabel(LanguageText.getConstant("")));
			}
		});
		
		// Add component to south panel
		this.southPanel.add(createButton);
		
		// Configure dialog
		this.setSize(new Dimension(300,350));
		this.setLocationRelativeTo(parentFrame);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	
	
}
