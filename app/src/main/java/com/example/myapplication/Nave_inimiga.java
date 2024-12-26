package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Nave_inimiga {
    private ImageView imag;
   private Bitmap inimigo;
    private float  ex,ey;
   private float velocidade=5;
   private Random random;
   private TextView equacao;
   private Tiro tiros;
   private ArrayList<Explosao>explosaos;





    public Nave_inimiga(ImageView imag, float ex,float ey,TextView equacao) {
        this.imag = imag;
        this.ex=ex;
        this.ey=ey;
        this.equacao=equacao;
        this.inimigo = BitmapFactory.decodeResource(imag.getResources(), R.drawable.alien_formato_novo);
        random = new Random();
        resetar_inimigo();


    }

    public void setImag(ImageView imag) {
        this.imag = imag;
    }

    public ImageView getImag() {
        return imag;
    }

    public void setEquacao(TextView equacao) {
        this.equacao = equacao;
    }

    public TextView getEquacao() {
        return equacao;
    }

    public Bitmap getInimigo() {
        return inimigo;
    }

    public void setEy(float ey) {
        this.ey = ey;
    }

    public float getEy() {
        return ey;
    }

    public void setEx(float ex) {
        this.ex = ex;
    }

    public float getEx() {
        return ex;
    }

    private void resetar_inimigo() {

        this.ey = 4 + random.nextInt(9);
        velocidade = 5;
    }

    public void update() {

        this.ey+=velocidade;

    }
    public void atualizar_equacao_inimiga(){
       equacao.setX(this.ex);
       equacao.setY(this.ey-4);
    }
    public void colisao(){
        if(this.ey==tiros.getTy()){

           this.imag.setX(explosaos.get(2).getEx());
            this.imag.setY(explosaos.get(2).getEy());

        }
    }
}