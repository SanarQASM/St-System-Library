module StLibrary{
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
	requires java.prefs;
	requires java.desktop;
    requires firebase.admin;
    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires google.cloud.storage;
    requires google.cloud.core;
    requires mysql.connector.j;
    opens application to javafx.fxml;
    exports application;
}
