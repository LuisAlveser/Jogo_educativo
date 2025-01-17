package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela_de_game_over extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    public void Voltar_menu(View v){
        mediaPlayer.start();
        Intent i =new Intent(this, MainActivity.class);
        startActivity(i);

    }
    public void JOGAR_Novamente(View v){
        mediaPlayer.start();
        Intent i =new Intent(this, Jogar.class);
        startActivity(i);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_de_game_over);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mediaPlayer=MediaPlayer.create(this,R.raw.botao);
        //String pontuacaoFinal = getIntent().getStringExtra("PONTUACAO_FINAL");
       // TextView F=findViewById(R.id.ponto_fim);
      //  F.setText(pontuacaoFinal);


    }

}