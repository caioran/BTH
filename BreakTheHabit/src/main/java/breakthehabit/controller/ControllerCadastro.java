package breakthehabit.controller;

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
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Method;
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
    private PasswordField txtMeta;
    @FXML
    private Slider sliDependencia;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Label lblErroSenha;


    private String nome;
    private String email;
    private String dataNasc;
    private String senha;
    private String confirmarSenha;
    private String dataInicioFumo;
    private String mediaCigarrosDia;
    private String reaisGastoDia;
    private String meta;
    private String dependencia;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }

    public String getDataInicioFumo() {
        return dataInicioFumo;
    }

    public void setDataInicioFumo(String dataInicioFumo) {
        this.dataInicioFumo = dataInicioFumo;
    }

    public String getMediaCigarrosDia() {
        return mediaCigarrosDia;
    }

    public void setMediaCigarrosDia(String mediaCigarrosDia) {
        this.mediaCigarrosDia = mediaCigarrosDia;
    }

    public String getReaisGastoDia() {
        return reaisGastoDia;
    }

    public void setReaisGastoDia(String reaisGastoDia) {
        this.reaisGastoDia = reaisGastoDia;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

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

    @FXML
    private void abrirTelaCadastro2(ActionEvent event) {
        List<TextField> ListaDeCampos = List.of(txtNome, txtEmail, txtDataNasc, txtSenha, txtConfirmarSenha);


        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");
            if(camposVazios(ListaDeCampos)) {
                if (verificarSenha()) {
                    if (validarEmail(txtEmail.getText())) {
                        if (!(RegistroDAO.verificadorEmail(conn, txtEmail.getText()))) {
                            setNome(txtNome.getText());
                            setEmail(txtEmail.getText());
                            setDataNasc(txtDataNasc.getText());
                            setSenha(txtSenha.getText());
                            setConfirmarSenha(txtConfirmarSenha.getText());

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
                            System.out.println("Email já existente no banco de dados!");
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
}

// setDataInicioFumo(txtDataInicioFumo.getText());
// setMediaCigarrosDia(txtMediaCigarrosDia.getText());
// setReaisGastoDia(txtReaisGastoDia.getText());
// setMeta(txtMeta.getText());
// setDependencia(Double.toString(sliDependencia.getValue()));