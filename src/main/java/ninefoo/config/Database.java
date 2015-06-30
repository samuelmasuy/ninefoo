package ninefoo.config;

import ninefoo.lib.database.ScriptRunner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created on 26-May-2015.
 * @author Farzad MajidFayyaz
 */
public class Database {
    
	// Constants
	public static final int ERROR = 0;
	public static final String DB_NAME = "pm_expert.db";
    
	// Variables
	private Connection dbConnection = null;

	// Logger
    private static final Logger LOGGER = LogManager.getLogger();
	
    // Singleton instance
    private final static Database instance = new Database();
    
    /**
     * Private constructor
     */
    private Database(){}
    
    /**
     * Create connection
     */
    public Connection openConnection(){
    	if(dbConnection == null){
    		try {
                Class.forName("org.sqlite.JDBC");
                dbConnection = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
                LOGGER.debug("Database connection opened...");
                
            } catch (ClassNotFoundException e) {
                LOGGER.error("Could not find org.sqlite.JDBC class --- detailed info: " +
                        e.getMessage());
            } catch (SQLException e) {
                LOGGER.error("Could not create a db connection --- detailed info: " +
                        e.getMessage());
            }
    	}
    	return dbConnection;
    }

    
    /**
     * Closes the connection to the database.
     */
    public void closeConnection() {

        if (dbConnection != null)
            try {
                dbConnection.close();
                dbConnection = null;
                LOGGER.debug("Database connection closed");
                
            } catch (SQLException e) {
                LOGGER.warn("Could not close db connection --- detailed info: " +
                        e.getMessage());
            }
    }

    /**
     * Create tables
     * Read them from SQL file
     */
    public void createTables() {
    	
    	// Open connection
    	openConnection();
    	
    	LOGGER.debug("Start creating the database: " + DB_NAME);
    	
    	// Load SQL File
		ScriptRunner runner = new ScriptRunner(this.dbConnection, false, true);
		try {
			runner.runScript(new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/db/tables.sql"))));
			
		} catch (FileNotFoundException e) {
			LOGGER.error("SQL file not found: " + e.getMessage());
		} catch (IOException e) {
			LOGGER.error("SQL file cannot be read: " + e.getMessage());
		} catch (SQLException e) {
			LOGGER.error("SQL error: " + e.getMessage());
		} catch (NullPointerException e) {
			LOGGER.error("SQL null pointer: " + e.getMessage());
		}
		
    	LOGGER.debug("Database created succesfully!");
		
		// Close connection
		closeConnection();
    }
    
    /**
     * Get singleton instance
     * @return instance
     */
    public static Database getInstance(){
    	return instance;
    }
}