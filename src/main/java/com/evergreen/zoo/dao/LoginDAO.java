package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.LoginDto;
import com.evergreen.zoo.entity.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface LoginDAO extends SuperDAO{
    ResultSet checkLogin(Users users) throws SQLException;
}
