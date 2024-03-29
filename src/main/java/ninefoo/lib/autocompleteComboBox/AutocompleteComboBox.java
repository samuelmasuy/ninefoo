package ninefoo.lib.autocompleteComboBox;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Auto complete version of combo box. Use it exactly as you use the combo box
 *
 * @author Amir El Bawab
 */
public class AutocompleteComboBox extends JComboBox<String> {

    private static final long serialVersionUID = 3133571804379824103L;

    public AutocompleteComboBox() {
        this(null);
    }

    /**
     * Constructor
     */
    public AutocompleteComboBox(String[] text) {

        // Add new items
        if (text != null)
            for (String str : text)
                this.addItem(str);

        // Set editable
        setEditable(true);

        // change the editor's document
        ((JTextComponent) getEditor().getEditorComponent()).setDocument(new AutocompleteContainer(this));

        // Set size
        setPreferredSize(new Dimension(150, 30));
    }

    /**
     * Get text component
     *
     * @return text component
     */
    public JTextComponent getTextComponent() {
        return (JTextComponent) getEditor().getEditorComponent();
    }

    /**
     * Checks that the data entered exist in the list and returns it
     *
     * @return string if the input exists in the list, <code>NULL</code> if the input is not in the list
     */
    public String checkAndGetText() {
        for (int i = 0; i < getModel().getSize(); i++) {
            if (getTextComponent().getText().equals(getModel().getElementAt(i)))
                return getTextComponent().getText();
        }
        return null;
    }

    /**
     * Checks that the data entered exist in the list and returns its index
     *
     * @return index or -1 if it doesn't exist
     */
    public int checkAndGetIndex() {
        for (int i = 0; i < getModel().getSize(); i++) {
            if (getTextComponent().getText().equals(getModel().getElementAt(i)))
                return i;
        }
        return -1;
    }
}
