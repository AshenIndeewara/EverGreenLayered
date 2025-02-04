package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.RegisterPaneBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.RegisterDAO;
import com.evergreen.zoo.dto.RegisterDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class RegisterPaneBOimpl implements RegisterPaneBO {
    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.REGISTRDAO);
    @Override
    public int getRoleIdByDescription(String position) throws SQLException {
        return registerDAO.getRoleIdByDescription(position);
    }

    @Override
    public boolean registerUser(RegisterDto registerDto) throws SQLException {
        return registerDAO.registerUser(registerDto);
    }

    @Override
    public ArrayList<String> getAllRoles() throws SQLException {
        return registerDAO.getAllRoles();
    }
}
