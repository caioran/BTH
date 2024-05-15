package breakthehabit.controller;

import breakthehabit.model.RegistroDiario;
import breakthehabit.model.Usuario;
import breakthehabit.model.dao.ConnectionDAO;
import breakthehabit.model.dao.RegistroDiarioDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerRegistroDiario {

    @FXML
    private TextField txtData;
    @FXML
    private TextField txtCigarrosFumados;
    @FXML
    private Slider sliNivelDesejoFumo;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Label lblErro;

    private String dataModelo;



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
    public static boolean validacaoNumerosInteiros(String texto) {
        // Expressão regular para permitir apenas números inteiros
        String regex = "\\d+"; // Equivalente a [0-9]+

        // Verifica se o texto corresponde ao padrão regex
        return texto.matches(regex);
    }


    @FXML
    public void inserirDados(ActionEvent event){
        List<TextField> ListaDeCampos = List.of(txtData, txtCigarrosFumados);

        try {
            Connection conn = ConnectionDAO.getConnection("breakthehabit", "root", "");

            if(camposVazios(ListaDeCampos)){
                this.dataModelo = this.formatarData(txtData.getText());
                if(dataModelo != null) {
                    if(!(RegistroDiarioDAO.verificadorData(conn, dataModelo))){
                        if(validacaoNumerosInteiros(txtCigarrosFumados.getText())){
                            RegistroDiario novoRegistro = new RegistroDiario(dataModelo, txtCigarrosFumados.getText(), sliNivelDesejoFumo.getValue());
                            if(novoRegistro.inserirNoBD()){
                                lblErro.setText("Dados inseridos com sucesso!");
                                Stage stage = (Stage) lblErro.getScene().getWindow();
                                Usuario.atualizarDados(Usuario.getEmailUser());
                                Usuario.atualizarMetricas();

                                stage.close();
                            }
                            else{
                                lblErro.setText("Houve algum erro na inserção dos dados!");
                            }
                        }else{
                            lblErro.setText("Número de cigarros fumados inválido.");
                        }
                    }else{
                        lblErro.setText("Data já existente no banco de dados!");
                    }
                }
                else{
                    lblErro.setText("Formato de data incorreto!");
                }

            }
            else{
                lblErro.setText("É necessário o preenchimento de todos os campos!");
            }
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("Não Conectou!");
            e.printStackTrace();
        }

    }

}
