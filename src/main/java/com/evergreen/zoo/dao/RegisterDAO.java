package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.RegisterDto;
import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterDAO extends SuperDAO{
    int getRoleIdByDescription(String position) throws SQLException;

    boolean registerUser(RegisterDto registerDto) throws SQLException;

    ArrayList<String> getAllRoles() throws SQLException;
}
