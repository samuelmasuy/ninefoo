package ninefoo.helper;

import java.util.ArrayList;

import ninefoo.config.ActivityConfig;
import ninefoo.config.Config;
import ninefoo.model.object.Activity;

/**
 * Helper class for the Activity
 * @see ActivityConfig
 * @see Activity
 * @author Amir
 *
 */
public class ActivityHelper {
	
	/**
	 * Get a filtered row
	 * @param table
	 * @param activity
	 */
	public static Object[] getFilteredRow(Activity activity) {
		
		// Get data
		Object[] data = getRow(activity);
			
		// Create new columns
		ArrayList<Object> filteredData = new ArrayList<>(ActivityConfig.TABLE_HEADER_TOGGLER.length);
		
		// Get only used data
		for(int i = 0; i < ActivityConfig.TABLE_HEADER_TOGGLER.length; i++)
			
			// If column enabled, add it to the object
			if(ActivityConfig.isEnabled(i))
				filteredData.add(data[i]);
		
		// Get row
		return filteredData.toArray(new Object[filteredData.size()]);
	}
	
	/**
	 * Get data in an array
	 * @param data
	 * @param activity
	 * @param offset
	 */
	public static Object[] getRow(Activity activity){
		
		Object[] data = new String[ActivityConfig.TABLE_HEADER.length];
		
		data[ActivityConfig.ACTIVITY_ID] = String.format("ACT%d", activity.getActivityId());
		data[ActivityConfig.ACTIVITY_NAME] = activity.getActivityLabel();
		data[ActivityConfig.DURATION] = String.valueOf(activity.getDuration());
		data[ActivityConfig.FINISH] = DateHelper.format(activity.getFinishDate(), Config.DATE_FORMAT_SHORT);
		data[ActivityConfig.LIKELY] = String.valueOf(activity.getLikelyDuration());
		data[ActivityConfig.MEMBER] = activity.getMember().getFirstName() + " " + activity.getMember().getLastName();
		data[ActivityConfig.OPTIMISTIC] = String.valueOf(activity.getOptimisticDuration());
		data[ActivityConfig.PESSIMISTIC] = String.valueOf(activity.getPessimisticDuration());
		data[ActivityConfig.PLANNED_PERCENTAGE] = DateHelper.getToday().before(activity.getFinishDate()) ? "0%" : "100%";
		data[ActivityConfig.START] = DateHelper.format(activity.getStartDate(), Config.DATE_FORMAT_SHORT);
		data[ActivityConfig.PREREQ] = activity.getPrerequisitesAsString();
		
		return data;
	}
	
	/**
	 * Get the id and name of activity as a string
	 * @param activity
	 */
	public static String getIdAndName(Activity activity){
		return String.format("%d - %s",activity.getActivityId(),activity.getActivityLabel());
	}
}
