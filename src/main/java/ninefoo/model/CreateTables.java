package ninefoo.model;

import java.sql.SQLException;
import java.sql.Statement;


public class CreateTables {

//    public static void createStatusTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS status");
//        statement.executeUpdate("CREATE TABLE status(statid INTEGER AUTO_INCREMENT, value TEXT NOT NULL, PRIMARY KEY (statid))");
//    }
//
//    public static void createMemberTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS member");
//        statement.executeUpdate("CREATE TABLE member(memid INTEGER AUTO_INCREMENT, fname TEXT NOT NULL, lname TEXT NOT NULL, username TEXT NOT NULL, password TEXT NOT NULL, reg_date DATETIME DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (memid), UNIQUE (username))");
//    }
//
//    public static void createProjectTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS project");
//        statement.executeUpdate("CREATE TABLE project (proid INTEGER AUTO_INCREMENT, name TEXT NOT NULL, create_date DATETIME DEFAULT CURRENT_TIMESTAMP, update_date DATETIME, budget REAL, deadline_date DATETIME, description TEXT, creator INTEGER NOT NULL, PRIMARY KEY (proid), FOREIGN KEY (creator) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }
//
//    public static void createActivityTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS activity");
//        statement.executeUpdate("CREATE TABLE activity(actid INTEGER AUTO_INCREMENT, name TEXT NOT NULL, duration_day INTEGER, description TEXT, create_date DATETIME DEFAULT CURRENT_TIMESTAMP, project INTEGER NOT NULL, member INTEGER NOT NULL, PRIMARY KEY (actid), FOREIGN KEY (project) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }
//
//    public static void createActivityLogTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS activitylog");
//        statement.executeUpdate("CREATE TABLE activitylog(actlogid INTEGER AUTO_INCREMENT, proid INTEGER NOT NULL, memid INTEGER NOT NULL, statid INTEGER NOT NULL, actid INTEGER, create_date DATETIME DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (actlogid), FOREIGN KEY (proid) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (memid) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (statid) REFERENCES status(statid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (actid) REFERENCES activity(actid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }
//
//    public static void createConfigTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS config");
//        statement.executeUpdate("CREATE TABLE config(confid INTEGER AUTO_INCREMENT, name TEXT NOT NULL, PRIMARY KEY (confid))");
//    }
//
//    public static void createMemberWorkProjectTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS member_works_project");
//        statement.executeUpdate("CREATE TABLE member_works_project(member INTEGER NOT NULL, project INTEGER NOT NULL, PRIMARY KEY (member, project), FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (project) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }
//
//    public static void createMemberManagesProjectTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS member_manages_project");
//        statement.executeUpdate("CREATE TABLE member_manages_project(member INTEGER NOT NULL, project INTEGER NOT NULL, PRIMARY KEY (member, project), FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (project) REFERENCES project(proid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }
//
//    public static void createActivityPrerequisiteTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS activity_prerequisite");
//        statement.executeUpdate("CREATE TABLE activity_prerequisite(activity1 INTEGER NOT NULL, activity2 INTEGER NOT NULL, PRIMARY KEY (activity1, activity2), FOREIGN KEY (activity1) REFERENCES activity(actid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (activity2) REFERENCES activity(actid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }
//
//    public static void createMemberConfigTable(Statement statement) throws SQLException {
//        statement.executeUpdate("DROP TABLE IF EXISTS member_config");
//        statement.executeUpdate("CREATE TABLE member_config(member INTEGER NOT NULL, config INTEGER NOT NULL, value INTEGER NOT NULL, PRIMARY KEY (member, config), FOREIGN KEY (member) REFERENCES member(memid) ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (config) REFERENCES config(confid) ON UPDATE CASCADE ON DELETE CASCADE)");
//    }


}
