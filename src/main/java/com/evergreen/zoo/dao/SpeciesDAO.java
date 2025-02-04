package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.dto.tanleDto.SpeciesDto;

import java.util.ArrayList;

public interface SpeciesDAO extends SuperDAO{
    public ArrayList<SpeciesDto> getSpecies();
    public ArrayList<AnimalTDto> getAnimals(String speciesID);
    public boolean addSpecies(String speciesName, String speciesDiet, String speciesStatus);
    public ArrayList<String> getDiets();
}
