package ninefoo.config;

/**
 * Configuration for the activities in the project.
 * @author Amir El Bawab
 */
public class ActivityConfig {
	
	// Constants
	public static final String[] TABLE_HEADER = {"Activity ID", "Activity Name", "Start", "Finish", "Duration", "Activity % Complete", "Total Float", "Dependency"};
	
	/**
	 * Get header at a position
	 * @param i
	 * @return header title
	 */
	public static String getHeaderAtIndex(int i){
		return TABLE_HEADER[i];
	}
	
	/**
	 * Get index of a title in the header
	 * @param title
	 * @return index
	 */
	public static int getIndexOfTitle(String title){
		for(int i = 0; i < TABLE_HEADER.length; i++)
			if(TABLE_HEADER[i].equalsIgnoreCase(title))
				return i;
		return -1;
	}
}
