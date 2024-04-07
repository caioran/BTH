module org.example.breakthehabit {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens breakthehabit.controller to javafx.fxml;
    exports breakthehabit.controller;
}