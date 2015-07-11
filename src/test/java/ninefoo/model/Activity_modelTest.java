package ninefoo.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;
import ninefoo.model.sql.Member_model;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ninefoo.model.sql.*;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)




public class Activity_modelTest {
	
	@Test
	public void test01getActivityByMemberId(){
		
		Activity_model act=new Activity_model();
		Date date01 = DateHelper.parse("01/01/2015", Config.DATE_FORMAT_SHORT);
		Date date02 = DateHelper.parse("24/03/2015", Config.DATE_FORMAT_SHORT);
		Member member01=new Member("Mel", "D", "melD","123");
		Project prj01=new Project ("much project", 100, date01, date02 , "beatiful project");
		Activity act01=new Activity("much activity", 100, "12/04/1994", "13/04/1994", prj01, member01);
		int dbId=act.insertNewActivity(act01);
		Activity actDb=act.getActivityByMemberId(dbId);
		int retrievedId=actDb.getActivityId();
		assertEquals("the databaseID is not equal to the retrieved id",dbId,retrievedId);
		
		
	}

}
