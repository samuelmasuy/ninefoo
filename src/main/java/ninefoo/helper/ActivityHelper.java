package ninefoo.helper;

import java.util.ArrayList;

import ninefoo.config.ActivityConfig;
import ninefoo.lib.excelTable.NumberedExcelTable;
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
		
		data[ActivityConfig.ACTIVITY_ID] = String.valueOf(activity.getActivityId());
		data[ActivityConfig.ACTIVITY_NAME] = String.valueOf(activity.getActivityLabel());
		data[ActivityConfig.DURATION] = String.valueOf(activity.getDuration());
		data[ActivityConfig.FINISH] = String.valueOf(activity.getFinishDate());
		data[ActivityConfig.LIKELY] = String.valueOf(activity.getLikelyDuration());
		data[ActivityConfig.MEMBER] = String.valueOf(activity.getMember().getFirstName() + " " + activity.getMember().getLastName());
		data[ActivityConfig.OPTIMISTIC] = String.valueOf(activity.getOptimisticDuration());
		data[ActivityConfig.PESSIMISTIC] = String.valueOf(activity.getPessimisticDuration());
		data[ActivityConfig.PLANNED_PERCENTAGE] = "???";
		data[ActivityConfig.START] = String.valueOf(activity.getStartDate());
		data[ActivityConfig.PREREQ] = String.valueOf(activity.getPrerequisitesAsString());
		
		return data;
	}
}
