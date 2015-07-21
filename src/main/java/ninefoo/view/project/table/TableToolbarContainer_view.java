package ninefoo.view.project.table;

import ninefoo.model.object.Activity;
import ninefoo.model.object.Project;
import ninefoo.view.project.table.listener.TableToolsListener;
import ninefoo.view.project.table.listener.ToolbarListener;

import javax.swing.*;
import java.awt.*;

public class TableToolbarContainer_view extends JPanel {
    private static final long serialVersionUID = 7053727563192342007L;

    // Declare Variables
    private TabularData_view tabularDataPanel;
    private TableToolbar_view tableToolbarPanel;

    public TableToolbarContainer_view(JFrame parentFrame, JPanel parentPanel) {
        // Set layout
        this.setLayout(new BorderLayout());

        // Initialize variables
        tableToolbarPanel = new TableToolbar_view(parentFrame);
        tabularDataPanel = new TabularData_view(parentPanel);

        // Add listener
        tableToolbarPanel.setToolbarListener(new ToolbarListener() {

            @Override
            public Activity getSelectedActivity() {
                return tabularDataPanel.getSelectedActivity();
            }
        });

        // Add components
        this.add(tableToolbarPanel, BorderLayout.SOUTH);
        this.add(tabularDataPanel, BorderLayout.CENTER);
    }

    /**
     * Set project
     *
     * @param project
     */
    public void setProject(Project project) {
        this.tabularDataPanel.setProject(project);
    }

    /**
     * Populate table
     */
    public void populateTable() {
        this.tabularDataPanel.populateTable();
    }

    /**
     * Reset table
     */
    public void resetTable() {
        this.tabularDataPanel.resetTable();
    }

    /**
     * Set table tools listener
     *
     * @param tableToolsListener
     */
    public void setTableToolsListener(TableToolsListener tableToolsListener) {
        this.tableToolbarPanel.setTableToolsListener(tableToolsListener);
    }

    /**
     * Set new activity button enabled
     *
     * @param enable Boolean
     */
    public void setAddActivityEnabled(boolean enable) {
        this.tableToolbarPanel.setAddActivityEnabled(enable);
    }

    /**
     * Set edit activity button enabled
     *
     * @param enable Boolean
     */
    public void setEditActivityEnabled(boolean enable) {
        this.tableToolbarPanel.setEditActivityEnabled(enable);
    }

    /**
     * Set delete activity button enabled
     *
     * @param enable Boolean
     */
    public void setDeleteActivityEnabled(boolean enable) {
        this.tableToolbarPanel.setDeleteActivityEnabled(enable);
    }

    /**
     * Set View activity details button enabled
     *
     * @param enable Boolean
     */
    public void setViewActivityEnabled(boolean enable) {
        this.tableToolbarPanel.setViewActivityEnabled(enable);
    }

    /**
     * Show/Hide tool bar below the table
     *
     * @param isVisible
     */
    public void setVisibleToolbar(boolean isVisible) {
        this.tableToolbarPanel.setVisible(isVisible);
        this.tableToolbarPanel.repaint();
        this.tableToolbarPanel.revalidate();
    }
}
