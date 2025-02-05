package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.AnimalPaneBO;
import com.evergreen.zoo.dao.AnimalDAO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.impl.AnimalDAOimpl;
import com.evergreen.zoo.dto.AnimalDto;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.entity.Animal;
import com.evergreen.zoo.entity.Healthrecords;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalPaneBOimpl implements AnimalPaneBO {
    AnimalDAO animalDAO = (AnimalDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.ANIMAL);
    @Override
    public Boolean isUpdateAnimal(String animalID, AnimalDto animalDto) throws SQLException {
//        return animalDAO.isUpdate(animalID, new Animal(
//                animalDto.getName(),
//                animalDto.getSpecies(),
//                animalDto.getHealth(),
//                animalDto.getHealthDescription(),
//                animalDto.getGender(),
//                animalDto.getAge(),
//                animalDto.getEmployeeID()
//        ));
        Animal animal = new Animal();
        Healthrecords healthrecords = new Healthrecords();
        animal.setNickName(animalDto.getName());
        animal.setSpeciesId(Integer.parseInt(animalDto.getSpecies()));
        animal.setGender(animalDto.getGender());
        animal.setAge(animalDto.getAge());
        animal.setEmID(Integer.parseInt(animalDto.getEmployeeID()));

        healthrecords.setType(animalDto.getHealth());
        healthrecords.setDescription(animalDto.getHealthDescription());

        return animalDAO.isUpdate(animalID, animal, healthrecords);
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
        System.out.println("getAnimals BO called");
        ArrayList<Animal> animals = animalDAO.getAnimals();
        ArrayList<AnimalTDto> animalTDtos = new ArrayList<>();
        for (Animal animal : animals) {
            System.out.println(animalDAO.getHealthDescription(String.valueOf(animal.getAnimalId())));
            AnimalTDto temp = new AnimalTDto(
                    String.valueOf(animal.getAnimalId()),
                    animal.getNickName(),
                    animal.getGender(),
                    animalDAO.getSpeciesName(String.valueOf(animal.getSpeciesId())),
                    animalDAO.getDietbyID(animal.getSpeciesId()),
                    animalDAO.getAnimalHealthType(String.valueOf(animal.getAnimalId())),
                    animalDAO.getHealthDescription(String.valueOf(animal.getAnimalId())),
                    animal.getAge(),
                    String.valueOf(animal.getEmID()
                    ));
            animalTDtos.add(temp);
        }
        return animalTDtos;
    }

    @Override
    public ArrayList<String> getSpecies() throws SQLException, ClassNotFoundException {
        return animalDAO.getSpecies();
    }

    @Override
    public boolean isAddAnimal(AnimalDto animal) throws SQLException {
//        return animalDAO.isAdd(new Animal(
//                animal.getName(),
//                animal.getSpecies(),
//                animal.getHealth(),
//                animal.getHealthDescription(),
//                animal.getGender(),
//                animal.getAge(),
//                animal.getEmployeeID()
//        ));
        Animal animal1 = new Animal();
        Healthrecords healthrecords = new Healthrecords();
        animal1.setNickName(animal.getName());
        animal1.setSpeciesId(Integer.parseInt(animalDAO.getSpeciesID(animal.getSpecies())));
        healthrecords.setType(animal.getHealth());
        animal1.setGender(animal.getGender());
        animal1.setAge(animal.getAge());
        animal1.setEmID(Integer.parseInt(animal.getEmployeeID()));
        healthrecords.setDescription(animal.getHealthDescription());

        return animalDAO.isAdd(animal1, healthrecords);
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
