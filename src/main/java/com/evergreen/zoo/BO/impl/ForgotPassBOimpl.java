package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.ForgotBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.ForgotDAO;
import com.evergreen.zoo.dto.ForgotDto;

import java.sql.SQLException;

public class ForgotPassBOimpl implements ForgotBO {
    ForgotDAO forgotDAO = (ForgotDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.FORGOTDAO);
    @Override
    public ForgotDto getUserData(String text) throws SQLException {
        return forgotDAO.getUserData(text);
    }

    @Override
    public Boolean isChangeUserPW(ForgotDto forgotDto, String newPasswd) throws SQLException {
        return forgotDAO.isChangeUserPW(forgotDto, newPasswd);
    }
}
