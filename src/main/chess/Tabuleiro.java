package main.chess;

import java.util.*;

public class Tabuleiro {
    private Casa[][] casas = new Casa[8][8];

    public Tabuleiro() {
        construirTabuleiro();
        iniciarTabuleiro();
    }

    /** cria as 64 casas */
    public void construirTabuleiro() {
        for (int i = 1; i <= 8; i++) {
            for (char c = 'a'; c <= 'h'; c++) {
                casas[i-1][c-'a'] = new Casa(new Posicao(i, c));
            }
        }
    }

    /** posiciona as peças nas casas iniciais */
    public void iniciarTabuleiro() {
        // jogadores criarão as peças após instanciar o tabuleiro
    }

    public Casa getCasa(Posicao pos) {
        return casas[pos.rowIndex()][pos.colIndex()];
    }

    /** return true se todas as casas entre orig e destino estao vazias */
    public boolean isCaminhoLivre(Posicao orig, Posicao dest) {
        int dr = Integer.signum(dest.getLinha() - orig.getLinha());
        int dc = Integer.signum(dest.getColuna() - orig.getColuna());
        int r = orig.rowIndex() + dr, c = orig.colIndex() + dc;
        while (r != dest.rowIndex() || c != dest.colIndex()) {
            if (casas[r][c].isOccupied()) return false;
            r += dr; c += dc;
        }
        return true;
    }

    /** testa se após o mov, o rei do mov.peca fica em xeque */
    public boolean testaMovimentoXeque(Movimento mov) {
        // para simplificar, não implementado detalhe de simulação
        return false;
    }
}
