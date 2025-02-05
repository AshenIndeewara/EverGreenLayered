package com.evergreen.zoo.dao;

import com.evergreen.zoo.dao.impl.*;

public class DAOFactory {
    private static DAOFactory DAOFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDAOFactory(){
        if(DAOFactory==null){
            DAOFactory=new DAOFactory();
        }
        return DAOFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case ANIMAL:
                return new AnimalDAOimpl();
            case DASHBOARD:
                return new DashboardDAOimpl();
            case SPECIES:
                return new SpeciesDAOimpl();
            case TICKETDAO:
                return new TicketDAOimpl();
            case FORGOTDAO:
                return new ForgotDAOimpl();
            case LOGINDAO:
                return new LoginDAOimpl();
            case REGISTRDAO:
                return new RegisterDAOimpl();
            case REPORTDAO:
                return new ReportDAOimpl();
            case STAFFDAO:
                return new StaffDAOimpl();
            case STOCKMANAGEDAO:
                return new StockDAOimpl();
            case SUPPLIERDAO:
                return new SupplierDAOimpl();
        }
        return null;
    }
}
