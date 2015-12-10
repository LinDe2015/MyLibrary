package utils.linde.library;

import android.app.Application;
import android.content.Context;

/**
 * Created by 19195 on 2015/12/8.
 *
 * @see Application 基类
 */
public class AppWrapper extends Application
{
    private static Context applicationContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        applicationContext = getApplicationContext();
    }

    /**
     * @return 全局上下文
     */
    public static Context c()
    {
        return applicationContext;
    }
}
