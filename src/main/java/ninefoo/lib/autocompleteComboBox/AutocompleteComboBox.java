package ninefoo.lib.autocompleteComboBox;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;

/**
 * Auto complete version of combo box. Use it exactly as you use the combo box
 * @author Amir El Bawab
 */
public class AutocompleteComboBox extends JComboBox<String>{

	private static final long serialVersionUID = 3133571804379824103L;
	
	public AutocompleteComboBox(){
		this(null);
	}
	
	/**
	 * Constructor
	 */
	public AutocompleteComboBox(String[] text) {
		
		if(text != null)
			for(String str : text)
				this.addItem(str);
		
		// Set editable
		setEditable(true);
		
        // change the editor's document
        ((JTextComponent) getEditor().getEditorComponent()).setDocument(new AutocompleteContainer(this));
		
		// Set size
		setPreferredSize(new Dimension(150, 30));
	}
}
