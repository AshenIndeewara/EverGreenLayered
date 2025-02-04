package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.FoodDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;
import com.evergreen.zoo.entity.Food;
import com.evergreen.zoo.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends SuperDAO{
    public ArrayList<SupplierDto> getSuppliers();
    public Boolean addSupplier(Supplier supplier);
    public Boolean isUpdateSupplier(Supplier supplier);
    public ArrayList<FoodDto> getSupplierItems(String supplierID);
    public Boolean isDeleteSupplier(Supplier supplier, ArrayList<Food> items) throws SQLException;
}
