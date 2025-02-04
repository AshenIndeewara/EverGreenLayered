package com.evergreen.zoo.dao.impl;

import com.evergreen.zoo.dao.TicketDAO;
import com.evergreen.zoo.db.DBConnection;
import com.evergreen.zoo.dto.TicketDto;
import com.evergreen.zoo.entity.Ticket;
import com.evergreen.zoo.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TicketDAOimpl implements TicketDAO {
    public HashMap<String, Integer> getTicketPrice() throws SQLException {
        HashMap<String, Integer> ticketPrice = new HashMap<>();
        String sql = "select * from ticket";
        ResultSet rs = CrudUtil.execute(sql);
        while (rs.next()) {
            ticketPrice.put(rs.getString("ticketType"), rs.getInt("price"));
        }
        return ticketPrice;
    }

    public void updateTicketPrice(String adult, double v) {
        String sql = "update ticket set price=? where ticketType=?";
        try {
            CrudUtil.execute(sql, v, adult);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addTicket(Ticket ticketDto) throws SQLException {
        String sql = "insert into visitor (name, email, number,emID) values (?,?,?,?)";

        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            CrudUtil.execute(sql, ticketDto.getName(), ticketDto.getEmail(), ticketDto.getNum(), ticketDto.getEmployeeId());
            ResultSet rs = CrudUtil.execute("select max(visitorID) from visitor");
            if (rs.next()) {
                int visitorID = rs.getInt(1);
                if(ticketDto.getAdult() > 0) {
                    String sql1 = "insert into visitordetails (visitorID, ticketID, qty) values (?, ?, ?)";
                    CrudUtil.execute(sql1, visitorID, 1, ticketDto.getAdult());
                }
                if(ticketDto.getChild() > 0) {
                    String sql1 = "insert into visitordetails (visitorID, ticketID, qty) values (?, ?, ?)";
                    CrudUtil.execute(sql1, visitorID, 2, ticketDto.getChild());
                }
                if(ticketDto.getStudent() > 0) {
                    String sql1 = "insert into visitordetails (visitorID, ticketID, qty) values (?, ?, ?)";
                    CrudUtil.execute(sql1, visitorID, 3, ticketDto.getStudent());
                }
                if(ticketDto.getForeigner() > 0) {
                    String sql1 = "insert into visitordetails (visitorID, ticketID, qty) values (?, ?, ?)";
                    CrudUtil.execute(sql1, visitorID, 4, ticketDto.getForeigner());
                }
                if(ticketDto.getForeignerChild() > 0) {
                    String sql1 = "insert into visitordetails (visitorID, ticketID, qty) values (?, ?, ?)";
                    CrudUtil.execute(sql1, visitorID, 5, ticketDto.getForeignerChild());
                }
                connection.commit();
            }
        } catch (SQLException throwables) {
            connection.rollback();
            throwables.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public Map<String, String> getUser(String number) {
        Map<String, String> user = new HashMap<>();
        String sql = "select * from visitor where number=?";
        try {
            ResultSet rs = CrudUtil.execute(sql, number);
            if (rs.next()) {
                user.put("name", rs.getString("name"));
                user.put("email", rs.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public ResultSet getEmployeeID() {
        String sql = "select * from employee";
        ResultSet rs = null;
        try {
            rs = CrudUtil.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }
}
