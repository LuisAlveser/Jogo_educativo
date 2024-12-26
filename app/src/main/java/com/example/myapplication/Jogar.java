package com.example.myapplication;

import android.graphics.drawable.Drawable;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class Jogar extends AppCompatActivity {


    private ArrayList<Nave_inimiga>inimigos=new ArrayList<>();

    private ArrayList<Tiro>tiros=new ArrayList<>();
    private Handler handler = new Handler();
    private int  vidas;
    private int  pont_jogador;
    private int  pont_Max;
    private Nave_jogador Jogador;














        //Função para rodar o jogo
        public void run() {
            for (Nave_inimiga inimigo : inimigos) {
                inimigo.update();

            }





        }










   public void Atirar(View view){
            ConstraintLayout tela=findViewById(R.id.tela);
      Jogador.getClass();
      float jx= Jogador.getOx();
      float jy=Jogador.getOy();
       Drawable t_img=getResources().getDrawable(R.drawable.disparo);
      Tiro tiro =new Tiro(t_img,jx,jy);
       tiros.add(tiro);
       float y_tela=tela.getMaxWidth();
       // Atualiza e remove tiros antigos
       for (int i = tiros.size() - 1; i >= 0; i--) {
           tiro = tiros.get(i);
           tiro.atualizar_tiro();

           // Remove o tiro se ele estiver fora da tela ou atingir um alvo
           if (tiro.getTy() < y_tela) {
               tiros.remove(i);
           }
       }
   }






    @Override
    //Função para carregar os objetos
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_de_jogo);// Infla o layout antes de acessar as Views



        // Referência para a ConstraintLayout


        ImageView nave1 = findViewById(R.id.nave_1);
        ImageView nave2 = findViewById(R.id.nave_2);
        ImageView nave3 = findViewById(R.id.nave_3);
        ImageView jogador = findViewById(R.id.nave_jogador);


        // Obtém as coordenadas e cria o objeto Nave_inimiga e Jogador
        // nave1
        float x_nave1 = nave1.getX();
        float y_nave1 = nave1.getY();
        TextView equacao_nave1 = findViewById(R.id.equacao_1);

        // nave2
        float x_nave2 = nave2.getX();
        float y_nave2 = nave2.getY();
        TextView equacao_nave2 = findViewById(R.id.equacao_2);

        // nave3
        float x_nave3 = nave3.getX();
        float y_nave3 = nave3.getY();
        TextView equacao_nave3 = findViewById(R.id.equacao_3);



        // jogador
        float x_jogador = jogador.getX();
        float y_jogador = jogador.getY();
        TextView result = findViewById(R.id.resultado);

        Jogador = new Nave_jogador(jogador, x_jogador, y_jogador, result);
        Nave_inimiga NAVE_1 = new Nave_inimiga(nave1, x_nave1, y_nave1, equacao_nave1);
        Nave_inimiga NAVE_2 = new Nave_inimiga(nave2, x_nave2, y_nave2, equacao_nave2);
        Nave_inimiga NAVE_3 = new Nave_inimiga(nave3, x_nave3, y_nave3, equacao_nave3);
        inimigos.add(NAVE_1);
        inimigos.add(NAVE_2);
        inimigos.add(NAVE_3);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_de_jogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

}