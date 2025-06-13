// Feito por Levi
package main.chess;

import java.util.*;

// Classe responsável por representar um jogador no contexto de um jogo de xadrez.
// Cada jogador possui um nome, uma cor (branco ou preto) e uma coleção de peças que controla.
public class Jogador {

    private String nome;
    private Cor cor;
    // Lista contendo todas as peças ativas do jogador no tabuleiro.
    // A estrutura de dados utilizada é uma ArrayList, adequada para operações dinâmicas de inserção e remoção.
    private List<Peca> pecas = new ArrayList<>();

    // Construtor que inicializa o jogador com nome e cor específicos.
    // Também inicia a lista de peças como vazia (serão adicionadas depois).
    public Jogador(String nome, Cor cor) {
        this.nome = nome;
        this.cor = cor;
    }

    // Método de acesso (getter) para retornar o nome do jogador.
    public String getNome() {
        return nome;
    }

    // Getter para retornar a cor das peças que o jogador controla.
    public Cor getCor() {
        return cor;
    }

    // Retorna a lista atual de peças do jogador.
    // Este método pode ser utilizado para lógica de jogo ou exibição gráfica.
    public List<Peca> getPecas() {
        return pecas;
    }

    // Adiciona uma peça à lista de peças do jogador.
    // Esse método é usado, por exemplo, na fase de inicialização do tabuleiro.
    void addPeca(Peca p) {
        pecas.add(p);
    }

    // Remove uma peça da lista do jogador.
    // Usado quando uma peça é capturada pelo adversário.
    public void removerPeca(Peca p) {
        pecas.remove(p);
    }

    // Este método verifica uma condição específica: se o jogador possui apenas o rei como peça ativa.
    // Isso pode ser útil para verificar situações de empate ou término do jogo por insuficiência de material.
    // A lógica funciona da seguinte forma:
    // 1. Filtra todas as peças que são instâncias da classe Rei.
    // 2. Verifica se há exatamente um rei E se essa é a única peça do jogador.
    public boolean temSomenteRei() {
        return getPecas().stream()
                .filter(p -> p instanceof Rei)
                .count() == 1
                && getPecas().size() == 1;
    }
}
