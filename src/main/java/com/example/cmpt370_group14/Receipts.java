package com.example.cmpt370_group14; /**
=======
package com.example.cmpt370_group14;
/**
>>>>>>> dbea0b77737d29746528494870582c3c2d5583c6
 * Receipts Class
 * by Demi
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Receipts extends Sqltest{

    /**
     * String to represent a receipt number
     */

    private String receiptNum;

    /**
     * A list of ReceiptItems
     */

    private ArrayList<ReceiptItem> list;

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    /**
     * Intialize an instance of Receipts
     */
    public Receipts() throws SQLException {



       // this.receiptNum = receiptNum;
        this.list = new ArrayList<>();

    }
    public Receipts(String receiptNum) throws SQLException {



        this.receiptNum = receiptNum;
        this.list = new ArrayList<>();

    }

    /**
     * @param receiptNum - a string to represent a receipt number
     * @param items - a list of receiptItems
     */
    public Receipts(String receiptNum, ArrayList<ReceiptItem> items) throws SQLException {

    //    String data = "CREATE TABLE IF NOT EXISTS Receipts ( Receipt No. int, Products varchar(255) , Total_Price int )";

        this.receiptNum = receiptNum;
        this.list = items;
    }

    /**
     * returns receipt's number
     * @return returns receipt's number
     */
    public String getReceiptNum(){

        return this.receiptNum;
    }

    public void setReceiptNum(String receiptNum ){

        this.receiptNum = receiptNum;

    }

    /**
     * adds a receiptItem into the receipt
     * @param x - receiptItem
     */
    public void addItem(ReceiptItem x){

       // String getstat = " SELECT  FROM Receipts WHERE Receipt No. = "+ x.;
//        ResultSet rs = stmt.executeQuery(getstat);
//        String name = null ;
//        ResultSetMetaData rsmd= rs.getMetaData();
//        ArrayList<String> param ;
//
//        while (rs.next()){
//
//            int i = 1;
//            param = new ArrayList<String>();
//
//            while (i <= rsmd.getColumnCount()){
//
//                param.add(rs.getString(i));
//                i++;
//            }



//        if(contain(x)){
//            x.setTotalQuantity(x.getTotalQuantity()+1);
//        }else{
            list.add(x);

       // }
    }

    /**
     * removes receipt item from list
     * @param x - receiptItem
     */
    public void removeItem(ReceiptItem x){
        if(this.list == null){
            throw new RuntimeException("Can't delete an item from an empty list!");
        }

        if(contain(x)){
            //loops through the list
            for(ReceiptItem y : list){
                //checks if product id is equal to the given product and then remove form list
                if(y.getProduct().getProductID() == x.getProduct().getProductID()){
                    list.remove(y);
                }
            }
        }
        else{
            System.out.println("Item not in list ");
        }



    }


    /**
     * Returns all the products in the receipt
     * @return
     */
    public ArrayList<ReceiptItem> getItems(){

        //TODO : Fetch all the items in a receipt from the Receipts table
        return list;
    }

    private Boolean contain(ReceiptItem x){
        //if the list is empty then it does not contain anything therefore return false
        if(this.list == null){
            return false;
        }
        for(ReceiptItem y : list){
            //checks if product id is equal to the given product and then remove form list
            if(y.getProduct().getProductID() == x.getProduct().getProductID()){
                return true;
            }
        }
        return false;

    }

    /**
     * returns the total price on a receipt
     * @return - returns the total price on a receipt
     */
    public double getTotalPrice() throws SQLException
    {
        if(this.list == null){
            return 0;
        }
        double total = 0.0;
        for(ReceiptItem y : list){
            total += y.getTotalPrice();
        }

        return total;
    }

    /**
     * prints the receipt to console
     */

    public void printReceipts() throws SQLException
    {
        if(this.list == null){
            throw new RuntimeException("Cannot print an empty list");

        }
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i).getProduct().getProductID() + " " + list.get(i).getTotalQuantity() + " " + list.get(i).getTotalPrice() );
        }


    }




}
