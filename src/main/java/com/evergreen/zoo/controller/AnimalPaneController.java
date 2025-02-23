package com.evergreen.zoo.controller;

import com.evergreen.zoo.BO.AnimalPaneBO;
import com.evergreen.zoo.BO.BOFactory;
import com.evergreen.zoo.BO.BOTypes;
import com.evergreen.zoo.BO.impl.AnimalPaneBOimpl;
import com.evergreen.zoo.dao.AnimalDAO;
import com.evergreen.zoo.dto.AnimalDto;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.util.ShowNotification;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnimalPaneController implements Initializable {
    @FXML
    private TableView<AnimalTDto> animalTable;

    @FXML
    private VBox vbox1;

    @FXML
    private VBox vbox12;

    @FXML
    private TextField animalAge;

    @FXML
    private TextField animalDiet;

    @FXML
    private TextField animalSearch;

    @FXML
    private ComboBox<String> animalHealth;

    @FXML
    private ComboBox<String> animalGender;

    @FXML
    private TextField animalName;

    @FXML
    private ComboBox<String> animalSpecies;

    @FXML
    private TableColumn<AnimalDto, Integer> tableAge;

    @FXML
    private TableColumn<AnimalDto, String> tableDiet;

    @FXML
    private TableColumn<AnimalDto, String> tableHealth;

    @FXML
    private TableColumn<AnimalDto, String> tableName;

    @FXML
    private TableColumn<AnimalDto, String> tableSpecies;

    @FXML
    private TableColumn<AnimalDto, String> tableGender;

    @FXML
    private TextArea descriptionTXT;

    @FXML
    private JFXButton addBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton updateBtn;

    @FXML
    private ComboBox<String> emID;

    AnimalDto animalDto = new AnimalDto();

    AnimalPaneBO animalBO = (AnimalPaneBO) BOFactory.getBoFactory().getBO(BOTypes.ANIMALPANEBO);

    private int role;

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableSpecies.setCellValueFactory(new PropertyValueFactory<>("species"));
        tableDiet.setCellValueFactory(new PropertyValueFactory<>("diet"));
        tableHealth.setCellValueFactory(new PropertyValueFactory<>("health"));
        tableAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        tableGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        try{
            loadGentder();
            getSpecies();
            loadHealth();
            loadEmployeeID();
            loadAnimalTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployeeID() {
        emID.getItems().clear();
        ArrayList<String> employeeIDs = animalBO.getEmployeeID();
        for(String ids : employeeIDs){
            emID.getItems().add(ids);
        }
    }

    private void loadGentder() {
        animalGender.getItems().add("Male");
        animalGender.getItems().add("Female");
    }

    private void loadAnimalTable() throws SQLException, ClassNotFoundException {
        System.out.println("loadAnimalTable");
        ArrayList<AnimalTDto> animalTDtos = animalBO.getAnimals();
        animalTable.getItems().clear();
//        for(AnimalTDto animalTDto : animalTDtos){
//            System.out.println(animalTDto.toString());
//        }
        animalTable.getItems().addAll(animalTDtos);
    }

    private void getSpecies() throws SQLException, ClassNotFoundException {
        ArrayList<String> species = animalBO.getSpecies();
        for(String names : species){
            animalSpecies.getItems().add(names);
        }
    }

    private void loadHealth() {
        animalHealth.getItems().add("Healthy");
        animalHealth.getItems().add("Under Observation");
        animalHealth.getItems().add("Sick");
    }

    @FXML
    void addAnimal(ActionEvent event) throws SQLException, ClassNotFoundException {
        String name = animalName.getText();
        String species = animalSpecies.getSelectionModel().getSelectedItem();
        String health = animalHealth.getSelectionModel().getSelectedItem();
        String gender = animalGender.getSelectionModel().getSelectedItem();
        int age = Integer.parseInt(animalAge.getText());
        if(name.isEmpty() || species.isEmpty() || health.isEmpty() ||gender.isEmpty() || age == 0){
            new ShowNotification("Empty Fields",
                    "Please fill all the fields",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
            return;
        }
        animalDto.setName(name);
        animalDto.setSpecies(species);
        animalDto.setHealth(health);
        animalDto.setAge(age);
        animalDto.setGender(gender);
        animalDto.setEmployeeID(emID.getSelectionModel().getSelectedItem());

        System.out.println(animalDto.toString());
        if (animalBO.isAddAnimal(animalDto)){
            loadAnimalTable();
            new ShowNotification("Animal Added",
                    "Animal has been added successfully",
                    "success.png",
                    "he he login notification eka click kala"
            ).start();
        }else{
            new ShowNotification("Animal Added Unsuccessful",
                    "Animal has not been added Unsuccessful",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
        }
    }

    @FXML
    void getDiet(ActionEvent event) throws SQLException, ClassNotFoundException {
        String diet = animalSpecies.getSelectionModel().getSelectedItem();
        String diet1 = animalBO.getDiet(diet);
        animalDiet.setText(diet1);

    }

    @FXML
    void clearAnimal(ActionEvent event) {
        animalName.clear();
        animalSpecies.getSelectionModel().clearSelection();
        animalDiet.clear();
        animalHealth.getSelectionModel().clearSelection();
        animalAge.clear();
        descriptionTXT.clear();
        animalGender.getSelectionModel().clearSelection();
        animalDto.clearAll();
        loadEmployeeID();

        updateBtn.setDisable(true);
        deleteBtn.setDisable(true);
        addBtn.setDisable(false);
    }

    @FXML
    void deleteAnimal(ActionEvent event) throws SQLException, ClassNotFoundException {
        AnimalTDto selectedItem = animalTable.getSelectionModel().getSelectedItem();
        //new Alert(Alert.AlertType.INFORMATION, "Payment Updated", ButtonType.OK).show();
        boolean yes = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this animal?", ButtonType.YES, ButtonType.NO).showAndWait().get() == ButtonType.YES;
        if (!yes) {
            return;
        }
        Boolean isDelete = animalBO.isDeleteAnimal(selectedItem.getAnimalID());
        if (isDelete){
            loadAnimalTable();
            new ShowNotification("Animal Deleted",
                    "Animal has been deleted successfully",
                    "success.png",
                    "he he login notification eka click kala"
            ).start();
        }else{
            new ShowNotification("Animal Deleted Unsuccessful",
                    "Animal has not been deleted successfully",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
        }
    }

    @FXML
    void searchAnimal(KeyEvent event) throws SQLException {
        
        String search = animalSearch.getText();
        ArrayList<AnimalTDto> animalTDtos = animalBO.searchAnimal(search);
        animalTable.getItems().clear();
        animalTable.getItems().addAll(animalTDtos);
    }

    @FXML
    void updateAnimal(ActionEvent event) throws SQLException, ClassNotFoundException {
        AnimalTDto selectedItem = animalTable.getSelectionModel().getSelectedItem();
        animalDto.setName(animalName.getText());
        animalDto.setSpecies(animalBO.getSpeciesID(animalSpecies.getSelectionModel().getSelectedItem()));
        animalDto.setHealth(animalHealth.getSelectionModel().getSelectedItem());
        animalDto.setAge(Integer.parseInt(animalAge.getText()));
        animalDto.setGender(animalGender.getSelectionModel().getSelectedItem());
        animalDto.setHealthDescription(descriptionTXT.getText());
        animalDto.setEmployeeID(emID.getSelectionModel().getSelectedItem());

        Boolean isUpdate = animalBO.isUpdateAnimal(selectedItem.getAnimalID(), animalDto);
        if (isUpdate) {
            loadAnimalTable();
            new ShowNotification("Animal Updated",
                    "Animal has been updated successfully",
                    "success.png",
                    "he he login notification eka click kala"
            ).start();
            clearAnimal(event);
        }else {
            new ShowNotification("Animal Updated Unsuccessful",
                    "Animal has not been updated successfully",
                    "unsuccess.png",
                    "he he login notification eka click kala"
            ).start();
        }
    }
    @FXML
    void getHealth(ActionEvent event) {
        String health = animalHealth.getSelectionModel().getSelectedItem();
        if (health != null && health.equals("Sick") | health.equals("Under Observation")) {
            vbox1.setVisible(false);
            vbox12.setVisible(true);
        }
    }

    @FXML
    void addDescription(ActionEvent event) {
        animalDto.clearAll();
        String description = descriptionTXT.getText();
        if (description.isEmpty()) {
            descriptionTXT.setStyle("-fx-border-color: red");
        } else {
            descriptionTXT.setStyle("-fx-border-color: green");
            animalDto.setHealthDescription(description);
            vbox12.setVisible(false);
            vbox1.setVisible(true);
        }

    }
    @FXML
    void backToAnimal(ActionEvent event) {
        vbox12.setVisible(false);
        vbox1.setVisible(true);
    }

    @FXML
    void onClickTable(MouseEvent event) {
        AnimalTDto selectedItem = animalTable.getSelectionModel().getSelectedItem();
        try {
            animalName.setText(selectedItem.getName());
            animalSpecies.setValue(selectedItem.getSpecies());
            animalDiet.setText(selectedItem.getDiet());
            animalHealth.setValue(selectedItem.getHealth());
            animalAge.setText(String.valueOf(selectedItem.getAge()));
            animalGender.setValue(selectedItem.getGender());
            descriptionTXT.setText(selectedItem.getHealthDescription());
            emID.setValue(selectedItem.getEmployeeID());
            updateBtn.setDisable(false);
            deleteBtn.setDisable(false);
            addBtn.setDisable(true);
        } catch (Exception e) {
            //System.out.println("Error in onClickTable : "+ e.getMessage());
            new Alert(Alert.AlertType.ERROR, "Error in onClickTable : "+ e.getMessage(), ButtonType.OK).show();
        }

    }

}
