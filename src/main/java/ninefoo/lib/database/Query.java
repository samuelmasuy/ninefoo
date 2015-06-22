package ninefoo.lib.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ninefoo.helper.StringHelper;
import ninefoo.model.DbManager;

/**
 * Dynamic database query generator
 * @author Amir El Bawab
 */
public class Query implements QueryCustom, QueryDelete, QueryInsert, QuerySelect, QueryUpdate{
	
	// Logger
	private static final Logger LOGGER = LogManager.getLogger();
	
	// Variables
	private String query;
	private String select;
	private String where;
	private String table;
	private ArrayList<String> attributes;
	private ArrayList<String> attributesValues;
	private ResultSet result;
	private int affectedRows;
	private Query_type type;
	
	// Constants
	public static final String AND = "AND";
	public static final String OR = "OR";

	// Query type
	private enum Query_type { SELECT, UPDATE, INSERT, DELETE, CUSTOM };
	
	/**
	 * Constructor
	 */
	public Query() {
		this.query = "";
		this.select = "";
		this.table = "";
		this.where = "";
	}
	
	/**
	 * Select
	 * @param select
	 * @return Current database object (this)
	 */
	public Query select(String select){
		
		// Set type
		this.type = Query_type.SELECT;
		this.select = StringHelper.join(this.select, select);
		return this;
	}
	
	/**
	 * From
	 * @param from
	 * @return Current database object (this)
	 */
	public Query from(String from){
		this.table = StringHelper.join(this.table, from);
		return this;
	}
	
	/**
	 * Where clause
	 * @param where Condition
	 * @param option <code>AND</code> <code>OR</code>
	 * @return Current database object (this)
	 */
	public Query where(String where, String option){
		switch(option){
		case AND:
		case OR:
			if(!this.where.isEmpty())
				this.where += String.format(" %s ", option);
			this.where += where;
			break;
		default:
			LOGGER.error("Wrong option chosen in the wehere clause.");
		}
		return this;
	}
	
	/**
	 * Where clause. Default AND option
	 * @param where
	 * @return Current database object (this)
	 */
	public Query where(String where){
		return this.where(where, AND);
	}
	
	/**
	 * Get query
	 * @return query as a string
	 */
	public String getQuery(){
		return this.query;
	}
	
	/**
	 * Add attribute to INSERT and UPDATE
	 * @param attribute
	 * @param value
	 * @return Current database object (this)
	 */
	public Query set(String attribute, String value){
		
		// If not defined
		if(this.attributes == null)
			this.attributes = new ArrayList<>();
		
		// If not defined
		if(this.attributesValues == null)
			this.attributesValues = new ArrayList<>();
		
		// Add values
		this.attributes.add(attribute);
		this.attributesValues.add(value);
		return this;
	}
	
	/**
	 * Insert query
	 * @param table
	 * @return Current database object (this)
	 */
	public Query insert(String table){
		
		// Set type
		this.type = Query_type.INSERT;
		this.table = table;
		return this;
	}
	
	/**
	 * Update query
	 * @param table
	 * @return Current database object (this)
	 */
	public Query update(String table){
		
		// Set type
		this.type = Query_type.UPDATE;
		this.table = table;
		return this;
	}
	
	public Query delete(String table){
		
		// Set type
		this.type = Query_type.DELETE;
		this.table = table;
		return this;
	}
	
	/**
	 * Get result
	 * @return result
	 */
	public ResultSet getResult(){
		return this.result;
	}
	
	/**
	 * Get the number of affected rows for update, insert and delete
	 * @return number of affected rows
	 */
	public int getAffectedRows(){
		return this.affectedRows;
	}
	
	/**
	 * Run custom query
	 * @param query
	 * @return true if the query was executed successfully
	 */
	public boolean run(String query){
		this.type = Query_type.CUSTOM;
		this.query = query.trim();
		return run();
	}
	
	/**
	 * Run custom query based on the type
	 * @return true if the query was executed successfully
	 */
	public boolean run(){
		Statement statement = DbManager.createConnectionStatement();

        if (statement == null) {
            LOGGER.warn("Could not get a connection statement to DB");
            return false;
        }
        
        try {
        	
			switch(this.type){
			
			case SELECT:
				this.query = String.format("SELECT %S FROM %S %s %s", this.select, this.table, this.where.isEmpty() ? "" : "WHERE", this.where).trim();
				this.result = statement.executeQuery(this.query);
				return true;
			
			case UPDATE:
				String setUpdate = "";
				for(int i = 0; i < this.attributes.size(); i++)
					setUpdate = StringHelper.join(setUpdate, String.format("%s = '%s'", this.attributes.get(i), this.attributesValues.get(i))).trim();
				this.query = String.format("UPDATE %S SET %s %s %s", this.table, setUpdate, this.where.isEmpty() ? "" : "WHERE", this.where);
				this.affectedRows = statement.executeUpdate(this.query);
				return true;
			
			case INSERT:
				String setInsert = "";
				for(int i = 0; i < this.attributes.size(); i++)
					setInsert = StringHelper.join(setInsert, this.attributes.get(i));
				
				String setInsertValue = "";
				for(int i = 0; i < this.attributesValues.size(); i++)
					setInsertValue = StringHelper.join(setInsertValue, String.format("\"%s\"", this.attributesValues.get(i)));
				
				this.query = String.format("INSERT INTO %s (%s) VALUES (%s)", this.table, setInsert, setInsertValue).trim();
				this.affectedRows = statement.executeUpdate(this.query);
				return true;
			
			case DELETE:
				this.query = String.format("DELETE FROM %s %s %s", this.table, this.where.isEmpty() ? "" : "WHERE", this.where).trim();
				this.affectedRows = statement.executeUpdate(this.query);
				return true;
				
			case CUSTOM:
				if(this.query.substring(0, this.query.indexOf(' ')).equalsIgnoreCase("select"))
					this.result = statement.executeQuery(query);
				else
					statement.executeUpdate(query);
				return true;
			}
			
			return true;
		} catch (SQLException e) {
			LOGGER.error("Could not run query: " + e.getMessage());
		}
        
        return false;
	}
}
