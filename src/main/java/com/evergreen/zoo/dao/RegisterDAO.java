package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.RegisterDto;
import com.evergreen.zoo.entity.Employee;
import com.evergreen.zoo.entity.Users;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RegisterDAO extends SuperDAO{
    int getRoleIdByDescription(String position) throws SQLException;
    boolean registerUser(Employee employee, Users users) throws SQLException;
    ArrayList<String> getAllRoles() throws SQLException;
}
