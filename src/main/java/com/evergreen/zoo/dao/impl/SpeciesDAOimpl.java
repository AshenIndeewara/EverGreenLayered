package com.evergreen.zoo.dao.impl;

import com.evergreen.zoo.dao.SpeciesDAO;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.dto.tanleDto.SpeciesDto;
import com.evergreen.zoo.entity.Animal;
import com.evergreen.zoo.entity.Species;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.ResultSet;
import java.util.ArrayList;

public class SpeciesDAOimpl implements SpeciesDAO {
    public int getSpeciesCount(String speciesID) {
        String query = "SELECT COUNT(*) FROM animal WHERE speciesID = ?";
        try {
            ResultSet rs = CrudUtil.execute(query, speciesID);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public String getFoodName(String foodId) {
        String query = "SELECT * FROM food WHERE foodId = ?";
        try {
            ResultSet rs = CrudUtil.execute(query, foodId);
            if (rs.next()) {
                return rs.getString(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Species> getSpecies() {
        String query = "SELECT * FROM species";
        ArrayList<Species> species = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(query);
            while (rs.next()) {
                Species species1 = new Species();
                species1.setId(rs.getString(1));
                species1.setName(rs.getString(2));
                species1.setConservationStatus(rs.getString(3));
                species1.setFoodID(Integer.parseInt(rs.getString(4)));
                species.add(species1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return species;
    }

//    public ArrayList<SpeciesDto> getSpecies() {
//        String query = "SELECT * FROM species";
//        String speciesCount = "SELECT COUNT(*) FROM animal WHERE speciesID = ?";
//        String speciesDiet = "SELECT * FROM food WHERE foodId = ?";
//        ArrayList<SpeciesDto> speciesDtos = new ArrayList<>();
//        try {
//             ResultSet rs = CrudUtil.execute(query);
//             while (rs.next()) {
//                 SpeciesDto speciesDto = new SpeciesDto();
//                 speciesDto.setSpeciesID(rs.getString(1));
//                 speciesDto.setSpeciesName(rs.getString(2));
//                ResultSet rs2 = CrudUtil.execute(speciesCount, rs.getString(1));
//                if (rs2.next()) {
//                    speciesDto.setSpeciesCount(rs2.getInt(1));
//                }
//                ResultSet rs3 = CrudUtil.execute(speciesDiet, rs.getString(4));
//                if (rs3.next()) {
//                    speciesDto.setSpeciesDiet(rs3.getString(2));
//                }
//                 speciesDto.setSpeciesStatus(rs.getString(3));
//                 speciesDtos.add(speciesDto);
//             }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return speciesDtos;
//    }

    public ArrayList<Animal> getAnimals(String speciesID) {
        System.out.println("getAnimals called");
        String query = "SELECT * FROM animal WHERE speciesID = ?";
        ArrayList<Animal> animals = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(query, speciesID);
            while (rs.next()) {
                Animal animal = new Animal();
                animal.setNickName(rs.getString(2));
                animals.add(animal);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return animals;
    }

//    public ArrayList<AnimalTDto> getAnimals(String speciesID) {
//        String query = "SELECT * FROM animal WHERE speciesID = ?";
//        ArrayList<AnimalTDto> animals = new ArrayList<>();
//        try {
//            ResultSet rs = CrudUtil.execute(query, speciesID);
//            while (rs.next()) {
//                AnimalTDto animal = new AnimalTDto();
//                animal.setName(rs.getString(2));
//                animals.add(animal);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return animals;
//    }

    public boolean addSpecies(String speciesName, String speciesDiet, String speciesStatus) {
        String query = "INSERT INTO species (name, foodId, ConservationStatus) VALUES (?, ?, ?)";
        String foodIdSQL = "SELECT * FROM food WHERE name = ?";
        try {
            ResultSet rs = CrudUtil.execute(foodIdSQL, speciesDiet);
            if (rs.next()) {
                String foodId = rs.getString(1);
                return CrudUtil.execute(query, speciesName, foodId, speciesStatus);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> getDiets() {
        String query = "SELECT * FROM food";
        ArrayList<String> diets = new ArrayList<>();
        try {
            ResultSet rs = CrudUtil.execute(query);
            while (rs.next()) {
                diets.add(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diets;
    }
}
