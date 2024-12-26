package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Tiro {
    private Bitmap tiro;
    private Drawable imag;
    private float tx, ty;
    float velocidade = 2;

    public Tiro(Drawable imag, float tx, float ty) {
        this.imag = imag;

        this.tx = tx;
        this.ty = ty;


    }

    public void atualizar_tiro() {

        this.ty -= velocidade;
    }

    public float getTx() {
        return tx;
    }

    public float getTy() {
        return ty;
    }

    public void setTx(float tx) {
        this.tx = tx;
    }

    public void setTy(float ty) {
        this.ty = ty;
    }

    public Bitmap getTiro() {
        return tiro;

    }

    public Drawable getImag() {
        return imag;
    }

    public void setImag(Drawable imag) {
        this.imag = imag;
    }
}
