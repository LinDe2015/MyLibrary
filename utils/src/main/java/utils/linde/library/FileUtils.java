package utils.linde.library;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 19195 on 2015/12/8.
 * 文件读写工具类
 */
@SuppressWarnings("unused")
public class FileUtils
{
    public static String readCache(String fileName)
    {
        return read(new File(AppWrapper.c().getCacheDir(), fileName));
    }

    public static boolean writeCache(String fileName, String write, boolean append)
    {
        return write(new File(AppWrapper.c().getCacheDir(), fileName), write, append);
    }

    public static String readFile(String fileName)
    {
        return read(new File(AppWrapper.c().getFilesDir(), fileName));
    }

    public static boolean writeFile(String fileName, String write, boolean append)
    {
        return write(new File(AppWrapper.c().getFilesDir(), fileName), write, append);
    }

    public static String read(File file)
    {
        String result = "";
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try
        {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[256];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1)
            {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            result = new String(byteArrayOutputStream.toByteArray());
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null)
            {
                try
                {
                    byteArrayOutputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static boolean write(File file, String write, boolean append)
    {
        boolean result = false;
        FileOutputStream fileOutputStream = null;
        try
        {
            fileOutputStream = new FileOutputStream(file, append);
            byte[] buffer = write.getBytes();
            fileOutputStream.write(buffer);
            result = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            if (fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
