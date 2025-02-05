package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.tanleDto.AnimalTDto;
import com.evergreen.zoo.dto.tanleDto.SpeciesDto;
import com.evergreen.zoo.entity.Animal;
import com.evergreen.zoo.entity.Species;

import java.util.ArrayList;

public interface SpeciesDAO extends SuperDAO{
    public ArrayList<Species> getSpecies();
    public ArrayList<Animal> getAnimals(String speciesID);
    public boolean addSpecies(String speciesName, String speciesDiet, String speciesStatus);
    public ArrayList<String> getDiets();
    public int getSpeciesCount(String speciesID);
    public String getFoodName(String foodId);
}
