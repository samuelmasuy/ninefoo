package ninefoo.view.include.menu.dialog;

import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.datePicker.DatePicker;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.view.include.menu.listener.ToolsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog that shows a form for creating a new project
 *
 * @author Amir El Bawab
 */
public class CreateProjectDialog extends CenterFormSouthButtonDialog {

    private static final long serialVersionUID = 216394661255136241L;

    // Create components
    private PMButton createButton;
    private JTextField name, budget;
    private JTextArea description;
    private DatePicker deadline, start;

    /**
     * Constructor
     */
    public CreateProjectDialog(JFrame parentFrame, final ToolsListener toolsListener) {

        // Initialize components
        this.createButton = new PMButton("CREATE_PROJECT");
        this.name = new JTextField(10);
        this.budget = new JTextField(10);
        this.deadline = new DatePicker(8);
        this.start = new DatePicker(8);
        this.description = new JTextArea(3, 10);

        // Set title
        this.setTitle(LanguageText.getConstant("CREATE_NEW_PROJECT"));

        // Set center panel
        this.setCenterPanel(new FormDialog() {
            private static final long serialVersionUID = -2943549344497607196L;

            @Override
            public void placeForm() {

                // Set border title
                this.titledBorder.setTitle(LanguageText.getConstant("PROJECT"));

                // Set input border
                name.setBorder(BorderFactory.createCompoundBorder(name.getBorder(), inputPadding));
                budget.setBorder(BorderFactory.createCompoundBorder(budget.getBorder(), inputPadding));
                deadline.setBorder(BorderFactory.createCompoundBorder(deadline.getBorder(), inputPadding));
                description.setBorder(BorderFactory.createCompoundBorder(description.getBorder(), inputPadding));
                start.setBorder(BorderFactory.createCompoundBorder(start.getBorder(), inputPadding));

                // Add components
                this.table.put(new PMLabel("NAME"));
                this.table.put(name);

                this.table.newRow();
                this.table.put(new PMLabel("BUDGET"));
                this.table.put(budget);

                this.table.newRow();
                this.table.put(new PMLabel("START_DATE"));
                this.table.put(start);

                this.table.newRow();
                this.table.put(new PMLabel("DEADLINE"));
                this.table.put(deadline);

                this.table.newRow();
                this.table.put(new PMLabel("DESCRIPTION"));
                this.table.put(new JScrollPane(description));
            }
        });

        // Add button listener
        this.createButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    toolsListener.newProject(CreateProjectDialog.this, name.getText(), budget.getText(), start.getText(), deadline.getText(), description.getText());
            }
        });

        // Add components to panels
        this.southPanel.add(createButton);

        // Configure dialog
        this.setSize(new Dimension(300, 350));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
    }
}