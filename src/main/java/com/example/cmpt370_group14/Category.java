package com.example.cmpt370_group14;


import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Category extends Sqltest implements Comparable {

    // Encapsulate data

    private String name;
    private ArrayList<Product> productList;
    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    Inventory I ;
    // constructor
    public Category(String name) throws SQLException {
    //    try{


//            String alter_table = "ALTER TABLE Categories ADD "+name+" varchar(255) ";
//
//            stmt.executeUpdate(alter_table);

  //      }catch (SQLException e){}
        this.I = new Inventory();
        this.name = name;
        this.productList = new ArrayList<Product>();
    }

    // check if product in the Category by it's ID
    public boolean checkProduct(Product product) throws SQLException {

        String Category = product.getTags();

        return Objects.equals(Category, this.name);

//        for (Product product1: productList){
//            if (product1.getProductID()==product.getProductID()){
//                return true;
//            }
//        }
//        return false;

    };

    // TODO :
    public void addToList(Product product) throws SQLException {

//        String insert = "INSERT INTO Categories ("+this.name+" ) VALUES (?)";
//        PreparedStatement valstat = connection.prepareStatement(insert);
//        valstat.setInt(1, product.getProductID());
//        valstat.executeUpdate();
//


//        if (!checkProduct(product))
//        this.productList.add(product);
    }

    public void removeFromList(Product product) throws SQLException {

 //       String data = "DELETE FROM Categories WHERE Category ='"+product.getTags()+"'";
//        if (checkProduct(product))
//        this.productList.remove(product);
    }

    public int getCategorySize(){
        return this.productList.size();
    }

    public String getName(){
        return this.name;
    }

    // compare Categories by it's size
    @Override
    public int compareTo(Object o) {
        Category c = (Category) o;
        return c.getCategorySize()-this.getCategorySize();
    }

    @Override
    public String toString() {
        String string = "";
        for (Product product1:productList){
            try {
                string+="Name:"+product1.getName()+" PID:"+product1.getProductID()+"\n";
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return string;
    }
    public ArrayList<Product> getProductList() throws SQLException {

        String getstat = " SELECT * FROM Products WHERE Tags = '"+this.name+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        Double price = null;
        ResultSetMetaData rsmd = rs.getMetaData();

        ArrayList<String> param ;
        Product newprod = null;
        while (rs.next()) {

            int i = 1;
            param = new ArrayList<String>();

            while (i <= rsmd.getColumnCount()) {
                param.add(rs.getString(i));
                //System.out.println();
                i++;
             }

            newprod = new Product( Integer.parseInt(param.get(0)), param.get(1), Double.parseDouble(param.get(2)), Integer.parseInt(param.get(3)), param.get(4), param.get(5));
            System.out.println(param);
            productList.add(newprod);

            // price = rs.getDouble("Price");
            //System.out.println(d);
            //  i++;
        }
       // return price;
        return productList;
    }


    public static void main(String[] args) throws SQLException {

        Category Food = new Category("Clothing");

        Product adrelani = new Product(1131, "Addrelaine", 10.5, 6, "Place", "Food");

        Food.addToList(adrelani);

        Category fd = new Category("Accesory");

        fd.getProductList();
    }
}
