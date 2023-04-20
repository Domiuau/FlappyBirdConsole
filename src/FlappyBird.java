import model.Cor;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlappyBird {

    static Cor[][] jogo = new Cor[200][40];
    static Random random = new Random();
    static int tamanhoCano;
    static int cima, baixo, meio;
    static int contador = 0;
    static int velocidade = 5;
    static boolean pular = false;
    static Scanner scanner;
    static int distancia = 0;
    static int espacoCano;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);

        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[0].length; j++) {

                jogo[i][j] = new Cor("44");


            }
        }


        cima = 9;
        meio = 10;
        baixo = 11;

        System.out.println("Insira um velocidade de jogo (quantidade de quadros por segundo)");
        System.out.println("1 quandro - lerdo");
        System.out.println("2 quandro - recomendado médio");
        System.out.println("3 quandro - recomendado rápido");
        System.out.println("4 quandro - jogável");
        System.out.println("5 quandro - quase injogável");
        int entrada = (int) 1000/scanner.nextInt();

        System.out.println("Escolha a distância entre os canos (recomendado - 20)");
        distancia = scanner.nextInt();

        System.out.println("Escolha o espaço entre os canos (recomendado - 10)");
        espacoCano = scanner.nextInt();

        scanner();



        jogo[51][baixo].setCor("40");
        jogo[52][baixo].setCor("40");
        jogo[53][baixo].setCor("40");
        jogo[54][baixo].setCor("40");
        jogo[55][baixo].setCor("40");
        jogo[56][baixo].setCor("40");
        jogo[57][baixo].setCor("40");
        jogo[58][baixo].setCor("40");


        jogo[50][meio].setCor("40");
        jogo[51][meio].setCor("40");
        jogo[52][meio].setCor("40");
        jogo[53][meio].setCor("40");
        jogo[54][meio].setCor("40");
        jogo[55][meio].setCor("40");
        jogo[56][meio].setCor("40");
        jogo[57][meio].setCor("40");
        jogo[58][meio].setCor("40");


        jogo[57][cima].setCor("43");
        jogo[58][cima].setCor("43");
        jogo[59][cima].setCor("41");


        adiconarCano();
        distancia += random.nextInt(20);
        contador = 0;


        for (int i = 0; i < 40; i++) {
            loopGame(velocidade);

        }

        for (int i = 0; i < 15; i++) {
            subirPassaro();
        }

        velocidade = entrada;

        for (int i = 0; i < 800; i++) {
            loopGame(velocidade);
        }


    }

    static void loopGame(int velocidade1) {
        moverJogo();
        imprimirJogo(velocidade1);
        contador++;
        if (contador == distancia) {
            adiconarCano();
            contador = 0;
            distancia = random.nextInt(15) + distancia;
        }

    }

    static void scanner() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(new Runnable() {
            @Override
            public void run() {

                scanner.next();
                pular = true;
                scanner();


            }
        });
    }

    static void adiconarCano() {

        tamanhoCano = random.nextInt(11) + 14;

        jogo[jogo.length - 1][tamanhoCano - 2] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano - 1] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano + espacoCano] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano + espacoCano + 1] = new Cor("42");
        moverJogo();

