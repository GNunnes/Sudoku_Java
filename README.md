<div align="center">
<h1>Conversor de Moedas Java com Interface Gráfica e API ExchangeRate</h1> 
</div>

![Java](https://img.shields.io/badge/Language-Java-orange)


- Descrição
- Funcionalidades
- Tecnologias
- Requisitos
- Como Usar
- Estrutura do código
- Detalhes de Implementação
- Tratamento de Erros
- Melhorias Futuras
- Licença
- Contato
<br><br>
## Descrição

Este é um programa em Java que funciona como um conversor de moedas com interface gráfica simples, usando JOptionPane. Ele utiliza a API pública da ExchangeRate-API para consultar as taxas de câmbio reais e fazer conversões atualizadas de valores entre as moedas. A aplicação foi desenvolvida para ser simples, amigável e robusta, com tratamento de erros para entradas inválidas e problemas de comunicação com a API.
<br><br>
## Funcionalidades

Menu gráfico com 6 opções de conversão entre as moedas:
- BRL (Real) <-> USD (Dólar)
- BRL (Real) <-> EUR (Euro)
- USD (Dólar) <-> BRL (Real)
- USD (Dólar) <-> EUR (Euro)
- EUR (Euro) <-> BRL (Real)
- EUR (Euro) <-> USD (Dólar)

<p align="center">
  <img src="https://github.com/GNunnes/ConversorDeMoedas/blob/master/img/tela_inicial.png">
</p>

Opção para sair do programa.

<p align="center">
  <img src="https://github.com/GNunnes/ConversorDeMoedas/blob/master/img/tela_saída.png">
</p>

Entrada do usuário por caixas de diálogo (JOptionPane).

<p align="center">
  <img src="https://github.com/GNunnes/ConversorDeMoedas/blob/master/img/tela_valor.png">
</p>

Validação da opção do menu e valor digitado.

<p align="center">
  <img src="https://github.com/GNunnes/ConversorDeMoedas/blob/master/img/tela_resultado.png">
</p>

Consulta dinâmica da taxa de câmbio via API ExchangeRate
Conversão do valor informado pelo usuário com exibição do resultado
Tratamento de erros com mensagens amigáveis para entradas inválidas e falhas na API
<br><br>
## Tecnologias Utilizadas

- Java SE 8+ (recomendado Java 11+)
- Biblioteca Gson para parsing de JSON (com.google.gson)
- API ExchangeRate (https://www.exchangerate-api.com/)
- Swing (JOptionPane) para interface gráfica simples
<br><br>
## Requisitos

- Java JDK 8 ou superior instalado e configurado no PATH
- Acesso à internet para consumir a API ExchangeRate
- Biblioteca Gson incluída no projeto (JAR ou via gerenciador de dependências)
- Chave válida da ExchangeRate API (free ou paga) — inserida na classe APIClient
<br><br>
## Como Usar

1. Configurando o projeto:

- Adicione a biblioteca Gson ao seu projeto (exemplo: gson-2.10.1.jar no classpath).
- Insira sua chave de API da ExchangeRate no arquivo APIClient.java:



```java
private static final String API_KEY = "SUA_CHAVE_API_AQUI";
``` 

2. Compilação:

    Compile as classes, por exemplo, pelo terminal:

```bash
javac -cp ".;gson-2.10.1.jar" ConversorMoedas.java APIClient.java
```
3. Execução:

    Execute a aplicação:

```bash
java -cp ".;gson-2.10.1.jar" ConversorMoedas
```

4. Interação:

- Escolha uma opção no menu gráfico.
- Insira o valor a ser convertido.
- Veja o resultado da conversão.
- Repita ou saia do programa.
<br><br>
## Estrutura do Código

ConversorMoedas.java
- Contém o loop principal com a interface gráfica.
- Gerencia o menu, leitura da opção do usuário com validação.
- Recebe o valor a ser convertido, validando tipo e valores positivos.
- Usa a classe APIClient para solicitar a conversão via API.
- Exibe resultados e mensagens de erro.

APIClient.java
- Contém a lógica para montar a URL da API com a moeda de origem, destino e valor.
- Faz a requisição HTTP GET para a ExchangeRate API.
- Lê a resposta JSON e faz o parsing usando Gson.
- Trata a resposta da API, lança exceção em caso de erro.
- Retorna o valor convertido para a aplicação.
<br><br>
## Detalhes de Implementação

- Uso de Locale.US para garantir que o valor decimal na URL utilize ponto (.) e não vírgula (,) — requisito para APIs internacionais.
- Requisição HTTP feita com HttpURLConnection.
- Parsing JSON usando JsonParser e JsonObject da biblioteca Gson.
- Interface gráfica feita com JOptionPane, incluindo caixas de diálogo para entrada e mensagens.
- Uso de tratamento de exceções para garantir que falhas na rede ou na API sejam capturadas e exibam mensagens amigáveis ao usuário.
- Validação rigorosa da entrada para garantir apenas números válidos e opções do menu corretas.
<br><br>
## Tratamento de Erros

|        Tipo de Erro        |	                          Como é tratado?                           |
|----------------------------|----------------------------------------------------------------------|
| Entrada inválida no menu   | Mensagem de erro e volta para o menu solicitando nova opção          |
| Valor digitado inválido	   | Mensagem indicativa para digitar um valor numérico válido e positivo |
| Erro na resposta da API	   | Mensagem de erro amigável informando falha na conversão              |
| Erros HTTP (ex.: 404, 500) | Exception capturada e mensagem exibida para o usuário                |
| Cancelamento ou fechamento | O programa encerra de forma elegante                                 |

## Melhorias Futuras

- Suporte a mais moedas (permitir escolha dinâmica via API ou lista ampliada)
- Histórico de conversões na sessão
- Interface gráfica mais avançada usando JavaFX ou Swing completo
- Implementação de testes automatizados
- Cache das taxas para evitar consultas em excesso e usar o plano gratuito da API
- Tradução e internacionalização da interface

<br><br>
 📄 Licença
 <br>
Este projeto está sob a licença MIT.
<br>
Veja o arquivo LICENSE para mais detalhes.
<br><br><br>
👤 Desenvolvido por

Gustavo N. Bezerra - [LinkedIn](https://www.linkedin.com/in/gustavo-nunnes) | [GitHub](https://github.com/GNunnes) |<i>gustavonunnes@hotmail.com</i>
