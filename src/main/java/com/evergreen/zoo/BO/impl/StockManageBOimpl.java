package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.StockManageBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.StockDAO;
import com.evergreen.zoo.dto.tanleDto.StockDto;
import com.evergreen.zoo.entity.Food;

import java.util.ArrayList;

public class StockManageBOimpl implements StockManageBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.STOCKMANAGEDAO);
    @Override
    public ArrayList<StockDto> getStock() {
        System.out.println("StockManageBOimpl.getStock");
//        return stockDAO.getStock();
        ArrayList<Food> foods = stockDAO.getFoods();
        ArrayList<StockDto> stockDtos = new ArrayList<>();
        for (Food food : foods) {
            StockDto temp = new StockDto();
            temp.setItemID(String.valueOf(food.getFoodID()));
            temp.setItem(food.getName());
            temp.setQty(food.getQtyOnHand());
            temp.setSupplier(stockDAO.getSupplierName(food.getSupplierId()));
            temp.setTypeImage(stockDAO.getStockImage(food.getQtyOnHand(), food.getMinQTY()));
            stockDtos.add(temp);
        }
        return stockDtos;
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
        Food food = new Food();
        food.setFoodID(Integer.parseInt(item.getItemID()));
        return stockDAO.isDeleteItem(food);
    }
}
