package ninefoo.view.project.chart;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.lib.lang.LanguageText;
import ninefoo.model.object.Activity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Chart painted showing data in the Table Chart
 *
 * @author Amir El Bawab
 * @author Sebouh Bardakjian
 */
public class GanttChart_view extends JPanel {

    private static final long serialVersionUID = 6876353610483002088L;

    // Constants
    private final int rowSize = 20;
    private final int columnSize = 50;
    private final int startRow = 20;
    private final int startCol = 20;
    private final int startRowLine = 10;

    // Define components
    private JPanel panel;
    private List<Activity> activities;
    private Calendar activityCal;

    /**
     * Constructor
     */
    public GanttChart_view() {

        // Set layout
        this.setLayout(new BorderLayout());

        // Define components
        panel = new JPanel() {


            private static final long serialVersionUID = 8383128594912669663L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Cast to G2D
                Graphics2D g2 = (Graphics2D) g;

                // Better quality
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int weeks = (DateHelper.getDifferenceDates(getMinDate(), getMaxDate()) / 7) + 2;
                weeks = Math.max(weeks, 5);

                drawCalendar(g2, weeks);

                // Set the panel size
                panel.setPreferredSize(new Dimension(weeks * 7 * columnSize + startCol, 0));

                if (activities != null) {

                    for (int i = 0; i < activities.size(); i++) {

                        int startDateCol = DateHelper.getDifferenceDates(getMinDate(), activities.get(i).getStartDate());
                        int activityLenght = DateHelper.getDifferenceDates(activities.get(i).getStartDate(), activities.get(i).getFinishDate());

                        drawActivity(g2, i, startDateCol, activityLenght, activities.get(i).getActivityLabel());
                    }
                }

                // Refresh panel
                panel.revalidate();
            }
        };
        JScrollPane scrollPane = new JScrollPane(panel);

        // Configure panel
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1000, 0));

        // Configure scrollPane
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(20);

        // Add components
        this.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Setter for list of activities
     *
     * @param List<Activity>
     */
    public void populateGanttChart(List<Activity> activities) {
        this.activities = activities;

        // If activity is not null, sort it
        if (this.activities != null) {
            for (int i = 0; i < activities.size(); i++) {
                for (int j = 0; j < activities.size() - 1; j++) {
                    if (activities.get(j).getStartDate().after(activities.get(j + 1).getStartDate())) {
                        Activity act = activities.get(j);
                        activities.set(j, activities.get(j + 1));
                        activities.set(j + 1, act);
                    }
                }
            }
        }

        panel.repaint();
    }

    /**
     * Get Minimum Date
     */
    private Date getMinDate() {
        if (activities != null) {
            List<Date> startDates = new ArrayList<Date>();
            for (int i = 0; i < activities.size(); i++) {
                startDates.add(activities.get(i).getStartDate());
            }
            Date minDate = DateHelper.getMinDate(startDates);
            if (minDate != null) {
                return minDate;
            }
        }

        return DateHelper.getToday();
    }

    /**
     * Get Maximum Date
     */
    private Date getMaxDate() {
        if (activities != null) {
            List<Date> endDates = new ArrayList<Date>();
            for (int i = 0; i < activities.size(); i++) {
                endDates.add(activities.get(i).getFinishDate());
            }
            Date maxDate = DateHelper.getMaxDate(endDates);
            if (maxDate != null) {
                return maxDate;
            }
        }

        return DateHelper.getDateRelativeToToday(30);
    }

    /**
     * Draw activity
     *
     * @param g2
     * @param row
     * @param start
     * @param end
     * @param activity
     */
    public void drawActivity(Graphics2D g2, int row, int start, int end, String activity) {
        Graphics2D g3 = (Graphics2D) g2.create();

        // Adjust the position
        int padding = (int) (rowSize / 1.2);
        int activityPadding = 20;
        row = (int) (row * rowSize * 1.5 + startRow + activityPadding) + padding;
        start = start * columnSize + startCol;
        end = end * columnSize;

        // Adjust row and graphics
        g3.setColor(new Color(0, 127, 255, 180));

        // Draw rectangle
        g3.fillRect(start, row, end, rowSize);

        // Adjust graphics
        g3.setColor(new Color(0, 100, 180));
        g3.fillRect(start, row, columnSize / 10, rowSize);

        // Draw shadow
        g3.setColor(new Color(0, 100, 180));
        g3.drawChars(activity.toCharArray(), 0, activity.length(), start + 11, 1 + row + 3 * rowSize / 4);

        // Draw the text
        g3.setColor(Color.WHITE);
        g3.drawChars(activity.toCharArray(), 0, activity.length(), start + 10, row + 3 * rowSize / 4);
    }

    /**
     * Draw calendar
     *
     * @param g2
     * @param weeks
     */
    private void drawCalendar(Graphics2D g2, int weeks) {

        Graphics2D g3 = (Graphics2D) g2.create();

        char days[] = {'S', 'M', 'T', 'W', 'T', 'F', 'S'};
        int row = startRow;
        Color lightGray = Color.decode("#D8D8D8");
        Color darkGray = Color.decode("#989898");
        Color blue = Color.decode("#0000ff");

        activityCal = Calendar.getInstance();
        activityCal.setTime(getMinDate());
        int dayOffset = activityCal.get(Calendar.DAY_OF_WEEK);

        for (int week = 0; week < weeks; week++) {
            // Week
            int weeklyColumn = startCol + days.length * week * columnSize;
            g3.setColor(blue);

            String date = DateHelper.format(activityCal.getTime(), Config.DATE_FORMAT_ALPHA);
            date = date.replace("January", LanguageText.getConstant("JANUARY"));
            date = date.replace("February", LanguageText.getConstant("FEBRUARY"));
            date = date.replace("March", LanguageText.getConstant("MARCH"));
            date = date.replace("April", LanguageText.getConstant("APRIL"));
            date = date.replace("May", LanguageText.getConstant("MAY"));
            date = date.replace("June", LanguageText.getConstant("JUNE"));
            date = date.replace("July", LanguageText.getConstant("JULY"));
            date = date.replace("August", LanguageText.getConstant("AUGUST"));
            date = date.replace("September", LanguageText.getConstant("SEPTEMBER"));
            date = date.replace("October", LanguageText.getConstant("OCTOBER"));
            date = date.replace("November", LanguageText.getConstant("NOVEMBER"));
            date = date.replace("December", LanguageText.getConstant("DECEMBER"));

            int datePadding = 3;
            g3.drawChars(date.toCharArray(), 0, date.length(), weeklyColumn + datePadding, startRow);
            activityCal.add(Calendar.DATE, 7);

            for (int day = 0; day < days.length; day++) {
                //Day
                g3.setColor(darkGray);

                // Draw characters
                int column = day * columnSize + startCol + days.length * week * columnSize;
                int daysPadding = 25;
                g3.drawChars(days, (day + dayOffset - 1) % days.length, 1, (int) (column + columnSize / 2.3), startRow + daysPadding);

                // Draw lines
                if ((day + dayOffset - 1) % days.length == 0) {
                    g3.setColor(darkGray);
                    g3.drawLine(column, startRowLine, column, this.getHeight());
                } else {
                    g3.setColor(lightGray);
                    int linePadding = 15;
                    g3.drawLine(column, startRowLine + linePadding, column, this.getHeight());
                }
            }
        }


    }
}
