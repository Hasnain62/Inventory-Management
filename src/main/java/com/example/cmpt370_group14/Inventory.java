package com.example.cmpt370_group14;

import java.sql.*;
import java.util.ArrayList;

public class Inventory extends Sqltest{
    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();

 //   private ArrayList<Product> productList;

    // constructor
    public Inventory() throws SQLException {
       // productList = new ArrayList<Product>();
    }

    public boolean searchByPID(int PID) throws SQLException {

        String getstat = " SELECT Name FROM Products WHERE PID = "+ PID;
        ResultSet rs = stmt.executeQuery(getstat);
        String name = null ;

        while (rs.next()){
            name = rs.getString("Name");
            System.out.println(name);

        }
        return name!= null;
    }

    public boolean searchByName(String name) throws SQLException {
        String getstat = " SELECT PID FROM Products WHERE Name = '"+ name +"'";
        ResultSet rs = stmt.executeQuery(getstat);
        int pid = 0 ;

        while (rs.next()){
            pid = rs.getInt("PID");
            System.out.println(pid);

        }
        return pid!= 0;
    }

    // add product if not exist
    public void addNewProduct(int PID ,String name, double price, int qty, String location, String tags) throws SQLException
    {

            Product prod = new Product(PID, name, price, qty, location, tags);

            Ext_Product newProduct = new Ext_Product(PID, name, price, qty, location, tags);

    }
//    public void addNewProduct(Product product){
//        if (!searchByPID(product.getProductID()))
//            productList.add(product);
//    }

    // get Product in Inventory by name
    public Product getProduct(String name) throws SQLException {

        int PID = getProductIDDatabase(name);

        return new Product(PID,getNameDatabase(PID),getProductPriceDatabase(PID),getQuantityDatabase(PID),getProductLocationDatabase(PID),getTagsDatabase(PID));
//        for (Product product1: productList){
//            if (product1.getName().equals(name)){
//                return product1;
//            }
//        }
//        throw new RuntimeException("item "+name+" is not in inventory");
    }

    // get Product in Inventory by PID
    public Product getProduct(int PID) throws SQLException, RuntimeException
    {


        return new Product(PID,getNameDatabase(PID),getProductPriceDatabase(PID),getQuantityDatabase(PID),getProductLocationDatabase(PID),getTagsDatabase(PID));

//        for (Product product1 : productList)
//        {
//            if (product1.getProductID() == PID)
//            {
//                return product1;
//            }
//
//        }
//        throw new RuntimeException("item " + PID + " is not in inventory");
    }

    // remove product if exist
    public void removeProduct(Product product) throws SQLException {

        int pid = product.getProductID();

        String remove = "DELETE FROM Products WHERE PID ="+pid;

        stmt.executeUpdate(remove);

//
//        if (searchByPID(product.getProductID()))
//            productList.remove(product);
    }

    //@Override
//    public String toString() {
//        String string = "";
//        for (Product product1:productList){
//            try {
//                string+=product1.getName()+" "+product1.getProductID()+"\n";
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return string;
//    }
//

    /**
     *
     * Helper functions to post and fetch data from database
     */

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


    public void setProductIDDatabase(int OldproductID, int NewID) throws SQLException {

        String stat = " UPDATE Products SET PID = " +NewID +" WHERE PID = " + OldproductID ;
        stmt.executeUpdate(stat);
        //  this.productID = ID;

    }

    public int getProductIDDatabase(String name) throws SQLException {

        String getstat = " SELECT PID FROM Products WHERE Name = '"+ name+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        int pid = 0;

        while (rs.next()){
            pid = rs.getInt("PID");
            //System.out.println(d);

        }
        return pid;
    }

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
        if(New_Quan >=0) {
            String stat = " UPDATE Products SET Quantity = " + New_Quan + " WHERE PID = " + productID;
            stmt.executeUpdate(stat);
        }
        else {
            throw new SQLException("Quantity cant be negative");

        }
        //     this.productQty -= qty;
    }
    public String getTagsDatabase(int productID) throws SQLException {
        String getstat = " SELECT Tags FROM Products WHERE PID = "+ productID;
        ResultSet rs = stmt.executeQuery(getstat);
        String Tags = null ;

        while (rs.next()){
            Tags = rs.getString("Tags");
            //System.out.println(d);

        }
        assert Tags != null;
        return Tags;
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

    public ArrayList<Product> getAllproducts() throws SQLException {

        String Data = "SELECT * FROM Products";
        ResultSet rs = stmt.executeQuery(Data);

        ResultSetMetaData rsmd= rs.getMetaData();
        ArrayList<String> param ;
        ArrayList<Product> allproducts = new ArrayList<Product>();
        Product newProd = null;

        while(rs.next()){

            int i =1;
            param = new ArrayList<String>();

            while (i <= rsmd.getColumnCount()){

                param.add(rs.getString(i));
                i++;
            }

            if (param.get(2) != null && param.get(3)!= null) {
                 newProd = new Product(Integer.parseInt(param.get(0)), param.get(1), Double.parseDouble(param.get(2)), Integer.parseInt(param.get(3)), param.get(4), param.get(5));
                 allproducts.add(newProd);

            }

        }


        return allproducts;
    }

    /**
     * Add a new image of the product with the id
     * @param id PID of the product
     * @param path image path
     * @throws SQLException
     */
    public void add_image(int id , String path) throws SQLException {


        String data= " create table Images(ID int , Image MEDIUMBLOB)";
        stmt.executeUpdate(data);

        String data2 = "INSERT INTO Images (ID , Image) VALUES (? , ?) ";

        PreparedStatement valstat = connection.prepareStatement(data2);

        valstat.setInt(1,id);
        valstat.setString(2, path);

        valstat.executeUpdate();

    }

    public String get_image(int id ) throws SQLException {

        String data = "Select Image from Images where id ="+id;
        ResultSet rs = stmt.executeQuery(data);
        String imageBLOB = null;

        while (rs.next()){
            imageBLOB = rs.getString("Image");
        }

        return imageBLOB;

    }


//      ********** TEST **********

    public static void main(String[] args) throws SQLException {

         // Product Cup = new Product(9005,"Gean",245,20,"Place","Food");
            Product test = new Product();
//        Product Apple = new Product("Apple", 1,"1",12,123,"asd");
//        Product Orange = new Product("Orange",2,"1",12,123,"asd");
//        Product Banana = new Product("Banana",5,"1",12,123,"asd");
//        Product Cheese = new Product("Cheese",3,"1",12,123,"asd");
//        Product Choc = new Product("Chocolate",4,"1",12,123,"asd");
        Inventory I = new Inventory();

//        test.setProductID(9005);
//      //  I.removeProduct(test);
//          I.removeQuantityDatabase(17,5858);
//
//          Product Marlb = I.getProduct("Marlboro");

          System.out.println(I.getAllproducts().get(4).getProductID());
         // I.getProduct(9001);

//        I.addNewProduct(Apple);
//        I.addNewProduct(Orange);
//        I.addNewProduct(Banana);
//        I.addNewProduct(Cheese);
//        I.addNewProduct(Choc);
//        System.out.println(I);
//        System.out.println(I.searchByName("Apple"));
//        I.removeProduct(Apple);
//        System.out.println(I);
//        System.out.println(I.searchByName("Apple"));
    }
}
