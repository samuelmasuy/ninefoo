package ninefoo.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Annotation {
	
	/**
	 * Auto load on program load
	 * @author Amir
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.METHOD)
	public @interface autoload{
		boolean active() default false;
	}
}
