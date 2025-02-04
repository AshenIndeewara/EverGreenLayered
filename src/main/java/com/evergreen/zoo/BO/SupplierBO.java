package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.FoodDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBo{
    ArrayList<SupplierDto> getSuppliers();

    Boolean addSupplier(SupplierDto supplierDto);

    ArrayList<FoodDto> getSupplierItems(String supplierID);

    Boolean isDeleteSupplier(SupplierDto supplierDto, ArrayList<FoodDto> items) throws SQLException;

    Boolean isUpdateSupplier(SupplierDto supplierDto);
}
