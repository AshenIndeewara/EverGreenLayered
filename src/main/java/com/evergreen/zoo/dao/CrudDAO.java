package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.tanleDto.AnimalTDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T> extends SuperDAO{
        public ArrayList<T> search(String search) throws SQLException;
}
