package com.kikoteam.mobileschool.avatar;

import android.graphics.Bitmap;

public class Avatar {

    private Bitmap base;
    private Bitmap eyes;
    private Bitmap mouth;
    private Bitmap hair;
    private Bitmap finalForm;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    ///singleton instance
    private static Avatar instance = null;

    private Avatar() {
        base = null;
        eyes = null;
        mouth = null;
        hair = null;
        finalForm = null;
        url = "empty";
    }

    public static Avatar getInstance() {
        if (instance == null) {
            instance = new Avatar();
        }
        return instance;
    }

    public static void resetInstance(){
        instance = null;
    }

    public Bitmap getHair() {
        return hair;
    }

    public void setHair(Bitmap hair) {
        this.hair = hair;
    }

    public void rebuild() {
        this.finalForm = this.base;

        if (this.eyes != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.eyes);

        if (this.mouth != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.mouth);

        if (this.hair != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.hair);

    }

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
