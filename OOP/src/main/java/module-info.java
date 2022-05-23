module sample.oop {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens sample.oop to javafx.fxml;
    exports sample.oop;
}