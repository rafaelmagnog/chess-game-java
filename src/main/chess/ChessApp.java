
// ChessApp.java
package main.chess;
import java.util.Scanner;
public class ChessApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do Jogador 1 (branco): ");
        Jogador j1 = new Jogador(sc.nextLine(), Cor.BRANCO);
        System.out.print("Nome do Jogador 2 (preto): ");
        Jogador j2 = new Jogador(sc.nextLine(), Cor.PRETO);
        new Jogo(j1, j2).jogar();
        sc.close();
    }
}
