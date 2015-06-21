package ninefoo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation for the application
 * @author Amir EL Bawab
 */
public class Annotation {
	
	/**
	 * Auto load methods on program load
	 * @param active Check if method should be executed
	 * @param priority Priority of execution (High priority = 0, by default 0)
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface autoload{
		boolean active() default true;
		int priority() default 0;
	}
	
	/**
	 * Configure the auto load
	 * @param lowestPriority The lowest possible priority (default 0)
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface autoloadConfig{
		int lowestPriority() default 0;
	}
}
