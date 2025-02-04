package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.ForgotDto;

import java.sql.SQLException;

public interface ForgotDAO extends SuperDAO {
    ForgotDto getUserData(String text) throws SQLException;
    Boolean isChangeUserPW(ForgotDto forgotDto, String newPasswd) throws SQLException;
}
