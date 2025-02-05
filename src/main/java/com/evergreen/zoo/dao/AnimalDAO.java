package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.AnimalDto;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.entity.Animal;
import com.evergreen.zoo.entity.Healthrecords;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnimalDAO extends CrudDAO<AnimalTDto> {
    public ArrayList<Animal> getAnimals() throws SQLException, ClassNotFoundException;
    public ArrayList<String> getSpecies() throws SQLException, ClassNotFoundException;
    public String getDiet(String id) throws SQLException, ClassNotFoundException;
    public Boolean isUpdate(String animalID, Animal animal, Healthrecords healthrecords) throws SQLException;
    public Boolean isDelete(String animalID) throws SQLException;
    public String getSpeciesID(String selectedItem);
    public ArrayList<String> getEmployeeID();
    boolean isAdd(Animal animal, Healthrecords healthrecords) throws SQLException;
    String getSpeciesName(String name) throws SQLException;
    String getAnimalHealthType(String selectedItem) throws SQLException;
    public String getDietbyID(int species) throws SQLException;
    String getHealthDescription(String selectedItem) throws SQLException;
}
