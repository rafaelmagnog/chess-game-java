# ChessGame Java

[![Status](https://img.shields.io/badge/status-Em%20Desenvolvimento-yellow.svg)](https://github.com/SEU_USUARIO/ChessJavaApp)
[![Java Version](https://img.shields.io/badge/Java-11%2B-blue.svg)]()

---

## 📋 Descrição

**ChessGame Java** é um projeto acadêmico em Java que implementa um jogo de xadrez básico em modo texto. Desenvolvido para demonstrar os pilares de Programação Orientada a Objetos — encapsulamento, herança, polimorfismo e composição — o sistema apresenta classes bem definidas para peças, tabuleiro, jogadores e lógica de jogo.

---

## 🎯 Objetivos

* Modelar cada peça do xadrez como classe especializada, preservando regras de movimentação e captura.
* Demonstrar aplicação de POO: abstração, herança e polimorfismo.
* Exibir o tabuleiro em console, com cores ANSI para diferenciar jogadores.
* Fornecer código limpo, documentado e organizado, seguindo boas práticas Java.

---

## ⚙️ Funcionalidades

* **Representação do Tabuleiro**: Matriz 8×8 de `Casa` contendo `Posicao` e `Peca`.
* **Peças e Movimentos**:

  * `Peca` (classe abstrata) e subclasses (`Peao`, `Torre`, `Cavalo`, `Bispo`, `Rainha`, `Rei`).
  * Validação de movimentos (rolha, linha reta, diagonal, L do cavalo).
  * Captura de peças inimigas.
* **Controle de Partida**:

  * Alternância de turnos entre jogadores (`Cor.BRANCO` e `Cor.PRETO`).
  * Comando de entrada no formato `e2 e4` ou `sair` para encerrar.

* **Interface ANSI**:

  * Cores definidas em `ANSI.java`.
  * Peças brancas em **branco brilhante** e peças pretas em **amarelo**.

---

## 🗂 Estrutura do Projeto

```
main.chess/
│
├── ANSI.java         # Cores ANSI para console
├── Cor.java          # Enum BRANCO, PRETO
├── Posicao.java      # Linha/coluna (1–8, a–h)
├── Casa.java         # Associação Posicao + Peca
├── Tabuleiro.java    # Matriz 8×8 de Casas
│
├── Peca.java         # Classe abstrata com método validarMovimento()
├── Peao.java         # Implementa movimentos de peão
├── Torre.java        # Movimentos retos (horizontal/vertical)
├── Cavalo.java       # Movimentos em "L"
├── Bispo.java        # Movimentos diagonais
├── Rainha.java       # Combina retilíneo e diagonal
└── Rei.java          # Um passo em qualquer direção
│
├── Movimento.java    # Origem, destino, peça e captura
├── Jogador.java      # Nome, cor e lista de peças
├── Jogo.java         # Loop principal, turnos e regras de fim
└── ChessApp.java     # Ponto de entrada (main)
```

---

## 🚀 Como Executar

### Pré‑requisitos

* Java 11 ou superior
* Maven ou Gradle (opcional)
* Terminal que suporte ANSI (Linux, macOS, Windows PowerShell/Terminal)

### Instalação e Build

1. Clone o repositório:

   ```bash
   git clone https://github.com/SEU_USUARIO/ChessJavaApp.git
   cd ChessJavaApp
   ```
2. Compile com Maven:

   ```bash
   mvn compile
   ```

   ou com Gradle:

   ```bash
   gradle build
   ```
3. Compile manualmente:

   ```bash
   javac -d out src/main/chess/*.java
   ```

### Execução

```bash
cd out
java main.chess.ChessApp
```

Siga as instruções no console:

* Digite os nomes dos jogadores.
* Use notação de casas `e2 e4` para mover.
* Digite `sair` para encerrar a partida.

---

## 🤝 **Contribuidores**

Agradecemos a todos que contribuíram para o desenvolvimento deste projeto:

- [Rafael Magno G.](https://github.com/rafaelmagnog)
- [Levi Adler](https://github.com/LeviAdler05)
- [José Henrique](https://github.com/josehmelo)
- [Hugo Lins](https://github.com/HugoLinsX)
- [Renato Alexandre](https://github.com/RenatoAlexandre06)

