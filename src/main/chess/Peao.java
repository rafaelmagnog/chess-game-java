// Peao.java
package main.chess;
public class Peao extends Peca {
    private boolean primeiroMovimento = true;
    public Peao(Cor cor, Casa casa, Jogador dono) { super(cor, casa, dono); }
    @Override public boolean validarMovimento(Posicao destino, Tabuleiro tab) {
        Posicao o = casaAtual.getPosicao();
        int dir = cor == Cor.BRANCO ? 1 : -1;
        int dx = destino.getLinha() - o.getLinha();
        int dy = destino.getColuna() - o.getColuna();
        Casa destCasa = tab.getCasa(destino);
        // avanço
        if (dy == 0) {
            if (dx == dir && !destCasa.isOccupied()) { primeiroMovimento = false; return true; }
            if (dx == 2 * dir && primeiroMovimento) {
                Posicao meio = new Posicao(o.getLinha() + dir, o.getColuna());
                if (!tab.getCasa(meio).isOccupied() && !destCasa.isOccupied()) {
                    primeiroMovimento = false;
                    return true;
                }
            }
        }
        // captura diagonal
        if (Math.abs(dy) == 1 && dx == dir && destCasa.isOccupied() && destCasa.getPeca().getCor() != cor) {
            primeiroMovimento = false;
            return true;
        }
        return false;
    }

    @Override
    public String getSymbol() { return "P"; }

}
