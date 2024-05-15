package breakthehabit.controller;

import breakthehabit.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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
    @FXML
    private Button btnSair;

    @FXML
    private BorderPane painelValorGasto;
    @FXML
    private BorderPane painelVontade;
    @FXML
    private BorderPane painelMediaDiaria;
    @FXML
    private BorderPane painelMeta;
    @FXML
    private BorderPane painelMediaValorEco;

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
        Usuario.atualizarDados(Usuario.getEmailUser());
        Usuario.atualizarMetricas();


        painelValorGasto.setCenter(lblValorGasto);
        painelVontade.setCenter(lblVontade);
        painelMediaDiaria.setCenter(lblMediaDiaria);
        painelMeta.setCenter(lblCheckMeta);
        painelMediaValorEco.setCenter(lblValorEconomizado);


        lblNome.setText(Usuario.getNomeUser());
        lblValorGasto.setText("R$ -" + String.valueOf(Usuario.calcularGasto()));
        lblValorEconomizado.setText("R$ " + String.valueOf(Usuario.calcularEconomia()));
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

    public static void atualizacaoDasMetricas(){
        Usuario.atualizarMetricas();
    }

    public void atualizacaoLabels(){
        Usuario.atualizarMetricas();
        lblNome.setText(Usuario.getNomeUser());
        lblValorGasto.setText("R$ -" + String.valueOf(Usuario.calcularGasto()));
        lblValorEconomizado.setText("R$ " + String.valueOf(Usuario.calcularEconomia()));
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
    @FXML
    public void sair(ActionEvent event){
        Usuario.sairDaAplicacao();
        try {
            // Carrega o arquivo FXML da segunda janela
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TelaInicial.fxml"));
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
