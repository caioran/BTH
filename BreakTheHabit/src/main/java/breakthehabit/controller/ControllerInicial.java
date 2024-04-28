package breakthehabit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerInicial {

    @FXML
    private Button btnIniCadastro;
    @FXML
    private Button btnIniEntrar;

    @FXML
    private void abrirTelaCadastro(ActionEvent event) {
        try {
            // Carrega o arquivo FXML da segunda janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaCadastro.fxml"));
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
    private void abrirTelaDeLogin(ActionEvent event) {
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
