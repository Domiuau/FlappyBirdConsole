import model.Cor;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FlappyBird {

    //Matriz principal do jogo, onde o jogo rodará
    static Cor[][] jogo = new Cor[200][40];
    //Random utilizado para gerar espaços aleatórios entre os canos
    static Random random = new Random();
    //Variável responsável por definir o espaçamento no cano
    static int tamanhoCano;
    //Variáveis responsáveis por projetar o pássaro
    static int cima, baixo, meio;
    //Variável responsável por definir quando um cano será colocado
    static int contador = 0;

    //Variável responsável pela largura do cano
    static int larguraCano = 15;

    //Variável responsável por controlar a quantidade de frames por segundo do jogo
    static int velocidade;
    //Controlar se o pássaro irá pular no proximo frame ou não
    static boolean pular = false;
    //Scanner responsável por receber as informações de dificuldade do jogo e quando pular
    static Scanner scanner = new Scanner(System.in);

    //Variável responsável por definir a distância entre os canos, distânciaBase + número de 0 a 8
    static int distancia = 0;
    //Distância base definida pelo usuário para a distância de um cano para outro

    static int distanciaBase;
    //Definir o espaço que o pássaro tem para passar
    static int espacoCano;

    static int entrada;

    public static void main(String[] args) {

        //Preenche toda a matriz com um objeto chamado "cor", responsável por definir o que é céu, pássaro ou cano

        //linha
        for (int i = 0; i < jogo.length; i++) {
            //coluna
            for (int j = 0; j < jogo[0].length; j++) {
                //Toda a matriz do jogo é preenchida com a cor "44", que representa o azul
                jogo[i][j] = new Cor("44");


            }
        }


        cima = 0;
        meio = 1;
        baixo = 2;

        System.out.println("\n  ______ _                         ____  _         _ \n" +
                " |  ____| |                       |  _ \\(_)       | |\n" +
                " | |__  | | __ _ _ __  _ __  _   _| |_) |_ _ __ __| |\n" +
                " |  __| | |/ _` | '_ \\| '_ \\| | | |  _ <| | '__/ _` |\n" +
                " | |    | | (_| | |_) | |_) | |_| | |_) | | | | (_| |\n" +
                " |_|    |_|\\__,_| .__/| .__/ \\__, |____/|_|_|  \\__,_|\n" +
                "                | |   | |     __/ |                  \n" +
                "                |_|   |_|    |___/  \n\n");
        System.out.println("Coloque qualquer caractere para fazer o pássaro pular (coloque e mande instantaneamente)");
        System.out.println("Insira uma velocidade de jogo (quantidade de quadros por segundo)");
        System.out.println("1 quandro - lerdo");
        System.out.println("2 quandro - recomendado médio");
        System.out.println("3 quandro - recomendado rápido");
        System.out.println("4 quandro - jogável");
        System.out.println("5 quandro - quase injogável");
        //Aqui é definido a quantidade de quadros por segundo que o jogo rodará, exemplo: caso o usuário insira 4, será 1000 % 4, então a tela atualizará a cada 250 milissegundos
        entrada = (int) 1000 / scanner.nextInt();

        //Aqui é solicitado a distância entre um cano e outro
        System.out.println("Escolha a distância entre os canos (recomendado - 20)");
        distanciaBase = scanner.nextInt();
        //O primeiro loop de distância é definido
        distancia = 10;

        //Aqui é solicitado o espaço entre o cano, lugar por onde o pássaro passa
        System.out.println("Escolha o espaço entre o cano (por onde o pássaro irá passar - recomendado - 10)");
        espacoCano = scanner.nextInt();
        //E aqui é solicitada a largura do cano
        System.out.println("Escolha a largura de cada cano (recomendado - 15)");
        larguraCano = scanner.nextInt();

        //O método scanner() é chamado, responsável por ler quando o pássaro deve pular
        scanner();

        //As linhas abaixo trocam a cor de determinadas cordenadas da matriz para preto, representando o corpo do pássaro

        //Parte de baixo do pássaro "▒▒▒▒▒▒▒▒" com a cor preta
        jogo[51][baixo].setCor("40");
        jogo[52][baixo].setCor("40");
        jogo[53][baixo].setCor("40");
        jogo[54][baixo].setCor("40");
        jogo[55][baixo].setCor("40");
        jogo[56][baixo].setCor("40");
        jogo[57][baixo].setCor("40");
        jogo[58][baixo].setCor("40");

        //Parte do meio "▒▒▒▒▒▒▒▒▒" com a cor preta
        jogo[50][meio].setCor("40");
        jogo[51][meio].setCor("40");
        jogo[52][meio].setCor("40");//          ▒▒▒
        jogo[53][meio].setCor("40");//   ▒▒▒▒▒▒▒▒▒
        jogo[54][meio].setCor("40");//    ▒▒▒▒▒▒▒▒
        jogo[55][meio].setCor("40");
        jogo[56][meio].setCor("40");
        jogo[57][meio].setCor("40");
        jogo[58][meio].setCor("40");

        //Parte de cima "▒▒▒" com os 2 primeiros espaços pintados de amarelo e o terceiro de vermelho
        jogo[57][cima].setCor("43");
        jogo[58][cima].setCor("43");
        jogo[59][cima].setCor("41");

        //A distância inserida pelo usuário é somada com um valor aleatório de 0 a 8, para que a distância entre os canos não sejam sempre as mesmas
        //distancia += random.nextInt(9);
        contador = 0;

        //O jogo avança 35 quadros que não são visíveis ao usuário para que a distância entre o pássaro e o primeiro cano não seja muito grande no começo, sem isso o jogo iria começar como no exemplo abaixo:
        for (int i = 0; i < 135; i++) {

            loopGame(velocidade);
            if (jogo[90][0].getCor().equals("42"))
                break;
        }

        //O pássaro é levantado para o meio da tela, sem isso o pássaro começaria no "chão"
        for (int i = 0; i < 20; i++) {
            subirPassaro();
        }

        //A quantidade de quadros por segundo é passada á variável responsável por controlar a velocidade em que o jogo passa
        velocidade = entrada;

        //Após receber as informações de jogo do usuário e preparar o ambiente, o loop do jogo é iniciado
        while (true) {
            loopGame(velocidade);
        }


    }

    /**
     * realiza um quadro toda vez que é chamado
     *
     * @param velocidade1 velocidade em que esses quadros serão atualizados
     */
    static void loopGame(int velocidade1) {
        //Os métodos a baixo serão explicados mais tarde
        //O jogo é movido nessa direção <---
        moverJogo();
        //Imprimi o jogo passando a quantidade de quadros por sengundo que foi passada no método
        imprimirJogo(velocidade1);
        //A cada vez que o método é chamado, é adicionado 1 a uma variável chamada "contador"
        contador++;
        //Quando a variável contador tiver o mesmo valor da variável distância (que tem o valor inicial que o usuário inseriu + um número de 0 a 8), significa que é hora de um cano ser adicionado
        if (contador == distancia) {
            //Chama o método responsável por adicionar um cano na matriz do jogo
            adicionarCano();
            //Volta o contador para 0,
            contador = 0;
            distancia = random.nextInt(9) + distanciaBase;
        }
    }

    /**
     * Método responsável por receber e registrar quando o pássaro deve pular
     */

    static void scanner() {

        //Aqui uma nova Thread é criada, sem ela não teria como fazer o jogo funcionar, pois o programa congelaria até que o usuário enviasse algum caractere
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Dentro da thread, um scanner é chamado apenas para detectar quando algo é enviado, nada é feito com o valor recebido
                scanner.next();
                //Após ele receber alguma coisa, a variável "pula" recebe true, sinalizando que no proximo frame o pássaro deve pular
                pular = true;
                //Após isso a função é chamada novamente, entrando em um loop para aguardar as próximas entradas do usuário
                scanner();
            }
        });

        thread.start();
    }

    /**
     * Método responsável por fazer o jogo inteiro se mover
     */

    static void moverJogo() {

        //coluna
        for (int j = 0; j < jogo[0].length; j++) {
            //linha
            for (int i = 0; i < jogo.length; i++) {

                //para mover o jogo, primeiro precisamos identificar o que é pássaro e o que é cano, pois o programa entende tudo como espaços pintados de cores diferentes
                //Para ser feita esse identificação, as cores da posição selecionada no for acima são pegas e comparadas, as cores 41, 43 e 40, são as cores do pássaro
                try {
                    if (!jogo[i + 1][j].getCor().equals("41") && !jogo[i + 1][j].getCor().equals("43") && !jogo[i + 1][j].getCor().equals("40")
                            && !jogo[i][j].getCor().equals("41") && !jogo[i][j].getCor().equals("43") && !jogo[i][j].getCor().equals("40")) {
                        //A linha abaixo é rodada em toda a matriz, e caso a cor selecionada não seja uma cor pertencente a uma cor do pássaro e a cor seguinte tambpem não, então essa posição é movida
                        //Assim é garantido que o pássaro fique estático dando a sensação de que o jogo tem um "fundo"
                        jogo[i][j].setCor(jogo[i + 1][j].getCor());
                    } else if (jogo[i][j].getCor().equals("40") && jogo[i + 1][j].getCor().equals("42")) {
                        //Aqui é comparado se a cor selecionado é a cor preta e a posição a frente dela tem a cor verde, caso isso seja verdadeiro, significa que o pássaro irá colidir com o cano no próximo frame, então o jogo acaba
                        System.out.println("\u001B[m5" +
                                "Você perdeu!");
                        System.exit(0);
                    }

                } catch (Exception e) {
                    //Tudo está dentro do bloco try catch para evitar o acesso a um índice que não existe, após chegar ao i (linhas) máximo, o programa rodaria novamente, tentando acessar i+1, então lançando uma exceção e parando o programa
                }
            }
        }

        //Após mover o cenário, é preciso mover o pássaro

        if (pular == true) {

            //Assim que o método é acessado, a variável que defini se o pássaro deve pular ou não no presente frame é passada como false, assim garantindo que o pássaro só irá pular novamente após o comando do usuário
            pular = false;

            //Após isso, o pássaro sobe 4 espaços utilizando o método abaixo

            try {
                for (int i = 0; i < 4; i++) {
                    subirPassaro();
                }

            } catch (Exception e) {

            }

        } else {

            try {
                //Agora caso a variável pular seja falsa, então o pássaro deve descer, o que simula a gravidade
                descerPassaro();
            } catch (Exception e) {

            }
        }
    }

    /**
     * Método responsável por adicionar um cano a matriz de jogo
     */
    static void adicionarCano() {

        //A variável "tamanhoCano" recebe onde um cano deve começar sua abertura, por exemplo: o usuário entrou com um tamanho de 10 espaços entre o cano para o pássaro passar, a largura de um cano pode variar, porém a altura não, ela sempre será 40
        //então pensando verticalmente, começa uma "linha" verde na vertical, que terá de 9 a 19 de altura, após isso, vem a abertura no meio do cano para o pássaro passar, que no caso exemplificado é de 10, temos 40 "pixeis" sendo de 9 a 19 uma linha verde + 10 do espaço no meio do cano e o que sobrar desses 40 pixeis é o resto do cano, a parte que ficará em baixo do espaço
        tamanhoCano = random.nextInt(11) + 9;

        //Aqui são criadas as bordinhas dos canos:
        // --> ▒▒▒▒▒▒▒▒▒▒ <--
        //      ▒▒▒▒▒▒▒▒
        //      ▒▒▒▒▒▒▒▒
        //      ▒▒▒▒▒▒▒▒

        //Continuando o exemplo citado anteriormente, "jogo.length - 1" representa o máximo possível a direita, direção oposta de para onde o jogo roda, e "tamanhoCano" é qual a posição verticalmente que o espaço entre o cano deve começar, então "-1" e "-2" sçao respectivamente 1 e 2 posições acima antes do espaço do cano, e "+0" e "+1" são 1 e 2 posições depois do espaçamento definido como 10
        //Essas cordenadas em específico são "coloridas" de verde antes, assim são formadas as bordinhas do cano
        jogo[jogo.length - 1][tamanhoCano - 2] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano - 1] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano + espacoCano] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano + espacoCano + 1] = new Cor("42");
        //Após as bordinhas serem desenhadas na tela, ao lado direito teremos algo parecido com:

        //   ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒
        //   ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒
        //   ▒▒▒▒▒▒▒▒                       ▒ <-
        //   ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒         ▒ <-
        //                 ▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒
        //   ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒
        //   ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒

        //Após as bordinhas serem formadas no lugar certo, o jogo é movido
        moverJogo();

        //Após o jogo ser movido, algo parecido com isso é formado

        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒                       ▒▒
        //  ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒
        // ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒         ▒▒
        //                ▒▒▒▒▒▒▒▒
        // ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒

        //Agora esse for é responsável por construir o restante do cano acima do espaço, o que formará isso
        for (int j = 0; j < tamanhoCano; j++) {
            jogo[jogo.length - 1][j].setCor("42"); // <- O máximo a direita, mexendo no J (coluna) do 0 até o começo do espaço do cano
        }

        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒          ▒
        //  ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒         ▒
        //  ▒▒▒▒▒▒▒▒                       ▒▒
        //  ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒
        // ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒         ▒▒
        //                ▒▒▒▒▒▒▒▒
        // ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒
        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒


        //E esse for abaixo é responsável por construir o restante do cano abaixo do espaço, "tamanhoCano + espacoCano" é igual ao índice onde o espaço acaba, o tamanho acima do espaço pode ser de 9 a 19, o tamanho do espaço no cano neste exemplo é de 10, então esse valores somados dará onde o linha deve começar e ir até o 40 (número limite verticalmente)
        //Então após a execução desse loop formará isso

        for (int j = tamanhoCano + espacoCano; j < jogo[0].length; j++) {
            jogo[jogo.length - 1][j].setCor("42");
        }

        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒          ▒
        //  ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒         ▒
        //  ▒▒▒▒▒▒▒▒                       ▒▒
        //  ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒▒▒
        // ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒         ▒▒
        //                ▒▒▒▒▒▒▒▒          ▒
        // ▒▒▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒          ▒
        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒          ▒
        //  ▒▒▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒          ▒

        //Feito isso, ja temos a base do cano, seu espaço definido, a localização do espaço e suas bordinhas

        //Agora é preciso deixar ele largo de acordo com o valor inserido pelo usuário, então caso o usuário insira uma largura de 10, então o jogo é movido 10 vezes
        for (int i = 0; i < larguraCano; i++) {

            //A verificação abaixo acontece para mudar a velocidade em que o cano é formado, sem isso caso o usuário colocasse uma largura muito algo para o cano, o jogo acabaria instanteneamente
            if (jogo[90][0].getCor().equals("42"))
                velocidade = entrada;

            moverJogo();
            //Após cada vez que o jogo é movido, ele é atualizado na tela do usuário, logo o restante do cano começa a "surgir" da direita
            imprimirJogo(velocidade);
        }

        //Movido 1 vez

        // ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒          ▒
        // ▒▒▒▒▒▒▒▒    ▒▒▒▒▒▒▒▒▒▒         ▒
        // ▒▒▒▒▒▒▒▒                      ▒▒
        // ▒▒▒▒▒▒▒▒    ▒▒▒▒▒▒▒▒▒▒
        //▒▒▒▒▒▒▒▒▒▒    ▒▒▒▒▒▒▒▒         ▒▒
        //              ▒▒▒▒▒▒▒▒          ▒
        //▒▒▒▒▒▒▒▒▒▒    ▒▒▒▒▒▒▒▒          ▒
        // ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒          ▒
        // ▒▒▒▒▒▒▒▒     ▒▒▒▒▒▒▒▒          ▒

        //movido de acordo com a largura inserida pelo usuário ficará assim

        //▒▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒▒    ▒▒▒▒▒▒▒▒▒▒         ▒▒▒▒▒▒▒▒
        //▒▒                      ▒▒▒▒▒▒▒▒▒
        //▒▒    ▒▒▒▒▒▒▒▒▒▒
        //▒▒▒    ▒▒▒▒▒▒▒▒         ▒▒▒▒▒▒▒▒▒
        //       ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒▒▒    ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒

        //Após chegar nesse estado, toda a coluna da direita é pintada com a cor azul, representando que é o fim do cano

        for (int j = 0; j < jogo[0].length; j++) {

            jogo[jogo.length - 1][j] = new Cor("44");

        }
        //E por cima dessas cores azuis, as bordinhas do final do cano são pintadas de verde, do mesmo jeito das bordas iniciais

        jogo[jogo.length - 1][tamanhoCano - 2] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano - 1] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano + espacoCano] = new Cor("42");
        jogo[jogo.length - 1][tamanhoCano + espacoCano + 1] = new Cor("42");

        moverJogo();

        //O jogo é movido e após a conclusão, isso estará formado

        //▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒    ▒▒▒▒▒▒▒▒▒▒         ▒▒▒▒▒▒▒▒
        //▒                      ▒▒▒▒▒▒▒▒▒▒▒
        //▒    ▒▒▒▒▒▒▒▒▒▒
        //▒▒    ▒▒▒▒▒▒▒▒         ▒▒▒▒▒▒▒▒▒▒▒
        //      ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒▒    ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒

        //então a ultima coluna é pintada de azul novamente

        for (int j = 0; j < jogo[0].length; j++) {

            jogo[jogo.length - 1][j] = new Cor("44");

        }

        //Ficando assim

        //▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒    ▒▒▒▒▒▒▒▒▒▒         ▒▒▒▒▒▒▒▒
        //▒                      ▒▒▒▒▒▒▒▒▒▒
        //▒    ▒▒▒▒▒▒▒▒▒▒
        //▒▒    ▒▒▒▒▒▒▒▒         ▒▒▒▒▒▒▒▒▒▒
        //      ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒▒    ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒
        //▒     ▒▒▒▒▒▒▒▒          ▒▒▒▒▒▒▒▒

        //E assim é formado um cano


    }

    /**
     * Método responsável por fazer o pássaro subir
     */

    static void subirPassaro() {

        //Aqui é verificado se após o pássaro subir, ele não baterá em nenhum cano a cima dele, caso ele encoste em algum o jogo acaba
        if (jogo[50][cima].getCor().equals("42") || jogo[58][cima].getCor().equals("42")) {
            imprimirJogo(velocidade);
            System.out.println("Você perdeu cima");
            System.exit(0);
        }


        //Caso ele não encoste em nenhum cabo após pular, ele é redesenhado a cima

        //para chegar até aqui "cima +1" é uma linha toda azul, sendo assim:
        // cima -1 ->
        // cima ->         ▒▒▒
        // meio ->  ▒▒▒▒▒▒▒▒▒
        // baixo ->  ▒▒▒▒▒▒▒▒

        jogo[57][cima - 1].setCor(jogo[57][cima].getCor());
        jogo[58][cima - 1].setCor(jogo[58][cima].getCor());
        jogo[59][cima - 1].setCor(jogo[59][cima].getCor());

        //então cima +1 recebe cima, ficando assim:

        // cima -1 ->      ▒▒▒
        // cima ->         ▒▒▒
        // meio ->  ▒▒▒▒▒▒▒▒▒
        // baixo ->  ▒▒▒▒▒▒▒▒

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

        //agora cima recebe meio, formando:

        // cima -1 ->      ▒▒▒
        // cima ->  ▒▒▒▒▒▒▒▒▒
        // meio ->  ▒▒▒▒▒▒▒▒▒
        // baixo ->  ▒▒▒▒▒▒▒▒

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

        //e meio recebe baixo, fomando:

        // cima -1 ->      ▒▒▒
        // cima ->  ▒▒▒▒▒▒▒▒▒
        // meio ->   ▒▒▒▒▒▒▒▒
        // baixo ->  ▒▒▒▒▒▒▒▒

        //Por fim, todas as posições de baixo recebe a cor "44" azul, ou seja, virando céu

        jogo[50][baixo].setCor("44");
        jogo[51][baixo].setCor("44");
        jogo[52][baixo].setCor("44");
        jogo[53][baixo].setCor("44");
        jogo[54][baixo].setCor("44");
        jogo[55][baixo].setCor("44");
        jogo[56][baixo].setCor("44");
        jogo[57][baixo].setCor("44");
        jogo[58][baixo].setCor("44");

        // cima -1 ->      ▒▒▒
        // cima ->  ▒▒▒▒▒▒▒▒▒
        // meio ->   ▒▒▒▒▒▒▒▒
        // baixo ->

        //Após isso as cordenadas de onde o pássaro está são atualizadas

        cima--;
        baixo--;
        meio--;


    }

    /**
     * Método responsável por fazer o pássaro descer
     */

    static void descerPassaro() {

        //O método funciona da mesma maneiro do método acima, mas faz o inverso

        if (jogo[50][baixo + 1].getCor().equals("42") || jogo[58][baixo + 1].getCor().equals("42") || jogo[56][cima].getCor().equals("42")) {
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

        jogo[50][meio].setCor("44");
        jogo[51][meio].setCor("44");
        jogo[52][meio].setCor("44");
        jogo[53][meio].setCor("44");
        jogo[54][meio].setCor("44");
        jogo[55][meio].setCor("44");
        jogo[56][meio].setCor("44");
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

    /**
     * Método responsável por imprimir o jogo e pausar ele de acordo com a velocidade escolhida
     * @param velocidade
     */

    static void imprimirJogo(int velocidade) {

        //Ao chamar esse método, ele imprime a matriz do jogo, que já foi manipulada anteriormente
        //Para a impressão ser realizada corretamente é necessário começar da coluna

        //A String linha armazena cada linha a ser desenhada
        String linha = "";

        //Coluna
        for (int j = 0; j < jogo[0].length; j++) {
            //linha
            for (int i = 0; i < jogo.length; i++) {
                linha += jogo[i][j].toString();

            }
            //Após a impressão de cada linha, é feita uma quebra de linha para a impressão das linhas subsequentes
            System.out.println(linha + "\u001B[40m");
            //A variável "linha" é limpa para receber a próxima linha após ser impressa
            linha = "";

        }

        try {
            Thread.sleep(velocidade);
        } catch (Exception e) {

        }
    }
}
