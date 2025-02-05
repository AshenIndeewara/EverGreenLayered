package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.ForgotDto;
import com.evergreen.zoo.entity.Users;

import java.sql.SQLException;

public interface ForgotDAO extends SuperDAO {
    ForgotDto getUserData(String text) throws SQLException;
    Boolean isChangeUserPW(Users users, String newPasswd) throws SQLException;
}
