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
//        return staffDAO.getAllStaff();
        ArrayList<Employee> allStaff = staffDAO.getAllStaff();
        ArrayList<StaffDto> staffDtos = new ArrayList<>();
        for (Employee employee : allStaff) {
            StaffDto staffDto = new StaffDto();
            staffDto.setStaffID(employee.getId());
            staffDto.setStaffName(employee.getName());
            staffDto.setStaffRole(employee.getPosition());
            staffDto.setStaffContact(employee.getPhone());
            staffDto.setStaffEmail(employee.getEmail());
            staffDto.setStaffAddress(employee.getAddress());
            staffDtos.add(staffDto);

        }
        return staffDtos;
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
        employee.toString();
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
        Employee employee = new Employee();
        employee.setPhone(staffDto.getStaffContact());
        employee.setEmail(staffDto.getStaffEmail());
        employee.setName(staffDto.getStaffName());
        employee.setAddress(staffDto.getStaffAddress());
        employee.setPosition(staffDto.getStaffRole());
        employee.setId(staffDto.getStaffID());
        return staffDAO.isUpdate(employee, userid);
    }
}
