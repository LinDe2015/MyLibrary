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
        month = initMonth(month);
        day = initDay(year, month, day);
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, day);
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormatEnum == null ? TimeFormatEnum.yyyy_mm_dd_HH_mm_ss.value : timeFormatEnum.value, Locale.getDefault());
        if (timeZoneEnum == null || timeZoneEnum.value == null)
        {
            sdf.setTimeZone(TimeZone.getDefault());
        } else
        {
            sdf.setTimeZone(TimeZone.getTimeZone(timeZoneEnum.value));
        }
        return sdf.format(calendar.getTime());
    }

    private int initDay(int year, int month, int day)
    {
        if (day < 1)
        {
            day = 1;
        } else
        {
            switch (month)
            {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    day = day > 31 ? 31 : day;
                    break;
                case 2:
                    if ((year % 100 != 0 && year % 4 == 0) || year % 400 == 0)
                    {
                        day = day > 29 ? 29 : day;
                    } else
                    {
                        day = day > 28 ? 28 : day;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    day = day > 30 ? 30 : day;
                    break;
            }
        }
        return day;
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