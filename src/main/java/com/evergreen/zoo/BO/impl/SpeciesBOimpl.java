package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.SpeciesBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.SpeciesDAO;
import com.evergreen.zoo.dao.impl.SpeciesDAOimpl;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.dto.tanleDto.SpeciesDto;
import com.evergreen.zoo.entity.Animal;
import com.evergreen.zoo.entity.Species;

import java.util.ArrayList;

public class SpeciesBOimpl implements SpeciesBO {
    SpeciesDAOimpl speciesDAOimpl = (SpeciesDAOimpl) DAOFactory.getDAOFactory().getDAO(DAOTypes.SPECIES);
    @Override
    public ArrayList<SpeciesDto> getSpecies() {
        ArrayList<SpeciesDto> species = new ArrayList<>();
        ArrayList<Species> species1 = speciesDAOimpl.getSpecies();
        for (Species species2 : species1) {
            SpeciesDto temp = new SpeciesDto();
            temp.setSpeciesID(species2.getId());
            temp.setSpeciesName(species2.getName());
            temp.setSpeciesDiet(speciesDAOimpl.getFoodName(String.valueOf(species2.getFoodID())));
            temp.setSpeciesStatus(species2.getConservationStatus());
            temp.setSpeciesCount(speciesDAOimpl.getSpeciesCount(String.valueOf(species2.getId())));
            species.add(temp);
        }
        return species;
//        return speciesDAOimpl.getSpecies();
    }

    @Override
    public ArrayList<String> getDiets() {
        return speciesDAOimpl.getDiets();
    }

    @Override
    public boolean addSpecies(String speciesName, String speciesDiet, String speciesStatus) {
        return speciesDAOimpl.addSpecies(speciesName, speciesDiet, speciesStatus);
    }

    @Override
    public ArrayList<AnimalTDto> getAnimals(String speciesID) {
//        return speciesDAOimpl.getAnimals(speciesID);
        ArrayList<AnimalTDto> animals = new ArrayList<>();
        ArrayList<Animal> animalTDto = speciesDAOimpl.getAnimals(speciesID);
        for (Animal animal : animalTDto) {
            AnimalTDto temp = new AnimalTDto();
            temp.setName(animal.getNickName());animals.add(temp);
        }
        return animals;
    }
}
