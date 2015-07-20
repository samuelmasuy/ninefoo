package ninefoo.lib.component;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenuItem extends JMenuItem {

    private String key;

    public PMMenuItem(String key) {
        super(LanguageText.getConstant(key));
        this.key = key;
    }
}
