package com.kikoteam.mobileschool.avatar.entities;

import android.graphics.Bitmap;

import com.kikoteam.mobileschool.avatar.processors.ImageProcessor;

public class Avatar {

    private Bitmap base;
    private Bitmap eyes;
    private Bitmap mouth;
    private Bitmap hair;
    private Bitmap ear;
    private Bitmap nose;
    private Bitmap eyes_accessory;
    private Bitmap other_accessory;
    private Bitmap finalForm;

    ///singleton instance
    private static Avatar instance = null;




    private Avatar() {
        base = null;
        eyes = null;
        mouth = null;
        hair = null;
        ear = null;
        nose = null;
        eyes_accessory = null;
        other_accessory = null;
        finalForm = null;

    }

    public static Avatar getInstance() {
        if (instance == null) {
            instance = new Avatar();
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }


    public void rebuild() {

        this.finalForm = this.base;

        if (this.eyes != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.eyes);

        if (this.mouth != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.mouth);

        if (this.hair != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.hair);

        if ( this.nose != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.nose);

        if ( this.ear != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.ear);

        if ( this.eyes_accessory != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.eyes_accessory);

        if ( this.other_accessory != null)
            this.finalForm = ImageProcessor.imageMerger(this.finalForm, this.other_accessory);


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

    public Bitmap getEar() {
        return ear;
    }

    public void setEar(Bitmap ear) {
        this.ear = ear;
    }

    public Bitmap getHair() {
        return hair;
    }

    public void setHair(Bitmap hair) {
        this.hair = hair;
    }

    public Bitmap getEyes_accessory() {
        return eyes_accessory;
    }

    public void setEyes_accessory(Bitmap eyes_accessory) {
        this.eyes_accessory = eyes_accessory;
    }

    public Bitmap getOther_accessory() {
        return other_accessory;
    }

    public void setOther_accessory(Bitmap other_accessory) {
        this.other_accessory = other_accessory;
    }
    public Bitmap getNose() {
        return nose;
    }

    public void setNose(Bitmap nose) {
        this.nose = nose;
    }
}
