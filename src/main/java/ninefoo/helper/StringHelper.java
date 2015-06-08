package ninefoo.helper;

public class StringHelper {
	
	/**
	 * Check if the object exist and return its <code>toString()</code> if it does, otherwise null
	 * @param object
	 * @return String or null
	 */
	public static String stringOrEmpty(Object object){
		return object == null ? "" : object.toString();
	}
}
