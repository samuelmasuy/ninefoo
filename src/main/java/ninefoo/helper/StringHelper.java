package ninefoo.helper;

/**
 * Helper methods for String
 * @author Amir EL Bawab
 */
public class StringHelper {
	
	/**
	 * Check if the object exist and return its <code>toString()</code> if it does, otherwise null
	 * @param object
	 * @return String or null
	 */
	public static String stringOrEmpty(Object object){
		return object == null ? "" : object.toString();
	}
	
	/**
	 * Join two strings with a comma
	 * @param original Original message
	 * @param extra Additional message
	 * @return Concatinated message
	 */
	public static String join(String original, String extra){
		if(original == null)
			return extra;
		
		if(extra == null)
			return original;
		
		String string = original;
		if(!string.isEmpty())
			string += ", ";
		return string + extra;
	}
}
