package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.StaffBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.StaffDAO;
import com.evergreen.zoo.dto.tanleDto.StaffDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class StaffBOimpl implements StaffBO {
    StaffDAO staffDAO = (StaffDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.STAFFDAO);
    @Override
    public ArrayList<String> getAllRoles() throws SQLException {
        return staffDAO.getAllRoles();
    }

    @Override
    public ArrayList<StaffDto> getAllStaff() throws SQLException {
        return staffDAO.getAllStaff();
    }

    @Override
    public ArrayList<StaffDto> searchStaff(String text) throws SQLException {
        return staffDAO.search(text);
    }

    @Override
    public void deleteStaff(StaffDto selectedItem) throws SQLException {
        staffDAO.isDelete(selectedItem);
    }

    @Override
    public int getEmployeeId(StaffDto selectedItem) {
        return staffDAO.getEmployeeId(selectedItem);
    }

    @Override
    public void updateStaff(StaffDto staffDto, int userid) {
        staffDAO.isUpdate(staffDto, userid);
    }
}
