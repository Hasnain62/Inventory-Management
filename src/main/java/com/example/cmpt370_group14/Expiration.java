package com.example.cmpt370_group14;

import com.example.cmpt370_group14.Product;

import java.sql.*;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Expiration extends Sqltest {

    private ArrayList<Product> products = new ArrayList<Product>();
    private String expiryDate;
    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    public Expiration(String expDate) throws SQLException {

        this.expiryDate = expDate;


    }

    public void addProducts(Product myProduct) throws SQLException {

        String date = this.expiryDate.toString();


        String table = "INSERT INTO Expirations ( Date , PID , Name ) VALUES ( ? ,? ,? )";
        System.out.println("Gets inside addProducts");
        PreparedStatement valstat = connection.prepareStatement(table);
        valstat.setString(1, date);
        valstat.setInt(2,myProduct.getProductID());
        valstat.setString(3,myProduct.getName());
        valstat.executeUpdate();


        this.products.add(myProduct);
    }

    public ArrayList getProducts() {
        return this.products;
    }

    public String getExpiryDate(){
        return expiryDate;
    }

    public boolean expires(){
        if(expiryDate == getTodaysDate()){
            return true;
        }else{
            return false;
        }
    }

    public String getTodaysDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime today = LocalDateTime.now();
        return dtf.format(today);
    }



    public static void main(String[] args) throws SQLException {

        Expiration test = new Expiration("2022");

        System.out.println(test.expiryDate);

    }

}