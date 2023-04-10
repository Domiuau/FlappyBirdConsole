import model.Cor;
import model.OlhoDoPassaro;

import java.util.Random;

public class A {

    static Cor[][] jogo = new Cor[200][40];
    static Random random = new Random();
    static int tamanhoCano;
    static int cima,baixo,meio;

    public static void main(String[] args) {

        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[0].length; j++) {

                jogo[i][j] = new Cor("44");


            }
        }

        cima = 9;
        meio = 10;
        baixo = 11;

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








        for (int j = 0; j < jogo[0].length; j++) {
            for (int i = 0; i < jogo.length; i++) {

                System.out.print(jogo[i][j]);



            }

            System.out.println();

        }

        imprimirJogo();

        adiconarCano();
        int distania = random.nextInt(11)+25;
        int contador = 0;





        for (int i = 0; i <800 ; i++) {
            try {
                Thread.sleep(200);
            } catch (Exception e){

            }

            moverJogo();
            imprimirJogo();
            contador++;
            if (contador == distania){
                adiconarCano();
                contador = 0;
                distania = random.nextInt(21)+10;
            }
        }









    }

    static void adiconarCano() {

       tamanhoCano = random.nextInt(11) + 14;

        jogo[jogo.length-1][tamanhoCano-2] = new Cor("42");
        jogo[jogo.length-1][tamanhoCano-1] = new Cor("42");
        jogo[jogo.length-1][tamanhoCano+9] = new Cor("42");
        jogo[jogo.length-1][tamanhoCano+10] = new Cor("42");
        moverJogo();

//ajustar dps para receber alguem tamanho
        for (int j = 0; j < tamanhoCano; j++) {

            jogo[jogo.length-1][j].setCor("42");

        }




//        jogo[jogo.length-1][tamanhoCano-1] = new Cor("42");
//        jogo[jogo.length-1][tamanhoCano] = new Cor("42");
//        moverJogo();

      //  jogo[jogo.length-1][tamanhoCano] = new Cor("44");

        for (int j = tamanhoCano+9; j < jogo[0].length; j++) {
            jogo[jogo.length-1][j].setCor("42");
        }

        for (int i = 0; i <15 ; i++) {
            try {
                Thread.sleep(200);
            } catch (Exception e){

            }

            moverJogo();
            imprimirJogo();
        }

        for (int j = 0; j < jogo[0].length; j++) {

            jogo[jogo.length-1][j] = new Cor("44");
            jogo[jogo.length-1][tamanhoCano-2] = new Cor("42");
            jogo[jogo.length-1][tamanhoCano-1] = new Cor("42");
            jogo[jogo.length-1][tamanhoCano+9] = new Cor("42");
            jogo[jogo.length-1][tamanhoCano+10] = new Cor("42");

        }

        moverJogo();

        for (int j = 0; j < jogo[0].length; j++) {

            jogo[jogo.length-1][j] = new Cor("44");

        }




    }

    static void moverJogo(){

        for (int j = 0; j <jogo[0].length; j++) {
            for (int i = 0; i <jogo.length; i++) {
                try {
                    if (!jogo[i+1][j].getCor().equals("41") && !jogo[i+1][j].getCor().equals("43") && !jogo[i+1][j].getCor().equals("40")
                    && !jogo[i][j].getCor().equals("41") && !jogo[i][j].getCor().equals("43") && !jogo[i][j].getCor().equals("40")) {
                        jogo[i][j].setCor(jogo[i+1][j].getCor());
                    }




//                    jogo[50][9] = new Cor("44");
//                    jogo[51][9] = new Cor("44");
//                    jogo[52][9] = new Cor("44");
//                    jogo[53][9] = new Cor("44");
//                    jogo[54][9] = new Cor("44");
//                    jogo[55][9] = new Cor("44");
//                    jogo[56][9] = new Cor("44");







                } catch (Exception e){

                }
            }
        }

        try {
            jogo[51][baixo+1].setCor(jogo[51][baixo].getCor());
            jogo[52][baixo+1].setCor(jogo[52][baixo].getCor());
            jogo[53][baixo+1].setCor(jogo[53][baixo].getCor());
            jogo[54][baixo+1].setCor(jogo[54][baixo].getCor());
            jogo[55][baixo+1].setCor(jogo[55][baixo].getCor());
            jogo[56][baixo+1].setCor(jogo[56][baixo].getCor());
            jogo[57][baixo+1].setCor(jogo[57][baixo].getCor());
            jogo[58][baixo+1].setCor(jogo[58][baixo].getCor());


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

            jogo[57][cima].setCor(jogo[57][cima-1].getCor());
            jogo[58][cima].setCor(jogo[58][cima-1].getCor());
            jogo[59][cima].setCor(jogo[59][cima-1].getCor());

            cima++;
            baixo++;
            meio++;
        } catch (Exception e){

        }







    }

    static void imprimirJogo(){

        String q = "";
//
//        for (int j = 0; j < 10; j++) {
//            for (int i = 0; i < jogo.length; i++) {
//                System.out.print(jogo[i][0]);
//            }
//
//            System.out.println("\u001B[40m");
//        }

//
//        for (int i = 0; i < 10; i++) {
//            System.out.println("\u001B[44m                                                                                                                                                                                                        \u001B[40m ");
//        }


        for (int j = 0; j < jogo[0].length; j++) {
            for (int i = 0; i < jogo.length; i++) {
                q += jogo[i][j].toString();



            }

            System.out.println(q + "\u001B[40m");
            q = "";
        }

        //System.out.println(q);

//        String a = "";
//
//        for (int i = 0; i < 10; i++) {
//            System.out.println("\u001B[44m                                                                                                                                                                                                        \u001B[40m ");
//        }
//
//        for (int j = 0; j < jogo[0].length; j++) {
//            for (int i = 0; i < jogo.length; i++) {
//
//                System.out.print(jogo[i][j].toString());
//
//
//
//
//
//
//            }
//            System.out.println("\u001B[40m");
//
//        }


    }
}
