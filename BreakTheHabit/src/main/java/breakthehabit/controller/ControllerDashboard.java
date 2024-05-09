package breakthehabit.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerDashboard {

    @FXML
    private Label lblValorGasto;

    @FXML
    private Label lblValorEconomizado;

    @FXML
    private Label lblMediaDiaria;

    @FXML
    private Label lblCheckMeta;

    @FXML
    private Label lblVontade;

    @FXML
    private Button btnNovoRegistro;

    public void abrirTelaRegistro(ActionEvent event){
        try {
            // Carrega o arquivo FXML da segunda janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/JanelaRegistroDiario.fxml"));
            Parent root = loader.load();

            // Cria uma nova cena
            Scene scene = new Scene(root);

            // Cria um novo palco (stage) para a segunda janela
            Stage newStage = new Stage();
            newStage.setTitle("Tela de Registro"); // Define o título da nova janela
            newStage.setScene(scene);

            // Mostra a nova janela
            newStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
