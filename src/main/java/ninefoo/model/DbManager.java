package ninefoo.model;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Farzad on 26-May-2015.
 */
public class DbManager {
    private static Connection dbConnection = null;
    private static final String dbName = "test.db";

    /**
     * Creates a connection to the database.
     * @return Newly created connection.
     * @throws ClassNotFoundException If SQLite JDBC class cannot be found.
     * @throws SQLException If there is any error when creating the connection.
     */
    public static Connection createConnection() throws ClassNotFoundException, SQLException {

        if (dbConnection != null)
            return dbConnection;

        Class.forName("org.sqlite.JDBC");
        dbConnection = DriverManager.getConnection("jdbc:sqlite:" + dbName);

        return dbConnection;
    }

    /**
     * Closes the connection to the database.
     * @throws SQLException In case there was an error when closing the connection.
     */
    public static void closeConnection() throws SQLException {

        if (dbConnection != null)
            dbConnection.close();
    }

    public static void createTables() throws SQLException, ClassNotFoundException {
        dbConnection = createConnection();
        Statement statement = dbConnection.createStatement();
        statement.setQueryTimeout(30);

        createMemberTable(statement);
        createRoleTable(statement);
        createStatusTable(statement);
        createProjectTable(statement);
        createProjectMemberTable(statement);
        createActivityTable(statement);
        createActivityRelationTable(statement);
        createActivityLogTable(statement);
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
                "role_name          VARCHAR(50)" +
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
                "prerequisite           INT, " +

                "FOREIGN KEY (project_id)       REFERENCES project(project_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (member_id)        REFERENCES member(member_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE, " +
                "FOREIGN KEY (prerequisite)     REFERENCES activity(activity_id) " +
                "                               ON UPDATE CASCADE ON DELETE CASCADE " +
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
}