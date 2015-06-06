package ninefoo.model;

import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Project_model{
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger();

    public static void createDatabase() throws ClassNotFoundException {
       
    	// load the sqlite-JDBC driver using the current class loader
        Class.forName("org.sqlite.JDBC");

        Connection connection = null;
        try
        {
            DbManager.createTables();
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            
//            CreateTables.createStatusTable(statement);
//            CreateTables.createMemberTable(statement);
//            CreateTables.createProjectTable(statement);
//            CreateTables.createActivityTable(statement);
//            CreateTables.createActivityLogTable(statement);
//            CreateTables.createConfigTable(statement);
//            CreateTables.createMemberWorkProjectTable(statement);
//            CreateTables.createMemberManagesProjectTable(statement);
//            CreateTables.createActivityPrerequisiteTable(statement);
//            CreateTables.createMemberConfigTable(statement);

            statement.executeUpdate("drop table if exists person");
            statement.executeUpdate("create table person (id integer, name string)");
            statement.executeUpdate("insert into person values(1, 'leo')");
            statement.executeUpdate("insert into person values(2, 'yui')");
            
            ResultSet rs = statement.executeQuery("select * from person");
            while(rs.next())
            {
                // read the result set
                LOGGER.debug("name = " + rs.getString("name"));
                LOGGER.debug("id = " + rs.getInt("id"));
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            LOGGER.error(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                LOGGER.error(e);
            }
        }
    }
}
