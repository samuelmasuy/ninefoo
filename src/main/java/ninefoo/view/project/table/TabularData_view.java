package ninefoo.view.project.table;

import ninefoo.config.ActivityConfig;
import ninefoo.helper.ActivityHelper;
import ninefoo.lib.excelTable.NumberedExcelTable;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Table showing data about activities in a project
 *
 * @author Amir El Bawab, Sebouh Bardakjian
 */
public class TabularData_view extends JPanel {

    // Logger
    private static final long serialVersionUID = 6595729954886810500L;

    // Variables
    private Project project;

    // Declare components
    private NumberedExcelTable dataTable;
    private JScrollPane dataTableScrollPane;

    // Constructor
    public TabularData_view(final JPanel parentPanel) {

        // Set layout
        this.setLayout(new BorderLayout());

        // Initialize components
        this.dataTable = new NumberedExcelTable(ActivityConfig.TABLE_HEADER);
        this.dataTableScrollPane = this.dataTable.getJScrollPane();

        // Configure scrolling speed
        this.dataTableScrollPane.getVerticalScrollBar().setUnitIncrement(20);
        this.dataTableScrollPane.getHorizontalScrollBar().setUnitIncrement(20);

        // Customize the scroll table
        this.dataTableScrollPane.setPreferredSize(new Dimension(300, 0));

        // Add components
        this.add(dataTableScrollPane, BorderLayout.CENTER);
    }

    /**
     * Set project
     *
     * @param project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Populate activities
     */
    public void populateTable() {

        // Remove all existing rows
        this.resetTable();

        // If project exists
        if (project != null) {

            // Add rows
            List<Activity> activities = this.project.getAcitivies();
            for (Activity activity : activities)
                this.dataTable.addRow(ActivityHelper.getFilteredRow(activity));
        }
    }

    /**
     * Empty table
     */
    public void resetTable() {
        this.dataTable.empty();
    }

    /**
     * Get the selected row
     *
     * @return activity or <code>NULL</code> if no row is selected
     */
    public Activity getSelectedActivity() {
        if (this.dataTable.getSelectedRow() >= 0)
            return this.project.getAcitivies().get(this.dataTable.getSelectedRow());
        return null;
    }
}
