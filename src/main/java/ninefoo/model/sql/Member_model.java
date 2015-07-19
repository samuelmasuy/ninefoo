package ninefoo.model.sql;

import ninefoo.helper.DateHelper;
import ninefoo.config.Config;
import ninefoo.config.Database;
import ninefoo.model.object.Member;
import ninefoo.model.sql.template.AbstractModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class contains all the methods for manipulating members in our database. For example,
 *      to find a specific member by ID or username, delete a member, or get a list of all members.
 * Created on 30-May-2015.
 * @author Farzad MajidFayyaz
 */
public class Member_model extends AbstractModel{

    /**
     * Inserts a new member into the database.
     * @param member Member object to be inserted to the database.
     * @return The ID (primary key) of the newly inserted record, <code>Database.ERROR</code> if the insertion
     *         was not successful.
     */
    // ------------------------------------ Testing notes ------------------------------
    // NOTE: to make sure there is not already a member with the same username as the
    //       new Member object you want to create, you can use one of the delete methods
    //       to delete the existing member.
    // Suggested test case:
    //  1. Create a new Member object
    //  2. Use this method to add the member to the DB
    //  3. Make sure the return value is not 0
    //  4. (the ideal way would be to mock the functionality of the query method) Run
    //     getMemberByUsername and check that the ID of the returned object is the same
    //     as the one we had in step 3.
    // Suggested alternate test case:
    //  1. Create a new Member object
    //  2. Use this method to add it to the DB.
    //  3. Check that the returned value is not 0.
    //  4. Try to add the user again using this method.
    //  5. The return value should be 0.
    public int insertNewMember(Member member) {
    	
    	// Open
    	this.open();

    	// Query
        sql = "INSERT INTO " +
                "member(first_name, last_name, username, password) " +
                "VALUES (?, ?, ?, ?)";
        
        try {
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, member.getFirstName());
        	ps.setString(2, member.getLastName());
        	ps.setString(3, member.getUsername());
        	ps.setString(4, member.getPassword());
        	
        	// Run
            affectedRows = ps.executeUpdate();

            // Check insert
            if (affectedRows == 1)
            	return this.getLastInsertId();

            LOGGER.warn("Updated row count was not equal to 1");

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not add member to db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return Database.ERROR;
    }

    /**
     * Returns all the members stored in the database.
     * @return List of Member objects.
     */
    // ------------------------------------ Testing notes ------------------------------
    // Suggested test case:
    //  1. First run the method and keep track of the size of the returned list.
    //  2. Create 1 or more Member objects.
    //  3. Add them to the DB using insertNewMember method.
    //  4. Now run this method again to get the list of member.
    //  5. The difference between the new List's size and the old one should be equal
    //     to the number of users you created in step 2.
    public List<Member> getAllMembers() {
    	
    	// Open
    	this.open();
    	
        List<Member> allMembers = new ArrayList<>();
        
        // Query
        sql = "SELECT * FROM member";
        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Run
            result = ps.executeQuery();
            
            // Get all
            while (result.next()) {
                Member nextMember = getNextMember(result);

                if (nextMember != null)
                    allMembers.add(nextMember);
            }

            return allMembers;

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get members from db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }
    
    /**
     * Returns all the members stored in the database for a project.
     * @return List of Member objects.
     */
    public List<Member> getAllMembersForAProject(int projectId) {
    	
    	// Open
    	this.open();
    	
        List<Member> allMembers = new ArrayList<>();
        
        // Query
        sql = 		"SELECT m.member_id AS member_id, first_name, last_name, username, password, register_date "
        		+ 	"FROM member m, project_member pm WHERE m.member_id = pm.member_id AND pm.project_id = ?";
        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, projectId);
        	
        	// Run
            result = ps.executeQuery();
            
            // Get all
            while (result.next()) {
                Member nextMember = getNextMember(result);

                if (nextMember != null)
                    allMembers.add(nextMember);
            }

