package breakthehabit.controller;

import breakthehabit.model.GastoEmCigarros;
import breakthehabit.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDashboard implements Initializable {

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
    private Label lblNome;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        lblNome.setText(Usuario.getNomeUser());
        lblValorGasto.setText("R$ -" + String.valueOf(GastoEmCigarros.calcularGasto()));
        lblValorEconomizado.setText("R$ " + String.valueOf(GastoEmCigarros.calcularEconomia()));
        if(Usuario.getMediaDiaria() > Usuario.getMetaUser()){
            lblMediaDiaria.setText(String.valueOf(Usuario.getMediaDiaria()));
            lblMediaDiaria.setTextFill(Color.web("#823038"));
        }
        else{
            lblMediaDiaria.setText(String.valueOf(Usuario.getMediaDiaria()));
            lblMediaDiaria.setTextFill(Color.web("#00D147"));
        }

        if(Usuario.getMediaDiaria() > Usuario.getMetaUser()){
           lblCheckMeta.setText("FORA");
           lblCheckMeta.setTextFill(Color.web("#823038"));
        }
        else{
            lblCheckMeta.setText("DENTRO");
            lblCheckMeta.setTextFill(Color.web("#00D147"));
        }
        if(Usuario.getMediaVontadeDiaria() > 5){
            lblVontade.setText(String.valueOf(Usuario.getMediaVontadeDiaria()));
            lblVontade.setTextFill(Color.web("#823038"));
        }
        else{
            lblVontade.setText(String.valueOf(Usuario.getMediaVontadeDiaria()));
            lblVontade.setTextFill(Color.web("#00D147"));
        }


    }



}
