package ninefoo.application;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;
import ninefoo.model.object.Activity;
import ninefoo.model.object.Member;
import ninefoo.model.object.Project;

public class Test {

	public static void main(String[] args) {
		Calendar calendar1 = Calendar.getInstance();
		
		calendar1.set(Calendar.YEAR, 2014);
		calendar1.set(Calendar.MONTH, 11);
		calendar1.set(Calendar.MINUTE, 33);
		
        Date minDate =  calendar1.getTime();
        
        
		Calendar calendar2 = Calendar.getInstance();
		
		calendar2.set(Calendar.YEAR, 2015);
		calendar2.set(Calendar.MONTH, 11);
		calendar2.set(Calendar.MINUTE, 33);
		
        Date maxDate =  calendar2.getTime();
        
        
        //(String activityLabel, int duration, String startDate, String finishDate, Project project, Member member){
        Activity act1 = new Activity("a", 5, DateHelper.format(minDate, Config.DATE_FORMAT), DateHelper.format(maxDate, Config.DATE_FORMAT), null, null);
        Activity act2 = new Activity("b", 10, DateHelper.format(maxDate, Config.DATE_FORMAT), DateHelper.format(minDate, Config.DATE_FORMAT), null, null);
        List<Activity> activities = new ArrayList<Activity>();
        activities.add(act1);
        activities.add(act2);
        
        System.out.println(DateHelper.getMinDate(activities).toString());
        System.out.println(DateHelper.getMaxDate(activities).toString());
        System.out.println(DateHelper.getDifferenceDates(minDate, maxDate));
	}

}
