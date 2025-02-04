package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.tanleDto.StaffDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StaffBO extends SuperBo{
    ArrayList<String> getAllRoles() throws SQLException;
    ArrayList<StaffDto> getAllStaff() throws SQLException;
    ArrayList<StaffDto> searchStaff(String text) throws SQLException;
    boolean deleteStaff(StaffDto selectedItem) throws SQLException;
    int getEmployeeId(StaffDto selectedItem);
    boolean updateStaff(StaffDto staffDto, int userid);
}
