package com.tongyuan.android.zhiquleyuan.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtil {
	
	public static GradientDrawable generateDrawable(){
		GradientDrawable drawable = new GradientDrawable();
		drawable.setShape(GradientDrawable.RECTANGLE);//设置为矩形，默认就是矩形
		drawable.setColor(ColorUtil.randomBeautifulColor());
		drawable.setCornerRadius(65);
		return drawable;
	}
	/**
	 * 使用代码的方式生成状态选择器
	 * @return
	 */
	public static StateListDrawable generateSelector(Drawable pressed,Drawable normal){
		StateListDrawable drawable = new StateListDrawable();
		drawable.addState(new int[]{android.R.attr.state_pressed}, pressed);//设置按下的图片
		drawable.addState(new int[]{}, normal);//设置默认的图片
		
		//添加渐变
		drawable.setEnterFadeDuration(500);
		drawable.setExitFadeDuration(500);
		
		return drawable;
	}
}
