package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.dto.tanleDto.SpeciesDto;

import java.util.ArrayList;

public interface SpeciesBO extends SuperBo {
    ArrayList<SpeciesDto> getSpecies();

    ArrayList<String> getDiets();

    boolean addSpecies(String speciesName, String speciesDiet, String speciesStatus);

    ArrayList<AnimalTDto> getAnimals(String speciesID);
}
