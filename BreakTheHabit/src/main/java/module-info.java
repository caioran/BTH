module org.example.breakthehabit {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.breakthehabit to javafx.fxml;
    exports org.example.breakthehabit;
}