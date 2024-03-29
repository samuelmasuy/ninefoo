package ninefoo.lib.layout.dialog;

import javax.swing.*;
import java.awt.*;

/**
 * This class must be inherited by dialog boxes that are composed of two parts:
 * - For in the center
 * - Buttons at the bottom
 * <p/>
 * +-----------------------+
 * |                       |
 * |                       |
 * |                       |
 * |         FORM          |
 * |                       |
 * |                       |
 * |                       |
 * |-----------------------|
 * |        BUTTONS        |
 * +-----------------------+
 *
 * @author Amir El Bawab
 */
public abstract class CenterFormSouthButtonDialog extends JDialog {

    private static final long serialVersionUID = 6124363441606459436L;

    protected JPanel southPanel;
    protected FormDialog centerPanel;

    /**
     * Constructor
     */
    public CenterFormSouthButtonDialog() {

        // Set layout
        this.setLayout(new BorderLayout());

        // Initialize components
        this.southPanel = new JPanel();

        // Configure south panel
        this.southPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        // Add components to dialog
        this.add(southPanel, BorderLayout.SOUTH);

        // Disable clicking on background windows
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }

    /**
     * Set center panel
     *
     * @param centerPanel
     */
    public void setCenterPanel(FormDialog centerPanel) {
        this.centerPanel = centerPanel;
        this.centerPanel.placeForm();
        this.add(this.centerPanel, BorderLayout.CENTER);
    }

    /**
     * Get center panel
     *
     * @return center panel
     */
    public FormDialog getCenterPanel() {
        return this.centerPanel;
    }

    /**
     * Set error message
     *
     * @param msg
     */
    public void setErrorMessage(String msg) {
        this.centerPanel.setErrorMessage(msg);
    }

    /**
     * Set success message
     *
     * @param msg
     */
    public void setSuccessMessage(String msg) {
        this.centerPanel.setSuccessMessage(msg);
    }
}
