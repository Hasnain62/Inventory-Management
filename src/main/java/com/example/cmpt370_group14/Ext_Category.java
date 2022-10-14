package com.example.cmpt370_group14;

import java.sql.*;

public class Ext_Category extends Sqltest{


    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();


    public Ext_Category() throws SQLException {
    }

    public Ext_Category(String name) throws SQLException {

        String table = "CREATE TABLE IF NOT EXISTS Categories ( Category varchar(255) )";
        stmt.executeUpdate(table);

        // Insert the data into the database ;
        String values = "INSERT INTO Categories ( Category ) VALUES (?)";
        PreparedStatement valstat = connection.prepareStatement(values);
        valstat.setString(1, name);

        valstat.executeUpdate();

    }
}
