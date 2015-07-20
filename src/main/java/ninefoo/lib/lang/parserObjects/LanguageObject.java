package ninefoo.lib.lang.parserObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

/**
 * Language class that will hold the JSON code into its variables
 *
 * @author Amir El Bawab
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LanguageObject {

    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
    public String description;

    @JsonProperty("language")
    public HashMap<String, String> language;
}
