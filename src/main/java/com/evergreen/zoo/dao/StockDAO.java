package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.tanleDto.StockDto;
import com.evergreen.zoo.entity.Food;
import com.evergreen.zoo.util.CrudUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface StockDAO extends SuperDAO {
    public ArrayList<StockDto> getStock();
    public ArrayList<String> getItems();
    public Boolean isUpdateStock(String item, String move, int QtyOnHand);
    public ArrayList<String> getSupplier();
    public Boolean isAddNewItem(String itemName, int newQTY, double price, String supplierName, int minQTY);
    public int getIteeQty(String item);
    public boolean isDeleteItem(Food food);
}
