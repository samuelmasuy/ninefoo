package ninefoo.lib.layout.grid;

import ninefoo.helper.LayoutHelper;
import org.apache.logging.log4j.LogManager;

import javax.swing.*;
import java.awt.*;

/**
 * Class that will place a table on an panel
 *
 * @author Amir El Bawab
 */
public class GridTable {

    // Logger
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    // Variables
    private JPanel panel;
    private GridBagConstraints gc;
    private int row;
    private int col;

    /**
     * Constructor
     *
     * @param panel
     */
    public GridTable(JPanel panel) {

        // Initialize variables
        this.panel = panel;
        this.gc = new GridBagConstraints();
        this.row = 0;
        this.col = 0;

        // Configure gc
        this.gc.fill = GridBagConstraints.NONE;

        // Verify that the panel layout is GridBagLayout
        if (!(panel.getLayout() instanceof GridBagLayout))
            LOGGER.error("Panel does not have a layout of GridBagLayout");
    }

    /**
     * Get GridBagConstraints
     *
     * @return GridBagConstraints
     */
    public GridBagConstraints getGridBagConstraints() {
        return this.gc;
    }

    /**
     * Increment row
     */
    public void newRow() {
        this.row++;
        this.col = 0;
    }

    /**
     * Put component in panel at the current position
     *
     * @param component Component
     * @param colSpan   Merged columns
     */
    public void put(Component component, int colSpan) {
        LayoutHelper.gcGrid(gc, row, col, colSpan);
        this.panel.add(component, this.gc);
        col += colSpan;
    }

    /**
     * Put component in panel at the current position.
     * Column span is 1
     *
     * @param component Component
     */
    public void put(Component component) {
        this.put(component, 1);
    }

    /**
     * Component will be placed on the Top-Left
     */
    public void placeTopLeft() {
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
    }

    /**
     * Component will be placed on the Center-Left
     */
    public void placeCenterLeft() {
        gc.anchor = GridBagConstraints.LINE_START;
    }

    /**
     * Component will be placed on the Bottom-Left
     */
    public void placeBottomLeft() {
        gc.anchor = GridBagConstraints.LAST_LINE_START;
    }

    /**
     * Component will be placed on the Top-Right
     */
    public void placeTopRight() {
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
    }

    /**
     * Component will be placed on the Center-Right
     */
    public void placeCenterRight() {
        gc.anchor = GridBagConstraints.LINE_END;
    }

    /**
     * Component will be placed on the Bottom-Right
     */
    public void placeBottomRight() {
        gc.anchor = GridBagConstraints.LAST_LINE_END;
    }

    /**
     * Component will be placed on the Center
     */
    public void placeCenter() {
        gc.anchor = GridBagConstraints.CENTER;
    }

    /**
     * Component will be placed on the Center-Top
     */
    public void placeCenterTop() {
        gc.anchor = GridBagConstraints.NORTH;
    }

    /**
     * Component will be placed on the Center-Bottom
     */
    public void placeCenterBottom() {
        gc.anchor = GridBagConstraints.SOUTH;
    }

    /**
     * Set padding space for each cell
     *
     * @param top
     * @param left
     * @param bottom
     * @param right
     */
    public void paddingCell(int top, int left, int bottom, int right) {
        this.gc.insets = new Insets(top, left, bottom, right);
    }

    /**
     * Set padding space for each cell
     *
     * @param all Top Left Bottom Right
     */
    public void paddingCell(int all) {
        this.paddingCell(all, all, all, all);
    }
}
