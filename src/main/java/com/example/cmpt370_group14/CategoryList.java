package com.example.cmpt370_group14;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CategoryList extends Sqltest{

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();


    private ArrayList<Category> categoryList;

    public CategoryList() throws SQLException {

        String table = "CREATE TABLE IF NOT EXISTS Categories ( Category varchar(255) )";
        stmt.executeUpdate(table);


        this.categoryList = new ArrayList<Category>();
    }

    public void addCategory(Category category) throws SQLException {

        String getstat = " SELECT * FROM Categories WHERE Category ='"+ category.getName()+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        String Fname = null ;

        while (rs.next()){
            Fname = rs.getString("Category");
            //   System.out.println(name);

        }

        // Check if the category already exists
        if (Fname!=null){
            return;
        }
        String values = "INSERT INTO Categories ( Category ) VALUES (?)";
        PreparedStatement valstat = connection.prepareStatement(values);
        valstat.setString(1, category.getName());
        valstat.executeUpdate();

        if (!checkCategory(category.getName()))
            this.categoryList.add(category);
    }

    public void removeCategory(Category category) throws SQLException {

        String getstat = " SELECT * FROM Categories WHERE Category ='"+ category.getName()+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        String Fname = null ;

        while (rs.next()){
            Fname = rs.getString("Category");
            //   System.out.println(name);

        }

        if (Fname==null){
            return;
        }
        String remove = "DELETE FROM Categories WHERE Category ='"+category.getName()+"'";

        stmt.executeUpdate(remove);


        if (checkCategory(category.getName()))
            this.categoryList.remove(category);
    }

    public boolean checkCategory(String name) throws SQLException {

        String getstat = " SELECT * FROM Categories WHERE Category ='"+ name+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        String Fname = null ;

        while (rs.next()){
            Fname = rs.getString("Category");

        }
       /// if (Fname==name)

        System.out.println(name);
        System.out.println("Retreived item"+Fname);

        return Objects.equals(Fname, name);

//        for (Category Category1: categoryList) {
//            if (Category1.getName().equals(name)) {
//                return true;
//            }
//        }
//        return false;
    }
    // get Category by it's name
    public Category getCategory(String name) throws SQLException {

        String getstat = " SELECT * FROM Categories WHERE Category ='"+ name+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        String Fname = null ;

        while (rs.next()){
            Fname = rs.getString("Category");
            //   System.out.println(name);

        }
        return new Category(Fname);

//        for (Category Category1: categoryList) {
//            if (Category1.getName().equals(name)) {
//                return Category1;
//            }
//        }
//        return null;
    }

    public void sortCategory(){
        Collections.sort(categoryList);
    }


    // TO String won't work
    @Override
    public String toString() {
        String string = "";
        for (Category Category1:categoryList){
            string+=Category1.getName()+" "+Category1.getCategorySize()+"\n";
        }
        return string;
    }

//      ********** TEST **********

    public static void main(String[] args) throws SQLException {


//        Product Apple = new Product("Apple", 1,"1",12,123,"asd");
//        Product Orange = new Product("Orange",2,"1",12,123,"asd");
//        Product Banana = new Product("Banana",5,"1",12,123,"asd");
//        Product Cheese = new Product("Cheese",3,"1",12,123,"asd");
//        Product Choc = new Product("Chocolate",4,"1",12,123,"asd");
          Category Fruit = new Category("Snack");

//        Category Snack = new Category("Snack");
          CategoryList C = new CategoryList();

          C.addCategory(Fruit);
          C.addCategory(Fruit);
        System.out.println("Get Catgory"+C.getCategory("Snack").getName());
        System.out.println(C.checkCategory("Snack"));
//        Fruit.addToList(Apple);
//        Fruit.addToList(Orange);
//        Fruit.addToList(Banana);
//        Snack.addToList(Cheese);
//        Snack.addToList(Choc);
//        C.addCategory(Snack);
//        C.addCategory(Fruit);
//        C.addCategory(Fruit);
//        System.out.println(C);
//        Category Snacktemp = C.getCategory("Snack");
//        System.out.println(Snacktemp);
//        C.sortCategory();
//        System.out.println(C);
    }
}
