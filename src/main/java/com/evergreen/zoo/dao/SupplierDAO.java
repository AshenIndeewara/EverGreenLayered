package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.FoodDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends SuperDAO{
    public ArrayList<SupplierDto> getSuppliers();
    public Boolean addSupplier(SupplierDto supplierDto);
    public Boolean isUpdateSupplier(SupplierDto supplierDto);
    public ArrayList<FoodDto> getSupplierItems(String supplierID);
    public Boolean isDeleteSupplier(SupplierDto supplierDto, ArrayList<FoodDto> items) throws SQLException;
}
