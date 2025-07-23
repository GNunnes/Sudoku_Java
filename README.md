<div align="center">
<h1>🎮 Jogo de Sudoku - Implementação em Java</h1> 
</div>

![Java](https://img.shields.io/badge/Language-Java-orange)
![Swing](https://img.shields.io/badge/GUI-Swing-yellowgreen)
![Licença](https://img.shields.io/badge/Licen%C3%A7a-MIT-blue)



Implementação completa de um jogo de Sudoku em Java com interface gráfica Swing, contendo todas as regras padrão e mecanismos de validação.

## ✨ Funcionalidades

- **Interface gráfica interativa** construída com Java Swing
- **Mecânicas completas** seguindo as regras oficiais do Sudoku
- **Múltiplos estados de jogo** (Não Iniciado, Incompleto, Completo)
- **Detecção de conflitos** em linhas, colunas e subgrades 3×3
- **Gerenciamento do jogo**:
  - Iniciar novos jogos com números iniciais personalizados
  - Adicionar/remover números com validação de posição
  - Limpar números inseridos mantendo os fixos
  - Verificar status e conclusão do jogo
- **Feedback visual** para conflitos e células fixas

## 🖥️ Requisitos do Sistema

- Java Runtime Environment (JRE) 17 ou superior
- Resolução mínima de tela: 800×600

## 🚀 Como Executar

1. **Compilar o jogo** (se usando código fonte):
   ```bash
   javac SudokuGame.java
   ```
2. Executar o jogo:

   ```bash
   java SudokuGame
   ```

## 🎮 Controles do Jogo

|Botão            |	Funcionalidade                                                         | 
|-----------------|-------------------------------------------------------------------------|
|Novo Jogo	      |  Inicia um novo jogo com números iniciais personalizados                | 
|Adicionar Número	|  Insere um número na posição especificada (formato: linha,coluna,valor) | 
|Remover Número	|  Remove número da posição especificada (se não for fixo)                | 
|Verificar Jogo	|  Verifica o tabuleiro atual por completude e conflitos                  | 
|Status do Jogo	|  Mostra o estado atual (Não Iniciado/Incompleto/Completo) e erros       | 
|Limpar           |  Remove todos os números inseridos mantendo os fixos                    | 
|Finalizar        |	Encerra o jogo se o tabuleiro estiver completo e correto               | 
<br>

## 📝 Configuração Inicial do Jogo
<br>
Ao iniciar um novo jogo, informe os números iniciais no formato:

```text
linha,coluna,valor;linha,coluna,valor;...
```
<br>Exemplo:

```text
0,0,5;0,1,3;0,4,7;1,0,6;1,3,1;1,4,9;1,5,5;...
```
## 🎨 Elementos de Design da Interface
- Números fixos: Fundo cinza claro, não editáveis
- Números do usuário: Fundo branco
- Conflitos: Fundo vermelho claro
- Bordas das subgrades: Linhas mais grossas separando regiões 3×3

## ⚖️ Regras de Validação
#### Validação de posição:
- Índices de linha e coluna devem ser 0-8
- Células fixas não podem ser modificadas

#### Validação de valores:
- Números devem ser 1-9
- Sem duplicatas em linhas, colunas ou subgrades 3×3

#### Conclusão do jogo:
- Todas as células preenchidas
- Sem conflitos no tabuleiro
<br><br>

## 📄 Licença

Este projeto está sob a licença MIT.
<br>
Veja o arquivo LICENSE para mais detalhes.
<br><br>

## 👤 Desenvolvido por

Gustavo N. Bezerra - [LinkedIn](https://www.linkedin.com/in/gustavo-nunnes) | [GitHub](https://github.com/GNunnes) |<i>gustavonunnes@hotmail.com</i>