package com.example.myapplication;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class Jogar extends AppCompatActivity implements  SensorEventListener {





    MediaPlayer mediaPlayer_tiro;

    private Nave_jogador Jogador;
    private Nave_inimiga nave1;
    private Nave_inimiga nave2;
    private Nave_inimiga nave3;
    private Tiro tiro;
    private Explosao explo;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView eq1;
    private TextView eq2;
    private TextView eq3;
    private TextView result;
    private RelativeLayout tela;
    private TextView Vida;
    private TextView  pontuacao_Jogador;
    private TextView pontuacao_Max;









   public void Atirar(View view) {
     mediaPlayer_tiro.start();
         if(tiro.getImag().getVisibility()==View.INVISIBLE) {
            tiro.getImag().setTranslationX(Jogador.getImag().getX());
            tiro.getImag().setTranslationY(Jogador.getImag().getY());
           tiro.getImag().setVisibility(View.VISIBLE);
        }
      
    }






    @Override
    public void onSensorChanged(SensorEvent event) {

             RelativeLayout tela=findViewById(R.id.tela);
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];
            float omegaMagnitude = (float) Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

            if (omegaMagnitude > 0.001) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
               // Log.d("onSensorChanged", "X: "+ Jogador.imag.getTranslationX());
                //Log.d("onSensorChanged", "X: "+ axisX+"y: "+axisY+"Z:"+axisZ);
                //Incrementar translação do jogador
              //esquerda= 0.7111966:
              //direita = 0.860943;

               // jogador d=63.703888
               //jogador e=806.5986

                    Jogador.getImag().setTranslationX(Jogador.getImag().getTranslationX() + axisY * 8);
                    Jogador.getResult().setTranslationX(Jogador.getImag().getTranslationX() + 40);







        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    //Função para carregar os objetos
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_tela_de_jogo);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
         mediaPlayer_tiro=MediaPlayer.create(this,R.raw.tiro);


           sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
           sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
           sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_GAME);


        start_game();
       RelativeLayout tela=findViewById(R.id.tela);


        Jogo_Thread jogo_Thread = new Jogo_Thread(this,tela,nave1,nave2,nave3,Jogador,
                Vida,pontuacao_Jogador,pontuacao_Max, explo.getExplo(), tiro.getImag());
        jogo_Thread.start();





    }


    public void start_game() {


         pontuacao_Jogador=findViewById(R.id.pontucao);





        //Equações
         eq1 = findViewById(R.id.equacao_1);

         eq2 = findViewById(R.id.equacao_2);

         eq3 = findViewById(R.id.equacao_3);


        result = findViewById(R.id.resultado);







        // Referência para a ConstraintLayout


        ImageView nave_1 = findViewById(R.id.nave_1);
        ImageView nave_2 = findViewById(R.id.nave_2);
        ImageView nave_3 = findViewById(R.id.nave_3);
        ImageView jogador = findViewById(R.id.nave_jogador);
        RelativeLayout tela=findViewById(R.id.tela);

        Jogador = new Nave_jogador(jogador, result);
        nave1 = new Nave_inimiga(nave_1, eq1);
        nave2 = new Nave_inimiga(nave_2, eq2);
        nave3 = new Nave_inimiga(nave_3, eq3);


        ImageView ex= findViewById(R.id.explo_nave);
        ImageView disparo = findViewById(R.id.tiro);
        tiro = new Tiro(disparo);
        explo = new Explosao(ex);

    }



}


