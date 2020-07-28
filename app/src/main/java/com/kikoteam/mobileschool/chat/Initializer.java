package com.kikoteam.mobileschool.chat;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.kikoteam.mobileschool.avatar.entities.Avatar;

import java.util.HashMap;
import java.util.Map;

public class Initializer {



    private  Map<String, Bitmap> avatars;
        ///here mates from conversation will be added

    public Initializer(){
        this.avatars = new HashMap<>();
        this.avatars.put("first", Avatar.getInstance().getFinalForm());
    }

    public void pasteBitmap(String id, ImageView where){
        if(!this.avatars.containsKey(id)){
            downloadProcess(id, where);
            return;
        }
        where.setImageBitmap(avatars.get(id));
        return;
    }

    private void downloadProcess(String id, ImageView where){

    }

    public Map<String, Bitmap> getAvatars() {
        return this.avatars;
    }

}
