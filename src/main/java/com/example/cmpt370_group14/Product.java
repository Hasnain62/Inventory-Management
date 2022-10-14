package com.example.cmpt370_group14;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Product {

    // Encapsulate data
    private int productID;
    private String Tags;
    private double productPrice;
    private int productQty;
    private String productLocation;
    private String name;




    public Product() throws SQLException {

    }
    /** Makes changes to PID **/
    // Create constructor
    public Product(int PID ,String name, double price, int qty, String location, String tags) throws SQLException {


        this.name = name;
        this.productID = PID;
        this.Tags = tags;
        this.productPrice = price;
        this.productQty = qty;
        this.productLocation = location;

    }


    public String getName() throws SQLException {


        return name;
    }

    /**
     PID should be auto generated unique with each product
     */

    public void setName(String NewName){

        name = NewName;
    }

    public void setProductID(int ID) throws SQLException {

        this.productID = ID;

    }

    public int getProductID()  {

        return this.productID;

    }

    public void setPrice(int price) throws SQLException {


       this.productPrice = price;
    }

    public double getProductPrice() throws SQLException {

        return productPrice;
    }

    public void setTags(Category myTags) {


        this.Tags += myTags;
    }

    public String getTags() {
        return this.Tags;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public int getQuantity() throws SQLException{

        return productQty;
    }

    public void addQuantity(int qty) throws SQLException {

        this.productQty += qty;
    }

    public void removeQuantity(int qty) throws SQLException {


        this.productQty -= qty;
    }

    public void setLocation(String location) throws SQLException {


        this.productLocation = location;
    }

    public String getProductLocation() throws SQLException {


        return productLocation;
    }

    public void setDiscount(double discountCost) throws SQLException {
        //discount must be a dollar value

        // Retrieve the data from the database

        this.productPrice -= discountCost;

    }


    public static void main(String[] args) throws SQLException {


        // Testing whether data goes into the database

//       try    {

//        Product apple = new Product(1220, "Charmer Perfume", 10.5, 66, "mYaISLE", "Accesories");
//
////           }
////        catch (SQLIntegrityConstraintViolationException e){
////            System.out.println("PID already exists !");
////            e.printStackTrace();
////        }
//
//       apple.addQuantity(27);
//       apple.removeQuantity(32);
//       apple.getName();
//
       Product test ;




    }

}