package com.evergreen.zoo.BO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddStaffBO extends SuperBo{
    ArrayList<String> getAllRoles() throws SQLException;
}
