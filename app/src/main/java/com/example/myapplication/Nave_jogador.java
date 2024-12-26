package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class Nave_jogador implements SensorEventListener {
   ImageView imag;
   private Bitmap jogador;
   private  float ox,oy;
   private boolean vivo=true;
   private    float velocidade;
   private Random random;
   private TextView resul;




    public Nave_jogador(ImageView imag,float ox,float oy,TextView result){
        this.imag=imag;
        this.ox=ox;
        this.oy=oy;
        this.resul=result;

        this.jogador= BitmapFactory.decodeResource(imag.getResources(),R.drawable.nave_formato_novo);
        random=new Random();


    }


    public float getOx() {
        return ox;
    }

    public void setOx(float ox) {
        this.ox = ox;
    }

    public void setOy(float oy) {
        this.oy = oy;
    }

    public float getOy() {
        return oy;
    }

    public void atualizar_equacao_jogador() {
        resul.setX(this.ox);
        resul.setY(this.oy-5);

    }



    private  float sensibilidade=5;
private float max_velocidade=200;
    @Override
    public void onSensorChanged(SensorEvent event) {

    if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
        float x=event.values[0];
        velocidade =  (x * sensibilidade);
        velocidade =  Math.max(-max_velocidade, Math.min(max_velocidade, velocidade));

        // Atualiza a posição da nave
        this.ox += velocidade;




      }
    }




    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
      if(sensor.getType()==Sensor.TYPE_ACCELEROMETER){
          Toast.makeText(imag.getContext(), (int) 5, Toast.LENGTH_LONG).show();

      }
    }
}
