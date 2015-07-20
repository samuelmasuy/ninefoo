package ninefoo.lib.component;

import ninefoo.lib.lang.LanguageText;

import javax.swing.*;

/**
 * @author Sebouh Bardakjian
 */
public class PMMenu extends JMenu {

    private String key;

    public PMMenu(String key) {
        super(LanguageText.getConstant(key));
        this.key = key;
    }
}
