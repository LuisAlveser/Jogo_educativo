package com.example.myapplication;

import static android.util.Half.EPSILON;

import android.content.Context;
import android.graphics.drawable.Drawable;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Jogar extends AppCompatActivity implements  SensorEventListener {


    private Handler handler = new Handler(Looper.getMainLooper());
    private int vidas;
    private int pont_jogador;
    private int pont_Max;
    private Nave_jogador Jogador;
    private Nave_inimiga nave1;
    private Nave_inimiga nave2;
    private Nave_inimiga nave3;
    private Tiro tiro;
    private RelativeLayout tela;
    private Explosao explo;
    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView eq1;
    private TextView eq2;
    private TextView eq3;


    public void Atirar(View view) {
        boolean resultado= colisao();
        ImageView disparo = findViewById(R.id.tiro);
        disparo.setVisibility(View.VISIBLE);
         Tiro tiro = new Tiro(disparo);
        RelativeLayout tela=findViewById(R.id.tela);


        if(tiro.getImag().getTranslationY()<tela.getTranslationY()||resultado==true){
            disparo.setVisibility(View.INVISIBLE);
            tiro.getImag().setTranslationX(Jogador.getImag().getTranslationX());
            tiro.getImag().setTranslationY(Jogador.getImag().getTranslationY());

            tiro.atualizar_tiro_inicio(Jogador.getImag().getTranslationX());

          //  tiro.atualizar_tiro_fim();
            disparo.setVisibility(View.VISIBLE);

        }
        else
            tiro.atualizar_tiro_fim();


    }
    public void resetar_nave_inimiga(){
        nave1.getImag().setTranslationX(35);
        nave1.getImag().setTranslationY(40);

        nave2.getImag().setTranslationX(170);
        nave2.getImag().setTranslationY(40);

        nave3.getImag().setTranslationX(310);
        nave3.getImag().setTranslationY(40);
    }
    public void resetar_equacao_e_nave(){
        resetar_nave_inimiga();

        eq1.setTranslationX(20);
        eq1.setTranslationY(30);

        eq2.setTranslationX(150);
        eq2.setTranslationY(30);

        eq3.setTranslationX(284);
        eq3.setTranslationY(30);

    }

    public boolean colisao() {
        List<Nave_inimiga> naves = Arrays.asList(nave1, nave2, nave3);
        RelativeLayout tela = findViewById(R.id.tela);
        ImageView disparo = findViewById(R.id.tiro);
        ImageView ex = findViewById(R.id.explo_nave);
        tiro = new Tiro(disparo);
        explo = new Explosao(ex);

        float Colisao = 15.0f;
        for (Nave_inimiga nave : naves) {
            float tx = tiro.getImag().getTranslationX();
            float ty = tiro.getImag().getTranslationY();

            float nx = nave.getImag().getTranslationX();
            float ny = nave.getImag().getTranslationX();
            float distancia = calcularDistancia(tx, ty, nx, ny);

            if (distancia < Colisao) {

                float f = nave.getImag().getTranslationX() ;
                float o = nave.getImag().getTranslationY();
                explo.getExplo().setTranslationX(f);
                explo.getExplo().setTranslationY(o);
                tela.findViewById(R.id.explo_nave).setVisibility(View.VISIBLE);
                return true;
            }

        }
       return false;

    }


    //calcula a distância  entre dois pontos no plano cartesiano.
    private float calcularDistancia(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {


            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];
            float omegaMagnitude = (float) Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

            if (omegaMagnitude > 0.001) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;

                Log.d("onSensorChanged", "X: "+ axisX+"y: "+axisY+"Z:"+axisZ);
                //Incrementar translação do jogador

                Jogador.getImag().setTranslationX(Jogador.getImag().getTranslationX() + axisY *8);
                Jogador.getResult().setTranslationX(Jogador.getImag().getTranslationX()+40);




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
        setContentView(R.layout.activity_tela_de_jogo);// Infla o layout antes de acessar as Views

           sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
           sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
           sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_GAME);
           start_game();

    }


    public void start_game() {
        //Equações
         eq1 = findViewById(R.id.equacao_1);
         eq2 = findViewById(R.id.equacao_2);
         eq3 = findViewById(R.id.equacao_3);

        TextView result = findViewById(R.id.resultado);
        // Referência para a ConstraintLayout


        ImageView nave_1 = findViewById(R.id.nave_1);
        ImageView nave_2 = findViewById(R.id.nave_2);
        ImageView nave_3 = findViewById(R.id.nave_3);
        ImageView jogador = findViewById(R.id.nave_jogador);


        Jogador = new Nave_jogador(jogador, result);
        nave1 = new Nave_inimiga(nave_1, eq1);
        nave2 = new Nave_inimiga(nave_2, eq2);
        nave3 = new Nave_inimiga(nave_3, eq3);


        nave1.atualizar_equacao_inimiga();
        nave2.atualizar_equacao_inimiga();
        nave3.atualizar_equacao_inimiga();


       /* public void run () {

            //game loop
            while (true) {

                //maquina de estados - inimigos e jogador

                //estado atirando = quando não houver projétil e na tela e o jogador clicou para atirar
                // deixa visível a image do tiro e translada pro eixo y

                //estado não-atirando = não o projétil passar dos limites da tela ou colidir na nave inimiga
                //projétil invisível e translada pra origem (0,0)

                //estado de movimentando = quando houver alteração nos valores do giroscópio
                //incrementar/decrementar os valores de translação no eixo x


                //condições de parada
                // quando acabam as vidas do jogador
                // quando acabarem as naves inimigas

            }
        }  */

    }

    }


