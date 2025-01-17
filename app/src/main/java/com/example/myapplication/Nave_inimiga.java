package com.example.myapplication;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

public class Nave_inimiga {
    private ImageView imag;
    private Bitmap inimigo;
    private float velocidade = 5;
    private Random random;
    private TextView equacao;
    private RelativeLayout tela;




    public Nave_inimiga(ImageView imag, TextView equacao) {
        this.imag = imag;

        this.equacao = equacao;


        this.inimigo = BitmapFactory.decodeResource(imag.getResources(), R.drawable.alien_formato_novo);
        random = new Random();


    }

    public void setImag(ImageView imag) {
        this.imag = imag;
    }

    public ImageView getImag() {
        return imag;
    }


    public void update() {

        float cont = imag.getTranslationY();
        imag.setTranslationY(cont + 0.0003F);


    }

    public void atualizar_equacao_inimiga() {

        update();
        float inimigo_y = imag.getTranslationY() - 0.0005f;
        equacao.setTranslationY(inimigo_y);


    }

    public TextView getEquacao() {
        return equacao;
    }
}
