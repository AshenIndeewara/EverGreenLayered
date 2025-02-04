package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.SupplierBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.SupplierDAO;
import com.evergreen.zoo.dto.FoodDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;

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
        return supplierDAO.addSupplier(supplierDto);
    }

    @Override
    public ArrayList<FoodDto> getSupplierItems(String supplierID) {
        return supplierDAO.getSupplierItems(supplierID);
    }

    @Override
    public Boolean isDeleteSupplier(SupplierDto supplierDto, ArrayList<FoodDto> items) throws SQLException {
        return supplierDAO.isDeleteSupplier(supplierDto, items);
    }

    @Override
    public Boolean isUpdateSupplier(SupplierDto supplierDto) {
        return supplierDAO.isUpdateSupplier(supplierDto);
    }
}
