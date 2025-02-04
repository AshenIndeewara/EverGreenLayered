package com.evergreen.zoo.BO;

import com.evergreen.zoo.dto.TicketDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface TicketPaneBO extends SuperBo{
    HashMap<String, Integer> getTicketPrice() throws SQLException;

    ResultSet getEmployeeID();

    void addTicket(TicketDto ticketDto) throws SQLException;

    void updateTicketPrice(String adult, double v);

    Map<String, String> getUser(String text);
}
