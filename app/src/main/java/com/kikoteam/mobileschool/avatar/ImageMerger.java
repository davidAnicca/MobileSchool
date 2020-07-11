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
    Avatar avatar = Avatar.getInstance();
    ProgressBar progressBar;
    ImageView finalForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_merger);

        finalForm = findViewById(R.id.finalForm);
        finalForm.setImageResource(R.drawable.square_base_388c91);
        selectBase();


        progressBar = findViewById(R.id.progressForMerge);
        progressBar.setVisibility(View.VISIBLE);

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

    public Bitmap imageMerge(Bitmap base, Bitmap added_detail) {

        Bitmap result = Bitmap.createBitmap(base.getWidth(), base.getHeight(), base.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(base, new Matrix(), null);
        canvas.drawBitmap(added_detail, 0, 0, null);
        return result;

    }

    private void rebuild() {

        avatar.setFinalForm(avatar.getBase());

        if (avatar.getEyes() != null) {
            avatar.setFinalForm(imageMerge(avatar.getFinalForm(), avatar.getEyes()));
        }
        if (avatar.getMouth() != null) {
            avatar.setFinalForm(imageMerge(avatar.getFinalForm(), avatar.getMouth()));
        }

        ImageView finalForm = findViewById(R.id.finalForm);
        finalForm.setImageBitmap(avatar.getFinalForm());

    }

    private Bitmap getBitmapFromView(View view) {

        Drawable optionDrawable = ((ImageView) view).getDrawable();

        BitmapDrawable optionBitMapDrawable = (BitmapDrawable) optionDrawable;
        if (optionBitMapDrawable.getBitmap() != null) {
            return optionBitMapDrawable.getBitmap();
        }
        return null;
    }

    public void selectBase() {
        avatar.setBase(BitmapFactory.decodeResource(ImageMerger.this.getResources(), R.drawable.square_base_388c91));
        avatar.setFinalForm(avatar.getBase());
    }

    public void selectEyes(View view) {
        this.finalForm.setVisibility(View.GONE);
        avatar.setEyes(getBitmapFromView(view));
        rebuild();
        this.finalForm.setVisibility(View.VISIBLE);
    }

    public void selectMouth(View view) {
        this.finalForm.setVisibility(View.GONE);
        avatar.setMouth(getBitmapFromView(view));
        rebuild();
        this.finalForm.setVisibility(View.VISIBLE);
    }


}


