module com.example.calculator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.calculator to javafx.fxml;
    exports com.example.calculator;
}