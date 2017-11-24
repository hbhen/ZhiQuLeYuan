package com.tongyuan.android.zhiquleyuan.utils;


/**
 * 存放一些比较杂的工具方法
 * @author lxj
 *
 */
public class CommonUtil {
	public static String getString(int resId){
		return ZQLYApp.context.getResources().getString(resId);
	}
	
	public static String[] getStringArray(int resId){
		return ZQLYApp.context.getResources().getStringArray(resId);
	}
	/**
	 * 获取dimens中的资源,但是获取的数值是将dp转换为px之后的值
	 * @param resId
	 * @return
	 */
	public static int getDimens(int resId){
		return ZQLYApp.context.getResources().getDimensionPixelSize(resId);
	}
}
