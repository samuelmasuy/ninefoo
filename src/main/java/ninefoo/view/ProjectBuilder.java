package ninefoo.view;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ProjectBuilder extends JPanel{

    // Define components
    private JButton newActivity;

    // Constructor
    public ProjectBuilder() {

        // Set layout
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Set border
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,5,5,5), BorderFactory.createTitledBorder("Project Builder")));

        // Initialize components
        this.newActivity = new JButton("New activity");

        // Add components
        this.add(newActivity);

        // Configure JPanel
        this.setPreferredSize(new Dimension(200, 0)); // Size of the JPanel (Height is automatically set)
        this.setVisible(true);
    }
}
