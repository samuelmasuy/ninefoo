package ninefoo.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ninefoo.config.Annotation.autoload;

/**
 * This class contains constants and methods that configure the application
 * @see Autoload
 */
public class Config {
	
	// Application name
	public final static String APPLICATION_NAME = "PM Expert";
	
	// Application verssion
	public final static String APPLICATION_VERSION = "1.0";
	
	// Path for the application
	public final static String APPLICATION_PATH = "ninefoo";
	
	/**
	 * Auto load methods
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException
	 */
	public static void autoload(){
		
		// Auto load methods in Autoload.java with annotation @autoload(active = true)
		// The methods are not executed in any order, so make sure they don't conflict
		Autoload autoload = new Autoload();
		Method methods[] = autoload.getClass().getMethods();
		for(Method method : methods){
			Annotation annotations[] = method.getAnnotations();
			for(Annotation annotation : annotations){
				if(annotation instanceof autoload){
					autoload autoloadAnnotation = (autoload) annotation;
					if(autoloadAnnotation.active()){
						try {
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
	}
}
