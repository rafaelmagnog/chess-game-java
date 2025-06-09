package main.chess.pecas;

public class Rainha extends Peca {
    public Rainha(Cor cor, Casa casa, Jogador dono) {
        super(cor, casa, dono);
    }
    @Override
    public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao orig = casaAtual.getPosicao();
        int dx = Math.abs(orig.getLinha() - destino.getLinha());
        int dy = Math.abs(orig.getColuna() - destino.getColuna());
        if (dx==dy || orig.getLinha()==destino.getLinha() || orig.getColuna()==destino.getColuna())
            return tab.isCaminhoLivre(orig, destino);
        return false;
    }
}
