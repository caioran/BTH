package breakthehabit.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/TelaInicial.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1440, 1024);
            stage.setTitle("BreakTheHabit!");
            stage.setScene(scene);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }

}
