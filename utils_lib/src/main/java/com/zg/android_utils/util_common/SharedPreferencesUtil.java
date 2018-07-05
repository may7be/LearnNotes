package com.zg.android_utils.util_common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesUtil {

	// 存储的sharedpreferences文件名
	private static final String FILE_NAME = "cnmts_produce_parameters";

	public static void saveData(String key, Object data) {
		saveData(BaseUtil.getContext(), key, data);
	}

	/**
	 * 保存数据到文件
	 * 
	 * @param context
	 * @param key
	 * @param data
	 */
	public static void saveData(Context context, String key, Object data) {

		String type = data.getClass().getSimpleName();
		SharedPreferences sharedPreferences = getSharedPreferences();
		Editor editor = sharedPreferences.edit();

		if ("Integer".equals(type)) {
			editor.putInt(key, (Integer) data);
		} else if ("Boolean".equals(type)) {
			editor.putBoolean(key, (Boolean) data);
		} else if ("String".equals(type)) {
			editor.putString(key, (String) data);
		} else if ("Float".equals(type)) {
			editor.putFloat(key, (Float) data);
		} else if ("Long".equals(type)) {
			editor.putLong(key, (Long) data);
		}

		editor.apply();
	}

	public static Object getData(String key, Object defValue) {
		return getData(BaseUtil.getContext(), key, defValue);
	}

	/**
	 * 从文件中读取数据
	 * 
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static Object getData(Context context, String key, Object defValue) {

		String type = defValue.getClass().getSimpleName();
		SharedPreferences sharedPreferences = getSharedPreferences();

		// defValue为为默认值，如果当前获取不到数据就返回它
		if ("Integer".equals(type)) {
			return sharedPreferences.getInt(key, (Integer) defValue);
		} else if ("Boolean".equals(type)) {
			return sharedPreferences.getBoolean(key, (Boolean) defValue);
		} else if ("String".equals(type)) {
			return sharedPreferences.getString(key, (String) defValue);
		} else if ("Float".equals(type)) {
			return sharedPreferences.getFloat(key, (Float) defValue);
		} else if ("Long".equals(type)) {
			return sharedPreferences.getLong(key, (Long) defValue);
		}

		return null;
	}

	public static boolean findData(Context context, String key, Object defValue) {
		String type = defValue.getClass().getSimpleName();
		SharedPreferences sharedPreferences = getSharedPreferences();

		// defValue为为默认值，如果当前获取不到数据就返回它
		if ("Integer".equals(type)) {
			return sharedPreferences.contains(key);
		} else if ("Boolean".equals(type)) {
			return sharedPreferences.contains(key);
		} else if ("String".equals(type)) {
			return sharedPreferences.contains(key);
		} else if ("Float".equals(type)) {
			return sharedPreferences.contains(key);
		} else if ("Long".equals(type)) {
			return sharedPreferences.contains(key);
		}

		return true;
	}

	public static SharedPreferences getSharedPreferences() {
		return BaseUtil.getContext().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
	}
}