//ajustar dps para receber alguem tamanho
        for (int j = 0; j < tamanhoCano; j++) {

            jogo[jogo.length - 1][j].setCor("42");

        }


        for (int j = tamanhoCano + espacoCano; j < jogo[0].length; j++) {
            jogo[jogo.length - 1][j].setCor("42");
        }

        for (int i = 0; i < 15; i++) {


            moverJogo();
            imprimirJogo(velocidade);
        }

        for (int j = 0; j < jogo[0].length; j++) {

            jogo[jogo.length - 1][j] = new Cor("44");
            jogo[jogo.length - 1][tamanhoCano - 2] = new Cor("42");
            jogo[jogo.length - 1][tamanhoCano - 1] = new Cor("42");
            jogo[jogo.length - 1][tamanhoCano + espacoCano] = new Cor("42");
            jogo[jogo.length - 1][tamanhoCano + espacoCano + 1] = new Cor("42");

        }

        moverJogo();

        for (int j = 0; j < jogo[0].length; j++) {

            jogo[jogo.length - 1][j] = new Cor("44");

        }


    }

    static void subirPassaro(){

        if (jogo[50][cima].getCor().equals("42") || jogo[58][cima].getCor().equals("42")){
            imprimirJogo(velocidade);
            System.out.println("Você perdeu cima");
            System.exit(0);
        }


        jogo[57][cima - 1].setCor(jogo[57][cima].getCor());
        jogo[58][cima - 1].setCor(jogo[58][cima].getCor());
        jogo[59][cima - 1].setCor(jogo[59][cima].getCor());

        jogo[50][cima].setCor(jogo[50][meio].getCor());
        jogo[51][cima].setCor(jogo[51][meio].getCor());
        jogo[52][cima].setCor(jogo[52][meio].getCor());
        jogo[53][cima].setCor(jogo[53][meio].getCor());
        jogo[54][cima].setCor(jogo[54][meio].getCor());
        jogo[55][cima].setCor(jogo[55][meio].getCor());
        jogo[56][cima].setCor(jogo[56][meio].getCor());
        jogo[57][cima].setCor(jogo[57][meio].getCor());
        jogo[58][cima].setCor(jogo[58][meio].getCor());
        jogo[59][cima].setCor(jogo[59][meio].getCor());

        jogo[50][meio].setCor(jogo[50][baixo].getCor());
        jogo[51][meio].setCor(jogo[51][baixo].getCor());
        jogo[52][meio].setCor(jogo[52][baixo].getCor());
        jogo[53][meio].setCor(jogo[53][baixo].getCor());
        jogo[54][meio].setCor(jogo[54][baixo].getCor());
        jogo[55][meio].setCor(jogo[55][baixo].getCor());
        jogo[56][meio].setCor(jogo[56][baixo].getCor());
        jogo[57][meio].setCor(jogo[57][baixo].getCor());
        jogo[58][meio].setCor(jogo[58][baixo].getCor());
        jogo[59][meio].setCor(jogo[59][baixo].getCor());

        jogo[50][baixo].setCor("44");
        jogo[51][baixo].setCor("44");
        jogo[52][baixo].setCor("44");
        jogo[53][baixo].setCor("44");
        jogo[54][baixo].setCor("44");
        jogo[55][baixo].setCor("44");
        jogo[56][baixo].setCor("44");
        jogo[57][baixo].setCor("44");
        jogo[58][baixo].setCor("44");

        cima--;
        baixo--;
        meio--;


    }

    static void descerPassaro(){

        if (jogo[50][baixo + 1].getCor().equals("42") || jogo[58][baixo + 1].getCor().equals("42")){
            System.out.println("Você perdeu baixo");
            System.exit(0);
        }

        jogo[51][baixo + 1].setCor(jogo[51][baixo].getCor());
        jogo[52][baixo + 1].setCor(jogo[52][baixo].getCor());
        jogo[53][baixo + 1].setCor(jogo[53][baixo].getCor());
        jogo[54][baixo + 1].setCor(jogo[54][baixo].getCor());
        jogo[55][baixo + 1].setCor(jogo[55][baixo].getCor());
        jogo[56][baixo + 1].setCor(jogo[56][baixo].getCor());
        jogo[57][baixo + 1].setCor(jogo[57][baixo].getCor());
        jogo[58][baixo + 1].setCor(jogo[58][baixo].getCor());


        jogo[50][baixo].setCor(jogo[50][meio].getCor());
        jogo[51][baixo].setCor(jogo[51][meio].getCor());
        jogo[52][baixo].setCor(jogo[52][meio].getCor());
        jogo[53][baixo].setCor(jogo[53][meio].getCor());
        jogo[54][baixo].setCor(jogo[54][meio].getCor());
        jogo[55][baixo].setCor(jogo[55][meio].getCor());
        jogo[56][baixo].setCor(jogo[56][meio].getCor());
        jogo[57][baixo].setCor(jogo[57][meio].getCor());
        jogo[58][baixo].setCor(jogo[58][meio].getCor());

        jogo[50][meio].setCor(jogo[50][cima].getCor());
        jogo[51][meio].setCor(jogo[51][cima].getCor());
        jogo[52][meio].setCor(jogo[52][cima].getCor());
        jogo[53][meio].setCor(jogo[53][cima].getCor());
        jogo[54][meio].setCor(jogo[54][cima].getCor());
        jogo[55][meio].setCor(jogo[55][cima].getCor());
        jogo[56][meio].setCor(jogo[56][cima].getCor());
        jogo[57][meio].setCor(jogo[57][cima].getCor());
        jogo[58][meio].setCor(jogo[58][cima].getCor());
        jogo[59][meio].setCor(jogo[59][cima].getCor());

        jogo[57][cima].setCor("44");
        jogo[58][cima].setCor("44");
        jogo[59][cima].setCor("44");

        cima++;
        baixo++;
        meio++;

    }

    static void moverJogo() {

        for (int j = 0; j < jogo[0].length; j++) {
            for (int i = 0; i < jogo.length; i++) {
                try {
                    if (!jogo[i + 1][j].getCor().equals("41") && !jogo[i + 1][j].getCor().equals("43") && !jogo[i + 1][j].getCor().equals("40")
                            && !jogo[i][j].getCor().equals("41") && !jogo[i][j].getCor().equals("43") && !jogo[i][j].getCor().equals("40")) {
                        jogo[i][j].setCor(jogo[i + 1][j].getCor());
                    } else if (jogo[i][j].getCor().equals("40") && jogo[i + 1][j].getCor().equals("42")){
                        System.out.println("Você perdeu!");
                        System.exit(0);
                    }

                } catch (Exception e) {

                }
            }
        }

        if (pular == true) {

            try {
                for (int i = 0; i < 4; i++) {
                    subirPassaro();

                }

                pular = false;

            } catch (Exception e) {

            }


        } else {

            try {



                   descerPassaro();




            } catch (Exception e) {

            }
        }


    }

    static void imprimirJogo(int velocidade) {

        String q = "";


        for (int j = 0; j < jogo[0].length; j++) {
            for (int i = 0; i < jogo.length; i++) {
                q += jogo[i][j].toString();


            }

            System.out.println(q + "\u001B[40m");
            q = "";
        }

        try {
            Thread.sleep(velocidade);
        } catch (Exception e) {

        }


    }
}
