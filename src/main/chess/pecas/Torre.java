package main.chess.pecas;

public class Torre extends Peca {
    public Torre(Cor cor, Casa casa, Jogador dono) {
        super(cor, casa, dono);
    }
    @Override
    public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao orig = casaAtual.getPosicao();
        if (orig.getLinha() != destino.getLinha() &&
            orig.getColuna() != destino.getColuna()) return false;
        return tab.isCaminhoLivre(orig, destino);
    }
}

