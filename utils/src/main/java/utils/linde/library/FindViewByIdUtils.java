package utils.linde.library;

import android.content.res.Resources;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 19195 on 2015/12/8.
 * 初始化控件工具类
 */
@SuppressWarnings("unused")
public class FindViewByIdUtils
{
    /**
     * @param receiver 对象(类)的实例化，类中自带findViewById(int)方法
     * @throws Exception
     */
    public static void autoInjectAllFields(Object receiver) throws Exception
    {
        final Class clazz = receiver.getClass();
        //noinspection unchecked
        final Method method = clazz.getMethod("findViewById", Integer.TYPE);
        final Resources res = AppWrapper.c().getResources();
        final String packageName = AppWrapper.c().getPackageName();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
        {
            final int viewId;
            try
            {
                viewId = res.getIdentifier(field.getName(), "id", packageName);
            } catch (Exception e)
            {
                continue;
            }
            if (viewId <= 0)
            {
                continue;
            }
            field.setAccessible(true);
            try
            {
                field.set(receiver, method.invoke(receiver, viewId));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }

    public static void autoInjectAllFields(Object receiver, View rootView) throws Exception
    {
        final Class clazz = receiver.getClass();
        final Resources res = AppWrapper.c().getResources();
        final String packageName = AppWrapper.c().getPackageName();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields)
        {
            final int viewId;
            try
            {
                viewId = res.getIdentifier(field.getName(), "id", packageName);
            } catch (Exception e)
            {
                continue;
            }
            if (viewId <= 0)
            {
                continue;
            }
            field.setAccessible(true);
            try
            {
                field.set(receiver, rootView.findViewById(viewId));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }
}
