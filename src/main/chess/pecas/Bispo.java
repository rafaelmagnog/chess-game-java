package main.chess.pecas;

public class Bispo extends Peca {
    public Bispo(Cor cor, Casa casa, Jogador dono) {
        super(cor, casa, dono);
    }
    @Override
    public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao orig = casaAtual.getPosicao();
        int dx = Math.abs(orig.getLinha() - destino.getLinha());
        int dy = Math.abs(orig.getColuna() - destino.getColuna());
        if (dx != dy || dx == 0) return false;
        return tab.isCaminhoLivre(orig, destino);
    }
}
