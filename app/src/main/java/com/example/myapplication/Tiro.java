package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Tiro {

    public ImageView imag;
    private float velocidade = 30;

    public Tiro(ImageView imag) {
        this.imag=imag;



    }

    public void atualizar_tiro_inicio(float jogadorX) {
        imag.setTranslationX(jogadorX);
        imag.setTranslationY(imag.getTranslationY()-30);

    }
    public void atualizar_tiro_fim() {

        imag.setTranslationY(imag.getTranslationY()-30);

    }

    public ImageView getImag() {
        return imag;
    }

}
