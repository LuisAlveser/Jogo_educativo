package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Nave_jogador {
   ImageView imag;
   private Bitmap jogador;


   private    float velocidade;

   private TextView result;





    public Nave_jogador(ImageView imag,TextView result){
        this.imag=imag;
        this.result=result;
        this.jogador= BitmapFactory.decodeResource(imag.getResources(),R.drawable.nave_formato_novo);

    }

    public ImageView getImag() {
        return imag;
    }

    public TextView getResult() {
        return result;
    }

    public void setResult(TextView result) {
        this.result = result;
    }
}
