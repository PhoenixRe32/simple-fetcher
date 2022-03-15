module com.pittacode.apihelper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires java.sql;
    requires com.google.gson;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires json.path;

    opens com.pittacode.apihelper to javafx.fxml;
    exports com.pittacode.apihelper;
}