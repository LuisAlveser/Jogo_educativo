package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class Explosao {
    ImageView imag;
    Bitmap explosao[]=new Bitmap[2];
    int explosao_frame;
    float ex,ey;

    public Explosao(ImageView imag,float  ex, float  ey){
    explosao[0]= BitmapFactory.decodeResource(imag.getResources(), R.drawable.explosao_1);
    explosao[1]= BitmapFactory.decodeResource(imag.getResources(), R.drawable.explosao_2);
   this.ex=ex;
   this.ey=ex;

    }
    public Bitmap getExplocao(){
        return  explosao[explosao_frame];
    }



    public void setEx(float ex) {
        this.ex = ex;
    }

    public float getEx() {
        return ex;
    }

    public void setEy(float ey) {
        this.ey = ey;
    }

    public float getEy() {
        return ey;
    }
}
