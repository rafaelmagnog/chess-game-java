package main.chess.pecas;

public class Peao extends Peca {
    private boolean primeiroMovimento = true;

    public Peao(Cor cor, Casa casa, Jogador dono) {
        super(cor, casa, dono);
    }
    @Override
    public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao orig = casaAtual.getPosicao();
        int dir = cor == Cor.BRANCO ? +1 : -1;
        int dx = destino.getLinha() - orig.getLinha();
        int dy = destino.getColuna() - orig.getColuna();
        Casa destCasa = tab.getCasa(destino);

        // avanço simples
        if (dy==0 && dx==dir && !destCasa.isOccupied()) {
            primeiroMovimento = false;
            return true;
        }
        // avanço duplo
        if (dy==0 && dx==2*dir && primeiroMovimento) {
            Posicao meio = new Posicao(orig.getLinha()+dir, orig.getColuna());
            if (!tab.getCasa(meio).isOccupied() && !destCasa.isOccupied()) {
                primeiroMovimento = false;
                return true;
            }
        }
        // captura diagonal
        if (Math.abs(dy)==1 && dx==dir && destCasa.isOccupied() &&
            destCasa.getPeca().getCor() != cor) {
            primeiroMovimento = false;
            return true;
        }
        return false;
    }
}
