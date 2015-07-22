package ninefoo.view.include.footer;

import ninefoo.lib.component.PMLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Footer status bar.
 *
 * @author Amir El Bawab
 */
public class StatusBar extends JPanel {

    private static final long serialVersionUID = -5534459903549181858L;

    // Define components
    private PMLabel status;

    // Constructor
    public StatusBar() {

        // Set layout
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // Initialize components
        this.status = new PMLabel("SAMPLE_TEXT", true);

        // Add components
        this.add(this.status);

        // Configure JPanel
        this.setPreferredSize(new Dimension(0, 30)); // Size of the JPanel (Width is automatically set)
        this.setVisible(false);
    }
}
