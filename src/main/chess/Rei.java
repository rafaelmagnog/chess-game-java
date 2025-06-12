// Rei.java
package main.chess;
public class Rei extends Peca {
    public Rei(Cor cor, Casa casa, Jogador dono) { super(cor, casa, dono); }
    @Override public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao o = casaAtual.getPosicao();
        int dx = Math.abs(o.getLinha() - destino.getLinha());
        int dy = Math.abs(o.getColuna() - destino.getColuna());
        if (dx <= 1 && dy <= 1 && (dx + dy > 0)) {
            Peca alvo = tab.getCasa(destino).getPeca();
            return alvo == null || alvo.getCor() != cor;
        }
        return false;
    }

    @Override
    public String getSymbol() { return "R"; }
}
