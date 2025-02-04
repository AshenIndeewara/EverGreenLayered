package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.ReportBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.ReportDAO;
import com.evergreen.zoo.dto.reportDto.AnimalReportDto;
import com.evergreen.zoo.dto.reportDto.TicketReportDto;
import com.evergreen.zoo.dto.tanleDto.CustomerTDto;
import com.evergreen.zoo.dto.tanleDto.StaffDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;

import java.util.ArrayList;

public class ReportBOimpl implements ReportBO {
    ReportDAO reportDAO = (ReportDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.REPORTDAO);
    @Override
    public ArrayList<CustomerTDto> getCustomerReport() {
        return reportDAO.getCustomerReport();
    }

    @Override
    public ArrayList<TicketReportDto> getSalesReport() {
        return reportDAO.getSalesReport();
    }

    @Override
    public ArrayList<AnimalReportDto> getAnimalReport() {
        return reportDAO.getAnimalReport();
    }

    @Override
    public ArrayList<StaffDto> getAllStaff() {
        return reportDAO.getAllStaff();
    }

    @Override
    public ArrayList<SupplierDto> getAllSupplier() {
        return reportDAO.getAllSupplier();
    }
}
