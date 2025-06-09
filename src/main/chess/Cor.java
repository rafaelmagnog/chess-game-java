package main.chess;

public enum Cor {
    BRANCO,
    PRETO;

    public Cor oposta() {
        return this == BRANCO ? PRETO : BRANCO;
    }
}
