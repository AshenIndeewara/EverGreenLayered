package com.evergreen.zoo.controller;

import com.evergreen.zoo.BO.BOFactory;
import com.evergreen.zoo.BO.BOTypes;
import com.evergreen.zoo.BO.StockManageBO;
import com.evergreen.zoo.dto.tanleDto.StockDto;
import com.evergreen.zoo.util.CheckRegex;
import com.evergreen.zoo.util.ShowNotification;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StockManageController implements Initializable {

    @FXML
    private ComboBox<String> itemList;

    @FXML
    private ComboBox<String> movement;

    @FXML
    private TextField qtyTxt;

    @FXML
    private TableView<StockDto> itemTable;

    @FXML
    private TableColumn<StockDto, String> supplier;

    @FXML
    private TableColumn<StockDto, String> item;

    @FXML
    private TableColumn<StockDto, Integer> qty;

    @FXML
    private TableColumn<StockDto, ImageView> typeImage;

    @FXML
    private TextField itemName;

    @FXML
    private TextField newQTY;

    @FXML
    private TextField price;

    @FXML
    private ComboBox<String> supplierName;

    @FXML
    private TextField minQTY;

    private int role;

    public void setRole(int role) {
        this.role = role;
    }

//    StockDAOimpl stockDAOimpl = new StockDAOimpl();
    StockManageBO stockDAOimpl = (StockManageBO) BOFactory.getBoFactory().getBO(BOTypes.STOCKMANAGEBO);

    Boolean isPriceValid = false;
    Boolean isNewQTYValid = false;
    Boolean isMinQTYValid = false;
    Boolean isQtyValid = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        item.setCellValueFactory(new PropertyValueFactory<>("item"));
        qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        typeImage.setCellValueFactory(new PropertyValueFactory<>("typeImage"));

        try {
            getStock();
            getItems();
            getSupplier();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getStock() {
        ArrayList<StockDto> stockDtos =  stockDAOimpl.getStock();
        itemTable.getItems().clear();
        itemTable.getItems().addAll(stockDtos);
    }

    public void getItems() {
        ArrayList<String> items = stockDAOimpl.getItems();
        itemList.getItems().clear();
        itemList.getItems().addAll(items);

        ArrayList<String> movements = new ArrayList<>();
        movements.add("Stock In");
        movements.add("Stock Out");
        movement.getItems().clear();
        movement.getItems().addAll(movements);
    }

    public void getSupplier() {
        ArrayList<String> suppliers = stockDAOimpl.getSupplier();
        supplierName.getItems().clear();
        supplierName.getItems().addAll(suppliers);
    }

    @FXML
    void isUpdateStock(ActionEvent event) {
        String item = itemList.getValue();
        String move = movement.getValue();
        if(!isQtyValid || item == null || move == null) {
            new ShowNotification("Invalid input",
                    "Please check the input fields",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
            return;
        }
        int qty = Integer.parseInt(qtyTxt.getText());
        if(qty > stockDAOimpl.getIteeQty(item)) {
            new ShowNotification("Invalid quantity",
                    "Please check the quantity",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
            return;
        }
        stockDAOimpl.isUpdateStock(item, move, qty);
        getStock();
        getItems();
        clearUpdateFields();
    }

    @FXML
    void addNewItem(ActionEvent event) {
        if(!isPriceValid || !isNewQTYValid || !isMinQTYValid) {
            new ShowNotification("Invalid input",
                    "Please check the input fields",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
            return;
        }
        String itemName = this.itemName.getText();
        int newQTY = Integer.parseInt(this.newQTY.getText());
        double price = Double.parseDouble(this.price.getText());
        String supplierName = this.supplierName.getValue();
        int minQTY = Integer.parseInt(this.minQTY.getText());
        if(stockDAOimpl.isAddNewItem(itemName, newQTY, price, supplierName, minQTY)) {
            getStock();
            new ShowNotification("Item added successfully",
                    itemName+" added new item to the stock",
                    "success.png",
                    "he he login notification eka click kala"
            ).start();
            getItems();
            getStock();
            clearAddFields();
        }else{
            new ShowNotification("Item added failed",
                    "Failed to add "+itemName+" to the stock",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
        }
    }

    @FXML
    void deleteItem(ActionEvent event) {
        StockDto item = itemTable.getSelectionModel().getSelectedItem();

        if(item == null) {
            new ShowNotification("Invalid input",
                    "Please check the input fields",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
            return;
        }
        boolean yes = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete "+item.getItem()+" from the stock?").showAndWait().get() == ButtonType.OK;
        if(!yes) {
            return;
        }
        if(stockDAOimpl.isDeleteItem(item)) {
            getStock();
            new ShowNotification("Item deleted successfully",
                    item.getItem()+" deleted from the stock",
                    "success.png",
                    "he he login notification eka click kala"
            ).start();
            getItems();
        }else{
            new ShowNotification("Item delete failed",
                    "Failed to delete "+item.getItem()+" from the stock",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
        }
    }

    @FXML
    void checkNumberRegex1(KeyEvent event) {
        if(CheckRegex.checkRegex("number", minQTY.getText())) {
            minQTY.setStyle("-fx-text-fill: BLACK");
            isPriceValid = true;
        }else{
            minQTY.setStyle("-fx-text-fill: RED");
            isPriceValid = false;
        }
    }

    @FXML
    void checkNumberRegex2(KeyEvent event) {
        if(CheckRegex.checkRegex("number", newQTY.getText())) {
            newQTY.setStyle("-fx-text-fill: BLACK");
            isNewQTYValid = true;
        }else{
            newQTY.setStyle("-fx-text-fill: RED");
            isNewQTYValid = false;
        }
    }
    @FXML
    void checkNumberRegex3(KeyEvent event) {
        if(CheckRegex.checkRegex("number", price.getText())) {
            price.setStyle("-fx-text-fill: BLACK");
            isMinQTYValid = true;
        }else{
            price.setStyle("-fx-text-fill: RED");
            isMinQTYValid = false;
        }
    }
    @FXML
    void qtyRegex(KeyEvent event) {
        if(CheckRegex.checkRegex("number", qtyTxt.getText())) {
            qtyTxt.setStyle("-fx-text-fill: BLACK");
            isQtyValid = true;
        }else{
            qtyTxt.setStyle("-fx-text-fill: RED");
            isQtyValid = false;
        }
    }

    void clearUpdateFields() {
        qtyTxt.clear();
        itemList.getSelectionModel().clearSelection();
        movement.getSelectionModel().clearSelection();
    }

    void clearAddFields() {
        qtyTxt.clear();
        itemName.clear();
        newQTY.clear();
        price.clear();
        minQTY.clear();
    }
}
