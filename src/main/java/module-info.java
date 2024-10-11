module Application {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens edu.metrostate to javafx.fxml;
    exports edu.metrostate;
    opens edu.metrostate.Controller to javafx.fxml;
    opens edu.metrostate.Model to javafx.base;

}