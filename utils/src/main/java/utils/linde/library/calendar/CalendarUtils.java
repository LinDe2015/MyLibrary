package utils.linde.library.calendar;

import java.util.Calendar;

/**
 * Created by 19195 on 2015/12/11.
 * @see Calendar 实体类
 */
@SuppressWarnings("unused")
public class CalendarUtils
{
    public final long timeMillis;
    public final int year;
    public final int month;
    public final int day;
    public final int dayOfWeek;
    public final int dayOfWeekInMonth;
    public final int dayOfMonth;
    public final int dayOfYear;
    public final int hour;
    public final int minute;
    public final int second;

    public CalendarUtils()
    {
        this(System.currentTimeMillis());
    }

    public CalendarUtils(long timeMillis)
    {
        this.timeMillis = timeMillis;
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DATE);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        dayOfWeekInMonth = calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);
    }
}
