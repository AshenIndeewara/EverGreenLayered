package com.evergreen.zoo.dao.impl;

import com.evergreen.zoo.dao.AnimalDAO;
import com.evergreen.zoo.db.DBConnection;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.entity.Animal;
import com.evergreen.zoo.entity.Healthrecords;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AnimalDAOimpl implements AnimalDAO {
    public String getSpeciesName(String speciesID) {
        String sql = "SELECT name FROM species WHERE id = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, speciesID);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getAnimalHealthType(String animalID) {
        String sql = "SELECT type FROM healthrecords WHERE animalId = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, animalID);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Animal> getAnimals() {
        String sql = "SELECT * FROM animal";
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setAnimalId(Integer.parseInt(rs.getString(1)));
                animal.setNickName(rs.getString(2));
                animal.setSpeciesId(Integer.parseInt(rs.getString(3)));
                animal.setGender(rs.getString(4));
                animal.setAge(rs.getInt(5));
                animal.setEmID(Integer.parseInt(rs.getString(6)));
                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }

//    public ArrayList<AnimalTDto> getAnimals1() {
//        String sql = "SELECT * FROM animal";
//        String speciesNameAQL = "SELECT * FROM species WHERE id = ?";
//        String foodNameSQL = "SELECT * FROM food WHERE foodId = ?";
//        String healthNameSQL = "SELECT * FROM healthrecords WHERE animalId = ?";
//        ArrayList<AnimalTDto> animals = new ArrayList<>();
//        try {
//            ResultSet rs = CrudUtil.execute(sql);
//            while (rs.next()) {
//                AnimalTDto animal = new AnimalTDto();
//                animal.setAnimalID(rs.getString(1));
//                animal.setName(rs.getString(2));
//                animal.setGender(rs.getString(4));
//                animal.setEmployeeID(rs.getString(6));
//                animal.setAge(rs.getInt(5));
//                ResultSet rs2 = CrudUtil.execute(speciesNameAQL, rs.getString(3));
//                if (rs2.next()) {
//                    animal.setSpecies(rs2.getString(2));
//                    ResultSet rs3 = CrudUtil.execute(foodNameSQL, rs2.getString(4));
//                    if (rs3.next()) {
//                        animal.setDiet(rs3.getString(2));
//                    }
//                }
//                ResultSet rs4 = CrudUtil.execute(healthNameSQL, rs.getString(1));
//                if (rs4.next()) {
//                    animal.setHealth(rs4.getString(5));
//                    animal.setHealthDescription(rs4.getString(2));
//                }
//                animals.add(animal);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return animals;
//    }

    public String getHealth(String animalID) {
        String sql = "SELECT type FROM healthrecords WHERE animalId = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, animalID);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getHealthDescription(String animalID) {
        String sql = "SELECT description FROM healthrecords WHERE animalId = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, animalID);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getSpecies() {
        String sql = "SELECT * FROM species";
        ArrayList<String> species = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                species.add(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return species;
    }


    public String getDiet(String species) {
        String sql = "SELECT foodId FROM species WHERE name = ?";
        String foodNameSQL = "SELECT name FROM food WHERE foodId = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, species);
            if (rs.next()) {
                ResultSet rs2 = CrudUtil.execute(foodNameSQL, rs.getString(1));
                if (rs2.next()) {
                    return rs2.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDietbyID(int species) {
        String sql = "SELECT foodId FROM species WHERE id = ?";
        String foodNameSQL = "SELECT name FROM food WHERE foodId = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, species);
            if (rs.next()) {
                ResultSet rs2 = CrudUtil.execute(foodNameSQL, rs.getString(1));
                if (rs2.next()) {
                    return rs2.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public ArrayList<AnimalTDto> searchAnimal(String search) {
//        String sql = "SELECT * FROM animal WHERE nickName LIKE ?";
//        String speciesNameAQL = "SELECT * FROM species WHERE id = ?";
//        String foodNameSQL = "SELECT * FROM food WHERE foodId = ?";
//        String healthNameSQL = "SELECT * FROM healthrecords WHERE animalId = ?";
//        ArrayList<AnimalTDto> animals = new ArrayList<>();
//        try {
//            ResultSet rs = CrudUtil.execute(sql, "%" + search + "%");
//            while (rs.next()) {
//                AnimalTDto animal = new AnimalTDto();
//                animal.setAnimalID(rs.getString(1));
//                animal.setName(rs.getString(2));
//                animal.setGender(rs.getString(4));
//                animal.setAge(rs.getInt(5));
//                ResultSet rs2 = CrudUtil.execute(speciesNameAQL, rs.getString(3));
//                if (rs2.next()) {
//                    animal.setSpecies(rs2.getString(2));
//                    ResultSet rs3 = CrudUtil.execute(foodNameSQL, rs2.getString(4));
//                    if (rs3.next()) {
//                        animal.setDiet(rs3.getString(2));
//                    }
//                }
//                ResultSet rs4 = CrudUtil.execute(healthNameSQL, rs.getString(1));
//                if (rs4.next()) {
//                    animal.setHealth(rs4.getString(5));
//                    animal.setHealthDescription(rs4.getString(2));
//                }
//                animals.add(animal);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return animals;
//    }

    @Override
    public Boolean isUpdate(String animalID, Animal animal, Healthrecords healthrecords) throws SQLException {
        String updateAnimalSQL = "UPDATE animal SET nickName = ?, speciesId = ?, gender = ?, age = ?, emID = ? WHERE animalId = ?";
        String updateHealthSQL = "UPDATE healthrecords SET description = ?, type = ? WHERE animalId = ?";
        try {
            if(CrudUtil.execute(updateAnimalSQL, animal.getNickName(), animal.getSpeciesId(), animal.getGender(), animal.getAge(), animal.getEmID(), animalID)){
                if(CrudUtil.execute(updateHealthSQL, healthrecords.getDescription(), healthrecords.getType(), animalID)){
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean isDelete(String animalID) throws SQLException {
        String animalSQL = "DELETE FROM animal WHERE animalId = ?";
        String healthSQL = "DELETE FROM healthrecords WHERE animalId = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            if(CrudUtil.execute(healthSQL, animalID)){
                if(CrudUtil.execute(animalSQL, animalID)){
                    connection.commit();
                    return true;
                }
            }
        } catch (Exception e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }


//    @Override
//    public boolean isAddAnimal(Animal animal) throws SQLException {
//        String speciesSQL = "SELECT id FROM species WHERE name = ?";
//        String animalSQL = "INSERT INTO animal (nickName, speciesId, gender, age, emID) VALUES (?,?,?,?,?)";
//        String animalHealthSQL = "INSERT INTO healthrecords (animalId, date, description, type) VALUES (?,?,?,?)";
//        String lastAnimalIdSQL = "SELECT LAST_INSERT_ID() AS `id`";
//        Date date = new Date();
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//        Connection connection = DBConnection.getInstance().getConnection();
//        connection.setAutoCommit(false);
//        try{
//            ResultSet rs = CrudUtil.execute(speciesSQL, animal.getSpecies());
//            if(rs.next()){
//                if(CrudUtil.execute(animalSQL, animal.getName(), rs.getString(1), animal.getGender(), animal.getAge(), animal.getEmployeeID())){
//                    ResultSet rs2 = CrudUtil.execute(lastAnimalIdSQL);
//                    if(rs2.next()){
//                        if(CrudUtil.execute(animalHealthSQL, rs2.getString(1), sqlDate, animal.getHealthDescription(), animal.getHealth())){
//                            connection.commit();
//                            return true;
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            System.out.println("Error in isAddAnimal : "+ e.getMessage());
//            connection.rollback();
//        } finally {
//            connection.setAutoCommit(true);
//        }
//        return false;
//    }

    public String getSpeciesID(String selectedItem) {
        String sql = "SELECT id FROM species WHERE name = ?";
        try {
            ResultSet rs = CrudUtil.execute(sql, selectedItem);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getEmployeeID() {
        String sql = "SELECT * FROM employee";
        ArrayList<String> employeeID = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql);
            while (rs.next()) {
                employeeID.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeID;
    }

    @Override
    public boolean isAdd(Animal animal, Healthrecords healthrecords) throws SQLException {
        System.out.println("isAdd running");
        String speciesSQL = "SELECT id FROM species WHERE name = ?";
        String animalSQL = "INSERT INTO animal (nickName, speciesId, gender, age, emID) VALUES (?,?,?,?,?)";
        String animalHealthSQL = "INSERT INTO healthrecords (animalId, date, description, type) VALUES (?,?,?,?)";
        String lastAnimalIdSQL = "SELECT LAST_INSERT_ID() AS `id`";
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try{
//            ResultSet rs = CrudUtil.execute(speciesSQL, animal.getSpeciesId());
            if(CrudUtil.execute(animalSQL, animal.getNickName(), animal.getSpeciesId(), animal.getGender(), animal.getAge(), animal.getEmID())){
                ResultSet rs2 = CrudUtil.execute(lastAnimalIdSQL);
                if(rs2.next()){
                    if(CrudUtil.execute(animalHealthSQL, rs2.getString(1), sqlDate, healthrecords.getDescription(), healthrecords.getType())){
                        connection.commit();
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error in isAddAnimal : "+ e.getMessage());
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }

    @Override
    public ArrayList<AnimalTDto> search(String search) {
        String sql = "SELECT * FROM animal WHERE nickName LIKE ?";
        String speciesNameAQL = "SELECT * FROM species WHERE id = ?";
        String foodNameSQL = "SELECT * FROM food WHERE foodId = ?";
        String healthNameSQL = "SELECT * FROM healthrecords WHERE animalId = ?";
        ArrayList<AnimalTDto> animals = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(sql, "%" + search + "%");
            while (rs.next()) {
                AnimalTDto animal = new AnimalTDto();
                animal.setAnimalID(rs.getString(1));
                animal.setName(rs.getString(2));
                animal.setGender(rs.getString(4));
                animal.setAge(rs.getInt(5));
                ResultSet rs2 = CrudUtil.execute(speciesNameAQL, rs.getString(3));
                if (rs2.next()) {
                    animal.setSpecies(rs2.getString(2));
                    ResultSet rs3 = CrudUtil.execute(foodNameSQL, rs2.getString(4));
                    if (rs3.next()) {
                        animal.setDiet(rs3.getString(2));
                    }
                }
                ResultSet rs4 = CrudUtil.execute(healthNameSQL, rs.getString(1));
                if (rs4.next()) {
                    animal.setHealth(rs4.getString(5));
                    animal.setHealthDescription(rs4.getString(2));
                }
                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }
}
