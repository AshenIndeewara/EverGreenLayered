package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.AnimalDto;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.entity.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnimalDAO extends CrudDAO<AnimalTDto> {
    public ArrayList<AnimalTDto> getAnimals() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getSpecies() throws SQLException, ClassNotFoundException;
    public String getDiet(String id) throws SQLException, ClassNotFoundException;
//    public ArrayList<AnimalTDto> searchAnimal(String search);
    public Boolean isUpdate(String animalID, Animal animalDto);
    public Boolean isDelete(String animalID) throws SQLException;
    public String getSpeciesID(String selectedItem);
    public ArrayList<String> getEmployeeID();
    boolean isAdd(Animal animalDto) throws SQLException;
}
