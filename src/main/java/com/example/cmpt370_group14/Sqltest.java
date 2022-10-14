package com.example.cmpt370_group14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sqltest {

    static final   String url = "jdbc:mysql://localhost:3306/Inventory_Management_system";
    static final String user = "project_370";
    static final String password = "Password@14";


        public static void main(String[] args)  throws SQLException{


            try {

              Connection   connection = DriverManager.getConnection(url, user, password);

            }catch (SQLException e){
                System.out.println("Oops, error !");
                e.printStackTrace();

            }
            System.out.println("Connected to the Database");


        }

    }


