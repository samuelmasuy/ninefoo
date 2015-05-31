package ninefoo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for the application
 */
public class Annotation {
	
	/**
	 * Auto load methods on program load
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface autoload{
		boolean active() default false;
	}
}
