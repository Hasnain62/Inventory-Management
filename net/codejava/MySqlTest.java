package net.codejava;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlTest {

    public static void main(String[] args) {


        String url = "jdbc:mysql://localhost:3306/Inventory_Management_system";
        String username = "project_370";
        String password = "Password@14";

        try {

            Connection connection = DriverManager.getConnection(url, username, password);
            

        }catch (SQLException e){
            System.out.println("Oops, error !");
            e.printStackTrace();
        }
        System.out.println("Connected to the Database");

    }



}
