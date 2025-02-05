package com.evergreen.zoo.dao.impl;

import com.evergreen.zoo.dao.StockDAO;
import com.evergreen.zoo.dto.tanleDto.StockDto;
import com.evergreen.zoo.entity.Food;
import com.evergreen.zoo.util.CrudUtil;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.ResultSet;
import java.util.ArrayList;

public class StockDAOimpl implements StockDAO {
    public ArrayList<StockDto> getStock() {
        String sql = "SELECT * FROM food";
        String getSupplier = "SELECT name FROM supplier WHERE supplierID = ?";
        ArrayList<StockDto> stockDtos = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                StockDto stockDto = new StockDto();
                stockDto.setItemID(rs.getString(1));
                String supplierId = rs.getString(3);
                ResultSet rs2 = CrudUtil.execute(getSupplier, supplierId);
                while (rs2.next()) {
                    stockDto.setSupplier(rs2.getString(1));
                }
                stockDto.setItem(rs.getString(2));
                stockDto.setQty(rs.getInt(6));
                if(rs.getInt(6) > rs.getInt(5)) {
                    ImageView upImageView = new ImageView(new Image(getClass().getResourceAsStream("/asserts/images/up.png")));
                    upImageView.setFitWidth(20);
                    upImageView.setFitHeight(20);
                    stockDto.setTypeImage(upImageView);
                } else {
                    ImageView downImageView = new ImageView(new Image(getClass().getResourceAsStream("/asserts/images/down.png")));
                    downImageView.setFitWidth(20);
                    downImageView.setFitHeight(20);
                    stockDto.setTypeImage(downImageView);
                }
                stockDtos.add(stockDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockDtos;
    }

    public ArrayList<String> getItems() {
        String sql = "SELECT name FROM food";
        ArrayList<String> items = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                items.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public Boolean isUpdateStock(String item, String move, int QtyOnHand) {
        if(move.equals("Stock In")) {
            String sql = "UPDATE food SET QtyOnHand = QtyOnHand + ? WHERE name = ?";
            try {
                CrudUtil.execute(sql, QtyOnHand, item);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String sql = "UPDATE food SET QtyOnHand = QtyOnHand - ? WHERE name = ?";
            try {
                CrudUtil.execute(sql, QtyOnHand, item);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public ArrayList<String> getSupplier() {
        String sql = "SELECT name FROM supplier";
        ArrayList<String> suppliers = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                suppliers.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    public Boolean isAddNewItem(String itemName, int newQTY, double price, String supplierName, int minQTY) {
        String sql = "INSERT INTO food (name, supplierID, QtyOnHand, price, minQty) VALUES (?, (SELECT supplierID FROM supplier WHERE name = ?), ?, ?, ?)";
        try {
            CrudUtil.execute(sql, itemName, supplierName, newQTY, price, minQTY);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getIteeQty(String item) {
        String sql = "SELECT QtyOnHand FROM food WHERE name = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, item);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean isDeleteItem(Food food) {
        String sql = "DELETE FROM food WHERE foodId = ?";
        System.out.println(food.toString());
        try {
            CrudUtil.execute(sql, food.getFoodID());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Food> getFoods() {
        String sql = "SELECT * FROM food";
        ArrayList<Food> foods = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                Food food = new Food();
                food.setFoodID(rs.getInt(1));
                food.setName(rs.getString(2));
                food.setQtyOnHand(rs.getInt(6));
                food.setPrice(rs.getDouble(4));
                food.setMinQTY(rs.getInt(5));
                food.setSupplierId(rs.getInt(3));
                foods.add(food);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foods;
    }

    public String getSupplierName(int supplierId) {
        String sql = "SELECT name FROM supplier WHERE supplierID = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, supplierId);
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ImageView getStockImage(int qty, int minQty) {
        if(qty > minQty) {
            ImageView upImageView = new ImageView(new Image(getClass().getResourceAsStream("/asserts/images/up.png")));
            upImageView.setFitWidth(20);
            upImageView.setFitHeight(20);
            return upImageView;
        } else {
            ImageView downImageView = new ImageView(new Image(getClass().getResourceAsStream("/asserts/images/down.png")));
            downImageView.setFitWidth(20);
            downImageView.setFitHeight(20);
            return downImageView;
        }
    }
}
