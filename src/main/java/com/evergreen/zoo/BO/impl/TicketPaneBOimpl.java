package com.evergreen.zoo.BO.impl;

import com.evergreen.zoo.BO.TicketPaneBO;
import com.evergreen.zoo.dao.DAOFactory;
import com.evergreen.zoo.dao.DAOTypes;
import com.evergreen.zoo.dao.TicketDAO;
import com.evergreen.zoo.dao.impl.TicketDAOimpl;
import com.evergreen.zoo.dto.TicketDto;
import com.evergreen.zoo.entity.Ticket;
import com.evergreen.zoo.entity.Visitor;
import com.evergreen.zoo.entity.Visitordetails;

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
        Visitor visitor = new Visitor();
        visitor.setName(ticketDto.getName());
        visitor.setEmail(ticketDto.getEmail());
        visitor.setNumber(ticketDto.getNum());
        visitor.setEmID(Integer.parseInt(ticketDto.getEmployeeId()));

        Visitordetails adult = new Visitordetails();
        adult.setQty(ticketDto.getAdult());

        Visitordetails child = new Visitordetails();
        child.setQty(ticketDto.getChild());

        Visitordetails foreigner = new Visitordetails();
        foreigner.setQty(ticketDto.getForeigner());

        Visitordetails foreignerChild = new Visitordetails();
        foreignerChild.setQty(ticketDto.getForeignerChild());

        Visitordetails student = new Visitordetails();
        student.setQty(ticketDto.getStudent());
        ticketDAO.addTicket(
                visitor,
                adult,
                child,
                foreigner,
                foreignerChild,
                student
        );
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
