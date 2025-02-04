package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.SupplierBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.SupplierDAO;
import com.evergreen.zoo.dto.FoodDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;
import com.evergreen.zoo.entity.Food;
import com.evergreen.zoo.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOimpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.SUPPLIERDAO);
    @Override
    public ArrayList<SupplierDto> getSuppliers() {
        return supplierDAO.getSuppliers();
    }

    @Override
    public Boolean addSupplier(SupplierDto supplierDto) {
        return supplierDAO.addSupplier(new Supplier(
                supplierDto.getName(),
                supplierDto.getPhone(),
                supplierDto.getEmail(),
                supplierDto.getAddress(),
                supplierDto.getDescription()
        ));
    }

    @Override
    public ArrayList<FoodDto> getSupplierItems(String supplierID) {
        return supplierDAO.getSupplierItems(supplierID);
    }

    @Override
    public Boolean isDeleteSupplier(SupplierDto supplierDto, ArrayList<FoodDto> items) throws SQLException {
        ArrayList<Food> food = new ArrayList<>();
        for (FoodDto item : items) {
            Food temp = new Food();
            temp.setFoodID(Integer.parseInt(item.getFoodID()));
            temp.setName(item.getName());
            temp.setPrice(item.getPrice());
            temp.setQtyOnHand(item.getQtyOnHand());
            food.add(temp);
        }
        return supplierDAO.isDeleteSupplier(new Supplier(
                supplierDto.getName(),
                supplierDto.getPhone(),
                supplierDto.getEmail(),
                supplierDto.getAddress(),
                supplierDto.getDescription()
        ), food);
    }

    @Override
    public Boolean isUpdateSupplier(SupplierDto supplierDto) {
        return supplierDAO.isUpdateSupplier(new Supplier(
                supplierDto.getName(),
                supplierDto.getPhone(),
                supplierDto.getEmail(),
                supplierDto.getAddress(),
                supplierDto.getDescription()
        ));
    }
}
