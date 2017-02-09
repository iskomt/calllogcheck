package com.example.matt.helloworld.Utilities;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date trim(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        return calendar.getTime();
    }

    public static Date getFirstDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0); // ! clear would not reset the hour of day !
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);

        while (cal.get(cal.DAY_OF_WEEK) != cal.MONDAY) {
            cal.add(Calendar.DATE, -1); // Substract 1 day until first day of week.
        }
       return cal.getTime();
    }
}
