package com.evergreen.zoo.controller;

import com.evergreen.zoo.BO.BOFactory;
import com.evergreen.zoo.BO.BOTypes;
import com.evergreen.zoo.BO.TicketPaneBO;
import com.evergreen.zoo.BO.impl.TicketPaneBOimpl;
import com.evergreen.zoo.db.DBConnection;
import com.evergreen.zoo.dto.TicketDto;
import com.evergreen.zoo.dao.impl.TicketDAOimpl;
import com.evergreen.zoo.util.ShowNotification;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TicketPaneController implements Initializable {

    @FXML
    private Label adultCount;

    @FXML
    private Label adultLabel;

    @FXML
    private Label adultLabel1;

    @FXML
    private Label childCount;

    @FXML
    private Label childLabel;

    @FXML
    private Label childLabel1;

    @FXML
    private Label foreignChildCount;

    @FXML
    private Label foreignChildLabel;

    @FXML
    private Label foreignChildLabel1;

    @FXML
    private Label foreignCount;

    @FXML
    private Label foreignLabel;

    @FXML
    private Label foreignLabel1;

    @FXML
    private Label studentCount;

    @FXML
    private Label studentLabel;

    @FXML
    private TextField nameTXT;

    @FXML
    private Label studentLabel1;

    @FXML
    private Label totalLabel;

    @FXML
    private JFXComboBox<String> paymentOptions;

    @FXML
    private GridPane buttonGrid;

    @FXML
    private AnchorPane changePricePane;

    @FXML
    private AnchorPane ticketPane;

    @FXML
    private TextField adultNewPrice;

    @FXML
    private TextField childNewPrice;

    @FXML
    private TextField studentNewPrice;

    @FXML
    private TextField foreignNewPrice;

    @FXML
    private TextField fChildNewPrice;

    @FXML
    private TextField emailTXT;

    @FXML
    private TextField numTXT;

    @FXML
    private ComboBox<String> employeeID;

//    private TicketDAOimpl ticketDAOimpl = new TicketDAOimpl();
    TicketPaneBO ticketDAOimpl = (TicketPaneBO) BOFactory.getBoFactory().getBO(BOTypes.TICKETBO);


    private HashMap<String, Integer> ticketPrice;
    private double totalPrice = 0;

    //different ticket prices
    private double adultPrice = 0;
    private double childPrice = 0;
    private double foreignChildPrice = 0;
    private double foreignPrice = 0;
    private double studentPrice = 0;

    private TicketDto ticketDto;

    private int role;

    public void setRole(int role) {
        this.role = role;
        System.out.println("Role set to: " + role);
    }

    double getTotalPrice() {
        return adultPrice + childPrice + foreignChildPrice + foreignPrice + studentPrice;
    }

    void loadPayments() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.addAll("Card", "Cash");
        paymentOptions.setItems(observableList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(role==4){
            changePricePane.setVisible(true);
        }
        totalLabel.setText("0");
        loadPayments();
        loadEmployeeID();
        try {
            ticketPrice = ticketDAOimpl.getTicketPrice();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        adultLabel.setText(ticketPrice.get("Adult").toString());
        childLabel.setText(ticketPrice.get("Child").toString());
        foreignChildLabel.setText(ticketPrice.get("ForeignChild").toString());
        foreignLabel.setText(ticketPrice.get("Foreign").toString());
        studentLabel.setText(ticketPrice.get("Student").toString());
    }

    private void loadEmployeeID() {
        try {
            ResultSet resultSet = ticketDAOimpl.getEmployeeID();
            ObservableList<String> observableList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                observableList.add(resultSet.getString("id"));
            }
            employeeID.setItems(observableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String add(int count) {
        return String.valueOf(count + 1);
    }

    String minus(int count) {
        if (count == 0) {
            return "0";
        }
        return String.valueOf(count - 1);
    }

    @FXML
    void ClearFields(ActionEvent event) {
        adultCount.setText("0");
        childCount.setText("0");
        foreignCount.setText("0");
        studentCount.setText("0");
        foreignChildCount.setText("0");
        totalLabel.setText("0.0");
        nameTXT.setText("");
        emailTXT.setText("");
        numTXT.setText("");
        adultPrice = 0;
        childPrice = 0;
        foreignChildPrice = 0;
        foreignPrice = 0;
        studentPrice = 0;
        totalPrice = 0;
        employeeID.setValue("");
    }

    @FXML
    void adultAdd(ActionEvent event) {
        adultCount.setText(add(Integer.parseInt(adultCount.getText())));
        adultPrice += ticketPrice.get("Adult");
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void adultRemove(ActionEvent event) {
        adultCount.setText(minus(Integer.parseInt(adultCount.getText())));
        if(adultPrice >= ticketPrice.get("Adult")) {
            adultPrice -= ticketPrice.get("Adult");
        }
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void childAdd(ActionEvent event) {
        childCount.setText(add(Integer.parseInt(childCount.getText())));
        childPrice += ticketPrice.get("Child");
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void childRemove(ActionEvent event) {
        childCount.setText(minus(Integer.parseInt(childCount.getText())));
        if(childPrice >= ticketPrice.get("Child")) {
            childPrice -= ticketPrice.get("Child");
        }
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void foreignAdd(ActionEvent event) {
        foreignCount.setText(add(Integer.parseInt(foreignCount.getText())));
        foreignPrice += ticketPrice.get("Foreign");
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void foreignRemove(ActionEvent event) {
        foreignCount.setText(minus(Integer.parseInt(foreignCount.getText())));
        if(foreignPrice >= ticketPrice.get("Foreign")) {
            foreignPrice -= ticketPrice.get("Foreign");
        }
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void studentAdd(ActionEvent event) {
        studentCount.setText(add(Integer.parseInt(studentCount.getText())));
        studentPrice += ticketPrice.get("Student");
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void studentRemove(ActionEvent event) {
        studentCount.setText(minus(Integer.parseInt(studentCount.getText())));
        if(studentPrice >= ticketPrice.get("Student")) {
            studentPrice -= ticketPrice.get("Student");
        }
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void foreignChildAdd(ActionEvent event) {
        foreignChildCount.setText(add(Integer.parseInt(foreignChildCount.getText())));
        foreignChildPrice += ticketPrice.get("ForeignChild");
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void foreignChildRemove(ActionEvent event) {
        foreignChildCount.setText(minus(Integer.parseInt(foreignChildCount.getText())));
        if(foreignChildPrice >= ticketPrice.get("ForeignChild")) {
            foreignChildPrice -= ticketPrice.get("ForeignChild");
        }
        totalLabel.setText(getTotalPrice()+"");

    }

    @FXML
    void changePrice(ActionEvent event) {
        //set the current ticket prices in labels
        adultLabel1.setText(ticketPrice.get("Adult")+"");
        childLabel1.setText(ticketPrice.get("Child").toString());
        foreignChildLabel1.setText(ticketPrice.get("ForeignChild").toString());
        foreignLabel1.setText(ticketPrice.get("Foreign").toString());
        studentLabel1.setText(ticketPrice.get("Student").toString());

        //set the new ticket prices in textfields
        adultNewPrice.setText(ticketPrice.get("Adult")+"");
        childNewPrice.setText(ticketPrice.get("Child").toString());
        fChildNewPrice.setText(ticketPrice.get("ForeignChild").toString());
        foreignNewPrice.setText(ticketPrice.get("Foreign").toString());
        studentNewPrice.setText(ticketPrice.get("Student").toString());

        ticketPane.setVisible(false);
        changePricePane.setVisible(true);
        buttonGrid.setVisible(false);

    }

    @FXML
    void completePurchase(ActionEvent event) {
        try {
            ticketDto = new TicketDto(
                    getTotalPrice(),
                    paymentOptions.getValue(),
                    Integer.parseInt(adultCount.getText()),
                    Integer.parseInt(childCount.getText()),
                    Integer.parseInt(foreignCount.getText()),
                    Integer.parseInt(foreignChildCount.getText()),
                    Integer.parseInt(studentCount.getText()),
                    nameTXT.getText(),
                    emailTXT.getText(),
                    numTXT.getText(),
                    employeeID.getValue()
            );

            ticketDAOimpl.addTicket(ticketDto);
            Connection connection = DBConnection.getInstance().getConnection();
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("today", LocalDate.now().toString());
            parameters.put("TODAY", LocalDate.now().toString());


            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/report/Blank_A4.jrxml"));


            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters,
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);
            ClearFields(event);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, "Fail to load report..!");
            e.printStackTrace();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Data empty..!");
            e.printStackTrace();
        }
    }
    void reloadNewPrice(String type) {
        try {
            ticketPrice = ticketDAOimpl.getTicketPrice();
            switch (type){
                case "main":
                    adultLabel.setText(ticketPrice.get("Adult").toString());
                    childLabel.setText(ticketPrice.get("Child").toString());
                    foreignChildLabel.setText(ticketPrice.get("ForeignChild").toString());
                    foreignLabel.setText(ticketPrice.get("Foreign").toString());
                    studentLabel.setText(ticketPrice.get("Student").toString());
                    break;
                case "":
                    adultLabel1.setText(ticketPrice.get("Adult")+"");
                    childLabel1.setText(ticketPrice.get("Child").toString());
                    foreignChildLabel1.setText(ticketPrice.get("ForeignChild").toString());
                    foreignLabel1.setText(ticketPrice.get("Foreign").toString());
                    studentLabel1.setText(ticketPrice.get("Student").toString());
                    break;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveNewTicketPrice(ActionEvent event){
        ticketDAOimpl.updateTicketPrice("Adult", Double.parseDouble(adultNewPrice.getText()));
        ticketDAOimpl.updateTicketPrice("Child", Double.parseDouble(childNewPrice.getText()));
        ticketDAOimpl.updateTicketPrice("ForeignChild", Double.parseDouble(fChildNewPrice.getText()));
        ticketDAOimpl.updateTicketPrice("Foreign", Double.parseDouble(foreignNewPrice.getText()));
        ticketDAOimpl.updateTicketPrice("Student", Double.parseDouble(studentNewPrice.getText()));
        new ShowNotification("Ticket Prices Updated",
                "New ticket prices saved successfully",
                "update.png",
                ""
        ).start();
        reloadNewPrice("");
    }

    @FXML
    void backAction(ActionEvent event){
        ticketPane.setVisible(true);
        changePricePane.setVisible(false);
        buttonGrid.setVisible(true);
        reloadNewPrice("main");
    }

    @FXML
    void checkUser(KeyEvent event) {
        if(numTXT.getLength() > 9){
            try {
                Map<String, String> user = ticketDAOimpl.getUser(numTXT.getText());
                nameTXT.setText(user.get("name"));
                emailTXT.setText(user.get("email"));
            } catch (Exception e) {}
        }
    }
}
