package com.evergreen.zoo.dao.impl;
import com.evergreen.zoo.dao.ForgotDAO;
import com.evergreen.zoo.db.DBConnection;
import com.evergreen.zoo.dto.ForgotDto;
import com.evergreen.zoo.entity.Users;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotDAOimpl implements ForgotDAO {

//    public Boolean isChangeUserPW(ForgotDto forgotDto, String newPW) throws SQLException {
////        Connection connection = DBConnection.getInstance().getConnection();
////        String sql = "UPDATE users SET password = ? WHERE username = ?";
////        PreparedStatement preparedStatement = connection.prepareStatement(sql);
////        preparedStatement.setString(1, newPW);
////        preparedStatement.setString(2, forgotDto.getUsername());
////
////        return preparedStatement.executeUpdate() > 0;
//        String sql = "UPDATE users SET password = ? WHERE username = ?";
//        return CrudUtil.execute(sql, newPW, forgotDto.getUsername());
//    }

    public ForgotDto getUserData(String username) throws SQLException {

//        Connection connection = DBConnection.getInstance().getConnection();
//        String sql = "select * from users where username=?";
//        PreparedStatement preparedStatement = connection.prepareStatement(sql);
//        preparedStatement.setString(1, username);
//        ResultSet resultSet = preparedStatement.executeQuery();
        String sql = "select * from users where username=?";
        ResultSet resultSet = CrudUtil.execute(sql, username);
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String fogrtSql = "select * from employee where userId=?";
//            PreparedStatement preparedStatement1 = connection.prepareStatement(fogrtSql);
//            preparedStatement1.setInt(1, id);
//            ResultSet resultSet1 = preparedStatement1.executeQuery();
            ResultSet resultSet1 = CrudUtil.execute(fogrtSql, id);
            if (resultSet1.next()) {
                ForgotDto forgotDto = new ForgotDto();
                forgotDto.setId(resultSet1.getString("id"));
                forgotDto.setEmail(resultSet1.getString("email"));
                forgotDto.setPhoneNumber(resultSet1.getString("phone"));
                forgotDto.setUsername(username);
                System.out.println(forgotDto.toString());
                return forgotDto;
            }
        }
        return null;
    }

    @Override
    public Boolean isChangeUserPW(Users users, String newPasswd) throws SQLException {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        return CrudUtil.execute(sql, newPasswd, users.getUsername());
    }

}
