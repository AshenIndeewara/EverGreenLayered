package com.evergreen.zoo.dao.impl;

import com.evergreen.zoo.dao.StaffDAO;
import com.evergreen.zoo.dto.tanleDto.StaffDto;
import com.evergreen.zoo.entity.Employee;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffDAOimpl implements StaffDAO {
    public ArrayList<StaffDto> getAllStaff() throws SQLException {
        String sql = "select * from employee";
        ResultSet rs = CrudUtil.execute(sql);
        ArrayList<StaffDto> staff = new ArrayList<>();
        while (rs.next()) {
            StaffDto newStaff = new StaffDto(
                    rs.getString("name"),
                    getRoleDescription(rs.getInt("position")),
                    rs.getString("phone"),
                    rs.getString("email")
            );
            staff.add(newStaff);
        }
        return staff;
    }

    public String getRoleDescription(int roleID) throws SQLException {
        String sql = "select description from role where role_id=?";
        ResultSet resultSet = CrudUtil.execute(sql, roleID);
        while (resultSet.next()) {
            return resultSet.getString("description");
        }
        return null;
    }

//    public void add(StaffDto staff) throws SQLException {
//        String sql = "insert into employee values(?,?,?,?)";
//        CrudUtil.execute(sql, staff.getStaffName(), staff.getStaffRole(), staff.getStaffContact(), staff.getStaffEmail());
//    }

//    public ArrayList<StaffDto> searchStaff(String name) throws SQLException {
//        String sql = "select * from employee where name like ?";
//        ResultSet rs = CrudUtil.execute(sql, name+"%");
//        ArrayList<StaffDto> staff = new ArrayList<>();
//        while (rs.next()) {
//            StaffDto newStaff = new StaffDto(
//                    rs.getString("name"),
//                    getRoleDescription(rs.getInt("position")),
//                    rs.getString("phone"),
//                    rs.getString("email")
//            );
//            staff.add(newStaff);
//        }
//        return staff;
//    }

//    public Boolean delete(StaffDto staffDto) {
//        String sql = "delete from employee where phone=?, where email=?, where name=?";
//        try {
//            CrudUtil.execute(sql, staffDto.getStaffContact(), staffDto.getStaffEmail(), staffDto.getStaffName());
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

//    public int getEmployeeId(StaffDto staffDto) {
//        String sql = "SELECT id FROM employee WHERE email = ? AND name = ? AND phone = ?";
//        try {
//            ResultSet resultSet = CrudUtil.execute(sql, staffDto.getStaffEmail(), staffDto.getStaffName(), staffDto.getStaffContact());
//            if (resultSet.next()) {
//                System.out.println(resultSet.getInt("id"));
//                return resultSet.getInt("id");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }

//    @Override
//    public boolean isUpdate(StaffDto staffDto, int userId) {
//        System.out.println(staffDto.toString());
//        String getROleId = "select role_id from role where description=?";
//        String sql = "update employee set name=?, position=?, phone=?, email=? where id=?";
//        try {
//            ResultSet resultSet = CrudUtil.execute(getROleId, staffDto.getStaffRole());
//            if (resultSet.next()) {
//                CrudUtil.execute(sql, staffDto.getStaffName(), resultSet.getInt("role_id"), staffDto.getStaffContact(), staffDto.getStaffEmail(), userId);
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

    @Override
    public ArrayList<String> getAllRoles() throws SQLException {
        ArrayList<String> roles = new ArrayList<>();
        String sqlAllDiscription = "select description from role";
        ResultSet resultSet = CrudUtil.execute(sqlAllDiscription);
        while (resultSet.next()) {
            roles.add(resultSet.getString("description"));
        }
        return roles;
    }

    @Override
    public boolean isAdd(StaffDto staff) throws SQLException {
        String sql = "insert into employee values(?,?,?,?)";
        return CrudUtil.execute(sql, staff.getStaffName(), staff.getStaffRole(), staff.getStaffContact(), staff.getStaffEmail());
    }

    @Override
    public Boolean isDelete(Employee employee) {
//        String sql = "delete from employee where phone=?, where email=?, where name=?";
        String sql = "DELETE FROM employee WHERE phone=? AND email=? AND name=?";
        try {
            CrudUtil.execute(sql, employee.getPhone(), employee.getEmail(), employee.getName());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int getEmployeeId(Employee employee) {
        String sql = "SELECT id FROM employee WHERE email = ? AND name = ? AND phone = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(sql, employee.getEmail(), employee.getName(), employee.getPhone());
            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id"));
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public boolean isUpdate(Employee employee, int userId) {
        System.out.println(employee.toString());
        String getROleId = "select role_id from role where description=?";
        String sql = "update employee set name=?, position=?, phone=?, email=? where id=?";
        try {
            ResultSet resultSet = CrudUtil.execute(getROleId, employee.getPosition());
            if (resultSet.next()) {
                CrudUtil.execute(sql, employee.getName(), resultSet.getInt("role_id"), employee.getPhone(), employee.getEmail(), userId);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<StaffDto> search(String name) throws SQLException {
        String sql = "select * from employee where name like ?";
        ResultSet rs = CrudUtil.execute(sql, name+"%");
        ArrayList<StaffDto> staff = new ArrayList<>();
        while (rs.next()) {
            StaffDto newStaff = new StaffDto(
                    rs.getString("name"),
                    getRoleDescription(rs.getInt("position")),
                    rs.getString("phone"),
                    rs.getString("email")
            );
            staff.add(newStaff);
        }
        return staff;
    }

//    public boolean update(StaffDto staffDto, int userId) {
//        System.out.println(staffDto.toString());
//        String getROleId = "select role_id from role where description=?";
//        String sql = "update employee set name=?, position=?, phone=?, email=? where id=?";
//        try {
//            ResultSet resultSet = CrudUtil.execute(getROleId, staffDto.getStaffRole());
//            if (resultSet.next()) {
//                CrudUtil.execute(sql, staffDto.getStaffName(), resultSet.getInt("role_id"), staffDto.getStaffContact(), staffDto.getStaffEmail(), userId);
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }

}
