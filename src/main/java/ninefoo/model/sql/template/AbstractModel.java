package ninefoo.model.sql.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ninefoo.config.Database;

public abstract class AbstractModel {
	
	// Variables
	protected Database db;
	protected Connection conn;
	protected PreparedStatement ps;
	protected String sql;
	protected int affectedRows;
	protected ResultSet result;
	
	// Logger
	protected static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Constructor
	 */
	public AbstractModel() {
		
		// Set the database
		this.db = Database.getInstance();
	}
	
	/**
	 * Prepare statement
	 * @throws SQLException
	 */
	public void prepareStatement() throws SQLException{
		ps = this.conn.prepareStatement(sql);
	}
	
	/**
	 * Get last inserted id
	 * @return Id or ERROR
	 * @throws SQLException
	 */
	public final int getLastInsertId() throws SQLException{
		
		// Query
		sql = "SELECT last_insert_rowid()";
		
		// Prepare
		this.prepareStatement();
		
		// Run
		result = ps.executeQuery();
		
		// Get id
		if (result.next())
	        return result.getInt("last_insert_rowid()");
		return Database.ERROR;
	}
	
	/**
	 * Open connection
	 */
	protected final void open(){
		this.conn = db.openConnection();
	}
	
	/**
	 * Close prepared statement and connection
	 */
	protected final void close(){
		
		// Close prepared statement
		if(ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				LOGGER.error("Prepared statement was not closed: " + e.getMessage());
			}
		
		// Close connection
		if(db != null)
			db.closeConnection();
		
		// Clean the variables
		sql = null;
		affectedRows = 0;
		result = null;
	}
}
