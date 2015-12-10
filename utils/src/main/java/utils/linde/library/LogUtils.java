package utils.linde.library;

import android.util.Log;

/**
 * Created by 19195 on 2015/12/8.
 * 日志工具类
 */
@SuppressWarnings("unused")
public class LogUtils
{
    public static void logV(String tag, String msg)
    {
        logV(tag, msg, null);
    }

    public static void logV(String tag, String msg, Throwable tr)
    {
        if (!AppWrapper.c().getResources().getBoolean(R.bool.debug))
            return;
        tag = tag == null ? LogUtils.class.getName() : tag;
        msg = msg == null ? "NullPointException" : msg;
        Log.v(tag, msg, tr);
    }

    public static void logD(String tag, String msg)
    {
        logD(tag, msg, null);
    }

    public static void logD(String tag, String msg, Throwable tr)
    {
        if (!AppWrapper.c().getResources().getBoolean(R.bool.debug))
            return;
        tag = tag == null ? LogUtils.class.getName() : tag;
        msg = msg == null ? "NullPointException" : msg;
        Log.d(tag, msg, tr);
    }

    public static void logI(String tag, String msg)
    {
        logI(tag, msg, null);
    }

    public static void logI(String tag, String msg, Throwable tr)
    {
        if (!AppWrapper.c().getResources().getBoolean(R.bool.debug))
            return;
        tag = tag == null ? LogUtils.class.getName() : tag;
        msg = msg == null ? "NullPointException" : msg;
        Log.i(tag, msg, tr);
    }

    public static void logW(String tag, String msg)
    {
        logW(tag, msg, null);
    }

    public static void logW(String tag, String msg, Throwable tr)
    {
        if (!AppWrapper.c().getResources().getBoolean(R.bool.debug))
            return;
        tag = tag == null ? LogUtils.class.getName() : tag;
        msg = msg == null ? "NullPointException" : msg;
        Log.w(tag, msg, tr);
    }

    public static void logE(String tag, String msg)
    {
        logE(tag, msg, null);
    }

    public static void logE(String tag, String msg, Throwable tr)
    {
        if (!AppWrapper.c().getResources().getBoolean(R.bool.debug))
            return;
        tag = tag == null ? LogUtils.class.getName() : tag;
        msg = msg == null ? "NullPointException" : msg;
        Log.e(tag, msg, tr);
    }
}
