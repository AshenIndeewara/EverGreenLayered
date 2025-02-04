package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.LoginPaneBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.LoginDAO;
import com.evergreen.zoo.dto.LoginDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPaneBOimpl implements LoginPaneBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.LOGINDAO);
    @Override
    public ResultSet checkLogin(LoginDto loginDto) throws SQLException {
        return loginDAO.checkLogin(loginDto);
    }
}
