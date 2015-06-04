package ninefoo.model;

import ninefoo.lib.DateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Farzad on 30-May-2015.
 */
public class MemberModel {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Inserts a new member into the database.
     * @param member Member object to be inserted to the database.
     * @return True if successful, false otherwise.
     */
    public boolean insertNewMember(Member member) {
        Statement statement = DbManager.createConnectionStatement();
        if (statement == null)
            return false;

        String insertMemberSql = String.format("INSERT INTO " +
                "member(first_name, last_name, username, password) " +
                "VALUES ('%s', '%s', '%s', '%s')", member.getFirstName(), member.getLastName(),
                member.getUsername(), member.getPassword());

        try {
            statement.executeUpdate(insertMemberSql);
            return true;

        } catch (SQLException e) {
            LOGGER.error("Could not add member to db --- detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return false;
    }

    // Utility method used to get the next member from the DB ResultSet object.
    private Member getNextMember(ResultSet members) {

        try {
            int memberId = members.getInt("member_id");
            String firstName = members.getString("first_name");
            String lastName = members.getString("last_name");
            String username = members.getString("username");
            String password = members.getString("password");
            Date registerDate = DateUtils.parse(members.getString("register_date"));

            return new Member(memberId, firstName, lastName, username, password, registerDate);
        } catch (SQLException e) {
            LOGGER.error("Problem reading next member from db --- detailed info: " + e.getMessage());
        }

        return null;
    }

    /**
     * Returns all the members stored in the database.
     * @return List of Member objects.
     */
    public List<Member> getAllMembers() {
        List<Member> allMembers = new ArrayList<>();
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getAllMembersSql = "SELECT * FROM member";
        try {
            ResultSet allMembersFromDb = statement.executeQuery(getAllMembersSql);

            while (allMembersFromDb.next()) {
                Member nextMember = getNextMember(allMembersFromDb);

                if (nextMember != null)
                    allMembers.add(nextMember);
            }

            return allMembers;

        } catch (SQLException e) {
            LOGGER.error("Could not get members from db --- detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }

    /**
     * Returns the Member object from the database that is associated with the specified ID.
     * @param memberId integer representing the ID of the member to be found.
     * @return Member object if it exists in the DB, NULL otherwise.
     */
    public Member getMemberById(int memberId) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getMemberByIdSql = "SELECT * FROM member " + "WHERE member_id = " + memberId;

        try {
            ResultSet members = statement.executeQuery(getMemberByIdSql);

            if (members.next()) {
                Member member = getNextMember(members);

                if (member != null)
                    return member;
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get member with member_id = " + memberId + " --- " +
                         "detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }

    /**
     * Returns the Member object from the database that has the specified username.
     * @param username String representing the username of the member.
     * @return Member object having the username, NULL if no member with this username is found.
     */
    public Member getMemberByUsername(String username) {
        Statement statement = DbManager.createConnectionStatement();

        if (statement == null)
            return null;

        String getMemberByUsernameSql = String.format("SELECT * FROM member WHERE " +
                "username = '%s'", username);

        try {
            ResultSet members = statement.executeQuery(getMemberByUsernameSql);

            if (members.next()) {
                Member member = getNextMember(members);

                if (member != null)
                    return member;
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get member with username = '" + username + "' --- " +
                    "detailed info: " + e.getMessage());
        } finally {
            DbManager.closeConnection();
        }

        return null;
    }
}
