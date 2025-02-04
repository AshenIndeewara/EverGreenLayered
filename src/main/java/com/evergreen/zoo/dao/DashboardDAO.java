package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.tanleDto.DashboardDto;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;

public interface DashboardDAO extends SuperDAO{
    public HashMap<String, String> status();
    public ArrayList<DashboardDto> getSpecies();
    public XYChart.Series<String, Number> getData();
}
