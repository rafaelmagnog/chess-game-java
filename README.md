# ChessGame

[![Status](https://img.shields.io/badge/status-em_desenvolvimento-yellow.svg)](https://github.com/SEU_USUARIO/ChessJavaApp)  

---

## 📋 Descrição

**ChessGame** é um projeto acadêmico em Java para desktop que implementa um jogo de xadrez simples, desenvolvido como parte de práticas de Programação Orientada a Objetos. Este repositório contém o código-fonte, documentação inicial e diagramas UML que guiam a arquitetura do sistema. A aplicação visa demonstrar conceitos fundamentais de classes, herança, polimorfismo e composição, seguindo boas práticas de design e padrões de projeto em Java.

---

## 📑 Tabela de Conteúdos

- [🎯 Objetivos](#-objetivos)  
- [⚙️ Funcionalidades](#️-funcionalidades)  
- [🛠 Tecnologias](#-tecnologias)  
- [🚀 Como Executar](#-como-executar)  
  - [Pré-requisitos](#pré-requisitos)  
  - [Instalação](#instalação)  
  - [Execução](#execução)  
- [🗂 Estrutura do Projeto](#-estrutura-do-projeto)  
- [🤝 Contribuição](#-contribuição)  
- [📝 Licença](#-licença)  
- [📬 Contato](#-contato)

---

## 🎯 Objetivos

1. **Aplicar princípios de POO**  
   - Demonstrar uso de herança, polimorfismo, encapsulamento e composição.  
   - Modelar cada peça de xadrez como classe especializada, respeitando regras de movimentação.

2. **Criar um jogo de xadrez básico**  
   - Permitir partidas em modo texto ou interface gráfica simples (Swing/JavaFX).  
   - Implementar movimentação de peças, detecção de captura e controle de turno.

3. **Documentar arquitetura e design**  
   - Incluir diagramas UML de classes (modelagem conceitual).  
   - Seguir convenções de commits, estrutura de pastas e padrões de codificação recomendados.

---

## ⚙️ Funcionalidades

- ✅ Representação do tabuleiro 8×8 com casas e peças.  
- ✅ Classes abstratas e herança para cada tipo de peça (Peão, Torre, Bispo, Cavalo, Rainha, Rei).  
- ✅ Verificação de movimentos válidos (movimento em linha reta, diagonal, “L” do cavalo, etc.).  
- ✅ Captura de peças adversárias e remoção do tabuleiro.  
- ✅ Alternância de turno entre jogadores (branco e preto).  
- ✅ Estrutura inicial para detecção de xeque básico (captura do rei).  
- ✅ Exibição em console (ou tela básica) para entrada de comandos (ex.: “e2 e4”).

---

## 🛠 Tecnologias

- **Java 11 (ou superior)**  
- **Maven** (ou Gradle) para gerenciamento de dependências e build  
- **JUnit 5** para testes unitários (planejado)  
- **Draw.io** para diagramas UML  
- **Git** para controle de versão  
- **IDE recomendada**: IntelliJ IDEA, Eclipse ou VS Code com extensão Java

---

## 🚀 Como Executar

### Pré-requisitos

Antes de começar, verifique se você possui instalado em sua máquina:

- Java Development Kit (JDK) 11 ou superior  
- Git  
- Maven (caso opte por usar o pom.xml)  

### Instalação

1. **Clone este repositório**  
   ```bash
   git clone https://github.com/SEU_USUARIO/ChessJavaApp.git
   cd ChessJavaApp
