package com.tongyuan.android.zhiquleyuan.utils;

import android.graphics.Color;

import java.util.Random;

public class ColorUtil {
	/**
	 * 生成漂亮的颜色
	 * @return
	 */
	public static int randomBeautifulColor(){
		Random random = new Random();
		int red = random.nextInt(200);
		int green = random.nextInt(200);
		int blue = random.nextInt(200);
		return Color.rgb(red, green, blue);//混合生成一种新的颜色
	}
}
