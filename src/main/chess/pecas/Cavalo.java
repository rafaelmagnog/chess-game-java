package main.chess.pecas;

public class Cavalo extends Peca {
    public Cavalo(Cor cor, Casa casa, Jogador dono) {
        super(cor, casa, dono);
    }
    @Override
    public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao orig = casaAtual.getPosicao();
        int dx = Math.abs(orig.getLinha() - destino.getLinha());
        int dy = Math.abs(orig.getColuna() - destino.getColuna());
        return dx*dy == 2; // (2,1) ou (1,2)
    }
}