            return allMembers;

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get members from db --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }

    /**
     * Returns the Member object from the database that is associated with the specified ID.
     * @param memberId integer representing the ID of the member to be found.
     * @return Member object if it exists in the DB, NULL otherwise.
     */
    // ------------------------------------ Testing notes ------------------------------
    // Suggested test case:
    //  1. Create a new Member and add it to DB using insertNewMember.
    //  2. (Assuming the returned value of insertNewMember is not zero) Run this method
    //     to make sure the returned value is not NULL.
    public Member getMemberById(int memberId) {
    	
    	// Open
    	this.open();

    	// Query
        sql = "	SELECT * FROM member " + 
        		"WHERE member_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, memberId);
        	
        	// Run
            result = ps.executeQuery();
            
            // Get single
            if (result.next()) {
                Member member = getNextMember(result);

                if (member != null)
                    return member;
            }
            
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get member with member_id = " + memberId + " --- " +
                         "detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }

    /**
     * Returns the Member object from the database that has the specified username.
     * @param username String representing the username of the member.
     * @return Member object having the username, NULL if no member with this username is found.
     */
    // ------------------------------------ Testing notes ------------------------------
    // Suggested test case:
    //  I think exactly the same as the first suggested one for insertNewMember.
    public Member getMemberByUsername(String username) {
    	
    	// Open
    	this.open();

    	// Query
        sql = "SELECT * FROM member WHERE username = ?";

        try {
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, username);;
        	
        	// Run
            result = ps.executeQuery();

            // Get single
            if (result.next()) {
                Member member = getNextMember(result);

                if (member != null)
                    return member;
            }
            
        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not get member with username = '" + username + "' --- " +
                    "detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return null;
    }

    /**
     * Deletes a member from DB corresponding to the specified Member object.
     * @param member Member object to be deleted from DB.
     * @return True if a record was deleted; False otherwise.
     */
    // ------------------------------------ Testing notes ------------------------------
    // NOTE: you cannot create a Member object directly and pass it to this method because
    //      the constructor we use to create instances from Java code doesn't allow
    //      specifying ID, so the resulting instance will NOT have an ID.
    // Suggested test case:
    //  1. Use on of the query methods (getMemberById or getMemberByUsername) to get
    //     a Member object from the database.
    //  2. Use this method to delete the member.
    //  3. Use the query method again to make sure the returned object is NULL.
    public boolean deleteMember(Member member) {
        if (member == null)
            return false;

        return deleteMemberById(member.getMemberId());
    }

    /**
     *
     * Deletes a member from DB corresponding to the specified member ID.
     * @param memberId integer representing the ID of the member to be deleted.
     * @return True if a record was deleted; False otherwise.
     */
    // ------------------------------------ Testing notes ------------------------------
    // Suggested test case:
    //  1. Make sure there exists in the DB a user with the ID you want to delete (you
    //     can use one of the query methods).
    //  2. Use this method to delete that member.
    //  3. Query again to make sure the result is NULL.
    public boolean deleteMemberById(int memberId) {
    	
    	// Open
    	this.open();
    	
    	// Query
        sql = "DELETE FROM member WHERE member_id = ?";

        try {
        	
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setInt(1, memberId);
        	
        	// Run
            affectedRows = ps.executeUpdate();
            return (affectedRows == 1);

        // Error
        } catch (SQLException e) {
            LOGGER.error("Could not delete member --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return false;
    }

    /**
     * Deletes a member from DB corresponding to the specified username.
     * @param username String representing the username of the member to be deleted.
     * @return True if a record was deleted; False otherwise.
     */
    // ------------------------------------ Testing notes ------------------------------
    // Suggested test case:
    //  Same as deleteMemberById, but considering username instead of the ID.
    public boolean deleteMemberByUsername(String username) {
    	
    	// Open
    	this.open();

    	// Query
        sql = "DELETE FROM member WHERE username LIKE ?";
        
        try {
        	// Prepare
        	this.prepareStatement();
        	
        	// Data
        	ps.setString(1, username);
        	
        	// Run
            affectedRows = ps.executeUpdate();
            return (affectedRows == 1);

        // Error
        } catch (SQLException e) {
            LOGGER.error("MELISSA Could not delete member --- detailed info: " + e.getMessage());
        
        // Close
        } finally {
        	this.close();
        }

        return false;
    }
    
 // Utility method used to get the next member from the DB ResultSet object.
    // ------------------------------------ Testing notes ------------------------------
    // No need to test this method because it's not "public".
    private Member getNextMember(ResultSet members) {

        try {
            int memberId = members.getInt("member_id");
            String firstName = members.getString("first_name");
            String lastName = members.getString("last_name");
            String username = members.getString("username");
            String password = members.getString("password");
            Date registerDate = DateHelper.parse(members.getString("register_date"), Config.DATE_FORMAT);

            return new Member(memberId, firstName, lastName, username, password, registerDate);
        } catch (SQLException e) {
            LOGGER.error("Problem reading next member from db --- detailed info: " + e.getMessage());
        }

        return null;
    }
}
