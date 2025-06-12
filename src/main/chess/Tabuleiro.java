
// Tabuleiro.java
package main.chess;
public class Tabuleiro {
    private Casa[][] casas = new Casa[8][8];
    public Tabuleiro() { construirTabuleiro(); }
    public void construirTabuleiro() {
        for (int l = 1; l <= 8; l++) {
            for (char c = 'a'; c <= 'h'; c++) {
                casas[l - 1][c - 'a'] = new Casa(new Posicao(l, c));
            }
        }
    }
    public Casa getCasa(Posicao pos) {
        return casas[pos.rowIndex()][pos.colIndex()];
    }
    public boolean isCaminhoLivre(Posicao o, Posicao d) {
        int dr = Integer.signum(d.getLinha() - o.getLinha());
        int dc = Integer.signum(d.getColuna() - o.getColuna());
        int r = o.rowIndex() + dr, c = o.colIndex() + dc;
        while (r != d.rowIndex() || c != d.colIndex()) {
            if (casas[r][c].isOccupied()) return false;
            r += dr; c += dc;
        }
        return true;
    }
}
