// Posicao.java
package main.chess;
public class Posicao {
    private int linha;
    private char coluna;
    public Posicao(int linha, char coluna) {
        if (linha < 1 || linha > 8 || coluna < 'a' || coluna > 'h')
            throw new IllegalArgumentException("Posição inválida: " + coluna + linha);
        this.linha = linha;
        this.coluna = coluna;
    }
    public static Posicao of(int linha, char coluna) {
        return new Posicao(linha, coluna);
    }
    public int getLinha() { return linha; }
    public char getColuna() { return coluna; }
    int rowIndex() { return linha - 1; }
    int colIndex() { return coluna - 'a'; }
    @Override public String toString() { return "" + coluna + linha; }
    @Override public boolean equals(Object o) {
        if (!(o instanceof Posicao)) return false;
        Posicao p = (Posicao) o;
        return p.linha == linha && p.coluna == coluna;
    }
    @Override public int hashCode() { return linha * 31 + coluna; }
}