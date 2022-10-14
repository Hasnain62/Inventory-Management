package com.example.cmpt370_group14;

import java.sql.*;

public  class  Ext_Product extends Sqltest {


    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();



    public  Ext_Product() throws SQLException {}

    public Ext_Product(int PID, String name, double price, int qty, String location, String tags) throws SQLException {


        try {

            String table = "CREATE TABLE IF NOT EXISTS Products ( PID int , Name varchar(255) , Price DOUBLE , Quantity int , Location varchar(255) , Tags varchar(255) , PRIMARY KEY (PID) )  ";
            stmt.executeUpdate(table);

            // Insert the data into the database ;
            String values = "INSERT INTO Products ( Name , Price , Quantity , Location , Tags , PID ) VALUES ( ? ,? , ?, ? , ? , ?)";
            PreparedStatement valstat = connection.prepareStatement(values);
            valstat.setString(1, name);
            valstat.setDouble(2,price);
            valstat.setInt(3,qty);
            valstat.setString(4,location);



            valstat.setString(5,tags);

            /**
             * TODO - make a GUI alert for duplicate PID's
             */

            valstat.setInt(6,PID);
            valstat.executeUpdate();

        }

        catch (SQLException e){
            System.out.println("Oops, error !");
            e.printStackTrace();

        }


    }

    public String getNameDatabase(int PID) throws SQLException {

        String getstat = " SELECT Name FROM Products WHERE PID = "+ PID;
        ResultSet rs = stmt.executeQuery(getstat);
        String name = null ;

        while (rs.next()){
            name = rs.getString("Name");
            System.out.println(name);

        }
        assert name != null;
        return name;
    }

    /**
     PID should be auto generated unique with each product
     */


    public void setProductIDDatabase(int OldproductID, int NewID) throws SQLException {

        String stat = " UPDATE Products SET PID = " +NewID +" WHERE PID = " + OldproductID ;
        stmt.executeUpdate(stat);
      //  this.productID = ID;

    }
//
//    public int getProductID()  {
//
//        String getstat = " SELECT Price FROM Products WHERE PID = "+ this.productID;
//        ResultSet rs = stmt.executeQuery(getstat);
//        Double price = null;
//
//        while (rs.next()){
//            price = rs.getDouble("Price");
//            //System.out.println(d);
//
//        }
//        return price;
//    }

    public void setPriceDatabase(int price , int productID) throws SQLException {

        String stat = " UPDATE Products SET Price = " +price +" WHERE PID = " + productID ;
        stmt.executeUpdate(stat);
        //pricestat.executeUpdate();

      //  this.productPrice = price;
    }

    public  double getProductPriceDatabase(int productID) throws SQLException {

        String getstat = " SELECT Price FROM Products WHERE PID = "+ productID;
        ResultSet rs = stmt.executeQuery(getstat);
        Double price = null;

        while (rs.next()){
            price = rs.getDouble("Price");
            //System.out.println(d);

        }
        return price;
    }

    /**
     *
     * TODO Work on catgories
    public void setTags(Category myTags) {


        this.Tags += myTags;
    }

     */


    public String getTagsDatabase(int productID) throws SQLException {

        String getstat = " SELECT Quantity FROM Products WHERE PID = "+ productID;
        ResultSet rs = stmt.executeQuery(getstat);
        String Tags = null ;

        while (rs.next()){
            Tags = rs.getString("Tags");
            //System.out.println(d);

        }
        assert Tags != null;
        return Tags;

    }



    /**
     *
     * @return
     * @throws SQLException
     */
    public int getQuantityDatabase(int productID) throws SQLException{
        String getstat = " SELECT Quantity FROM Products WHERE PID = "+ productID;
        ResultSet rs = stmt.executeQuery(getstat);
        int quantity =0 ;

        while (rs.next()){
            quantity = rs.getInt("Quantity");
            //System.out.println(d);

        }
        assert quantity != 0;
        return quantity;
    }

    public void addQuantityDatabase(int qty , int productID) throws SQLException {

        int quantity = getQuantityDatabase(productID) ;
        int New_Quan = quantity + qty;
        String stat = " UPDATE Products SET Quantity = " +New_Quan +" WHERE PID = " + productID ;
        stmt.executeUpdate(stat);
      //  this.productQty += qty;
    }

    public void removeQuantityDatabase(int qty , int productID) throws SQLException {

        int quantity = getQuantityDatabase(productID) ;
        int New_Quan = quantity - qty;
        String stat = " UPDATE Products SET Quantity = " +New_Quan +" WHERE PID = " + productID ;
        stmt.executeUpdate(stat);

   //     this.productQty -= qty;
    }

    public void setLocationDatabase(String location , int productID) throws SQLException {

        String stat = " UPDATE Products SET Location = " +location +" WHERE PID = " + productID ;
        stmt.executeUpdate(stat);
     //   this.productLocation = location;
    }

    public String getProductLocationDatabase(int productID) throws SQLException {

        String getstat = " SELECT Location FROM Products WHERE PID = "+ productID;
        ResultSet rs = stmt.executeQuery(getstat);
        String location = null ;

        while (rs.next()){
            location = rs.getString("Location");
            //System.out.println(d);

        }
        assert location != null;
        return location;
    }

    public void setDiscount(double discountCost , int productID) throws SQLException {
        //discount must be a dollar value

        // Retrieve the data from the database
        String getstat = " SELECT Price FROM Products WHERE PID = "+ productID;
        ResultSet rs = stmt.executeQuery(getstat);
        Double price = null;

        while (rs.next()){
            price = rs.getDouble("Price");
            //System.out.println(d);

        }
        assert price != null;
        price = price - discountCost;

        // Update the value in the database
        String stat = " UPDATE Products SET Price = " +price +" WHERE PID = " + productID ;
        stmt.executeUpdate(stat);
      //  this.productPrice -= discountCost;

    }

    public static void main(String[] args) throws SQLException {

        Product test = new Product();

        test.setProductID(11112);

        Ext_Product test1 = new Ext_Product();

      System.out.println( test1.getProductLocationDatabase(9000));

    //   System.out.println(test1.getNameDatabase(9000));
    }
}
