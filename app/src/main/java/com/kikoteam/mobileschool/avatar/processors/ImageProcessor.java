package com.kikoteam.mobileschool.avatar.processors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

public class ImageProcessor {

    public static Bitmap imageMerger(Bitmap base, Bitmap added_detail) {
        Bitmap result = Bitmap.createBitmap(base.getWidth(), base.getHeight(), base.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(base, new Matrix(), null);
        canvas.drawBitmap(added_detail, 0, 0, null);
        return result;
    }

    public static Bitmap getBitmapFromView(View view) {
        Drawable optionDrawable = ((ImageView) view).getDrawable();
        BitmapDrawable optionBitMapDrawable = (BitmapDrawable) optionDrawable;
        if (optionBitMapDrawable.getBitmap() != null) {
            return optionBitMapDrawable.getBitmap();
        }
        return null;
    }

}
