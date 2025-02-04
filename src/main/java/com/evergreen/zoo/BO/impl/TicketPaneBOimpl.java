package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.TicketPaneBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.TicketDAO;
import com.evergreen.zoo.dao.impl.TicketDAOimpl;
import com.evergreen.zoo.dto.TicketDto;
import com.evergreen.zoo.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TicketPaneBOimpl implements TicketPaneBO {
    TicketDAO ticketDAO = (TicketDAO) DAOFactory.getDAOFactory().getDAO(DAOTypes.TICKETDAO);
    @Override
    public HashMap<String, Integer> getTicketPrice() throws SQLException {
        return ticketDAO.getTicketPrice();
    }

    @Override
    public ResultSet getEmployeeID() {
        return ticketDAO.getEmployeeID();
    }

    @Override
    public void addTicket(TicketDto ticketDto) throws SQLException {

        ticketDAO.addTicket(new Ticket(
                ticketDto.getTotal(),
                ticketDto.getPaymentMethod(),
                ticketDto.getAdult(),
                ticketDto.getChild(),
                ticketDto.getForeigner(),
                ticketDto.getForeignerChild(),
                ticketDto.getStudent(),
                ticketDto.getName(),
                ticketDto.getEmail(),
                ticketDto.getNum(),
                ticketDto.getEmployeeId()));
    }

    @Override
    public void updateTicketPrice(String adult, double v) {
        ticketDAO.updateTicketPrice(adult, v);
    }

    @Override
    public Map<String, String> getUser(String text) {
        return ticketDAO.getUser(text);
    }
}
