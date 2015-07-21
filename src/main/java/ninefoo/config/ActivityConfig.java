package ninefoo.config;

import ninefoo.lib.lang.LanguageText;

import java.util.ArrayList;

/**
 * Configuration for the activities in the project.
 *
 * @author Amir El Bawab
 */
public class ActivityConfig {

    // Constants
    public static final String[] TABLE_HEADER = {
            LanguageText.getConstant("ACTIVITY_ID_ACT"),
            LanguageText.getConstant("ACTIVITY_NAME_ACT"),
            LanguageText.getConstant("START_ACT"),
            LanguageText.getConstant("FINISH_ACT"),
            LanguageText.getConstant("DURATION_ACT"),
            LanguageText.getConstant("PLANNED_PERCENTAGE_ACT"),
            LanguageText.getConstant("OPTIMISTIC_ACT"),
            LanguageText.getConstant("LIKELY_ACT"),
            LanguageText.getConstant("PESSIMISTIC_ACT"),
            LanguageText.getConstant("MEMBER_ACT"),
            LanguageText.getConstant("COST_ACT"),
            LanguageText.getConstant("PREREQ_ACT")
    };

    // Disabled columns
    public static boolean[] TABLE_HEADER_TOGGLER = new boolean[TABLE_HEADER.length];

    // Header as indexes
    public static final int ACTIVITY_ID = getIndexOfTitle(LanguageText.getConstant("ACTIVITY_ID_ACT"));
    public static final int ACTIVITY_NAME = getIndexOfTitle(LanguageText.getConstant("ACTIVITY_NAME_ACT"));
    public static final int START = getIndexOfTitle(LanguageText.getConstant("START_ACT"));
    public static final int FINISH = getIndexOfTitle(LanguageText.getConstant("FINISH_ACT"));
    public static final int DURATION = getIndexOfTitle(LanguageText.getConstant("DURATION_ACT"));
    public static final int PLANNED_PERCENTAGE = getIndexOfTitle(LanguageText.getConstant("PLANNED_PERCENTAGE_ACT"));
    public static final int OPTIMISTIC = getIndexOfTitle(LanguageText.getConstant("OPTIMISTIC_ACT"));
    public static final int LIKELY = getIndexOfTitle(LanguageText.getConstant("LIKELY_ACT"));
    public static final int PESSIMISTIC = getIndexOfTitle(LanguageText.getConstant("PESSIMISTIC_ACT"));
    public static final int MEMBER = getIndexOfTitle(LanguageText.getConstant("MEMBER_ACT"));
    public static final int PREREQ = getIndexOfTitle(LanguageText.getConstant("PREREQ_ACT"));
    public static final int COST = getIndexOfTitle(LanguageText.getConstant("COST_ACT"));

    /**
     * Get index of a title in the header
     *
     * @param title
     * @return index
     */
    public static int getIndexOfTitle(String title) {
        for (int i = 0; i < TABLE_HEADER.length; i++)
            if (TABLE_HEADER[i].equalsIgnoreCase(title))
                return i;
        return -1;
    }

    /**
     * Get the list of active columns
     *
     * @return String array of active columns
     */
    public static String[] getFilteredHeader() {
        ArrayList<String> header = new ArrayList<>(TABLE_HEADER.length);
        for (int i = 0; i < TABLE_HEADER_TOGGLER.length; i++)
            if (TABLE_HEADER_TOGGLER[i] == false)
                header.add(TABLE_HEADER[i]);
        return header.toArray(new String[header.size()]);
    }

    /**
     * Check if it's enabled
     *
     * @param index
     * @return boolean
     */
    public static boolean isEnabled(int index) {
        return ActivityConfig.TABLE_HEADER_TOGGLER[index] == false;
    }
}
