
// Jogo.java
package main.chess;
import java.util.*;
public class Jogo {
    private Tabuleiro tabuleiro;
    private List<Jogador> jogadores;
    private int idxAtual = 0;
    private boolean fimDeJogo = false;
    private Jogador vencedor;
    private List<Movimento> historicoMovimento = new ArrayList<>();


    public Jogo(Jogador j1, Jogador j2) {
        this.tabuleiro = new Tabuleiro();
        this.jogadores = Arrays.asList(j1, j2);
        setupPieces(j1, 1);
        setupPieces(j2, 8);
    }

    private void setupPieces(Jogador j, int linhaBase) {
        Cor cor = j.getCor();
        int linhaPeao = cor == Cor.BRANCO ? 2 : 7;
        // Peões
        for (char c = 'a'; c <= 'h'; c++)
            new Peao(cor, tabuleiro.getCasa(new Posicao(linhaPeao, c)), j);
        // Principais
        new Torre(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'a')), j);
        new Cavalo(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'b')), j);
        new Bispo(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'c')), j);
        new Rainha(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'd')), j);
        new Rei(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'e')), j);
        new Bispo(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'f')), j);
        new Cavalo(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'g')), j);
        new Torre(cor, tabuleiro.getCasa(new Posicao(linhaBase, 'h')), j);
    }

    public void jogar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nP -> Peão\nT -> Torre\nC -> Cavalo\nB -> Bispo\nD -> Rainha / Dama\nR -> Rei\n\n(Para encerrar a partida digite: 'sair')\n\n");
        while (!fimDeJogo) {
            imprimirTabuleiro();
            Jogador atual = jogadores.get(idxAtual);
            System.out.print("\nOrigem -> Destinho (ex: e2 e3)\nTurno de: " + atual.getNome() + " (" + atual.getCor() + ")> ");
            String linha = sc.nextLine().trim().toLowerCase();
            if (linha.equals("sair")) { fimDeJogo = true; System.out.println("Partida encerrada."); break; }
            String[] partes = linha.split("\\s+");
            if (partes.length != 2) { erroEntrada(); continue; }
            try {
                Posicao o = Posicao.of(Integer.parseInt(partes[0].substring(1)), partes[0].charAt(0));
                Posicao d = Posicao.of(Integer.parseInt(partes[1].substring(1)), partes[1].charAt(0));
                Peca p = tabuleiro.getCasa(o).getPeca();
                if (p == null || p.getCor() != atual.getCor()) { erroEntrada(); continue; }
                Movimento mov = new Movimento(p, o, d);
                if (!mov.executar(tabuleiro)) { erroEntrada(); continue; }
                historicoMovimento.add(mov);

                // Empate por apenas reis restantes?
                Jogador oponente = getOponente();
                if (atual.temSomenteRei() && oponente.temSomenteRei()) {
                    imprimirTabuleiro();
                    System.out.println("EMPATE! Apenas reis restantes.");
                    break;
                }
                // Checa captura de rei ou xeque-mate
                if (mov.getPecaCapturada() instanceof Rei || verificarXequeMate(oponente)) {
                    fimDeJogo = true;
                    this.vencedor = atual;
                    imprimirTabuleiro();
                    System.out.println("XEQUE-MATE! " + vencedor.getCor() + " Ganhou!\n");
                    break;
                }
                idxAtual = 1 - idxAtual;
            } catch (Exception e) {
                erroEntrada();
            }
        }
        sc.close();
        if (vencedor != null) {
            System.out.println("Vencedor da partida: " + vencedor.getNome());
        }
    }

    private void erroEntrada() {
        System.out.println("Erro na entrada, use por exemplo: e2 e4 ou 'sair'.");
    }

    private Jogador getOponente() {
        return jogadores.get(1 - idxAtual);
    }

    // Testa se o jogador 'jog' está em xeque
    public boolean verificarXeque(Jogador jog) {
        // Encontra rei
        Posicao posRei = jog.getPecas().stream()
                .filter(p -> p instanceof Rei)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Rei faltando"))
                .getCasaAtual()
                .getPosicao();
        // Testa movimentos do oponente
        for (Peca p : jogadores.stream().filter(x -> x != jog).findFirst().get().getPecas()) {
            if (p.validarMovimento(posRei, tabuleiro)) return true;
        }
        return false;
    }

    // Simula movimentos para verificar xeque-mate
    public boolean verificarXequeMate(Jogador jog) {
        if (!verificarXeque(jog)) return false;
        // Para cada peça do jogador em xeque
        for (Peca p : jog.getPecas()) {
            Posicao origem = p.getCasaAtual().getPosicao();
            // Tenta todas as posições
            for (int l = 1; l <= 8; l++) {
                for (char c = 'a'; c <= 'h'; c++) {
                    Posicao destino = new Posicao(l, c);
                    if (!p.validarMovimento(destino, tabuleiro)) continue;
                    // Salva estado
                    Casa casaOrig = tabuleiro.getCasa(origem);
                    Casa casaDest = tabuleiro.getCasa(destino);
                    Peca capturada = casaDest.getPeca();
                    // Executa movimento
                    p.mover(destino, tabuleiro);
                    // Verifica se ainda em xeque
                    boolean emXeque = verificarXeque(jog);
                    // Reverte movimento
                    tabuleiro.getCasa(destino).removePeca();
                    casaOrig.setPeca(p);
                    if (capturada != null) {
                        casaDest.setPeca(capturada);
                        capturada.getDono().addPeca(capturada);
                    }
                    if (!emXeque) return false;
                }
            }
        }
        return true;
    }
    private void imprimirTabuleiro() {
        // 1) Cabeçalho das colunas, 3 espaços iniciais + 8 colunas de 3 posições
        System.out.print("   ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.printf("%3c", c);
        }
        System.out.println();

        // 2) Linhas 8 → 1
        for (int l = 8; l >= 1; l--) {
            // imprime o número da linha em 3 colunas
            System.out.printf("%3d", l);

            // percorre as casas da linha
            for (char c = 'a'; c <= 'h'; c++) {
                Casa casa = tabuleiro.getCasa(new Posicao(l, c));
                if (casa.isOccupied()) {
                    Peca p = casa.getPeca();
                    String fg = (p.getCor() == Cor.BRANCO)
                            ? ANSI.WHITE_PIECE
                            : ANSI.YELLOW_PIECE;
                    // símbolo da peça (1 caractere), mas encaixado em 3 colunas
                    String cell = p.getSymbol();
                    System.out.print(fg);
                    System.out.printf("%3s", cell);
                    System.out.print(ANSI.RESET);
                } else {
                    // dois hífens encaixados em 3 colunas
                    System.out.printf("%3s", "-");
                }
            }
            System.out.println();
        }

        // 3) Rodapé igual ao cabeçalho
        System.out.print("   ");
        for (char c = 'a'; c <= 'h'; c++) {
            System.out.printf("%3c", c);
        }
        System.out.println();
    }



}
