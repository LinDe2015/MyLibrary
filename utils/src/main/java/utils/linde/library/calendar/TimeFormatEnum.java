package utils.linde.library.calendar;

/**
 * Created by 19195 on 2015/12/11.
 * 时间格式枚举
 */
public enum TimeFormatEnum
{
    yyyy_M_d("yyyy-M-d"),
    yyyy_MM_dd("yyyy-MM-dd"),
    yyyy_MM_dd_HH_mm("yy-MM-dd HH:mm"),
    yyyy_mm_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
    MM_dd("MM-dd"),
    HH_mm_ss("HH:mm:ss"),
    HH_mm("HH:mm");
    public final String value;

    TimeFormatEnum(String value)
    {
        this.value = value;
    }
}