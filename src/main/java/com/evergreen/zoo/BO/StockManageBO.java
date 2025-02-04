package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.tanleDto.StockDto;

import java.util.ArrayList;

public interface StockManageBO extends SuperBo {
    ArrayList<StockDto> getStock();

    ArrayList<String> getItems();

    ArrayList<String> getSupplier();

    int getIteeQty(String item);

    void isUpdateStock(String item, String move, int qty);

    boolean isAddNewItem(String itemName, int newQTY, double price, String supplierName, int minQTY);

    boolean isDeleteItem(StockDto item);
}
