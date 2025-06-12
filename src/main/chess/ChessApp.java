package main.chess;

import java.util.Scanner;

/**
 * Ponto de entrada do jogo de xadrez em linha de comando.
 * Lê nomes dos jogadores, cria a instância de Jogo e inicia o loop de jogadas.
 */
public class ChessApp {
    public static void main(String[] args) {
        // Scanner para leitura de entradas do usuário
        Scanner sc = new Scanner(System.in);

        // Solicita nome do jogador que controlará as peças brancas
        System.out.print("Nome do Jogador 1 (branco): ");
        Jogador j1 = new Jogador(sc.nextLine(), Cor.BRANCO);

        // Solicita nome do jogador que controlará as peças pretas
        System.out.print("Nome do Jogador 2 (preto): ");
        Jogador j2 = new Jogador(sc.nextLine(), Cor.PRETO);

        // Cria uma partida e inicia o loop de jogadas
        Jogo partida = new Jogo(j1, j2);
        partida.jogar();

        // Fecha o scanner ao final da partida
        sc.close();
    }
}