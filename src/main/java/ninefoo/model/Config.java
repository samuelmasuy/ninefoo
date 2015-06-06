package ninefoo.model;

/**
 * This class represents a config entity in the database.
 * Created by Farzad on 30-May-2015.
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
