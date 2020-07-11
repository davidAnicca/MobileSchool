package com.kikoteam.mobileschool.avatar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.graphics.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.kikoteam.mobileschool.R;

import java.util.ArrayList;
import java.util.List;

public class ImageMerger extends AppCompatActivity {

    List<Bitmap> options = new ArrayList<>();
    Bitmap resultBase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_merger);

        ImageView base = findViewById(R.id.base);
        base.setImageResource(R.drawable.square_base_388c91);

        resultBase = BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.square_base_388c91);

        progressBar  = findViewById(R.id.progressForMerge);
        progressBar.setVisibility(View.INVISIBLE);
        loadOptions();
        showOptions();


    }


    public void loadOptions() {
        this.options.add(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.eyes_384f90));
        this.options.add(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.eyes_60901b));
        this.options.add(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.mouth_almost_closed));
        this.options.add(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.mouth_open));
        ///ADD OPTIONS HERE

    }

    public void showOptions() {
        ImageView imageOption1 = findViewById(R.id.eyes1);
        imageOption1.setImageBitmap(this.options.get(0));

        ImageView imageOption2 = findViewById(R.id.eyes2);
        imageOption2.setImageBitmap(this.options.get(1));

        ImageView imageOption3 = findViewById(R.id.mouth1);
        imageOption3.setImageBitmap(this.options.get(2));

        ImageView imageOption4 = findViewById(R.id.mouth2);
        imageOption4.setImageBitmap(this.options.get(3));
        ///ADD OPTIONS HERE
    }


    public  Bitmap imageMerge(Bitmap base, Bitmap added_detail, String type) {

        switch (type) {
            case "test":
                Bitmap result = Bitmap.createBitmap(base.getWidth(), base.getHeight(), base.getConfig());
                Canvas canvas2 = new Canvas(result);
                canvas2.drawBitmap(base, new Matrix(), null);
                canvas2.drawBitmap(added_detail, 0, 0, null);
                return result;
            case "eyes":
                break;
            case "șamd":
                break;
        }
        return null;
    }

    private Bitmap getBitmapFromView(View view){

        progressBar.setVisibility(View.VISIBLE);

        ImageView option = (ImageView) view;
        Drawable optionDrawable = ((ImageView) view).getDrawable();

        Bitmap optionBitMap = null;


        BitmapDrawable optionBitMapDrawable = (BitmapDrawable) optionDrawable;
        if (optionBitMapDrawable.getBitmap() != null) {
            optionBitMap = optionBitMapDrawable.getBitmap();
        }
        return optionBitMap;
    }

    public void selectOption(View view) {



        Bitmap optionBitMap = getBitmapFromView(view);

        resultBase = imageMerge(resultBase, optionBitMap, "test");

        ImageView resultShow = findViewById(R.id.base);
        resultShow.setImageBitmap(resultBase);

        progressBar.setVisibility(View.INVISIBLE);

    }

}


