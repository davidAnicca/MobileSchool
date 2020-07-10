package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.graphics.*;
import android.view.View;
import android.widget.ImageView;

import com.kikoteam.mobileschool.R;

import java.util.ArrayList;
import java.util.List;

public class ImageMerger extends AppCompatActivity {

    List<Bitmap> options = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_merger);

        ImageView base = findViewById(R.id.base);
        base.setImageResource(R.drawable.test_base);

        loadOptions();
        showOptions();


    }


    public void loadOptions() {
        this.options.add(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.test_option1));
        this.options.add(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.option2));
        ///ADD OPTIONS HERE

    }

    public void showOptions() {
        ImageView imageOption1 = findViewById(R.id.option1);
        imageOption1.setImageBitmap(this.options.get(0));

        ImageView imageOption2 = findViewById(R.id.option2);
        imageOption2.setImageBitmap(this.options.get(1));

        ///ADD OPTIONS HERE
    }

    public void imageOpening(View view) {

    }

    public static Bitmap imageMerge(Bitmap base, Bitmap added_detail, String type) {
        switch (type) {
            case "test":
                Bitmap result = Bitmap.createBitmap(base.getWidth(), base.getHeight(), base.getConfig());
                Canvas canvas2 = new Canvas(result);
                canvas2.drawBitmap(base, new Matrix(), null);
                canvas2.drawBitmap(added_detail, 0, 1000, null);
                return result;
            case "eyes":
                break;
            case "șamd":
                break;
        }
        return null;
    }

    public void selectOption(View view) {

        ImageView option = (ImageView) view;
        Drawable optionDrawable = ((ImageView) view).getDrawable();

        Bitmap optionBitMap = null;


        BitmapDrawable optionBitMapDrawable = (BitmapDrawable) optionDrawable;
        if (optionBitMapDrawable.getBitmap() != null) {
            optionBitMap = optionBitMapDrawable.getBitmap();
        }

        Bitmap base = BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.test_base);
        Bitmap result = imageMerge(base, optionBitMap, "test");

        ImageView resultShow = findViewById(R.id.base);
        resultShow.setImageBitmap(result);

    }

}


