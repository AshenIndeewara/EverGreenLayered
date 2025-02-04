package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.StaffBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.StaffDAO;
import com.evergreen.zoo.dto.tanleDto.StaffDto;
import com.evergreen.zoo.entity.Employee;

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
    public boolean deleteStaff(StaffDto selectedItem) throws SQLException {
        Employee employee = new Employee();
        employee.setPhone(selectedItem.getStaffContact());
        employee.setEmail(selectedItem.getStaffEmail());
        employee.setName(selectedItem.getStaffName());
        return staffDAO.isDelete(employee);
    }

    @Override
    public int getEmployeeId(StaffDto selectedItem) {
        Employee employee = new Employee();
        employee.setPhone(selectedItem.getStaffContact());
        employee.setEmail(selectedItem.getStaffEmail());
        employee.setName(selectedItem.getStaffName());
        return staffDAO.getEmployeeId(employee);
    }

    @Override
    public boolean updateStaff(StaffDto staffDto, int userid) {
        return staffDAO.isUpdate(new Employee(
                staffDto.getStaffID(),
                staffDto.getStaffName(),
                staffDto.getStaffRole(),
                staffDto.getStaffContact(),
                staffDto.getStaffEmail(),
                staffDto.getStaffAddress()
        ), userid);
    }
}
