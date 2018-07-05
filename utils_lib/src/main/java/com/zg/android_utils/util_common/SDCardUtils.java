package com.zg.android_utils.util_common;

import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.format.DateFormat;

import com.zg.android_utils.bean.StorageInfo;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * SD卡相关的辅助类
 *
 * @author 王璞
 * @version V1.0
 * @date 2016-4-29 下午8:23:24
 */
public class SDCardUtils {

    public static final String PROJECT_NAME = "cnmst_produce";
    // 查看SD卡状态
    public static boolean ishasCard = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    public static File path = Environment.getExternalStorageDirectory();
    public static String pathStr = path.getAbsolutePath(); // 获得SD卡路径
    public static StatFs statfs = new StatFs(path.getPath()); // 获得 SD卡内存
    public static long blockSize = statfs.getBlockSize(); // block 大小
    public static long availableBlock = statfs.getAvailableBlocks(); // 可用block

    private SDCardUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

    }

    public static boolean isHasCard() {
        ishasCard = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        return ishasCard;
    }

    public static long availableSize() {// 可用空间大小 单位为M
        blockSize = statfs.getBlockSize();
        availableBlock = statfs.getAvailableBlocks();
        return (blockSize * availableBlock) / (1024 * 1024);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    private static String getSDCardPath() {
        List<StorageInfo> listAvaliableStorage = listAvailableStorage();
        for (int i = 0; i < listAvaliableStorage.size(); i++) {
            StorageInfo storageInfo = listAvaliableStorage.get(i);
            if (!storageInfo.isRemoveable) {
                return storageInfo.path + File.separator;
            }
        }

        if (isSDCardEnable()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
        }
        return null;
    }

    public static String getReallyPath(String dir) {
        return pathStr + File.separator + dir;
    }

    public static boolean isHasFile(String dir, String filename) {// 是否存在文件
        boolean isdir = false;
        ishasCard = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
        if (ishasCard) {
            File file = new File(pathStr + File.separator + dir + File.separator + filename);
            isdir = file.exists();
        }
        return isdir;
    }

    public static boolean creatDir(String dir) { // 创建目录
        File file = new File(pathStr + File.separator + dir);
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    public static void deleteDir(String dir) {// 删除目录
        File file = new File(dir);
        if (file.exists() || file.isDirectory()) {
            String[] tempList = file.list();
            File temp = null;
            for (int i = 0; i < tempList.length; i++) {
                if (dir.endsWith(File.separator)) {
                    temp = new File(dir + tempList[i]);
                } else {
                    temp = new File(dir + File.separator + tempList[i]);
                }

                if (temp.isDirectory()) {
                    deleteDir(temp.getPath());
                }
                temp.delete();
            }
        }
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    public static List<StorageInfo> listAvailableStorage() {
        ArrayList<StorageInfo> storagges = new ArrayList<StorageInfo>();
        StorageManager storageManager = (StorageManager) BaseUtil.getContext().getSystemService(BaseUtil.getContext().STORAGE_SERVICE);
        try {
            Class<?>[] paramClasses = {};
            Method getVolumeList = StorageManager.class.getMethod("getVolumeList", paramClasses);
            getVolumeList.setAccessible(true);
            Object[] params = {};
            Object[] invokes = (Object[]) getVolumeList.invoke(storageManager, params);
            if (invokes != null) {
                StorageInfo info = null;
                for (int i = 0; i < invokes.length; i++) {
                    Object obj = invokes[i];
                    Method getPath = obj.getClass().getMethod("getPath");
                    String path = (String) getPath.invoke(obj);
                    info = new StorageInfo(path);
                    File file = new File(info.path);
                    if ((file.exists()) && (file.isDirectory()) && (file.canWrite())) {
                        Method isRemovable = obj.getClass().getMethod("isRemovable");
                        String state = null;
                        try {
                            Method getVolumeState = StorageManager.class.getMethod("getVolumeState", String.class);
                            state = (String) getVolumeState.invoke(storageManager, info.path);
                            info.state = state;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (info.isMounted()) {
                            info.isRemoveable = ((Boolean) isRemovable.invoke(obj)).booleanValue();
                            storagges.add(info);
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e1) {
            e1.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e1) {
            e1.printStackTrace();
        }
        storagges.trimToSize();

        return storagges;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    public static String getVoicePath() {
        return getSDCardPath() + PROJECT_NAME + "/voice";
    }

    public static String getImagePath() {
        return getSDCardPath() + PROJECT_NAME + "/image";
    }

    public static String getFileName() {
        return (String) DateFormat.format("yyyyMMdd_HHmmss", new Date());
    }

    public static String getFileName(String sufix) {
        return getFileName() + sufix;
    }

    public static String getCachePath() {
        return getSDCardPath() + PROJECT_NAME + "/cache";
    }

    public static String getCrashReportPath() {
        return getSDCardPath() + PROJECT_NAME + "/crash";
    }

    public static String getTextPath() {
        return getSDCardPath() + PROJECT_NAME + "/text";
    }
}
