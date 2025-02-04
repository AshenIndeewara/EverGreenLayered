package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.LoginDto;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginDAO extends SuperDAO{
    ResultSet checkLogin(LoginDto loginDto) throws SQLException;
}
