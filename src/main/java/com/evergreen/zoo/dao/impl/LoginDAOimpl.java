package com.evergreen.zoo.dao.impl;
import com.evergreen.zoo.dao.LoginDAO;
import com.evergreen.zoo.dto.LoginDto;
import com.evergreen.zoo.entity.Users;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOimpl implements LoginDAO {
    public ResultSet checkLogin(Users users) throws SQLException {
        String sql = "SELECT e.name, e.email, e.phone, e.position, users.password FROM users JOIN employee e ON users.id = e.userId WHERE users.username = ?";
        ResultSet rst = CrudUtil.execute(sql, users.getUsername());
        if (rst.next()) {
            return rst;
        }
        return null;
    }
}
