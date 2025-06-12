// Casa.java
package main.chess;
public class Casa {
    private Posicao posicao;
    private Peca peca;
    public Casa(Posicao posicao) { this.posicao = posicao; }
    public Posicao getPosicao() { return posicao; }
    public boolean isOccupied() { return peca != null; }
    public Peca getPeca() { return peca; }
    public void setPeca(Peca p) {
        this.peca = p;
        if (p != null) p.setCasaAtual(this);
    }
    public void removePeca() { this.peca = null; }
    @Override public String toString() { return isOccupied() ? peca.toString() : "--"; }
}
