```markdown
# Conversor de Moedas API

Este é um projeto de Conversor de Moedas que utiliza a API da [ExchangeRate-API]
(https://www.exchangerate-api.com/)para obter taxas de câmbio e converter valores
entre diferentes moedas. O projeto está implementado em **Java** e
utiliza o cliente HTTP nativo (`HttpClient`) e a biblioteca **Gson** para trabalhar com JSON.

# Funcionalidades

- Consultar as taxas de câmbio de diversas moedas.
- Converter valores de uma moeda para outra com base nas taxas mais recentes.
- Modularização do código com classes dedicadas para serviços de API e conversão de moedas.

# Tecnologias Utilizadas

- Java 11+ (ou versões mais recentes)
- HttpClient para requisições HTTP.
- Gson** para manipulação de JSON.
- ExchangeRate-API para obter as taxas de câmbio.

# Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- [Java JDK 11+](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven](https://maven.apache.org/) (opcional, se você usar Maven para gerenciamento de dependências)

# Configuração

1. Clone o repositório:

```bash
git clone https://github.com/jrmesquita7/encriptgit
cd nome-do-repositorio
```

2. Crie um arquivo chamado `config.properties` na raiz do projeto e adicione sua chave de API da ExchangeRate API:

```properties
API_KEY=coloque_sua_api_key_aqui
```

3. Certifique-se de que o arquivo `config.properties` está no seu `.gitignore` para que ele não seja enviado ao Git:

```bash
echo "config.properties" >> .gitignore
```

4. Compile e execute o projeto:

```bash
javac -d out src/*.java
java -cp out Main
```

## Exemplos de Uso

### Consulta de Taxas de Câmbio

Você pode usar o serviço para buscar taxas de câmbio com base em uma moeda de origem:

```java
TaxasService taxasService = new TaxasService();
Taxas taxas = taxasService.conversao("USD"); // Exemplo: USD para outras moedas
```

### Conversão de Moedas

Após obter as taxas de câmbio, você pode realizar a conversão de valores entre diferentes moedas:

```java
double valorConvertido = taxasService.converter("USD", "BRL", 100.0, taxas); 
System.out.println("Valor convertido: " + valorConvertido + " BRL");
```

Exemplo de output:

```plaintext
Valor convertido: 550.48 BRL
```

### Menu Interativo

O projeto inclui uma interface de menu interativa que permite ao usuário selecionar moedas e realizar conversões diretamente do terminal:

```bash
*********************************************
Seja bem vindo/a ao Conversor de Moedas =]
1) Dólar => Peso Argentino
2) Peso Argentino => Dólar
3) Dólar => Real Brasileiro
4) Real Brasileiro => Dólar
5) Dólar => Peso Colombiano
6) Peso Colombiano => Dólar
7) Sair
Escolha uma opção válida:
```

### Exemplo de Execução

Ao selecionar uma opção, o usuário será solicitado a inserir um valor para conversão, e o resultado será exibido no terminal. Exemplo de fluxo:

```bash
Escolha uma opção válida:
1
Digite o valor a ser convertido:
100
Valor 100.0 USD corresponde ao valor final de =>>> 13300.0 ARS
```

## Contribuições

Contribuições são bem-vindas! Se você deseja melhorar o projeto, sinta-se à vontade para abrir uma _issue_ ou fazer um _fork_ do repositório e enviar um _pull request_.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
