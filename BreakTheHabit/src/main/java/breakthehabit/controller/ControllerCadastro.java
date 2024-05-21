package breakthehabit.controller;

import breakthehabit.model.Registro;
import breakthehabit.model.Usuario;
import breakthehabit.model.dao.ConnectionDAO;
import breakthehabit.model.dao.RegistroDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ControllerCadastro {
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDataNasc;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private PasswordField txtConfirmarSenha;
    @FXML
    private Button btnProsseguir;
    @FXML
    private TextField txtDataInicioFumo;
    @FXML
    private TextField txtMediaCigarrosDia;
    @FXML
    private TextField txtReaisGastoDia;
    @FXML
    private TextField txtMeta;
    @FXML
    private Slider sliDependencia;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Label lblErroSenha;
    @FXML
    private Label lblErro2;
    @FXML
    private Label lblData1;
    @FXML
    private Label lblData2;
    @FXML
    private Label lblNome1;
    @FXML
    private Label lblLogin;
    @FXML
    private Label lblLogin2;

    private String dataModelo;

    Registro novoUsuario = new Registro();

    public boolean verificarSenha() {
        if (txtSenha.getText().equals(txtConfirmarSenha.getText())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificadorCampo(TextField campo){
        String texto = campo.getText().trim();

        return texto.isEmpty();
    }

    public boolean camposVazios(List<TextField> ListaDeCampos){
        for(TextField campo : ListaDeCampos){
            if(verificadorCampo(campo)){
                return false;
            }
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        // Expressão regular para validar o formato do e-mail
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


        // Compila a expressão regular em um padrão
        Pattern pattern = Pattern.compile(regex);

        // Cria um objeto Matcher para comparar o e-mail com o padrão
        Matcher matcher = pattern.matcher(email);

        // Retorna true se o e-mail corresponder ao padrão, caso contrário, retorna false
        return matcher.matches();
    }

    public String formatarData(String data) {
        StringBuilder dataFormatada = new StringBuilder();
        // Expressão regular para capturar os componentes da data
        String regex = "^(\\d{2})/(\\d{2})/(\\d{4})$";
        // Compila a expressão regular em um padrão
        Pattern pattern = Pattern.compile(regex);
        // Cria um objeto Matcher para comparar a data com o padrão
        Matcher matcher = pattern.matcher(data);

        // Verifica se a data corresponde ao padrão
        if (matcher.matches()) {
            // Extrai os grupos de captura da data
            String dia = matcher.group(1);
            String mes = matcher.group(2);
            String ano = matcher.group(3);
            // Insere a data no formato desejado: YYYY-MM-DD na variável dataFormatada
            dataFormatada.append(ano).append("-").append(mes).append("-").append(dia);
            // Retorna a data formatada
            return dataFormatada.toString();
        } else {
            return null; // Retorna null se a formatação falhou
        }
    }

    @FXML
    private void abrirTelaCadastro2(ActionEvent event) {
        List<TextField> ListaDeCampos = List.of(txtNome, txtEmail, txtDataNasc, txtSenha, txtConfirmarSenha);


        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            if(camposVazios(ListaDeCampos)) {
                if (verificarSenha()) {
                    if (validarEmail(txtEmail.getText())) {
                        this.dataModelo = this.formatarData(txtDataNasc.getText());
                        if(dataModelo != null) {
                            if (!(RegistroDAO.verificadorEmail(conn, txtEmail.getText()))) {
//                                novoUsuario.setNome(txtNome.getText());
//                                novoUsuario.setEmail(txtEmail.getText());
//                                novoUsuario.setDataNasc(this.dataModelo);
//                                novoUsuario.setSenha(txtSenha.getText());
//                                novoUsuario.setConfirmarSenha(txtConfirmarSenha.getText());

                                novoUsuario.primeiraInsercao(txtNome.getText(), txtEmail.getText(), this.dataModelo, txtSenha.getText());


                                try {
                                    // Carrega o arquivo FXML da segunda janela
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaCadastro2.fxml"));
                                    Parent root = loader.load();

                                    // Cria uma nova cena
                                    Scene scene = new Scene(root);

                                    // Obtém o palco (stage) atual a partir do evento
                                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                    // Fecha a janela atual
                                    stage.close();

                                    // Define a nova cena no palco (stage)
                                    stage.setScene(scene);
                                    stage.show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                lblErroSenha.setText("Email já existente no banco de dados!");
                                txtEmail.setText("");
                            }
                        } else {
                            lblErroSenha.setText("Formato de data incorreto!");
                            txtDataNasc.setText("");
                        }
                    } else {
                        lblErroSenha.setText("Email inválido!");
                        txtEmail.setText("");
                    }
                } else {
                    lblErroSenha.setText("As senhas não correspondem!");
                    txtSenha.setText("");
                    txtConfirmarSenha.setText("");
                }
            } else{
                lblErroSenha.setText("É necessário o preenchimento de todos os campos!");
            }

        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Não Conectou!");
            e.printStackTrace();
        }

    }
    public static boolean validacaoNumerosInteiros(String texto) {
        // Expressão regular para permitir apenas números inteiros
        String regex = "\\d+"; // Equivalente a [0-9]+

        // Verifica se o texto corresponde ao padrão regex
        return texto.matches(regex);
    }

    public static boolean validacaoNumerosDecimais(String texto) {
        // Expressão regular para validar o formato desejado
        String regex = "\\d{1,3}(\\.\\d{1,2})?";

        // Verifica se o texto corresponde ao padrão regex
        return texto.matches(regex);
    }



    @FXML
    public void cadastrarUsuario(ActionEvent event){
        List<TextField> ListaDeCampos = List.of(txtDataInicioFumo, txtMediaCigarrosDia, txtReaisGastoDia, txtMeta);

        if(camposVazios(ListaDeCampos)){
            this.dataModelo = this.formatarData(txtDataInicioFumo.getText());
            if(dataModelo != null){
                if(validacaoNumerosInteiros(txtMediaCigarrosDia.getText())){
                    if(validacaoNumerosDecimais(txtReaisGastoDia.getText())){
                        if(validacaoNumerosInteiros(txtMeta.getText())){

                            try{
                                Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
//                                novoUsuario.setDataInicioFumo(dataModelo);
//                                novoUsuario.setMediaCigarrosDia(txtMediaCigarrosDia.getText());
//                                novoUsuario.setReaisGastoDia(txtReaisGastoDia.getText());
//                                novoUsuario.setMeta(txtMeta.getText());
//                                novoUsuario.setDependencia(Double.toString(sliDependencia.getValue()));

                                novoUsuario.segundaInsercao(dataModelo, txtMediaCigarrosDia.getText(), txtReaisGastoDia.getText(), txtMeta.getText(),
                                        Double.toString(sliDependencia.getValue()));

//                                lblData2.setText(novoUsuario.getDataInicioFumo());
//                                lblData1.setText(novoUsuario.getDataNasc());
//                                lblNome1.setText(novoUsuario.getNome());
                                if(novoUsuario.inserirNoBD()){
                                    Usuario.atualizarDados(novoUsuario.getEmail());
                                    try {
                                        // Carrega o arquivo FXML da segunda janela
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JanelaDashboard.fxml"));
                                        Parent root = loader.load();

                                        // Cria uma nova cena
                                        Scene scene = new Scene(root);

                                        // Obtém o palco (stage) atual a partir do evento
                                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                                        // Fecha a janela atual
                                        stage.close();

                                        // Define a nova cena no palco (stage)
                                        stage.setScene(scene);
                                        stage.show();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    lblErro2.setText("Houve um erro! Revise os dados e tente novamente!");

                                }


                            }catch (ClassNotFoundException | SQLException e){
                                System.out.println("Não Conectou!");
                                e.printStackTrace();
                            }


                        }else{
                            lblErro2.setText("A meta deve ser composta de apenas números inteiros!");
                        }
                    }else{
                        lblErro2.setText("A média de reais gastos por dia está fora do padrão.");
                    }
                }else{
                    lblErro2.setText("A média de cigarros por dia deve ser composta de apenas números!");
                    txtMediaCigarrosDia.setText("");
                }

            }else{
                lblErro2.setText("Formato inválido de data.");
            }
        }else{
            lblErro2.setText("É necessário o preenchimento de todos os campos.");
        }


    }

    @FXML
    public void abrirDashboard(ActionEvent event){
        try {
            // Carrega o arquivo FXML da segunda janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JanelaDashboard.fxml"));
            Parent root = loader.load();

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Obtém o palco (stage) atual a partir do evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Fecha a janela atual
            stage.close();

            // Define a nova cena no palco (stage)
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void abrirTelaLogin(ActionEvent event){
        try {
            // Carrega o arquivo FXML da segunda janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaDeLogin.fxml"));
            Parent root = loader.load();

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Obtém o palco (stage) atual a partir do evento
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Fecha a janela atual
            stage.close();

            // Define a nova cena no palco (stage)
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

// setDataInicioFumo(txtDataInicioFumo.getText());
// setMediaCigarrosDia(txtMediaCigarrosDia.getText());
// setReaisGastoDia(txtReaisGastoDia.getText());
// setMeta(txtMeta.getText());
// setDependencia(Double.toString(sliDependencia.getValue()));