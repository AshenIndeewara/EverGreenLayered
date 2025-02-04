package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.tanleDto.DashboardDto;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;

public interface DashboardBO  extends SuperBo{
    HashMap<String, String> status();

    ArrayList<DashboardDto> getSpecies();

    XYChart.Series<String, Number> getData();
}
