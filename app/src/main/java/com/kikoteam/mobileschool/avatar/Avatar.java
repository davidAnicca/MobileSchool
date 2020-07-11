package com.kikoteam.mobileschool.avatar;

import android.graphics.Bitmap;

public class Avatar {


    private static Avatar instance = null;

    private Avatar(){
        base = null;
        eyes = null;
        mouth = null;
        finalForm = null;
    }

    public static Avatar getInstance(){
        if ( instance == null ){
            instance = new Avatar();
        }
        return instance;
    }


    public Bitmap base;
    public Bitmap eyes;
    public Bitmap mouth;
    public Bitmap finalForm;

    public Bitmap getBase() {
        return base;
    }

    public void setBase(Bitmap base) {
        this.base = base;
    }

    public Bitmap getEyes() {
        return eyes;
    }

    public void setEyes(Bitmap eyes) {
        this.eyes = eyes;
    }

    public Bitmap getMouth() {
        return mouth;
    }

    public void setMouth(Bitmap mouth) {
        this.mouth = mouth;
    }

    public Bitmap getFinalForm() {
        return finalForm;
    }

    public void setFinalForm(Bitmap finalForm) {
        this.finalForm = finalForm;
    }
}
