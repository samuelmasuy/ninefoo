package ninefoo.lib.lang.parserObjects;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageObject {
	
	@JsonProperty("name")
	public String name;
	
	@JsonProperty("description")
	public String description;
	
	@JsonProperty("language")
	public HashMap<String,String> language;
}
