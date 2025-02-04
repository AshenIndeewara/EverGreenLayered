package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.RegisterDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterPaneBO extends SuperBo{
    int getRoleIdByDescription(String position) throws SQLException;

    boolean registerUser(RegisterDto registerDto) throws SQLException;

    ArrayList<String> getAllRoles() throws SQLException;
}
