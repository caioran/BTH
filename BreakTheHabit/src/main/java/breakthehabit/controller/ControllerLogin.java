package breakthehabit.controller;

import breakthehabit.model.Login;
import breakthehabit.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnEntrar;

    @FXML
    private Label lblErro;


   @FXML
   public void realizarLogin(ActionEvent event){

       Login novoLogin = new Login(txtEmail.getText(), txtSenha.getText());

       if(novoLogin.checarLogin()){
           Usuario.setEmailUser(txtEmail.getText());
           try {
               // Carrega o arquivo FXML da segunda janela
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaDashboard.fxml"));
               Parent root = loader.load();

               // Cria uma nova cena
               Scene scene = new Scene(root);

               // Obtém o palco (stage) atual a partir do evento
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

               // Fecha a janela atual
               stage.close();

               // Define a nova cena no palco (stage)
               stage.setTitle("BreakTheHabit");
               stage.setScene(scene);
               stage.show();

           } catch (IOException e) {
               e.printStackTrace();
           }
       }
       else{
           lblErro.setText("Usuário ou senha incorretos!");
       }


   }




}
