![image](https://github.com/caioran/BTH/assets/143942475/9f0e435e-de2a-4e06-9e7b-8c5cc7723e90)

# BreakTheHabit
O projeto em questão recebeu o nome de BreakTheHabit

## Descrição
Com base no 3º Objetivo de Desenvolvimento Sustentável da ONU, meta 3.A - "Fortalecer a implementação da Convenção-Quadro para o Controle do Tabaco em todos os países, conforme apropriado" -, o projeto tem como objetivo auxiliar o usuário a se desprender do vício em cigarros. Através de uma interface intuitiva e uma base de dados que coletará informações diárias acerca do processo de abandono da dependência em tabaco do usuário, a aplicação, com base nos dados coletados, irá apresentar informações que tem o intuito de motivar e acompanhar o usuário durante esse processo.

## Pré-requisitos      ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)       	![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)   ![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)        ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
A aplicação está sendo desenvolvida através da linguagem Java e da biblioteca gráfica JavaFX, a IDE que está sendo utilizada é o IntelliJ. Para o desenvolvimento do banco de dados estamos utilizando o Sistema Gerenciador de Banco de Dados MySQL.

## Versionamento
O projeto se encontra na versão 2.0.

## Estrutura do Projeto
+ **Controller**
  - ControllerCadastro: Classe controladora responsável por pegar todos os dados das telas de cadastro, passar pelas validações e enviar para as verificações da classe Registro.
  - ControllerDashboard: Classe controladora responsável por puxar os dados da classe Usuario e apresentá-los ao usuário.
  - ControllerInicial: Classe controladora responsável pela tela inicial do projeto, a classe tem como função redirecionar o usuário a tela de Login ou Cadastro, conforme desejado.
  - ControllerLogin: Classe controladora responsável por pegar todos os dado da telas de login, passar pelas validações e enviar para as verificações da classe Login.
  - ControllerRegistroDiario: Classe controladora por pegar todos os dados da tela de registro diário, passar pelas validações e enviar para as verificações da classe RegistroDiario.
  - MainController: Classe main do projeto.
+ **Model**
  - ConnectionDAO: Realizar a conexão com o banco de dados.
  - RegistroDAO: Classe responsável por armazenar o método de inserção dos dados coletados pela classe Controller das telas de cadastro e inseri-los no banco de dados.
  - LoginDAO: Classe responsável por armazenar o método de validação do email e senha inseridos na tela de Login da aplicação.
  - RegistroDiario: Classe responsável por armazenar o método de inserção dos dados coletados pela classe Controller das tela de registro diário e inseri-los no banco de dados.
  - UserDAO: Classe responsável por, através da validação do email e senha do usuário, puxar os dados correspondentes ao email inserido e armazená-los em variáveis da classe Usuario.
  - Login: Receber os dados da classe controladora ControllerLogin e chamar o método responsável pela validação do login, presente na classe LoginDAO.
  - Registro: Receber os dados da classse controladora ControllerCadastro, armazená-los em variáveis temporárias e chamar o método para inserção dos dados na base de dados do projeto, método que se encontra presente na classe RegistroDAO.
  - RegistroDiario: Receber os dados da classse controladora ControllerRegistroDiario, armazená-los em variáveis temporárias e chamar o método para inserção dos dados na base de dados do projeto, método que se encontra presente na classe RegistroDiarioDAO.
  - Usuario: Classe responsável pelo armazenamento em variáveis de todos os dados do usuário que o sistema precisa para realização dos cálculos, responsável pela realização de cálculos com base nos dados coletados.
+ **View** - arquivos FXML
  - TelaInicial: Tela inicial do projeto.
  - TelaCadastro1: Primeira tela de cadastro do projeto.
  - TelaCadastro2: Segunda tela de cadastro do projeto.
  - TelaDeLogin: Tela de login da aplicação.
  - JanelaDashboard: Tela principal da aplicação.
  - JanelaRegistroDiario: Janela responsável pela inserção diária de dados do projeto.


## Autores

- Caio Alves de Sousa (RA: 235440, email: caioalvesdesousa2005@gmail.com),
- Cristian Souza (RA: 237085, email: 237085@facens.br),
- Pedro Henrique Arruda  (RA: 236720, email: pedrohenriquearruda158@gmail.com),
-  Vinícius Lacava Aguiar (RA: 236962, email: 236962@facens.br).
  
-  Contato da equipe: pedrohenriquearruda158@gmail.com - (Pedro Henrique Arruda).
