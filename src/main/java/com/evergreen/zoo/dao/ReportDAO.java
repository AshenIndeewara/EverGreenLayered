package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.reportDto.AnimalReportDto;
import com.evergreen.zoo.dto.reportDto.TicketReportDto;
import com.evergreen.zoo.dto.tanleDto.CustomerTDto;
import com.evergreen.zoo.dto.tanleDto.StaffDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportDAO extends SuperDAO{
    public ArrayList<CustomerTDto> getCustomerReport();
    public ArrayList<TicketReportDto> getSalesReport();
    public ArrayList<AnimalReportDto> getAnimalReport();
    public ArrayList<StaffDto> getAllStaff();
    public String getRoleDescription(int roleID) throws SQLException;
    public ArrayList<SupplierDto> getAllSupplier();
}
