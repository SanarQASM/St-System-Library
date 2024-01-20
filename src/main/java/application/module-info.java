module StLibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires jakarta.mail;
    requires java.management;
    requires org.controlsfx.controls;
    requires java.logging;
    requires jakarta.activation;

    opens application to javafx.fxml;
    exports application;
}
