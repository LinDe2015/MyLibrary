package utils.linde.library.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by 19195 on 2015/12/11.
 * 日期工具类
 */
@SuppressWarnings("unused")
public class DateFormatUtils
{
    private static DateFormatUtils instance;

    private DateFormatUtils()
    {
    }

    public static DateFormatUtils getInstance()
    {
        if (instance == null)
        {
            synchronized (DateFormatUtils.class)
            {
                if (instance == null)
                {
                    instance = new DateFormatUtils();
                }
            }
        }
        return instance;
    }

    public String getString(TimeFormatEnum timeFormatEnum, long timeMillis)
    {
        return this.getString(null, timeFormatEnum, timeMillis);
    }

    public String getString(TimeZoneEnum timeZoneEnum, TimeFormatEnum timeFormatEnum, long timeMillis)
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillis);
        final SimpleDateFormat sdf = new SimpleDateFormat(timeFormatEnum == null ? TimeFormatEnum.yyyy_mm_dd_HH_mm_ss.value : timeFormatEnum.value, Locale.getDefault());
        if (timeZoneEnum == null || timeZoneEnum.value == null)
        {
            sdf.setTimeZone(TimeZone.getDefault());
        } else
        {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZoneEnum.value));
        }
        return sdf.format(calendar.getTime());
    }

    public String getString(TimeFormatEnum timeFormatEnum, int year, int month, int day)
    {
        return this.getString(null, timeFormatEnum, year, month, day);
    }

    public String getString(TimeZoneEnum timeZoneEnum, TimeFormatEnum timeFormatEnum, int year, int month, int day)
    {
        return getString(timeZoneEnum, timeFormatEnum, year, month, day, 0, 0, 0);
    }

    public String getString(TimeZoneEnum timeZoneEnum, TimeFormatEnum timeFormatEnum, int year, int month, int day, int hour, int minute, int second)
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(year, month, day, hour, minute, second);
        final SimpleDateFormat sdf = new SimpleDateFormat(timeFormatEnum == null ? TimeFormatEnum.yyyy_mm_dd_HH_mm_ss.value : timeFormatEnum.value, Locale.getDefault());
        if (timeZoneEnum == null || timeZoneEnum.value == null)
        {
            sdf.setTimeZone(TimeZone.getDefault());
        } else
        {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZoneEnum.value));
        }
        return sdf.format(calendar.getTime());
    }

    private int initMonth(int month)
    {
        if (month < 1)
        {
            month = 1;
        } else if (month > 12)
        {
            month = 12;
        }
        return month;
    }
}