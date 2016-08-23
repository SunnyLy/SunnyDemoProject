package com.het.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.view.View;

/**
 * 
 图片工具类
 */
public class BitmapUtils {

	/**
	 * 图片旋转
	 * 
	 * @param bitmap
	 *            图片
	 * @param degree
	 *            旋转角度
	 * @return
	 */
	public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
		Matrix m = new Matrix();
		m.setRotate(degree); // 旋转angle度
		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), m, true);// 从新生成图片
	}

	/**
	 * 保存图片到本地
	 * 
	 * @param path
	 *            存储路径
	 * @param bm
	 *            图片
	 * @return
	 */
	public static void saveToLocal(String path, Bitmap bm) {
		/* String path = "/sdcard/mm.jpg"; */
		try {
			FileOutputStream fos = new FileOutputStream(path);
			bm.compress(CompressFormat.PNG, 90, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 获取缩略图
	 * 
	 * @param imagePath
	 * 
	 * @param width
	 * 
	 * @param height
	 * 
	 * @return
	 */
	public static Bitmap getThumbnailImage(String imagePath, int width,
			int height) {
		Bitmap bitmap = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		options.inJustDecodeBounds = false;
		int h = options.outHeight;
		int w = options.outWidth;
		int beWidth = w / width;
		int beHeight = h / height;
		int be = 1;
		if (beWidth < beHeight) {
			be = beWidth;
		} else {
			be = beHeight;
		}
		if (be <= 0) {
			be = 1;
		}
		options.inSampleSize = be;
		bitmap = BitmapFactory.decodeFile(imagePath, options);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
				ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
		return bitmap;
	}

	/**
	 * 读取图片属性：旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return degree旋转的角度
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * 图片缩放
	 * 
	 * @param bitmap
	 *            源图片
	 * @param maxWidth
	 *            图片缩放的最大宽度
	 * @param maxHeight
	 *            图片缩放的最大高度
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {

		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) maxWidth / width);
		float scaleHeight = ((float) maxHeight / height);

		float scale = 1f;
		if (scaleHeight <= 0) {
			scale = scaleWidth;
		} else if (scaleWidth <= 0) {
			scale = scaleHeight;
		} else
			scale = Math.min(scaleWidth, scaleHeight);
		matrix.postScale(scale, scale);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
				matrix, true);
		return newbmp;
	}

	/**
	 * 获取图片尺寸--分辨
	 * 
	 * @param context
	 * @param drawable
	 * @return
	 */
	public static int getImageHeight(final Context context, final int drawable) {
		int bg_height = 0;
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				drawable);
		bg_height = bmp.getHeight();
		bmp.recycle();
		return bg_height;
	}

	/**
	 * 图片回收
	 * 
	 * @param bm
	 */
	public static void recycleBitmap(Bitmap bm) {
		if (bm != null) {
			bm.recycle();
			bm = null;
			System.gc();
		}
	}

	/**
	 * 半径为roundPixels的圆形图�?图片源为正方�?
	 * 
	 * @param bitmap
	 * @param roundPixels
	 * @return
	 */
	public static Bitmap getRoundCornerHeadHetImage(final Context context,
			final Bitmap bitmap) {

		int h = bitmap.getHeight();
		// 创建和原始图片一样大小位图
		Bitmap roundConcerImage = Bitmap.createBitmap(h, h, Config.ARGB_8888);
		// 创建带有位图roundConcerImage的画布
		Canvas canvas = new Canvas(roundConcerImage);
		// 创建画笔
		Paint paint = new Paint();
		// 创建和原始图片一样大小的矩形
		int real_round = h / 2;
		Rect rect = new Rect(0, 0, h, h);
		RectF rectF = new RectF(rect);
		// 去锯齿
		paint.setAntiAlias(true);
		// 画一个和原始图片一样大小的圆角矩形
		canvas.drawRoundRect(rectF, real_round, real_round, paint);
		// 设置相交模式
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		// 把图片画到矩形去
		canvas.drawBitmap(bitmap, null, rect, paint);
		return roundConcerImage;
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return 圆形图片
	 */
	public static Bitmap getRoundBitmap(Bitmap bitmap) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		return output;
	}

	/**
	 * 从文件解析出Bitmap格式的图片
	 * 
	 * @param path
	 * @param maxWidth
	 * @param maxHeight
	 * @return
	 */
	public static Bitmap decodeFile(String path, int maxWidth, int maxHeight,
			Bitmap.Config config) {
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, options);
			FileInputStream fInputStream = new FileInputStream(path);
			return decode(fInputStream, maxWidth, maxHeight, options, config);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static Bitmap decodeFile(String path, int maxWidth, int maxHeight) {
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, options);
			FileInputStream fInputStream = new FileInputStream(path);
			return decode(fInputStream, maxWidth, maxHeight, options, null);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static Bitmap decodeFromResources(Context c, int id, int maxWidth,
			int maxHeight, Config config) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(c.getResources(), id, options);
		return decode(c.getResources().openRawResource(id), maxWidth,
				maxHeight, options, config);
	}

	public static Bitmap decodeFromResources(Context c, int id, int maxWidth,
			int maxHeight) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(c.getResources(), id, options);
		return decode(c.getResources().openRawResource(id), maxWidth,
				maxHeight, options, null);
	}

	public static Bitmap decodeFile(String path) {
		return decodeFile(path, 0, 0, null);
	}

	public static Bitmap decodeFile(String path, int inSimpleSize) {
		// return decodeFile(path, 0, 0, null);
		BitmapFactory.Options options = new Options();
		options.inSampleSize = inSimpleSize;
		options.inJustDecodeBounds = false;
		// if (config != null)
		options.inPreferredConfig = Config.RGB_565;// Bitmap.Config.ARGB_4444;
		options.inPurgeable = true;
		options.inInputShareable = true;
		return BitmapFactory.decodeFile(path, options);
	}

	public static Bitmap decodeFile(String path, Bitmap.Config config) {
		return decodeFile(path, 0, 0, config);
	}

	public static Bitmap decodeFromResources(Context c, int id) {
		return decodeFromResources(c, id, 0, 0, null);
	}

	public static Bitmap decodeFromResources(Context c, int id,
			Bitmap.Config config) {
		return decodeFromResources(c, id, 0, 0, config);
	}

	private static Bitmap decode(InputStream in, int maxWidth, int maxHeight,
			BitmapFactory.Options options, Bitmap.Config config) {
		double ratio = 1D;
		if (maxWidth > 0 && maxHeight <= 0) {
			// 限定宽度，高度不做限制
			ratio = Math.ceil(options.outWidth / maxWidth);
		} else if (maxHeight > 0 && maxWidth <= 0) {
			// 限定高度，不限制宽度
			ratio = Math.ceil(options.outHeight / maxHeight);
		} else if (maxWidth > 0 && maxHeight > 0) {
			// 高度和宽度都做了限制，这时候我们计算在这个限制内能容纳的最大的图片尺寸，不会使图片变形
			double widthRatio = Math.ceil(options.outWidth / maxWidth);
			double heightRatio = (double) Math.ceil(options.outHeight
					/ maxHeight);
			ratio = widthRatio > heightRatio ? widthRatio : heightRatio;
		}
		if (ratio > 1) {
			options.inSampleSize = (int) Math.round(ratio);
		}
		options.inJustDecodeBounds = false;
		// if (config != null)
		options.inPreferredConfig = config != null ? config : Config.RGB_565;// Bitmap.Config.ARGB_4444;
		/*
		 * 如果 inPurgeable
		 * 设为True的话表示使用BitmapFactory创建的Bitmap用于存储Pixel的内存空间在系统内存不足时可以被回收
		 * ，在应用需要再次访问Bitmap的Pixel时（如绘制Bitmap或是调用getPixel），系统会再次调用BitmapFactory
		 * decoder重新生成Bitmap的Pixel数组.为了能够重新解码图像，bitmap要能够访问存储Bitmap的原始数据.
		 * 在inPurgeable为false时表示创建的Bitmap的Pixel内存空间不能被回收
		 * ，这样BitmapFactory在不停decodeByteArray创建新的Bitmap对象
		 * ，不同设备的内存不同，因此能够同时创建的Bitmap个数可能有所不同
		 * ，200个bitmap足以使大部分的设备重新OutOfMemory错误.
		 * 当isPurgable设为true时，系统中内存不足时，可以回收部分Bitmap占据的内存空间，这时一般不会出现OutOfMemory
		 * 错误.
		 */
		options.inPurgeable = true;
		/*
		 * inInputShareable与inPurgeable一起使用，如果inPurgeable为false那该设置将被忽略，如果为true，
		 * 那么它可以决定位图是否能够共享一个指向数据源的引用，或者是进行一份拷贝；
		 */
		options.inInputShareable = true;
		Bitmap bitmap = BitmapFactory.decodeStream(in, null, options);
		System.out.println("old width:" + options.outWidth + ",old height:"
				+ options.outHeight + ", new width :" + bitmap.getWidth()
				+ ",new height:" + bitmap.getHeight());
		return bitmap;
	}

	/**
	 * 获取屏幕截图
	 * 
	 * @param v
	 * @return
	 */
	public static Bitmap getScreenClipBitmap(View v) {
		v.clearFocus();
		v.setPressed(false);
		boolean willNotCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);
		// Reset the drawing cache background color to fully transparent
		// for the duration of this operation
		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);
		if (color != 0) {
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();
		Bitmap cacheBitmap = v.getDrawingCache();
		if (cacheBitmap == null) {
			LogUtils.e("TTTTTTTTActivity", "failed getViewBitmap(" + v + ")"+
					new RuntimeException());
			return viewToBitmap(v, v.getWidth(), v.getHeight());
		}

		Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

		// Restore the view
		v.destroyDrawingCache();
		v.setWillNotCacheDrawing(willNotCache);
		v.setDrawingCacheBackgroundColor(color);

		return bitmap;
	}

	public static Bitmap viewToBitmap(View view, int bitmapWidth,
			int bitmapHeight) {
		Bitmap bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight,
				Bitmap.Config.ARGB_8888);
		view.draw(new Canvas(bitmap));

		return bitmap;
	}

	/**
	 * 
	 * @param sentBitmap
	 *            源图片
	 * @param radius
	 *            模糊度
	 * @return
	 */
	public static Bitmap createBlurBitmap(Bitmap sentBitmap, int radius) {
		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
		if (radius < 1) {
			return (null);
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		int[] pix = new int[w * h];
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);
		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;
		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];
		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int dv[] = new int[256 * divsum];
		for (i = 0; i < 256 * divsum; i++) {
			dv[i] = (i / divsum);
		}
		yw = yi = 0;
		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;
		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;
			for (x = 0; x < w; x++) {
				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;
				sir = stack[i + radius];
				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];
				rbs = r1 - Math.abs(i);
				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
						| (dv[gsum] << 8) | dv[bsum];
				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;
				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];
				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];
				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];
				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];
				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];
				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;
				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];
				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];
				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];
				yi += w;
			}
		}
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}

	/**
	 * create thumb bitmap
	 * 
	 */
	public static byte[] createThumbBitmap(final Bitmap bmp,
			final boolean needRecycle) {
		int i;
		int j;
		if (bmp.getHeight() > bmp.getWidth()) {
			i = bmp.getWidth();
			j = bmp.getWidth();
		} else {
			i = bmp.getHeight();
			j = bmp.getHeight();
		}

		Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.RGB_565);
		Canvas localCanvas = new Canvas(localBitmap);

		while (true) {
			localCanvas.drawBitmap(bmp, new Rect(0, 0, i, j), new Rect(0, 0, i,
					j), null);
			if (needRecycle)
				bmp.recycle();
			ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
			localBitmap.compress(Bitmap.CompressFormat.JPEG, 100,
					localByteArrayOutputStream);
			localBitmap.recycle();
			byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
			try {
				localByteArrayOutputStream.close();
				return arrayOfByte;
			} catch (Exception e) {
				// F.out(e);
			}
			i = bmp.getHeight();
			j = bmp.getHeight();
		}
	}

	/**
	 * 
	 高斯模糊
	 */
	public static Bitmap BoxBlurFilter(Bitmap bmp) {
		int width = bmp.getWidth();
		int height = bmp.getHeight();
		int[] inPixels = new int[width * height];
		int[] outPixels = new int[width * height];
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
		for (int i = 0; i < 7; i++) {
			blur(inPixels, outPixels, width, height, 10);
			blur(outPixels, inPixels, height, width, 10);
		}
		blurFractional(inPixels, outPixels, width, height, 10);
		blurFractional(outPixels, inPixels, height, width, 10);
		bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
		// Drawable drawable = new BitmapDrawable(bitmap);
		return bitmap;
	}

	public static void blur(int[] in, int[] out, int width, int height,
			float radius) {
		int widthMinus1 = width - 1;
		int r = (int) radius;
		int tableSize = 2 * r + 1;
		int divide[] = new int[256 * tableSize];

		for (int i = 0; i < 256 * tableSize; i++)
			divide[i] = i / tableSize;

		int inIndex = 0;

		for (int y = 0; y < height; y++) {
			int outIndex = y;
			int ta = 0, tr = 0, tg = 0, tb = 0;

			for (int i = -r; i <= r; i++) {
				int rgb = in[inIndex + clamp(i, 0, width - 1)];
				ta += (rgb >> 24) & 0xff;
				tr += (rgb >> 16) & 0xff;
				tg += (rgb >> 8) & 0xff;
				tb += rgb & 0xff;
			}

			for (int x = 0; x < width; x++) {
				out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16)
						| (divide[tg] << 8) | divide[tb];

				int i1 = x + r + 1;
				if (i1 > widthMinus1)
					i1 = widthMinus1;
				int i2 = x - r;
				if (i2 < 0)
					i2 = 0;
				int rgb1 = in[inIndex + i1];
				int rgb2 = in[inIndex + i2];

				ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
				tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
				tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
				tb += (rgb1 & 0xff) - (rgb2 & 0xff);
				outIndex += height;
			}
			inIndex += width;
		}
	}

	public static void blurFractional(int[] in, int[] out, int width,
			int height, float radius) {
		radius -= (int) radius;
		float f = 1.0f / (1 + 2 * radius);
		int inIndex = 0;

		for (int y = 0; y < height; y++) {
			int outIndex = y;

			out[outIndex] = in[0];
			outIndex += height;
			for (int x = 1; x < width - 1; x++) {
				int i = inIndex + x;
				int rgb1 = in[i - 1];
				int rgb2 = in[i];
				int rgb3 = in[i + 1];

				int a1 = (rgb1 >> 24) & 0xff;
				int r1 = (rgb1 >> 16) & 0xff;
				int g1 = (rgb1 >> 8) & 0xff;
				int b1 = rgb1 & 0xff;
				int a2 = (rgb2 >> 24) & 0xff;
				int r2 = (rgb2 >> 16) & 0xff;
				int g2 = (rgb2 >> 8) & 0xff;
				int b2 = rgb2 & 0xff;
				int a3 = (rgb3 >> 24) & 0xff;
				int r3 = (rgb3 >> 16) & 0xff;
				int g3 = (rgb3 >> 8) & 0xff;
				int b3 = rgb3 & 0xff;
				a1 = a2 + (int) ((a1 + a3) * radius);
				r1 = r2 + (int) ((r1 + r3) * radius);
				g1 = g2 + (int) ((g1 + g3) * radius);
				b1 = b2 + (int) ((b1 + b3) * radius);
				a1 *= f;
				r1 *= f;
				g1 *= f;
				b1 *= f;
				out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
				outIndex += height;
			}
			out[outIndex] = in[width - 1];
			inIndex += width;
		}
	}

	public static int clamp(int x, int a, int b) {
		return (x < a) ? a : (x > b) ? b : x;
	}
}
