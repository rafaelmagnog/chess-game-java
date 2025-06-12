
// Movimento.java
package main.chess;
public class Movimento {
    private Posicao origem, destino;
    private Peca peca;
    private Peca pecaCapturada;
    public Movimento(Peca peca, Posicao origem, Posicao destino) {
        this.peca = peca;
        this.origem = origem;
        this.destino = destino;
    }
    public boolean executar(Tabuleiro tab) {
        if (peca == null) return false;
        if (!peca.validarMovimento(destino, tab)) return false;
        Casa destCasa = tab.getCasa(destino);
        pecaCapturada = destCasa.getPeca();
        peca.mover(destino, tab);
        return true;
    }
    public Peca getPecaCapturada() { return pecaCapturada; }

}
