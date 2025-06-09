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

    /** Executa o movimento (assume válido) */
    public void mover(Posicao destino, Tabuleiro tab) {
        Casa origemCasa = casaAtual;
        Casa destCasa = tab.getCasa(destino);
        Peca cap = destCasa.getPeca();
        if (cap != null) cap.getDono().removerPeca(cap);
        origemCasa.removePeca();
        destCasa.setPeca(this);
    }

    public Cor getCor() { return cor; }
    public Casa getCasaAtual() { return casaAtual; }
    public void setCasaAtual(Casa c) { this.casaAtual = c; }
    public Jogador getDono() { return dono; }

    @Override
    public String toString() {
        String s = getClass().getSimpleName().substring(0,1);
        return cor == Cor.BRANCO ? s + "B" : s + "P";
    }
}
