package com.ironz.heros6;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * @author zhoujun
 * @date 19-5-6
 */
public class ImageHelper {

    public static Bitmap handleImageEffect(Bitmap bitmap, float hue, float saturation, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();

        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);

        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum, lum, lum, 1);

        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bmp;
    }


    public static final int TYPE_NEGATIVE = 0;
    public static final int TYPE_OLDPIC = 1;
    public static final int TYPE_RELIEF = 2;

    public static Bitmap handleImagePixelsType(Bitmap bm, int type) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color, colorBefore;
        int r, g, b, a;
        int r1, g1, b1;
        Bitmap bmp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);
        for(int i=0; i<width*height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            switch (type) {
                case TYPE_NEGATIVE:
                    r = 255 - r;
                    g = 255 - g;
                    b = 255 - b;
                    break;
                case TYPE_OLDPIC:
                    r1 = (int)(0.393*r + 0.769*g + 0.189*b);
                    g1 = (int)(0.349*r + 0.686*g + 0.168*b);
                    b1 = (int)(0.272*r + 0.534*g + 0.131*b);
                    r = r1;
                    g = g1;
                    b = b1;
                    break;
                case TYPE_RELIEF:
                    // todo 浮雕效果算处理算法不太理解
                    if(i < 1) {
                        continue;
                    } else {
                        colorBefore = oldPx[i - 1];
                        a = Color.alpha(colorBefore);
                        r = Color.red(colorBefore);
                        g = Color.green(colorBefore);
                        b = Color.blue(colorBefore);

                        color = oldPx[i];
                        r1 = Color.red(color);
                        g1 = Color.green(color);
                        b1 = Color.green(color);
                        r = r - r1 + 127;
                        g = g - g1 + 127;
                        b = b - b1 + 127;
                    }
                    break;
                default:
                    break;
            }

            if(r > 255) {
                r = 255;
            } else if(r < 0) {
                r = 0;
            }
            if(g > 255) {
                g = 255;
            } else if(g < 0) {
                g = 0;
            }
            if(b > 255) {
                b = 255;
            } else if(b < 0) {
                b = 0;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
}
