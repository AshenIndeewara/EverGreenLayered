package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.AddStaffBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.RegisterDAO;
import com.evergreen.zoo.dao.impl.RegisterDAOimpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddStaffBOimpl implements AddStaffBO {
//    RegisterDAOimpl registerDAOimpl = new RegisterDAOimpl();
    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.REGISTRBO);
    public ArrayList<String> getAllRoles() throws SQLException {
        return registerDAO.getAllRoles();
    }
}
