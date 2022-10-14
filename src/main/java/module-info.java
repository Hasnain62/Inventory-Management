module com.example.cmpt370_group14 {
    requires java.sql;
    requires javafx.graphics;
    requires javafx.controls;


    opens com.example.cmpt370_group14 to javafx.fxml;
    exports com.example.cmpt370_group14;
    exports com.example.cmpt370_group14.UI;
    opens com.example.cmpt370_group14.UI to javafx.fxml;
}