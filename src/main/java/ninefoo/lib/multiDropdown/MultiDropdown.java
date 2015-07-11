package ninefoo.lib.multiDropdown;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ninefoo.lib.autocompleteComboBox.AutocompleteComboBox;

/**
 * Panel that can have mutliple dropdown fields. You can add more fields by pressing on the button
 * that appears on the top of the panel.
 * +--------------------------+
 * | [+ add]                  |
 * |                          |
 * | +----------------------+ |
 * | | [              ][x]  | |
 * | | [              ][x]  | |
 * | | [              ][x]  | |
 * | |                      | |
 * | +----------------------+ |
 * +--------------------------+
 * @author Amir El Bawab
 */
public class MultiDropdown extends JPanel{
	
	private static final long serialVersionUID = 1560308657758435285L;
	
	// Declare components
	private JButton addButton;
	private JPanel northPanel, centerPanel;
	private JScrollPane centerPanelScroll;
	private ArrayList<AutocompleteAndRemoveWrapper>  listBoxRemoveWrapper;
	
	public MultiDropdown(String buttonText, final String[] data) {
		
		// Set layout
		this.setLayout(new BorderLayout());
					
		// Initialize
		this.addButton = new JButton(buttonText, new ImageIcon(getClass().getResource("/images/buttons/act_add_dependency.png")));
		this.centerPanel = new JPanel();
		this.northPanel = new JPanel();
		this.centerPanelScroll = new JScrollPane(this.centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.listBoxRemoveWrapper = new ArrayList<>();
		
		// Set layout
		this.centerPanel.setLayout(new BoxLayout(this.centerPanel, BoxLayout.Y_AXIS));
		this.northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		// Configure component
		this.centerPanelScroll.setBorder(null);
		this.addButton.setMargin(new Insets(0, 0, 0, 5));
		
		// Add action listener
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Create panel
				AutocompleteAndRemoveWrapper wrapper = new AutocompleteAndRemoveWrapper(data);
				
				// Add it to the list of boxes
				listBoxRemoveWrapper.add(wrapper);
				
				// Add the panel and configure it
				centerPanel.add(wrapper);
				MultiDropdown.this.repaint();
				MultiDropdown.this.revalidate();
				
			}
		});
		
		// Add component to north panel
		this.northPanel.add(addButton);
		
		// Add component
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanelScroll, BorderLayout.CENTER);
	}
	
	/**
	 * Get only the correct data
	 * @return array of the valid data
	 */
	public String[] getData(){
		ArrayList<String> data = new ArrayList<>();
		for(int i = 0; i < listBoxRemoveWrapper.size(); i++)
			if(listBoxRemoveWrapper.get(i).getText() != null)
				data.add(listBoxRemoveWrapper.get(i).getText());
		
		return data.toArray(new String[data.size()]);
	}
	
	/**
	 * Panel that holds the auto complete combo box with the remove button
	 * +----------------------------+
	 * | [                  ] [ X ] |
	 * +----------------------------+
	 * @author Amir El Bawab
	 */
	private class AutocompleteAndRemoveWrapper extends JPanel {

		private static final long serialVersionUID = 6905854705840310003L;
		
		// Declare components
		private AutocompleteComboBox boxField;
		private JButton removeButton;
		
		public AutocompleteAndRemoveWrapper(String[] data) {
			
			// Initialize components
			this.boxField = new AutocompleteComboBox(data);
			this.removeButton = new JButton(new ImageIcon(getClass().getResource("/images/buttons/act_remove_dependency.png")));
			
			// Configure button
			this.removeButton.setBorderPainted(false); 
			this.removeButton.setContentAreaFilled(false); 
			this.removeButton.setFocusPainted(false); 
			this.removeButton.setOpaque(false);
			this.removeButton.setPreferredSize(new Dimension(25,25));
			
			// Set max size
			Dimension dim = new Dimension(200,40);
			this.setMaximumSize(dim);
			
			// Add action listener to remove
			this.removeButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton clickedButton = (JButton) e.getSource();
					
					// Remove element from list and panel
					for(int i=0; i < listBoxRemoveWrapper.size(); i++){
						if(clickedButton == listBoxRemoveWrapper.get(i).removeButton){
							centerPanel.remove(listBoxRemoveWrapper.get(i));
							listBoxRemoveWrapper.remove(i);
							MultiDropdown.this.repaint();
							MultiDropdown.this.revalidate();
							break;
						}
					}
				}
			});
			
			// Left align
			this.setAlignmentX(Component.LEFT_ALIGNMENT);
			
			// Add components
			this.add(boxField);
			this.add(removeButton);
		}
		
		/**
		 * Get text of the field
		 * @return string if the input exists in the list, <code>NULL</code> if the input is not in the list
		 */
		public String getText(){
			return boxField.checkAndGetText();
		}
	}
}
