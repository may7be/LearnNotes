package com.zg.android_utils.util_common;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Method;

/**
 * @author zhao
 */
public class FileUtil {

    /**
     * 将对应的字符串写入对应的文件中
     *
     * @param pathName 文件目录
     * @param str      s
     */
    public static void writeStringToFile(String pathName, String str) {
        if (TextUtils.isEmpty(pathName) || TextUtils.isEmpty(str)) {
            return;
        }
        File file = new File(pathName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileUtil.writeStringToFile(file, str);
    }

    public static void writeStringToFile(File saveFile, String str) {
        if (TextUtils.isEmpty(str) || !isFileCanWrite(saveFile)) {
            //文件不存在，文件不能写，字符串内容不存在，都会直接返回
            return;
        }
        Writer writer = null;
        try {
            writer = new FileWriter(saveFile, false);
            writer.append(str);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean isFileCanWrite(File file) {
        return file != null && file.exists() && file.isFile() && file.canWrite();
    }

    public static String readStringFromFilePath(String filePath) {
        return readStringFromFile(new File(filePath));
    }

    /**
     * 将文件内容读为字符串，此方法不适宜读取大文件
     *
     * @param file 需要读取的文件
     * @return 文件中的字符串，文件不存在或者不可读返回null
     */
    public static String readStringFromFile(File file) {
        if (!isFileCanRead(file)) {
            return null;
        }
        BufferedReader br = null;
        StringBuilder stringBuffer = new StringBuilder();
        if (!file.exists()) {
            return null;
        }
        try {
            FileReader fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String str;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }

    public static boolean isFileCanRead(File file) {
        return file != null && file.exists() && file.canRead();
    }

    /**
     * Checks if external storage is available for read and write
     *
     * @return t
     */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * Checks if external storage is available to at least read
     *
     * @return t
     */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

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
