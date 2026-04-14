module com.example.leltargpu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.leltargpu to javafx.fxml;
    exports com.example.leltargpu;
}