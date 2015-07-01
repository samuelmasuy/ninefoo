package ninefoo.config;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;

import ninefoo.config.Annotation.autoload;
import ninefoo.config.Annotation.autoloadConfig;

/**
 * This class contains constants and methods that configure the application
 * @see Autoload
 * @author Amir EL Bawab, Samuel Masuy, Farzad MajidFayyaz, Sebouh Bardakjian
 */
public class Config {
	
	// Logger
	private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();
		
	// Application name
	public final static String APPLICATION_NAME = "PM Expert";
	
	// Application version
	public final static String APPLICATION_VERSION = "1.0";
	
	// Path for the application
	public final static String APPLICATION_PATH = "ninefoo";

	// Date format used when creating (and parsing) dates.
	public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	// Date format short version
	public final static String DATE_FORMAT_SHORT = "dd/MM/yyyy";
	
	// Date format alpha version
	public final static String DATE_FORMAT_ALPHA = "MMMMM d, yyyy";

	/**
	 * Auto load methods
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException
	 */
	public static void autoload(){
		
		LOGGER.info("--------- AUTOLOAD START ----------");
		
		// Auto load methods in Autoload.java with annotation @autoload(active = true)
		// The methods are not executed in any order, so make sure they don't conflict, or use priority attribute
		Autoload autoload = new Autoload();
		autoloadConfig configAnnotation = autoload.getClass().getAnnotation(autoloadConfig.class);
		Method methods[] = autoload.getClass().getMethods();

		// If class annotation exists
		if(configAnnotation != null){
			
			// Execute methods by priority
			for(int priority=0; priority <= configAnnotation.lowestPriority(); priority++){
				for(Method method : methods){
					autoload autoloadAnnotation = method.getAnnotation(autoload.class);
					
					if(autoloadAnnotation != null){
						if(autoloadAnnotation.active() && autoloadAnnotation.priority() == priority){
							try {
								LOGGER.info(String.format("Autoload method: %s with priority: %d", method.getName(), autoloadAnnotation.priority()));
								method.invoke(autoload);
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			
			// Run methods with priority more than last priority
			for(Method method : methods){
				autoload autoloadAnnotation = method.getAnnotation(autoload.class);
				
				if(autoloadAnnotation != null){
					if(autoloadAnnotation.active() && autoloadAnnotation.priority() > configAnnotation.lowestPriority()){
						try {
							LOGGER.info(String.format("Autoload method: %s with priority: %d", method.getName(), autoloadAnnotation.priority()));
							method.invoke(autoload);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		LOGGER.info("--------- AUTOLOAD COMPLETE ----------");
	}
}
