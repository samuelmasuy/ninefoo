package ninefoo.model;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Farzad on 26-May-2015.
 */
public class DbManager {
    private static Connection dbConnection = null;
//    private static Statement statement = null;
    private static final String dbName = "test.db";
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Creates a Statement object to be used to run SQL commands against the database
     * @return the desired Statement object
     */
    public static Statement createConnectionStatement() {

//        try {
//            Class.forName("org.sqlite.JDBC");
//            dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName);
//            statement = dbConnection.createStatement();
//
//        } catch (ClassNotFoundException e) {
//            LOGGER.error("Could not find org.sqlite.JDBC class --- detailed info: " +
//                    e.getMessage());
//            return null;
//        } catch (SQLException e) {
//            LOGGER.error("Could not create a db connection --- detailed info: " +
//                    e.getMessage());
//            return null;
//        }
//
//        return statement;

        Statement statement = null;

        if (dbConnection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

            } catch (ClassNotFoundException e) {
                LOGGER.error("Could not find org.sqlite.JDBC class --- detailed info: " +
                        e.getMessage());
                return null;
            } catch (SQLException e) {
                LOGGER.error("Could not create a db connection --- detailed info: " +
                        e.getMessage());
                return null;
            }
        }

        try {

            statement = dbConnection.createStatement();

        } catch (SQLException e) {
            LOGGER.error("Could not create db statement object --- detailed info: "+
                    e.getMessage());
        }

        return statement;
    }

    /**
     * Closes the connection to the database.
     */
    public static void closeConnection() {

        if (dbConnection != null)
            try {
                dbConnection.close();
                dbConnection = null;
            } catch (SQLException e) {
                LOGGER.warn("Could not close db connection --- detailed info: " +
                        e.getMessage());
            }
    }

    public static void createTables() {
        Statement statement = createConnectionStatement();

        if (statement == null)
            return;

        try {
            statement.setQueryTimeout(30);

            createMemberTable(statement);
            createRoleTable(statement);
            createStatusTable(statement);
            createProjectTable(statement);
            createProjectMemberTable(statement);
            createActivityTable(statement);
            createActivityRelationTable(statement);
            createActivityLogTable(statement);
            createConfigTable(statement);
        } catch (SQLException e) {
            LOGGER.error("Problem creating db entities --- detailed info: " + e.getMessage());
        }

        closeConnection();
    }

    private static void createMemberTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS member");
        statement.executeUpdate("CREATE TABLE member(" +
                "member_id          INTEGER     PRIMARY KEY         AUTOINCREMENT, " +
                "first_name         VARCHAR(50), " +
                "last_name          VARCHAR(50), " +
                "username           VARCHAR(50), " +
                "password           VARCHAR(50), " +
                "register_date      DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')), " +

                "UNIQUE (username) " +
        ")");
    }

    private static void createRoleTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS role");
        statement.executeUpdate("CREATE TABLE role(" +
                "role_id            INTEGER     PRIMARY KEY         AUTOINCREMENT, " +
                "role_name          VARCHAR(50), " +

                "UNIQUE (role_name)" +
        ")");
    }

    private static void createStatusTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS status");
        statement.executeUpdate("CREATE TABLE status(" +
                "status_id          INTEGER     PRIMARY KEY         AUTOINCREMENT, " +
                "status_name        VARCHAR(50)" +
        ")");
    }

    private static void createProjectTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS project");
        statement.executeUpdate("CREATE TABLE project(" +
                "project_id         INTEGER     PRIMARY KEY         AUTOINCREMENT, " +
                "project_name       VARCHAR(50) NOT NULL, " +
                "create_date        DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')), " +
                "start_date         DATETIME, " +
                "update_date        DATETIME, " +
                "budget             DECIMAL(14, 2), " +
                "deadline_date      DATETIME, " +
                "description        VARCHAR(255)" +
        ")");
    }

    private static void createProjectMemberTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS project_member");
        statement.executeUpdate("CREATE TABLE project_member(" +
                "project_id         INT, " +
                "member_id          INT, " +
                "role_id            INT, " +

                "PRIMARY KEY (project_id, member_id, role_id), " +
                "FOREIGN KEY (project_id)       REFERENCES project(project_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (member_id)        REFERENCES member(member_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (role_id)          REFERENCES role(role_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE" +
        ")");
    }

    private static void createActivityTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS activity");
        statement.executeUpdate("CREATE TABLE activity(" +
                "activity_id            INTEGER     PRIMARY KEY         AUTOINCREMENT, " +
                "activity_label         VARCHAR(50), " +
                "description            VARCHAR(255), " +
                "duration               INT, " +
                "optimistic_duration    INT, " +
                "likely_duration        INT, " +
                "pessimistic_duration   INT, " +
                "create_date            DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')), " +
                "project_id             INT         NOT NULL, " +
                "member_id              INT, " +

                "FOREIGN KEY (project_id)       REFERENCES project(project_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (member_id)        REFERENCES member(member_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE" +
        ")");
    }

    private static void createActivityRelationTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS activity_relation");
        statement.executeUpdate("CREATE TABLE activity_relation(" +
                "activity_id            INT, " +
                "prereq_activity_id     INT, " +

                "PRIMARY KEY (activity_id, prereq_activity_id), " +
                "FOREIGN KEY (activity_id)          REFERENCES activity(activity_id) " +
                "                                   ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (prereq_activity_id)   REFERENCES activity(activity_id) " +
                "                                   ON UPDATE CASCADE ON DELETE CASCADE" +
        ")");
    }

    private static void createActivityLogTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS activity_log");
        statement.executeUpdate("CREATE TABLE activity_log(" +
                "activity_log_id        INTEGER     PRIMARY KEY        AUTOINCREMENT, " +
                "project_id             INT, " +
                "member_id              INT, " +
                "status_id              INT, " +
                "activity_id            INT, " +
                "create_date            DATETIME    DEFAULT (datetime(CURRENT_TIMESTAMP, 'localtime')), " +

                "FOREIGN KEY (project_id)   REFERENCES project(project_id) " +
                "                           ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (member_id)    REFERENCES member(member_id)" +
                "                           ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (status_id)    REFERENCES status(status_id) " +
                "                           ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (activity_id)  REFERENCES activity(activity_id) " +
                "                           ON UPDATE CASCADE ON DELETE CASCADE " +
        ")");
    }

    private static void createConfigTable(Statement statement) throws SQLException {
        statement.executeUpdate("DROP TABLE IF EXISTS config");
        statement.executeUpdate("CREATE TABLE config(" +
                "config_id          INTEGER         PRIMARY KEY     AUTOINCREMENT, " +
                "config_name        VARCHAR(50)     NOT NULL" +
        ")");
    }
}