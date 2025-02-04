package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.reportDto.AnimalReportDto;
import com.evergreen.zoo.dto.reportDto.TicketReportDto;
import com.evergreen.zoo.dto.tanleDto.CustomerTDto;
import com.evergreen.zoo.dto.tanleDto.StaffDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;

import java.util.ArrayList;

public interface ReportBO extends SuperBo{
    ArrayList<CustomerTDto> getCustomerReport();

    ArrayList<TicketReportDto> getSalesReport();

    ArrayList<AnimalReportDto> getAnimalReport();

    ArrayList<StaffDto> getAllStaff();

    ArrayList<SupplierDto> getAllSupplier();
}
