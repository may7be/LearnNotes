package com.zg.android_utils.util_common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class BitmapUtil {

    private static final String TAG = "BitmapUtil";

    public static Bitmap base64ToBitmap(String base64String) {

        byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        return bitmap;

    }

    public static Bitmap base64ToBitmapLarge(String base64String) {

        byte[] bytes = Base64.decode(base64String, Base64.DEFAULT);

        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        Matrix matrix = new Matrix();

        Bitmap resizeBmp;
        if (bitmap.getWidth() < 300) {
            matrix.postScale(3f, 3f); // 长和宽放大缩小的比例
            Bitmap bitmap1 = centerSquareScaleBitmap(bitmap, 250);
            resizeBmp = Bitmap.createBitmap(bitmap1, 0, 0, bitmap1.getWidth(), bitmap1.getHeight(), matrix, true);
        } else {
            matrix.postScale(2f, 2f); // 长和宽放大缩小的比例
            resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        return resizeBmp;

    }

    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }

        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg > edgeLength && heightOrg > edgeLength) {
            // 压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg);
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }

            // 从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }

        return result;
    }

    /**
     * 将base64字符解码保存文件
     *
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void decoderBase64File(String base64Code, String targetPath) {
        byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(targetPath);
            out.write(buffer);
        } catch (FileNotFoundException e) {
            Log.e(BitmapUtil.class.getSimpleName(), e.getMessage());
        } catch (IOException e) {
            Log.e(BitmapUtil.class.getSimpleName(), e.getMessage());
        } finally {
            FileUtil.close(out);
        }
    }

    public static String encodeBase64File(String path, int imageSize) {
        ByteArrayOutputStream output = null;
        try {
            Bitmap bmp = compressBitmap(path, imageSize);
            if (bmp != null) {
                output = new ByteArrayOutputStream();
                bmp.compress(CompressFormat.JPEG, 70, output);
                bmp.recycle();

                byte[] result = output.toByteArray();
                return Base64.encodeToString(output.toByteArray(), Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(output);
        }
        return "";
    }

    public static String encodeBase64VideoFile(String path) {
        FileInputStream inp = null;
        try {
            File file = new File(path);
            // long fileSize = file.length();
            inp = new FileInputStream(file);
            // byte[] data = new byte[(int) fileSize];
            byte[] data = new byte[inp.available()];
            inp.read(data);
            return Base64.encodeToString(data, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            FileUtil.close(inp);
        }
        return "";

    }

    public static String encodeBase64File(Bitmap bitmap) {
        ByteArrayOutputStream output = null;
        try {
            if (bitmap != null) {
                output = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 70, output);
                bitmap.recycle();
                byte[] result = output.toByteArray();
                return Base64.encodeToString(output.toByteArray(), Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtil.close(output);
        }
        return "";
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = null;
        byte[] result = null;
        try {
            output = new ByteArrayOutputStream();
            bmp.compress(CompressFormat.JPEG, 40, output);
            if (needRecycle) {
                bmp.recycle();
            }
            result = output.toByteArray();
        } catch (Exception e) {
        } finally {
            FileUtil.close(output);
        }
        return result;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, int quality, final boolean needRecycle) {
        if (quality <= 0 || quality > 100) {
            bmpToByteArray(bmp, needRecycle);
        }
        ByteArrayOutputStream output = null;
        byte[] result = null;
        try {
            output = new ByteArrayOutputStream();
            bmp.compress(CompressFormat.JPEG, quality, output);
            if (needRecycle) {
                bmp.recycle();
            }
            result = output.toByteArray();
        } catch (Exception e) {
        } finally {
            FileUtil.close(output);
        }
        return result;
    }

    /**
     * 从RES中读取资源图片，可以解决OOM问题
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitmap(Context context, int resId) {
        return readBitmap(context, resId, Config.RGB_565);
    }

    public static BitmapDrawable readBitmapDrawable(Context context, int resId, Config cfg) {
        BitmapDrawable drawable = new BitmapDrawable(readBitmap(context, resId, cfg));
        return drawable;
    }

    public static Bitmap readBitmap(Context context, int resId, Config cfg) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = cfg;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = null;
        Bitmap bitmap = null;
        // 获取资源图片
        try {
            is = context.getResources().openRawResource(resId);
            bitmap = BitmapFactory.decodeStream(is, null, opt);
        } catch (Exception e1) {
        } finally {
            FileUtil.close(is);
        }
        return bitmap;
    }

    public static Bitmap readBitmap(Context context, InputStream stream) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        return BitmapFactory.decodeStream(stream, null, opt);
    }

    public static Bitmap readBitmap(Context context, byte[] byteArray) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opt);
    }

    public static Bitmap readBitmap(String path) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        FileInputStream is = null;
        try {
            is = new FileInputStream(path);
            return BitmapFactory.decodeStream(is, null, opt);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } finally {
            FileUtil.close(is);
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String filePath) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 640, 640);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static Bitmap compressBitmap(String filePath, int imageSize) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = getinSampleSize(options, imageSize);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    public static int getinSampleSize(BitmapFactory.Options options, int imageSize) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        float reqHeight = imageSize;
        float reqWidth = imageSize;
        int inSampleSize = 1;

        if (height <= width && height > reqHeight) {
            inSampleSize = Math.round(height / reqHeight);
            inSampleSize = (int) Math.round(inSampleSize * 1.5);
        } else if (width < height && width > reqWidth) {
            inSampleSize = Math.round(width / reqWidth);
            inSampleSize = (int) Math.round(inSampleSize * 1.5);
        }

        return inSampleSize;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    /**
     * 根据给定路径，读取并按照一定比例放缩图片，之后获取图片创建日期作为水印文字，添加到图片上，并返回
     *
     * @param context
     * @param options  图片防缩比例
     * @param filePath 本地文件路径
     * @return Bitmap 放缩后并添加日期水印的图片
     */
    public static Bitmap getWaterBitmap(Context context, BitmapFactory.Options options, String filePath) {
        options.inJustDecodeBounds = false;
        Bitmap b;
        try {
            b = BitmapFactory.decodeFile(filePath, options);
            ExifInterface exifInterface = new ExifInterface(filePath);
            String waterTxt = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
            if (waterTxt == null || waterTxt.isEmpty()) {
                waterTxt = getModifyTime(filePath);
            }
            return drawText(context, b, waterTxt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 想bitmap文件中添加水印后并返回bitmap
     *
     * @param context
     * @param bitmap  添加水印文字的图片
     * @param txt     水印文字
     * @return
     */
    public static Bitmap drawText(Context context, Bitmap bitmap, String txt) {
        int imgWidth = bitmap.getWidth();
        int imgHeith = bitmap.getHeight();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY); // 设置字体颜色
        paint.setAntiAlias(true);   //防锯齿
        paint.setDither(true);      //防抖动
        final float scale = context.getResources().getDisplayMetrics().density;
        int textSize = 150;
        Rect bounds = new Rect();
        while (textSize > 0) {
            paint.setTextSize((int) (textSize * scale + 0.5f));
            paint.getTextBounds(txt, 0, txt.length(), bounds);
            if (bounds.width() < imgWidth / 2 && bounds.height() < imgHeith / 2) {
                break;
            }
            textSize -= 20;
        }
        int paddingLeft = (int) (imgWidth * 0.95f) - bounds.width();
        int paddingTop = (int) (imgHeith * 0.95f) - bounds.height();

        Config bitmapConfig = bitmap.getConfig();

        paint.setDither(true); // 获取跟清晰的图像采样
        paint.setFilterBitmap(true);// 过滤一些
        if (bitmapConfig == null) {
            bitmapConfig = Config.ARGB_8888;
        }
        bitmap = bitmap.copy(bitmapConfig, true);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawText(txt, paddingLeft, paddingTop, paint);
        return bitmap;
    }

    public static String getModifyTime(String imgPath) {
        File file = new File(imgPath);
        Date modifyDate = new Date(file.lastModified());
        String formatType = "yyyy/MM/dd HH:mm:ss";
        return new SimpleDateFormat(formatType).format(modifyDate);
    }

    /**
     * 获取视频的缩略图 先�?过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图�?
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的�?，这样会节省内存�?
     *
     * @param videoPath 视频的路�?
     * @param width     指定输出视频缩略图的宽度
     * @param height    指定输出视频缩略图的高度�?
     * @param kind      参照MediaStore.Images.Thumbnails类中的常量MINI_KIND和MICRO_KIND�?
     *                  其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        System.out.println("w" + bitmap.getWidth());
        System.out.println("h" + bitmap.getHeight());
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        return bitmap;
    }

    /**
     * 通过网络视频url生成bitmap
     *
     * @param filePath
     * @param kind
     * @return
     */
    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            if (filePath.startsWith("http://")
                    || filePath.startsWith("https://")
                    || filePath.startsWith("widevine://")) {
                //下列代码执行时间平均500ms,需要子线程
                retriever.setDataSource(filePath, new Hashtable<String, String>());
            } else {
                retriever.setDataSource(filePath);
            }
            bitmap = retriever.getFrameAtTime(-1);
        } catch (IllegalArgumentException ex) {
            // Assume this is a corrupt video file
            ex.printStackTrace();
        } catch (RuntimeException ex) {
            // Assume this is a corrupt video file.
            ex.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (RuntimeException ex) {
                // Ignore failures while cleaning up.
                ex.printStackTrace();
            }
        }

        if (bitmap == null) return null;

        if (kind == MediaStore.Images.Thumbnails.MINI_KIND) {
            // Scale down the bitmap if it's too large.
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int max = Math.max(width, height);
            if (max > 512) {
                float scale = 512f / max;
                int w = Math.round(scale * width);
                int h = Math.round(scale * height);
                bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
            }
        } else if (kind == MediaStore.Images.Thumbnails.MICRO_KIND) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                    96,
                    96,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    /**
     * 读取图片属�?：旋转的角度
     *
     * @param path 图片绝对路径
     * @return degree旋转的角�?
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation =
                    exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
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

    public static byte[] imageFileToUploadByteArray(String filePath, int imageSize) {
        Bitmap bitmap = BitmapUtil.compressBitmap(filePath, imageSize);
        Bitmap resizedBitmap = getResizedBitmap(filePath, bitmap);
        return bmpToByteArray(resizedBitmap, true);
    }

    public static Bitmap getResizedBitmap(String filePath, Bitmap bitmap) {
        int degree = BitmapUtil.readPictureDegree(filePath);
        Bitmap resizedBitmap = null;
        if (degree == 0) {
            resizedBitmap = bitmap;
        } else {
            // 旋转图片
            Matrix matrix = new Matrix();
            matrix.postRotate(degree);
            // 创建新的图片
            resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            if (bitmap != resizedBitmap) {
                bitmap.recycle();
            }
        }
        return resizedBitmap;
    }

    public static Bitmap createBitmap(String filePath) {
        Bitmap bitmap = BitmapUtil.decodeSampledBitmapFromFile(filePath);
        Bitmap resizedBitmap = getResizedBitmap(filePath, bitmap);
        return resizedBitmap;
    }

}
