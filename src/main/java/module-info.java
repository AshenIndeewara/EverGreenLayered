module com.evergreen.zoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;
    requires lombok;
    requires java.sql;
    requires resend.java;
    requires jbcrypt;
    requires org.controlsfx.controls;
    requires java.management;
    requires java.desktop;
    requires org.json;
    requires webcam.capture;
    requires net.sf.jasperreports.core;


    opens com.evergreen.zoo.controller to javafx.fxml;
    opens com.evergreen.zoo.dto to javafx.base;
    opens com.evergreen.zoo.dto.tanleDto to javafx.base;

    exports com.evergreen.zoo;
    exports com.evergreen.zoo.controller;
    opens com.evergreen.zoo.dto.reportDto to javafx.base;
    exports com.evergreen.zoo.util;
    opens com.evergreen.zoo.util to javafx.base, javafx.fxml;
}