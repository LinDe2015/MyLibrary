package utils.linde.library;

import android.content.res.Resources;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by 19195 on 2015/12/9.
 * 点击事件
 */
@SuppressWarnings("unused")
public class OnClickListenerUtils
{
    public static void injectAllMethods(final Object receiver) throws Exception
    {
        final Class clazz = receiver.getClass();
        //noinspection unchecked
        final Method findViewById = clazz.getMethod("findViewById", Integer.TYPE);
        final Method[] methods = clazz.getDeclaredMethods();
        final Resources res = AppWrapper.c().getResources();
        final String packageName = AppWrapper.c().getPackageName();
        for (final Method method : methods)
        {
            final int viewId;
            try
            {
                viewId = res.getIdentifier(method.getName(), "id", packageName);
            } catch (Exception e)
            {
                continue;
            }
            if (viewId <= 0)
            {
                continue;
            }
            method.setAccessible(true);
            try
            {
                final View view = (View) findViewById.invoke(receiver, viewId);
                view.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        try
                        {
                            method.invoke(receiver,v);
                        } catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            method.setAccessible(false);
        }
    }
}
