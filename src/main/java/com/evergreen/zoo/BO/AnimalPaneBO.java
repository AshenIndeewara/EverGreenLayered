package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.AnimalDto;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.entity.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AnimalPaneBO extends SuperBo {
    Boolean isUpdateAnimal(String animalID, AnimalDto animalDto) throws SQLException;

    ArrayList<AnimalTDto> searchAnimal(String search) throws SQLException;

    String getSpeciesID(String selectedItem);

    ArrayList<String> getEmployeeID();

    ArrayList<AnimalTDto> getAnimals() throws SQLException, ClassNotFoundException;

    ArrayList<String> getSpecies() throws SQLException, ClassNotFoundException;

    boolean isAddAnimal(AnimalDto animal) throws SQLException;

    String getDiet(String diet) throws SQLException, ClassNotFoundException;

    Boolean isDeleteAnimal(String animalID) throws SQLException;
}
