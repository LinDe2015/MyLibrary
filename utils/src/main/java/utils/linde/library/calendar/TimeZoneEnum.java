package utils.linde.library.calendar;

/**
 * Created by 19195 on 2015/12/11.
 * 时区枚举
 */
public enum TimeZoneEnum
{
    DEFAULT(null),

    /**
     * 格林威治时区
     */
    GMT("GMT"), UTC("UTC");

    public final String value;

    TimeZoneEnum(String value)
    {
        this.value = value;
    }
}
