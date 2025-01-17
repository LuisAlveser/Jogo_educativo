package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Jogo_Thread extends  Thread {
    RelativeLayout tela;
    Nave_inimiga n1;
    Nave_inimiga n2;
    Nave_inimiga n3;
    Nave_jogador jogador;
    ImageView ex;
    ImageView disparo;
MediaPlayer mediaPlayer_explo;
    TextView  vidas;
    TextView ponto_jogador;
    TextView  Ponto_jogador_max;
    int contador_rodadas=0;

    int n_1=0;
    int n_2=0;
    int n_3=0;
    int r=0;
    Handler handler;
    boolean n1_visivel = true;
    boolean n2_visivel = true;
    boolean n3_visivel = true;
    Activity atividade;
    boolean nave_movimento_ativo=true;




    public  Jogo_Thread(Activity atividade, RelativeLayout tela, Nave_inimiga n1, Nave_inimiga n2,
                        Nave_inimiga n3, Nave_jogador jogador
    , TextView  vidas,   TextView ponto_jogador,  TextView  Ponto_jogador_max,
                        ImageView ex, ImageView disparo) {
        this.atividade = atividade;
        this.tela = tela;
        this.n1=n1;
        this.n2=n2;
        this.n3=n3;
        this.jogador=jogador;
        this.vidas=vidas;
        this.ponto_jogador=ponto_jogador;
        this.Ponto_jogador_max=Ponto_jogador_max;
        this.ex = ex;
        this.disparo = disparo;

    }


    ArrayList<String> equacao_1 = new ArrayList<>(Arrays.asList(
            "2x+3=7", //resultado
            "x-2=-4",
            "3x+1=11",
            "2x+1=9",//resultado
            "x+4=9",
            "x+5=10"
    ));
    ArrayList<String> equacao_2 = new ArrayList<>(Arrays.asList(
            "5x-2=15",
            "3x-1=8",//resultado
            "2x+1=6",
            "x-3=0",
            "2x+2=12",//resultado
            "x+1=5"
    ));

    ArrayList<String> equacao_3 = new ArrayList<>(Arrays.asList(
            "3x+7=16",
            "2x-5=9",
            "5x-12=18",//resultado
            "4x+2=-1",
            "x-8=2",
            "7x+1=15"//resultado
    ));

    ArrayList<String> resultado_e = new ArrayList<>(Arrays.asList(
            "x=2",//equações _1
            "x=3",//equações _2
            "x=6",//equações _3
            "x=4",//equações _1
            "x=5",//equações _2
            "x=2"//equações _3
    ));


    // Calcula a distância entre dois pontos no plano cartesiano.
    private synchronized float calcularDistancia(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public synchronized boolean colisao() {

        List<Nave_inimiga> naves = Arrays.asList(n1, n2, n3);
        float Colisao = 30f;
        int index = 0;

        for (Nave_inimiga nave : naves) {
            float tx = disparo.getX();
            float ty = disparo.getY();

            float nx = nave.getImag().getX();
            float ny = nave.getImag().getY();
            float distancia = calcularDistancia(tx, ty, nx, ny);

            if (distancia < Colisao) {
                float g = nave.getImag().getX();
                float e = nave.getImag().getY();

                ex.setX(g);
                ex.setY(e);

                switch (index) {
                    case 0:
                        n1_visivel = false;
                        break;
                    case 1:
                        n2_visivel = false;
                        break;
                    case 2:
                        n3_visivel = false;
                        break;
                }
                int pontos = contador_rodadas * 100;  //  cálculo  de pontuação
                atividade.runOnUiThread(() -> {
                    ponto_jogador.setText(String.valueOf(pontos));
                });

                    // Atualizar equações e resultados
                    if (contador_rodadas < 5) {
                        atividade.runOnUiThread(() -> {
                        n1.getEquacao().setText(equacao_1.get(n_1));
                        n2.getEquacao().setText(equacao_2.get(n_2));
                        n3.getEquacao().setText(equacao_3.get(n_3));
                        jogador.getResult().setText(resultado_e.get(r));
                        });


                        if (contador_rodadas == 0 &&!n1_visivel) {
                            r++;
                            n_1++;
                            n_2++;
                            n_3++;
                            contador_rodadas++;


                        } else if (contador_rodadas == 1 && !n2_visivel) {

                            r++;
                            n_1++;
                            n_2++;
                            n_3++;
                            contador_rodadas++;

                        } else if (contador_rodadas == 2 && !n3_visivel) {

                            r++;
                            n_1++;
                            n_2++;
                            n_3++;
                            contador_rodadas++;

                        } else if (contador_rodadas == 3 && !n1_visivel) {

                            r++;
                            n_1++;
                            n_2++;
                            n_3++;
                            contador_rodadas++;

                        } else if (contador_rodadas == 4 && !n2_visivel) {

                            r++;
                            n_1++;
                            n_2++;
                            n_3++;
                            contador_rodadas++;

                        } else if (contador_rodadas == 5 && !n3_visivel) {

                            r++;
                            n_1++;
                            n_2++;
                            n_3++;
                            contador_rodadas++;

                        }
                        if(contador_rodadas==0&&!n2_visivel||!n3_visivel){
                            Intent i = new Intent(atividade, Tela_de_game_over.class);
                            atividade.runOnUiThread(() -> {
                                String pontuacao = ponto_jogador.getText().toString();
                                i.putExtra("PONTUACAO_FINAL",pontuacao);
                            });
                                atividade.startActivity(i);


                        }
                        if(contador_rodadas==0&&!n1_visivel||!n3_visivel){
                            Intent i = new Intent(atividade, Tela_de_game_over.class);
                            atividade.runOnUiThread(() -> {
                                String pontuacao = ponto_jogador.getText().toString();
                                i.putExtra("PONTUACAO_FINAL",pontuacao);
                            });
                            atividade.startActivity(i);


                        }




                    }

                    if (contador_rodadas == 5) {
                        contador_rodadas = 0;
                    }




                atividade.runOnUiThread(() -> {
                    mediaPlayer_explo.start();
                    ex.setVisibility(View.VISIBLE);
                    nave.getImag().setVisibility(View.INVISIBLE);
                    disparo.setVisibility(View.INVISIBLE);
                    nave_movimento_ativo = false;
                });

                if (!nave_movimento_ativo) {
                    atividade.runOnUiThread(() -> {
                        ex.setVisibility(View.INVISIBLE);
                        nave.getImag().setVisibility(View.VISIBLE);
                        resetar_equacao_e_nave();

                    });
                }




                return true;
            }
            index++;
        }
        return false;
    }

    public void resetar_nave_inimiga() {
        atividade.runOnUiThread(() -> {
            n1.getImag().setX(35);
            n1.getImag().setY(40);

            n2.getImag().setX(470);
            n2.getImag().setY(50);

            n3.getImag().setX(810);
            n3.getImag().setY(40);
        });
    }

    public void resetar_equacao_e_nave() {
        atividade.runOnUiThread(() -> {
            n1_visivel = true;
            n2_visivel = true;
            n3_visivel = true;

            resetar_nave_inimiga();

            n1.getEquacao().setX(20);
            n1.getEquacao().setY(30);

            n2.getEquacao().setX(400);
            n2.getEquacao().setY(30);

            n3.getEquacao().setX(730);
            n3.getEquacao().setY(30);
        });
    }




    @Override
    public synchronized void run() {
        ImageView disparo = (tela.findViewById(R.id.tiro));
        Tiro tiro = new Tiro(disparo);
        atividade.runOnUiThread(() -> {
            // Define as equações iniciais para as naves
            n1.getEquacao().setText(equacao_1.get(n_1));
            n2.getEquacao().setText(equacao_2.get(n_2));
            n3.getEquacao().setText(equacao_3.get(n_3));
            jogador.getResult().setText(resultado_e.get(r));

        });
        mediaPlayer_explo=MediaPlayer.create(atividade,R.raw.explosao);






        while (true) {

            colisao();


                n1.atualizar_equacao_inimiga();
                n2.atualizar_equacao_inimiga();
                n3.atualizar_equacao_inimiga();



            if (tiro.getImag().getVisibility() == View.VISIBLE) {
                tiro.atualizar_tiro_fim();
            }
            if (tiro.getImag().getY() < 0) {
                tiro.atualiza_tiro_visivel();
            }
        }
    }

    }






