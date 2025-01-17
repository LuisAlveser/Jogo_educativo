package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Tiro {

    public ImageView imag;

    public Tiro(ImageView imag) {
        this.imag=imag;




    }

    public void atualizar_tiro_inicio(float jogadorX) {
        imag.setTranslationX(jogadorX);
        imag.setTranslationY(imag.getTranslationY()-0.0010F);

    }

    synchronized public void  atualizar_tiro_fim() {

        imag.setTranslationY(imag.getY()-0.003F);

    }

    synchronized public void  atualiza_tiro_visivel() {

        imag.setVisibility(View.INVISIBLE);

    }



    public ImageView getImag() {
        return imag;
    }




}
