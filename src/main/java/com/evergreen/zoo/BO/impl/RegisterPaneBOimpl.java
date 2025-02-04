package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.RegisterPaneBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.RegisterDAO;
import com.evergreen.zoo.dto.RegisterDto;
import com.evergreen.zoo.entity.Employee;
import com.evergreen.zoo.entity.Users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class RegisterPaneBOimpl implements RegisterPaneBO {
    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.REGISTRDAO);
    @Override
    public int getRoleIdByDescription(String position) throws SQLException {
        return registerDAO.getRoleIdByDescription(position);
    }

    @Override
    public boolean registerUser(RegisterDto registerDto) throws SQLException {
        return registerDAO.registerUser(new Employee(
                registerDto.getName(),
                registerDto.getEmail(),
                registerDto.getPhone(),
                registerDto.getAddress(),
                String.valueOf(registerDto.getRole())
        ), new Users(
                registerDto.getUsername(),
                registerDto.getPassword()
        ));
    }

    @Override
    public ArrayList<String> getAllRoles() throws SQLException {
        return registerDAO.getAllRoles();
    }
}
