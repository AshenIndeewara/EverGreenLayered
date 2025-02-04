package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.ForgotDto;

import java.sql.SQLException;

public interface ForgotBO extends SuperBo{
    ForgotDto getUserData(String text) throws SQLException;

    Boolean isChangeUserPW(ForgotDto forgotDto, String newPasswd) throws SQLException;
}
