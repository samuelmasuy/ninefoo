package ninefoo.view.include.menu.dialog;

import ninefoo.lib.component.PMButton;
import ninefoo.lib.component.PMLabel;
import ninefoo.lib.datePicker.DatePicker;
import ninefoo.lib.lang.LanguageText;
import ninefoo.lib.layout.dialog.CenterFormSouthButtonDialog;
import ninefoo.lib.layout.dialog.FormDialog;
import ninefoo.model.object.Project;
import ninefoo.view.include.menu.listener.ToolsListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog for editing a project.
 *
 * @author Amir El Bawab
 */
public class EditProjectDialog extends CenterFormSouthButtonDialog {

    private static final long serialVersionUID = 216394661255136241L;

    // Create components
    private PMButton saveButton;
    private JTextField name, budget;
    private JTextArea description;
    private DatePicker deadline, start;

    /**
     * Constructor
     */
    public EditProjectDialog(JFrame parentFrame, final ViewMyProjectsDialog parentPanel, final ToolsListener toolsListener, final int projectId) {

        // Initialize components
        this.saveButton = new PMButton("UPDATE_PROJECT");
        this.name = new JTextField(10);
        this.budget = new JTextField(10);
        this.deadline = new DatePicker(8);
        this.start = new DatePicker(8);
        this.description = new JTextArea(3, 10);

        // Set title
        this.setTitle(LanguageText.getConstant("UPDATE_PROJECT"));

        // Load project
        if (toolsListener != null)
            toolsListener.loadEditProjectFields(this, projectId);

        // Add button listener
        this.saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (toolsListener != null)
                    toolsListener.updateProject(parentPanel, EditProjectDialog.this, projectId, name.getText(), budget.getText(), start.getText(), deadline.getText(), description.getText());
            }
        });

        // Set center form
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

        // Add components to panels
        this.southPanel.add(saveButton);

        // Configure dialog
        this.setSize(new Dimension(300, 350));
        this.setLocationRelativeTo(parentFrame);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Populate fields
     *
     * @param project
     */
    public void populateFields(Project project) {
        this.name.setText(project.getProjectName());
        this.budget.setText(project.getBudget() + "");
        this.description.setText(project.getDescription());
        this.start.setDate(project.getStartDate());
        this.deadline.setDate(project.getDeadlineDate());
    }
}
