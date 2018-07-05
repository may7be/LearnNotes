package com.zg.android_utils.util_common;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore.Images;
import android.text.format.DateFormat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * #Dr.@适配某些机型SD卡引起的Null Pointer bug#  
 * 
 * Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
 */

public class UseSDcard {
	// 查看SD卡状态
	public static boolean ishasCard = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	public static File path = Environment.getExternalStorageDirectory();
	public static String pathStr = path.getAbsolutePath(); // 获得SD卡路径
	public static StatFs statfs = new StatFs(path.getPath()); // 获得 SD卡内存
	public static long blockSize = statfs.getBlockSize(); // block 大小
	public static long availableBlock = statfs.getAvailableBlocks(); // 可用block
	private static Bitmap bitmap;

																	// 数量

	public static long availableSize() {// 可用空间大小 单位为M
		blockSize = statfs.getBlockSize();
		availableBlock = statfs.getAvailableBlocks();
		return (blockSize * availableBlock) / (1024 * 1024);
	}

	public static boolean isHasFile(String dir, String filename) {// 是否存在文件
		boolean isdir = false;
		ishasCard =  Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
		if (ishasCard) {
			File file = new File(pathStr + File.separator + dir	+ File.separator + filename);
            isdir = file.exists();
		}
		return isdir;
	}

	public static boolean isHasCard() {
		ishasCard =  Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
		return ishasCard;
	}

	// 得到文件
	public static File getFile(String filePath) {
		ishasCard =  Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
		if (ishasCard) {
			File file = new File(pathStr + File.separator + filePath);
			if (file.exists()) {
				return file;
			} else {
				return creatFile(filePath);
			}
		}
		return null;
	}

	public static boolean creatDir(String dir) { // 创建目录
		File file = new File(pathStr + File.separator + dir);
		if (!file.exists()) {
			return file.mkdirs();
		}
		return true;
	}

	// 创建文件
	public static File creatFile(String fileName) {
		File file = new File(pathStr + File.separator + fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
				return new File(pathStr + File.separator + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	public static String getReallyPath(String dir) {
		return pathStr + File.separator + dir;
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

	public static void deleteFile(String dir) {// 删除文件
		String pathstr = pathStr + File.separator + dir;
		File file = new File(pathstr);
		if (file.exists() && !file.isDirectory()) {
			file.delete();
		}
	}

	public static boolean deleteFile(File file) { // 删除文件
		if (file.exists() && !file.isDirectory()) {
			return file.delete();
		}
		return false;
	}

	// 得到目录下的文件名
	public static String[] getFileName(String dir) {
		String pathstr = pathStr + File.separator + dir;
		File file = new File(pathstr);
		if (file.exists() && file.isDirectory()) {
			return file.list();
		}
		return null;
	}

	// 创建文件夹-->
	public static String createSDCardDir() {
		ishasCard = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
		if (isHasCard()) {
			if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
				// 创建一个文件夹对象，赋值为外部存储器的目录
				File sdcardDir = Environment.getExternalStorageDirectory();
				// 得到一个路径，内容是sdcard的文件夹路径和名字
				String newPath = sdcardDir.getPath() + "/"	+ "produce" + "/images/";
				File path1 = new File(newPath);
				if (!path1.exists()) {
					// 若不存在，创建目录，可以在应用启动的时候创建
					path1.mkdirs();
				}
				return newPath;
			}
		}
		return "";
	}

	public static boolean saveBitmap(Context context, String bitName, int id) throws IOException {
		String newPath = createSDCardDir();
		if (newPath.equals("")) {
			return false;
		}
		bitmap = BitmapFactory.decodeResource(context.getResources(), id);
		File f = new File(newPath + bitName + ".jpg");
		f.createNewFile();
		FileOutputStream fOut = null;
		try {
			fOut = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
		} catch (FileNotFoundException e) {
			return false;
		} finally {
			if (fOut != null) {
				try {
					fOut.flush();
					fOut.close();
				} catch (IOException e) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean saveBitmaptoPhoto(Context context, String bitName, int id) throws IOException {
		boolean hasCard = false;
		final String CAMERA_IMAGE_BUCKET_NAME = Images.Media.EXTERNAL_CONTENT_URI
				.toString() + "/" + bitName;
		final String CAMERA_IMAGE_BUCKET_ID = String.valueOf(CAMERA_IMAGE_BUCKET_NAME.hashCode());
		File f = new File(CAMERA_IMAGE_BUCKET_NAME);
		bitmap = BitmapFactory.decodeResource(context.getResources(), id);
		ContentValues values = new ContentValues(8);
		String newname = DateFormat.format("yyyy-MM-dd kk.mm.ss", System.currentTimeMillis()).toString();
		values.put(Images.Media.TITLE, bitName);// 名称，随便
		values.put(Images.Media.DISPLAY_NAME, bitName);
		values.put(Images.Media.DESCRIPTION, "test");// 描述，随便
		values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis());// 图像的拍摄时间，显示时根据这个排序
		values.put(Images.Media.MIME_TYPE, "image/jpeg");// 默认为jpg格式
		values.put(Images.Media.ORIENTATION, 0);//
		values.put(Images.Media.SIZE, bitmap.getRowBytes() * bitmap.getHeight());//
		String name = f.getName().toLowerCase();
		values.put(Images.ImageColumns.BUCKET_ID, CAMERA_IMAGE_BUCKET_ID);// id
		values.put(Images.ImageColumns.BUCKET_DISPLAY_NAME, name);

		// 先得到新的URI
		Uri uri = context.getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, values);
		OutputStream outStream =null;
		try {
			// 写入数据
			outStream = context.getContentResolver().openOutputStream(uri);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
			bitmap.recycle();
			hasCard = true;
			return hasCard;
		} catch (Exception e) {
			hasCard = false;
		} finally {
			FileUtil.close(outStream);
		}
		return hasCard;
	}

}
