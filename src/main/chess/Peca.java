// Peca.java
package main.chess;

public abstract class Peca {
    protected Cor cor;
    protected Casa casaAtual;
    protected Jogador dono;

    public Peca(Cor cor, Casa casa, Jogador dono) {
        this.cor = cor;
        this.casaAtual = casa;
        this.dono = dono;
        casa.setPeca(this);
        dono.addPeca(this);
    }

    public abstract boolean validarMovimento(Posicao destino, Tabuleiro tab);

    public void mover(Posicao destino, Tabuleiro tab) {
        Casa origem = casaAtual;
        Casa dest   = tab.getCasa(destino);

        Peca capturada = dest.getPeca();
        if (capturada != null) capturada.getDono().removerPeca(capturada);

        origem.removePeca();
        dest.setPeca(this);
    }

    public Cor getCor()        { return cor; }
    public Casa getCasaAtual() { return casaAtual; }
    public Jogador getDono()   { return dono; }
    public void setCasaAtual(Casa c) { this.casaAtual = c; }

    /** Letra símbolo da peça: R,N,B,Q,K,P */
    public abstract String getSymbol();

    @Override
    public String toString() {
        return getSymbol();
    }
}
