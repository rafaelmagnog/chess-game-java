// Feito por Levi
package main.chess;

// Enumeração responsável por representar as duas cores possíveis das peças de xadrez.
// Usar um enum aqui é ideal porque limita as opções de cor ao conjunto fixo {BRANCO, PRETO},
// garantindo que não haja valores inválidos no decorrer do jogo.
public enum Cor {
    // Representa o jogador ou peças que se movem primeiro no xadrez.
    BRANCO,

    // Representa o jogador ou peças que se movem na segunda vez.
    PRETO;
}
