
// Bispo.java
package main.chess;
public class Bispo extends Peca {
    public Bispo(Cor cor, Casa casa, Jogador dono) { super(cor, casa, dono); }
    @Override public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao o = casaAtual.getPosicao();
        int dx = Math.abs(o.getLinha() - destino.getLinha());
        int dy = Math.abs(o.getColuna() - destino.getColuna());
        if (dx != dy || dx == 0) return false;
        if (!tab.isCaminhoLivre(o, destino)) return false;
        Peca alvo = tab.getCasa(destino).getPeca();
        return alvo == null || alvo.getCor() != cor;
    }
    // Bispo.java
    @Override
    public String getSymbol() { return "B"; }
}
