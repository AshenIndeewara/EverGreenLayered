package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.tanleDto.StaffDto;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StaffDAO extends CrudDAO<StaffDto>{
    public ArrayList<StaffDto> getAllStaff() throws SQLException;
    public String getRoleDescription(int roleID) throws SQLException;
    public boolean isAdd(StaffDto staff) throws SQLException;
//    public ArrayList<StaffDto> searchStaff(String name) throws SQLException;
    public Boolean isDelete(StaffDto staffDto);
    public int getEmployeeId(StaffDto staffDto);
    public boolean isUpdate(StaffDto staffDto, int userId);
    ArrayList<String> getAllRoles() throws SQLException;
}
