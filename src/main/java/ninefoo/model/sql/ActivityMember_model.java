package ninefoo.model.sql;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Role;
import ninefoo.model.sql.template.AbstractModel;

/**
 * This class contains methods for manipulating the records in the database
 * that has a relationship between activities and members.
 * Created on 04-Jul-2015
 * @author Vince Abruzzese
 *
 */
public class ActivityMember_model extends AbstractModel
{
	/**
	 * Gets the list of activities for the specified member if that member has 
	 * a specified role.
	 * @param member Member object corresponding to the member
	 * @param role Role object corresponding to the member
	 * @return List of activity objects corresponding to this member/role
	 */
	public List<Activity> getActivitiesByMember(Member member, Role role)
	{
		if (member == null || role == null)
		{
			return null;
		}
		return getActivitiesByMember(member.getMemberId(), role.getRoleId());
	}
	
	/**
	 * Gets the list of activities corresponding to the specified ids of the member,
	 * and role
	 * @param memberId ID of member for activities
	 * @param roleId ID of the role corresponding to the activities
	 * @return List of activities or null if error
	 */
	public List<Activity> getActivitiesByMember(int memberId, int roleId)
	{
		if (memberId == 0 || roleId == 0)
		{
			return null;
		}
		
		this.open();
		
		List<Activity> activities = new ArrayList<>();
		
		//Query:
		sql = "SELECT * activity a " +
			  "WHERE member_id = ? AND role_id = ? AND a.activity_id = am.activity_id";
		
		try
		{
			this.prepareStatement();
			
			ps.setInt(1,  memberId);
			ps.setInt(2, roleId);
			
			result = ps.executeQuery();
			
			while (result.next())
			{
				Activity activity = this.getNextActivity(result);
				
				if (activity != null)
				{
					activities.add(activity);
				}
			}
		}
		catch (SQLException e)
		{
			LOGGER.error("Could not get activities from member_id = " + memberId +
						" --- detailed info " + e.getMessage());
			return null;
		}
		finally
		{
			this.close();
		}
		return activities;
	}
	
	
	
	
}
