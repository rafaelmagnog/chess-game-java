
// Torre.java
package main.chess;
public class Torre extends Peca {
    public Torre(Cor cor, Casa casa, Jogador dono) { super(cor, casa, dono); }
    @Override public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao o = casaAtual.getPosicao();
        if (o.getLinha() != destino.getLinha() && o.getColuna() != destino.getColuna()) return false;
        if (!tab.isCaminhoLivre(o, destino)) return false;
        Peca alvo = tab.getCasa(destino).getPeca();
        return alvo == null || alvo.getCor() != cor;
    }

    @Override
    public String getSymbol() { return "T"; }
}
