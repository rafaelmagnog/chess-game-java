package main.chess;

public class Movimento {
    private Posicao origem, destino;
    private Peca peca, pecaCapturada;

    public Movimento(Peca peca, Posicao origem, Posicao destino) {
        this.peca = peca;
        this.origem = origem;
        this.destino = destino;
    }

    /** retorna true se o movimento foi realizado */
    public boolean executar(Tabuleiro tab) {
        if (!peca.validarMovimento(destino, tab)) return false;
        Casa dest = tab.getCasa(destino);
        pecaCapturada = dest.getPeca();
        peca.mover(destino, tab);
        return true;
    }
}
