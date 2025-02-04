package com.evergreen.zoo.BO;

import com.evergreen.zoo.dao.RegisterDAOimpl;
import com.evergreen.zoo.dto.RegisterDto;
import com.evergreen.zoo.util.CheckRegex;
import com.evergreen.zoo.util.ShowNotification;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddStaffBOimpl{
    RegisterDAOimpl registerDAOimpl = new RegisterDAOimpl();

    ArrayList<String> loadRoles() throws SQLException {
        ArrayList<String> roles = registerDAOimpl.getAllRoles();
        return roles;
    }

}
