package com.evergreen.zoo.dao;

import com.evergreen.zoo.dto.TicketDto;
import com.evergreen.zoo.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface TicketDAO extends SuperDAO{
    public HashMap<String, Integer> getTicketPrice() throws SQLException;
    public void updateTicketPrice(String adult, double v);
    public void addTicket(Ticket ticketDto) throws SQLException;
    public Map<String, String> getUser(String number);
    public ResultSet getEmployeeID();
}
