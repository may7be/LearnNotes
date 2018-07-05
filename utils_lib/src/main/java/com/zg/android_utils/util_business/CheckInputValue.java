package com.zg.android_utils.util_business;

import android.content.Context;

import com.zg.android_utils.util_common.ToastUtil;

/**
 * 检测频率额开度值
 * 
 * @author xin.zhang
 * 
 */
public class CheckInputValue {
	// 密度 频率
	public static boolean checkInputValueDensity(Context context, int type, int type2, String Density) {
		// type2 0是密度 1 是频率 2 流量 3 开度
		if (type == 1&&type2==1) {
			if ("".equals(Density)) {
				ToastUtil.showToast(context, "请输入合适的值");
				return false;
			}
			float min = 1.0f;
			float max = 1.9f;
			String str = "密度";
			String unit = "g/cm3";
			float value = Float.parseFloat(Density);
			if (value < min || value > max) {
				ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
				return false;
			}

		} else if (type == 1&&type2==2) {
		 
				if ("".equals(Density)) {
					ToastUtil.showToast(context, "请输入合适的值");
					return false;
				}
				float min = 5.0f;
				float max = 50.0f;
				String str = "频率";
				String unit = "Hz";
				float value = Float.parseFloat(Density);
				if (value < min || value > max) {
					ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
					return false;
				 
			}
		}else if (type==2&&type2==1){
				if ("".equals(Density)) {
					ToastUtil.showToast(context, "请输入合适的值");
					return false;
				}
				float min = 0.0f;
				float max = 100f;
				String str = "开度";
				String unit = "%";
				float value = Float.parseFloat(Density);
				if (value < min || value > max) {
					ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
					return false;
				}
		}else if (type==2&&type2==2){
			if ("".equals(Density)) {
				ToastUtil.showToast(context, "请输入合适的值");
				return false;
			}
			float min = 0.0f;
			float max = 200f;
			String str = "流量";
			String unit = "m3";
			float value = Float.parseFloat(Density);
			if (value < min || value > max) {
				ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
				return false;
			}
	
			
		}
		return true;
	}

	// 频率
	public static boolean checkInputValueRate(Context context, String rate) {

		if ("".equals(rate)) {
			ToastUtil.showToast(context, "请输入合适的值");
			return false;
		}
		int min = 5;
		int max = 50;
		String str = "频率";
		String unit = "Hz";
		float value = Float.parseFloat(rate);
		if (value < min || value > max) {
			ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
			return false;
		}
		return true;

	}

	// 开度
	public static boolean checkInputValueOpen(Context context, String valueOpen) {

		if ("".equals(valueOpen)) {
			ToastUtil.showToast(context, "请输入合适的值");

			return false;
		}
		if (!valueOpen.matches("^\\d+$$")) {
			ToastUtil.showToast(context, "请输入整数");
			return false;
		}

		int min = 0;
		int max = 100;
		String str = "开度";
		String unit = "%";
		float value = Float.parseFloat(valueOpen);
		if (value < min || value > max) {
			ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
			return false;
		}

		return true;

	}

	// 高低液位
	public static boolean checkInputValueLowHight(Context context, String low, String hight, double bucketHeight) {
		if ("".equals(low)) {
			ToastUtil.showToast(context, "请输入合适低液位的值");
			return false;
		}
		if ("".equals(hight)) {
			ToastUtil.showToast(context, "请输入合适高液位的值");
			return false;
		}
		int min = 0;
		double max = bucketHeight;
		String str = "低液位";
		String strh = "高液位";
		String unit = "m";
		double value = Double.parseDouble(low);
		if (value < min || value > max) {
			ToastUtil.showToast(context, "请输入合适的" + str + "值 范围" + min + "-" + max + unit);
			return false;
		}
		double hightvalue = Double.parseDouble(hight);
		if (hightvalue < min || hightvalue > max) {
			ToastUtil.showToast(context, "请输入合适的" + strh + "值 范围" + min + "-" + max + unit);
			return false;
		}

		if (hightvalue < value) {
			ToastUtil.showToast(context, "高液位应大于低液位");
			return false;
		}
		return true;

	}
	

}
