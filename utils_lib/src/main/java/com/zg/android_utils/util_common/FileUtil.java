package com.zg.android_utils.util_common;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

public class FileUtil {

    //得到会员key
    public static String getRaw(int id, Context context) {
        InputStream is = context.getResources().openRawResource(id);
        BufferedReader br = new BufferedReader(new InputStreamReader(is), 8096);
        String line = null;
        StringBuffer sb = new StringBuffer("");
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(is);
            FileUtil.close(br);
        }
        return sb.toString();
    }

    public static void close(Object o) {
        if (o == null) {
            return;
        }
        try {
            if (o instanceof Closeable) {
                ((Closeable) o).close();
            } else {
                Method method = o.getClass().getMethod("close");
                method.invoke(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
