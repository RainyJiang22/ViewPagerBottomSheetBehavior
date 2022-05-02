package com.base.vpbs.base;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 屏幕 分辨率适配 工具
 * 
 * @author zhongwenqi
 */
public class ScreenUtil {
	private static int sScreenWidth;
	private static int sScreenHeight;
	private static float sScale;
	private static int statusBarHeight;
	private static int sScaledTouchSlop;

	/**
	 * 获取手机的屏幕的密度
	 * 
	 * @param context
	 */
	public static void init(Context context) {
		if (context != null) {
			DisplayMetrics displayMetrics = context.getResources()
					.getDisplayMetrics();
			sScale = displayMetrics.density;
			sScreenWidth = displayMetrics.widthPixels;
			sScreenHeight = displayMetrics.heightPixels;
		}
	}

	/**
	 * @deprecated might no init, use {@link #getScreenWidth(Context)} instead
	 * @return
	 */
	public static int getScreenWidth() {
		return sScreenWidth;
	}

	/**
	 * 
	 * @param context
	 * @return 屏幕宽带（不含按钮）
	 */
	public static int getScreenWidth(Context context) {
		if (sScreenWidth <= 0) {
			init(context);
		}
		return sScreenWidth;
	}

	/**
	 * @deprecated might no init, use {@link #getScreenHeight(Context)} instead
	 * @return
	 */
	public static int getScreenHeight() {
		return sScreenHeight;
	}

	/**
	 * @see #getRealHeight(Context)
	 * @param context
	 * @return 屏幕高度（不含按钮的高度）
	 */
	public static int getScreenHeight(Context context) {
		if (sScreenHeight <= 0) {
			init(context);
		}
		return sScreenHeight;
	}

	public static int getRealWidth(Context context) {
		return getScreenWidth(context);
	}

	/**
	 * 获取屏幕高度（含按钮）
	 *
	 * @param context
	 * @return
	 */
	public static int getRealHeight(Context context) {
		if (sReadHeight <= 0) {
			initRealSize(context);
		}
		return sReadHeight;
	}
	
	public static int getNavBarHeight(Context context) {
		if (sNavBarHeight < 0) {
			initRealSize(context);
		}
		return sNavBarHeight;
	}
	
	private static int sReadHeight;
	private static int sNavBarHeight =  -1;

	public static void initRealSize(Context context) {
		if (context != null) {
			WindowManager wm = (WindowManager) context
					.getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			try {
				Class<?> disPlayClass = Class.forName("android.view.Display");
				Point realSize = new Point();
				Method method = disPlayClass.getMethod("getRealSize", Point.class);
				method.invoke(display, realSize);
				sReadHeight = realSize.y;
			} catch (Exception e) {
				sReadHeight = getScreenHeight(context);
			}
			sNavBarHeight = sReadHeight - getScreenHeight(context);
		}
	}

	/**
	 * @deprecated might no init, use {@link #dip2px(Context, float)} instead
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(float dipValue) {
		return Math.round(dipValue * sScale);
	}

	/**
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		if (sScale <= 0) {
			init(context);
		}
		return Math.round(dipValue * sScale);
	}

	/**
	 * sp 转 px
	 *
	 * @param spValue
	 *            sp大小
	 * @return 像素值
	 */
	public static int sp2px(Context context, float spValue) {
		if (sScale <= 0) {
			init(context);
		}
		return (int) (sScale * spValue);
	}

	public static int dip2pxForXH(Context context, float dipValue) {
		if (sScale <= 0) {
			init(context);
		}
		return Math.round(dipValue * sScale / 2);
	}

	/**
	 * @param dipValue
	 * @return
	 */
	public static float dip2pxF(Context context, float dipValue) {
		if (sScale <= 0) {
			init(context);
		}
		return dipValue * sScale;
	}

	public static float dip2pxForXHF(Context context, float dipValue) {
		if (sScale <= 0) {
			init(context);
		}
		return dipValue * sScale / 2;
	}

	/**
	 * @deprecated might no init, use {@link #px2dip(Context, float)} instead
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(float pxValue) {
		return Math.round(pxValue / sScale);
	}

	public static int px2dip(Context context, float pxValue) {
		if (sScale <= 0) {
			init(context);
		}
		return Math.round(pxValue / sScale);
	}

	public static int getStatusBarHeight(Context context) {
		if (statusBarHeight <= 0) {
			Class<?> c = null;
			Object obj = null;
			Field field = null;
			int x = 0;
			try {
				c = Class.forName("com.android.internal.R$dimen");
				obj = c.newInstance();
				field = c.getField("status_bar_height");
				x = Integer.parseInt(field.get(obj).toString());
				statusBarHeight = context.getResources().getDimensionPixelSize(
						x);
			} catch (Exception ignore) {
//				e1.printStackTrace();
			}
		}
		return statusBarHeight;
	}

	public static int getScaledTouchSlop(Context context) {
		if (sScaledTouchSlop <= 0) {
			sScaledTouchSlop = ViewConfiguration.get(context)
					.getScaledTouchSlop();
		}
		return sScaledTouchSlop;
	}
}
