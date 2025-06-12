
// Cavalo.java
package main.chess;
public class Cavalo extends Peca {
    public Cavalo(Cor cor, Casa casa, Jogador dono) { super(cor, casa, dono); }
    @Override public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao o = casaAtual.getPosicao();
        int dx = Math.abs(o.getLinha() - destino.getLinha());
        int dy = Math.abs(o.getColuna() - destino.getColuna());
        if (dx * dy != 2) return false;
        Peca alvo = tab.getCasa(destino).getPeca();
        return alvo == null || alvo.getCor() != cor;
    }
    // Cavalo.java
    @Override
    public String getSymbol() { return "C"; }

}

