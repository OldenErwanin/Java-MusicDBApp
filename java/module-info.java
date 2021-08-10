module sample.musicdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sample.musicdatabase to javafx.fxml;
    opens sample.musicdatabase.model;
    exports sample.musicdatabase;
}