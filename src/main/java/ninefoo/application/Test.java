package ninefoo.application;

import java.util.Arrays;
import java.util.Date;

import ninefoo.config.Config;
import ninefoo.helper.DateHelper;

public class Test {

	public static void main(String[] args) {
        Date minDate =  DateHelper.parse("01/01/2014", Config.DATE_FORMAT_SHORT);
        Date maxDate =  DateHelper.parse("01/01/2012", Config.DATE_FORMAT_SHORT);
		Date between = DateHelper.parse("01/01/2013", Config.DATE_FORMAT_SHORT);
		

		Date[] dates = {minDate, maxDate, between};
        
        System.out.println(DateHelper.getMinDate(Arrays.asList(dates)).toString());
//        System.out.println(DateHelper.getMaxDate(activities).toString());

        System.out.println(DateHelper.getDifferenceDates(minDate, maxDate));
	}

}
