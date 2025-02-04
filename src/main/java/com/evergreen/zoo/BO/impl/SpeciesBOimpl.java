package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.SpeciesBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.SpeciesDAO;
import com.evergreen.zoo.dao.impl.SpeciesDAOimpl;
import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.dto.tanleDto.SpeciesDto;

import java.util.ArrayList;

public class SpeciesBOimpl implements SpeciesBO {
    SpeciesDAOimpl speciesDAOimpl = (SpeciesDAOimpl) DAOFactory.getDAOFactory().getDAO(DAOTypes.SPECIES);
    @Override
    public ArrayList<SpeciesDto> getSpecies() {
        return speciesDAOimpl.getSpecies();
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
        return speciesDAOimpl.getAnimals(speciesID);
    }
}
