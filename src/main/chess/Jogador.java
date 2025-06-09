package main.chess;

import java.util.*;

public class Jogador {
    private String nome;
    private Cor cor;
    private List<Peca> pecas = new ArrayList<>();

    public Jogador(String nome, Cor cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() { return nome; }
    public Cor getCor() { return cor; }
    public List<Peca> getPecas() { return pecas; }

    void addPeca(Peca p) { pecas.add(p); }
    public void removerPeca(Peca p) { pecas.remove(p); }

    /* só resta o rei? então perdeu! */
    public boolean temSomenteRei() {
        return pecas.stream().allMatch(p -> p instanceof Rei);
    }
}
