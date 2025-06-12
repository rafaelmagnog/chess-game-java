package main.chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Representa uma partida de xadrez, controla tabuleiro, jogadores e fluxo de turnos.
 */
public class Jogo {
    private Tabuleiro tabuleiro;
    private List<Jogador> jogadores;
    private int idxAtual = 0;              // índice do jogador da vez (0 ou 1)
    private boolean fimDeJogo = false;     // flag para encerrar o loop principal
    private Jogador vencedor;              // referência ao jogador vencedor, se houver
    private List<Movimento> historicoMovimento = new ArrayList<>();

    /**
     * Inicializa o tabuleiro e posiciona as peças dos dois jogadores.
     */
    public Jogo(Jogador j1, Jogador j2) {
        this.tabuleiro = new Tabuleiro();
        this.jogadores = Arrays.asList(j1, j2);

        // Coloca as peças na posição inicial (linhas 1 e 8)
        setupPieces(j1, 1);
        setupPieces(j2, 8);
    }

    /**
     * Posiciona peões e peças principais na linha base informada.
     * @param j jogador dono das peças
     * @param linhaBase linha 1 (brancas) ou 8 (pretas) para as peças principais
     */
    private void setupPieces(Jogador j, int linhaBase) {
        Cor cor = j.getCor();
        int linhaPeao = (cor == Cor.BRANCO) ? 2 : 7;

        // Posiciona os 8 peões na fila correspondente
        for (char c = 'a'; c <= 'h'; c++) {
            new Peao(cor, tabuleiro.getCasa(new Posicao(linhaPeao, c)), j);
        }

        // Posiciona as peças principais: Torre, Cavalo, Bispo, Rainha, Rei, Bispo, Cavalo, Torre
        new Torre(cor,  tabuleiro.getCasa(new Posicao(linhaBase, 'a')), j);
        new Cavalo(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'b')), j);
        new Bispo(cor,  tabuleiro.getCasa(new Posicao(linhaBase, 'c')), j);
        new Rainha(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'd')), j);
        new Rei(cor,    tabuleiro.getCasa(new Posicao(linhaBase, 'e')), j);
        new Bispo(cor,  tabuleiro.getCasa(new Posicao(linhaBase, 'f')), j);
        new Cavalo(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'g')), j);
        new Torre(cor,  tabuleiro.getCasa(new Posicao(linhaBase, 'h')), j);
    }

    /**
     * Loop principal de jogo: exibe tabuleiro, lê jogadas e aplica regras de fim de partida.
     */
    public void jogar() {
        Scanner sc = new Scanner(System.in);

        // Instruções iniciais para o usuário
        System.out.print(
                "\nP -> Peão\n" +
                        "T -> Torre\n" +
                        "C -> Cavalo\n" +
                        "B -> Bispo\n" +
                        "D -> Rainha / Dama\n" +
                        "R -> Rei\n\n" +
                        "(Para encerrar a partida digite: 'sair')\n\n"
        );

        // Repete enquanto a partida não terminar
        while (!fimDeJogo) {
            imprimirTabuleiro();
            Jogador atual = jogadores.get(idxAtual);

            // Prompt de origem e destino
            System.out.print(
                    "\nOrigem -> Destino (ex: e2 e3)\n" +
                            "Turno de: " + atual.getNome() + " (" + atual.getCor() + ")> "
            );

            String linha = sc.nextLine().trim().toLowerCase();
            if (linha.equals("sair")) {
                fimDeJogo = true;
                System.out.println("Partida encerrada.");
                break;
            }

            String[] partes = linha.split("\\s+");
            if (partes.length != 2) {
                erroEntrada();
                continue;
            }

            try {
                // Converte notação textual para objetos Posicao
                Posicao o = Posicao.of(
                        Integer.parseInt(partes[0].substring(1)),
                        partes[0].charAt(0)
                );
                Posicao d = Posicao.of(
                        Integer.parseInt(partes[1].substring(1)),
                        partes[1].charAt(0)
                );

                Peca p = tabuleiro.getCasa(o).getPeca();
                // Valida peça existente e cor correta
                if (p == null || p.getCor() != atual.getCor()) {
                    erroEntrada();
                    continue;
                }

                Movimento mov = new Movimento(p, o, d);
                // Executa e registra o movimento
                if (!mov.executar(tabuleiro)) {
                    erroEntrada();
                    continue;
                }
                historicoMovimento.add(mov);

                // Empate por material mínimo (apenas reis)
                Jogador oponente = getOponente();
                if (atual.temSomenteRei() && oponente.temSomenteRei()) {
                    imprimirTabuleiro();
                    System.out.println("EMPATE! Apenas reis restantes.");
                    break;
                }

                // Fim de jogo: captura do Rei ou xeque-mate
                if (mov.getPecaCapturada() instanceof Rei
                        || verificarXequeMate(oponente)) {

                    fimDeJogo = true;
                    vencedor = atual;
                    imprimirTabuleiro();
                    System.out.println(
                            "XEQUE-MATE! " + vencedor.getCor() + " Ganhou!\n"
                    );
                    break;
                }

                // Alterna jogador da vez
                idxAtual = 1 - idxAtual;

            } catch (Exception e) {
                erroEntrada();
            }
        }

        sc.close();

        // Se houver vencedor, informa ao final
        if (vencedor != null) {
            System.out.println("Vencedor da partida: " + vencedor.getNome());
        }
    }

    /**
     * Exibe mensagem de erro de entrada.
     */
    private void erroEntrada() {
        System.out.println(
                "Erro na entrada, use por exemplo: e2 e4 ou 'sair'."
        );
    }

    /**
     * Retorna o jogador adversário ao de índice idxAtual.
     */
    private Jogador getOponente() {
        return jogadores.get(1 - idxAtual);
    }

    /**
     * Verifica se o jogador "jog" está em xeque.
     */
    public boolean verificarXeque(Jogador jog) {
        // Localiza a posição do Rei de "jog"
        Posicao posRei = jog.getPecas().stream()
                .filter(p -> p instanceof Rei)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Rei faltando"))
                .getCasaAtual()
                .getPosicao();

        // Para cada peça do oponente, testa se consegue atingir o Rei
        Jogador oponente = getOponente();
        for (Peca p : oponente.getPecas()) {
            if (p.validarMovimento(posRei, tabuleiro)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simula movimentos para determinar se o jogador "jog" está em xeque-mate.
     */
    public boolean verificarXequeMate(Jogador jog) {
        // Se nem está em xeque, não há mate
        if (!verificarXeque(jog)) {
            return false;
        }

        // Para cada peça e cada destino válido, simula e reverte o movimento
        for (Peca p : jog.getPecas()) {
            Posicao origem = p.getCasaAtual().getPosicao();
            for (int l = 1; l <= 8; l++) {
                for (char c = 'a'; c <= 'h'; c++) {
                    Posicao destino = new Posicao(l, c);
                    if (!p.validarMovimento(destino, tabuleiro)) {
                        continue;
                    }

                    // Salva estado atual das casas
                    Casa casaOrig = tabuleiro.getCasa(origem);
                    Casa casaDest = tabuleiro.getCasa(destino);
                    Peca capturada = casaDest.getPeca();

                    // Executa movimento temporário
                    p.mover(destino, tabuleiro);
                    boolean emXeque = verificarXeque(jog);

                    // Reverte movimento
                    tabuleiro.getCasa(destino).removePeca();
                    casaOrig.setPeca(p);
                    if (capturada != null) {
                        casaDest.setPeca(capturada);
                        capturada.getDono().addPeca(capturada);
                    }

                    // Se algum movimento livra do xeque, não é mate
                    if (!emXeque) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Imprime o tabuleiro no console, com colunas e linhas alinhadas.
     */
    private void imprimirTabuleiro() {
        // Cabeçalho das colunas (3 espaços + 8 colunas de largura fixa)
        System.out.print("   ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.printf("%3c", c);
        }
        System.out.println();

        // Linhas do tabuleiro (8 a 1)
        for (int l = 8; l >= 1; l--) {
            // Número da linha em 3 colunas
            System.out.printf("%3d", l);

            // Cada célula ocupa 3 colunas: peça colorida ou hífens
            for (char c = 'a'; c <= 'h'; c++) {
                Casa casa = tabuleiro.getCasa(new Posicao(l, c));
                if (casa.isOccupied()) {
                    Peca p = casa.getPeca();
                    String fg = (p.getCor() == Cor.BRANCO)
                            ? ANSI.WHITE_PIECE
                            : ANSI.YELLOW_PIECE;
                    System.out.print(fg);
                    System.out.printf("%3s", p.getSymbol());
                    System.out.print(ANSI.RESET);
                } else {
                    System.out.printf("%3s", "--");
                }
            }
            System.out.println();
        }

        // Rodapé idêntico ao cabeçalho
        System.out.print("   ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.printf("%3c", c);
        }
        System.out.println();
    }
}
