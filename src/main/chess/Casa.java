package main.chess;

public class Casa {
    private Posicao posicao;
    private Peca peca; // pode ser null

    public Casa(Posicao posicao) {
        this.posicao = posicao;
    }
    public boolean isOccupied() {
        return peca != null;
    }
    public Peca getPeca() {
        return peca;
    }
    public void setPeca(Peca p) {
        this.peca = p;
        if (p != null) p.setCasaAtual(this);
    }
    public void removePeca() {
        this.peca = null;
    }
    public Posicao getPosicao() {
        return posicao;
    }
    @Override
    public String toString() {
        return isOccupied() ? peca.toString() : "--";
    }
}


