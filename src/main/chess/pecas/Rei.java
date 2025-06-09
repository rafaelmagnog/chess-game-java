package main.chess.pecas;

public class Rei extends Peca {
    public Rei(Cor cor, Casa casa, Jogador dono) {
        super(cor, casa, dono);
    }
    @Override
    public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao orig = casaAtual.getPosicao();
        int dx = Math.abs(orig.getLinha() - destino.getLinha());
        int dy = Math.abs(orig.getColuna() - destino.getColuna());
        if ((dx<=1 && dy<=1) && !(dx==0 && dy==0)) {
            // aqui poderia testar se o destino fica em xeque
            return true;
        }
        return false;
    }
}
