package ninefoo.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

	// Define variables
	private static Date date = new Date();;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
	
	/**
	 * Constructor - Private
	 */
	private Console(){}
	
	/**
	 * Print log information
	 * @param msg Message to be printed
	 */
	public static void log(String msg){
		System.out.println(Console.template("LOG", msg));
	}
	
	/**
	 * Print error information
	 * @param msg Message to be printed
	 */
	public static void error(String msg){
		System.err.println(Console.template("ERROR", msg));
	}
	
	/**
	 * Template of the console messages
	 * @param type
	 * @param msg
	 * @return String
	 */
	private static String template(String type, String msg){
		Console.date.setTime(System.currentTimeMillis());
		return String.format("[%0$-5s]\t| %s | %s", type, Console.dateFormat.format(Console.date), msg);
	}
}
