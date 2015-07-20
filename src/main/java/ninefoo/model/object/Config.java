package ninefoo.model.object;

/**
 * This class represents a config entity in the database.
 * Created on 30-May-2015.
 *
 * @author Farzad MajidFayyaz
 */
public class Config {
    private int configId;
    private String configName;

    public Config(String configName) {
        this.configName = configName;
    }

    public int getConfigId() {
        return configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }
}
