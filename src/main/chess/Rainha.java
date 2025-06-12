
// Rainha.java
package main.chess;
public class Rainha extends Peca {
    public Rainha(Cor cor, Casa casa, Jogador dono) { super(cor, casa, dono); }
    @Override public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao o = casaAtual.getPosicao();
        int dx = Math.abs(o.getLinha() - destino.getLinha());
        int dy = Math.abs(o.getColuna() - destino.getColuna());
        boolean retil = o.getLinha() == destino.getLinha() || o.getColuna() == destino.getColuna();
        boolean diag = dx == dy && dx > 0;
        if (!retil && !diag) return false;
        if (!tab.isCaminhoLivre(o, destino)) return false;
        Peca alvo = tab.getCasa(destino).getPeca();
        return alvo == null || alvo.getCor() != cor;
    }

    @Override
    public String getSymbol() { return "D"; }

}
