package ninefoo.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ninefoo.config.Annotation.autoload;

public class Config {
	
	/**
	 * Auto load methods
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public static void autoload(){
		
		// Auto load method in Autoload.java with annotation @autoload(active = true)
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
