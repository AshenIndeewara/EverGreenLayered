package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.DashboardBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.DashboardDAO;
import com.evergreen.zoo.dao.impl.DashboardDAOimpl;
import com.evergreen.zoo.dto.tanleDto.DashboardDto;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardBOimpl implements DashboardBO {
    DashboardDAO dashboardBO = (DashboardDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.DASHBOARD);
    @Override
    public HashMap<String, String> status() {
        return dashboardBO.status();
    }

    @Override
    public ArrayList<DashboardDto> getSpecies() {
        return dashboardBO.getSpecies();
    }

    @Override
    public XYChart.Series<String, Number> getData() {
        return dashboardBO.getData();
    }
}
