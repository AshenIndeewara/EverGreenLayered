package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.LoginDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginPaneBO extends SuperBo{
    ResultSet checkLogin(LoginDto loginDto) throws SQLException;
}
