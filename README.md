![BTHIntellij](https://github.com/caioran/BTH/assets/143942475/58813b4d-a02e-4514-aa78-7fcc9b467570)

# BreakTheHabit
O projeto em questão recebeu o nome de BreakTheHabit

## Descrição
Com base no 3º Objetivo de Desenvolvimento Sustentável da ONU, meta 3.A - "Fortalecer a implementação da Convenção-Quadro para o Controle do Tabaco em todos os países, conforme apropriado" -, o projeto tem como objetivo auxiliar o usuário a se desprender do vício em cigarros. Através de uma interface intuitiva e uma base de dados que coletará informações diárias acerca do processo de abandono da dependência em tabaco do usuário, a aplicação, com base nos dados coletados, irá apresentar informações que tem o intuito de motivar e acompanhar o usuário durante esse processo.

## Pré-requisitos      ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)       	![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)   ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)        ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
A aplicação está sendo desenvolvida através da linguagem Java e da biblioteca gráfica JavaFX, a IDE que está sendo utilizada é o IntelliJ. Para o desenvolvimento do banco de dados estamos utilizando o Sistema Gerenciador de Banco de Dados MySQL.

## Versionamento
O projeto se encontra na versão 1.0.

## Estrutura do Projeto
- Registro: Pegar os dados, verificar se o campo "Senha" e o campo "Confirmar Senha" estão iguais.
- RegistroDAO: Enviar os dados da classe Registro para o banco de dados.
- ConnectionDAO: Realizar a conexão com o banco de dados.
- Login: Pegar os dados do campo Email e Senha, enviar para a classe UserDAO para autenticação, enviar o email autenticado para 
 classe UserSession.
- UserSession: Fazer o login, logout, criar uma sessão para o usuário, enviar os dados da sessão para a classe User.
- User: armazenar em váriaveis os dados do usuário da sessão. 
- RegistroDiario: Coletar os dados que o usuário irá inserir diariamente e enviar para a classe RegistroDiarioDAO.
- RegistroDiarioDAO: Verificar se a data que o usuário inseriu é única no banco de dados e cadastrar os dados no banco de dados.
- ValorGasto: Calcular, através dos dados herdados pela superclasse User, quantos reais o usuário já gastou em cigarros.
- VerificadorMedia: Calcular, através dos dados herdados pela superclasse User, quantos cigarros em média o usuário está fumando por dia.
- ValorEconomizado: Calcular, através dos dados herdados pela superclasse User, quantos reais o usuário já economizou desde que começou a usar a aplicação.
- VerificadorVontade: Calcular, através dos dados herdados pela superclasse User, a média do desejo de fumar dos último sete dias do usuário.

## Autores

- Caio Alves de Sousa (RA: 235440, email: caioalvesdesousa2005@gmail.com),
- Cristian Souza (RA: 237085, email: 237085@facens.br),
- Pedro Henrique Arruda  (RA: 236720, email: pedrohenriquearruda158@gmail.com),
-  Vinícius Lacava Aguiar (RA: 236962, email: 236962@facens.br).
