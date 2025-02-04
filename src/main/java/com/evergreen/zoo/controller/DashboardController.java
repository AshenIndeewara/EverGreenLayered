package com.evergreen.zoo.controller;

import com.evergreen.zoo.BO.BOFactory;
import com.evergreen.zoo.BO.BOTypes;
import com.evergreen.zoo.BO.DashboardBO;
import com.evergreen.zoo.BO.impl.DashboardBOimpl;
import com.evergreen.zoo.dto.tanleDto.DashboardDto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Label animalCount;

    @FXML
    private Label eventCount;

    @FXML
    private Label staffCount;

    @FXML
    private Label visitorCount;
    @FXML
    private TableColumn<DashboardDto, Integer> tableQTY;

    @FXML
    private TableColumn<DashboardDto, String> tableSpecies;

    @FXML
    private TableColumn<DashboardDto, String> tableStatus;

    @FXML
    private TableView<DashboardDto> animalTable;

    @FXML
    private VBox vboxVisitor;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private BarChart<String, Number> barChartTicket;

    DashboardBO dashboardDAOimpl = (DashboardBO) BOFactory.getBoFactory().getBO(BOTypes.DASHBOARDBO);

    private int role;

    public void setRole(int role) {
        this.role = role;
        System.out.println("role from dashboard pane = " + role);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> status = dashboardDAOimpl.status();

        animalCount.setText(status.get("animal"));
        staffCount.setText(status.get("employee"));
        visitorCount.setText(status.get("visitordetails"));

        tableSpecies.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("species"));
        tableQTY.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("count"));
        tableStatus.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("status"));

        loadTable();

    }

    void loadTable() {
        ArrayList<DashboardDto> dashboardDtos = dashboardDAOimpl.getSpecies();
        animalTable.getItems().clear();
        animalTable.getItems().addAll(dashboardDtos);

        XYChart.Series<String, Number> series = dashboardDAOimpl.getData();
        barChartTicket.getData().add(series);
    }

}
