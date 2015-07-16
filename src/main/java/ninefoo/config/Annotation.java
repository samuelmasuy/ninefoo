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
	public @interface AutoloadAtRuntime{
		boolean active() default true;
		int priority() default 0;
	}
	
	/**
	 * Configure the auto load
	 * @param lowestPriority The lowest possible priority (default 0)
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface AutoloadConfig{
		int lowestPriority() default 0;
	}
	
	/**
	 * Methods and classes that have this annotation should not be modified in this version.
	 * If any changes are required, mention it in the @param changesRequiredInNextVersion
	 * @author Amir El Bawab
	 */
	@Retention(RetentionPolicy.SOURCE)
	@Target({ElementType.METHOD, ElementType.TYPE})
	public @interface FinalVersion {
		int version();
		String[] changesRequiredInNextVersion() default {};
	}
	
}
