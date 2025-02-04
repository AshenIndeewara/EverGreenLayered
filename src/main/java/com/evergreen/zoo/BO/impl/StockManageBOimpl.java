package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.StockManageBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.StockDAO;
import com.evergreen.zoo.dto.tanleDto.StockDto;

import java.util.ArrayList;

public class StockManageBOimpl implements StockManageBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.STOCKMANAGEDAO);
    @Override
    public ArrayList<StockDto> getStock() {
        return stockDAO.getStock();
    }

    @Override
    public ArrayList<String> getItems() {
        return stockDAO.getItems();
    }

    @Override
    public ArrayList<String> getSupplier() {
        return stockDAO.getSupplier();
    }

    @Override
    public int getIteeQty(String item) {
        return stockDAO.getIteeQty(item);
    }

    @Override
    public void isUpdateStock(String item, String move, int qty) {
        stockDAO.isUpdateStock(item, move, qty);
    }

    @Override
    public boolean isAddNewItem(String itemName, int newQTY, double price, String supplierName, int minQTY) {
        return stockDAO.isAddNewItem(itemName, newQTY, price, supplierName, minQTY);
    }

    @Override
    public boolean isDeleteItem(StockDto item) {
        return stockDAO.isDeleteItem(item);
    }
}
