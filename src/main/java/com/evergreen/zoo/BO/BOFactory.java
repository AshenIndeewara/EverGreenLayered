package com.evergreen.zoo.BO;

import com.evergreen.zoo.BO.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        if(boFactory==null){
            boFactory=new BOFactory();
        }
        return boFactory;
    }

    public SuperBo getBO(BOTypes boTypes){
        switch (boTypes){
            case ANIMALPANEBO :
                return new AnimalPaneBOimpl();
            case DASHBOARDBO:
                return new DashboardBOimpl();
            case SPECIESBO:
                return new SpeciesBOimpl();
            case TICKETBO:
                return new TicketPaneBOimpl();
            case FORGOTBO:
                return new ForgotPassBOimpl();
            case LOGINBO:
                return new LoginPaneBOimpl();
            case REGISTRBO:
                return new RegisterPaneBOimpl();
            case REPORTBO:
                return new ReportBOimpl();
            case STAFFBO:
                return new StaffBOimpl();
            case STOCKMANAGEBO:
                return new StockManageBOimpl();
            case SUPPLIERBO:
                return new SupplierBOimpl();
        }
        return null;
    }
}
