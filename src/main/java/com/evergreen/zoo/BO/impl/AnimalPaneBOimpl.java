package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.AnimalPaneBO;
import com.evergreen.zoo.dao.AnimalDAO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.impl.AnimalDAOimpl;
import com.evergreen.zoo.dto.AnimalDto;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.entity.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalPaneBOimpl implements AnimalPaneBO {
    AnimalDAO animalDAO = (AnimalDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.ANIMAL);
    @Override
    public Boolean isUpdateAnimal(String animalID, AnimalDto animalDto) {
        return animalDAO.isUpdate(animalID, new Animal(
                animalDto.getName(),
                animalDto.getSpecies(),
                animalDto.getHealth(),
                animalDto.getHealthDescription(),
                animalDto.getGender(),
                animalDto.getAge(),
                animalDto.getEmployeeID()
        ));
    }

    @Override
    public ArrayList<AnimalTDto> searchAnimal(String search) throws SQLException {
        return animalDAO.search(search);
    }

    @Override
    public String getSpeciesID(String selectedItem) {
        return animalDAO.getSpeciesID(selectedItem);
    }

    @Override
    public ArrayList<String> getEmployeeID() {
        return animalDAO.getEmployeeID();
    }

    @Override
    public ArrayList<AnimalTDto> getAnimals() throws SQLException, ClassNotFoundException {
        return animalDAO.getAnimals();
    }

    @Override
    public ArrayList<String> getSpecies() throws SQLException, ClassNotFoundException {
        return animalDAO.getSpecies();
    }

    @Override
    public boolean isAddAnimal(AnimalDto animal) throws SQLException {
        return animalDAO.isAdd(new Animal(
                animal.getName(),
                animal.getSpecies(),
                animal.getHealth(),
                animal.getHealthDescription(),
                animal.getGender(),
                animal.getAge(),
                animal.getEmployeeID()
        ));
    }

    @Override
    public String getDiet(String diet) throws SQLException, ClassNotFoundException {
        return animalDAO.getDiet(diet);
    }

    @Override
    public Boolean isDeleteAnimal(String animalID) throws SQLException {
        return animalDAO.isDelete(animalID);
    }
}
