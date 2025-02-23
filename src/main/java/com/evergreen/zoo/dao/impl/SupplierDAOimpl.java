package com.evergreen.zoo.dao.impl;

import com.evergreen.zoo.dao.SupplierDAO;
import com.evergreen.zoo.db.DBConnection;
import com.evergreen.zoo.dto.FoodDto;
import com.evergreen.zoo.dto.tanleDto.SupplierDto;
import com.evergreen.zoo.entity.Food;
import com.evergreen.zoo.entity.Supplier;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOimpl implements SupplierDAO {
    public ArrayList<Supplier> getSuppliers() {
        String sql = "SELECT * FROM supplier";
        ArrayList<Supplier> supplierDtos = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                Supplier supplierDto = new Supplier();
                supplierDto.setSupplierID(Integer.parseInt(rs.getString(1)));
                supplierDto.setName(rs.getString(2));
                supplierDto.setAddress(rs.getString(5));
                supplierDto.setEmail(rs.getString(4));
                supplierDto.setContact(rs.getString(3));
                supplierDto.setDescription(rs.getString(6));
                supplierDtos.add(supplierDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplierDtos;
    }
//    public ArrayList<SupplierDto> getSuppliers() {
//        String sql = "SELECT * FROM supplier";
//        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
//        try {
//            ResultSet rs = CrudUtil.execute(sql);
//            while (rs.next()) {
//                SupplierDto supplierDto = new SupplierDto();
//                supplierDto.setSupplierID(rs.getString(1));
//                supplierDto.setName(rs.getString(2));
//                supplierDto.setAddress(rs.getString(5));
//                supplierDto.setEmail(rs.getString(4));
//                supplierDto.setPhone(rs.getString(3));
//                supplierDto.setDescription(rs.getString(6));
//                supplierDtos.add(supplierDto);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return supplierDtos;
//    }

    @Override
    public Boolean addSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier (name, contact, email, address, description) VALUES (?,?,?,?,?)";
        try {
            return CrudUtil.execute(sql, supplier.getName(), supplier.getContact(), supplier.getEmail(), supplier.getAddress(), supplier.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean isUpdateSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET contact = ?, email = ?, address = ?, description = ? WHERE name = ?";
        try {
            return CrudUtil.execute(sql, supplier.getContact(), supplier.getEmail(), supplier.getAddress(), supplier.getDescription(), supplier.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

//    public Boolean addSupplier(SupplierDto supplierDto) {
//        String sql = "INSERT INTO supplier (name, contact, email, address, description) VALUES (?,?,?,?,?)";
//        try {
//            return CrudUtil.execute(sql, supplierDto.getName(), supplierDto.getPhone(), supplierDto.getEmail(), supplierDto.getAddress(), supplierDto.getDescription());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

//    public Boolean isUpdateSupplier(SupplierDto supplierDto) {
//        String sql = "UPDATE supplier SET contact = ?, email = ?, address = ?, description = ? WHERE name = ?";
//        try {
//            return CrudUtil.execute(sql, supplierDto.getPhone(), supplierDto.getEmail(), supplierDto.getAddress(), supplierDto.getDescription(), supplierDto.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public ArrayList<Food> getSupplierItems(String supplierID) {
        String sql = "SELECT * FROM food WHERE supplierID = ?";
        ArrayList<Food> items = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql, supplierID);
            while (rs.next()) {
                Food foodDto = new Food();
                foodDto.setFoodID(Integer.parseInt(rs.getString(1)));
                foodDto.setName(rs.getString(2));
                foodDto.setQtyOnHand(rs.getInt(3));
                foodDto.setPrice(rs.getDouble(4));
                foodDto.setMinQTY(rs.getInt(5));
                items.add(foodDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Boolean isDeleteSupplier(Supplier supplier, ArrayList<Food> items) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            String sql = "DELETE FROM supplier WHERE name = ?";
            CrudUtil.execute(sql, supplier.getName());
            for (Food item : items) {
                String sql1 = "DELETE FROM food WHERE supplierId = ?";
                Boolean isFoodDelete =  CrudUtil.execute(sql1, item.getSupplierId());
                if (!isFoodDelete) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

//    public Boolean isDeleteSupplier(SupplierDto supplierDto, ArrayList<FoodDto> items) throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        connection.setAutoCommit(false);
//        try {
//            String sql = "DELETE FROM supplier WHERE name = ?";
//            CrudUtil.execute(sql, supplierDto.getName());
//            for (FoodDto item : items) {
//                String sql1 = "DELETE FROM food WHERE supplierId = ?";
//                Boolean isFoodDelete =  CrudUtil.execute(sql1, item.getSupplier());
//                if (!isFoodDelete) {
//                    connection.rollback();
//                    return false;
//                }
//            }
//            connection.commit();
//            return true;
//        } catch (Exception e) {
//            connection.rollback();
//            e.printStackTrace();
//            return false;
//        } finally {
//            connection.setAutoCommit(true);
//        }
//    }
}
