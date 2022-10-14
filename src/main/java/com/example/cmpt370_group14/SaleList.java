package com.example.cmpt370_group14;
/**
 * SaleList Class
 * by Demi
 */


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class SaleList extends Sqltest {

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    /**
     * String representing Today's date
     */
    private String Date;

    /**
     * list of receipts
     */
    private ArrayList<Receipts> list;

    public SaleList( ) throws SQLException {
      //  String LocalDate = null;
        this.Date = String.valueOf(LocalDate.now());
        list = new ArrayList<>();

        String data = "CREATE TABLE IF NOT EXISTS Receipts ( Receipt_No int, Products varchar(255) , Total_Price DOUBLE , Date DATE , PRIMARY KEY(Receipt_NO) )";
        stmt.executeUpdate(data);

    }

    public int generateReceiptNumber(){
        Random random = new Random();
        int number = random.nextInt(99999999);
        //TODO: check if this number already exists in database
        return number;
    }

    /**
     * add receipt list for the day to the database
     * @param newReceipt - new receipt list
     */
    public void addReceipt(Receipts newReceipt) throws SQLException {

        // TODO if receipts already exists return
/**
        String getstat = "SELECT EXISTS(SELECT * FROM Receipts WHERE Receipt_No = " +62220462+")";
        ResultSet rs = stmt.executeQuery(getstat);
        int result = 0 ;

        while (rs.next()){
            result = rs.getInt("EXISTS(SELECT * FROM Receipts WHERE Receipt_No =" + 62220462+")");
            System.out.println("aara hai "+result);
        }
 **/
        String data =  " INSERT INTO Receipts ( Receipt_No , Products , Total_Price  , Date ) VALUES (? , ? , ? , ?)";
        PreparedStatement valstat = connection.prepareStatement(data);
        String ItemProducts ;
//        for (ReceiptItem item : newReceipt.getItems()) {
            // ItemProducts = ItemProducts + item.getProduct().getName()+ " "+ "x" + " "+ item.getTotalQuantity()+" "+"@"+item.getProduct().getProductPrice()+" , " ;
//        }

        int i = 1;
        ItemProducts =   newReceipt.getItems().get(0).getProduct().getName()+ " "+ "x" + " "+ newReceipt.getItems().get(0).getTotalQuantity()+" "+"@"+newReceipt.getItems().get(0).getProduct().getProductPrice()+" , " ;

        while (i <newReceipt.getItems().size()) {

            ItemProducts = ItemProducts + newReceipt.getItems().get(i).getProduct().getName()+ " "+ "x" + " "+ newReceipt.getItems().get(i).getTotalQuantity()+" "+"@"+newReceipt.getItems().get(i).getProduct().getProductPrice()+" , " ;
            i++;
        }

        System.out.println(newReceipt.getItems().size()+" "+ItemProducts);
        int GetReceiptNo = generateReceiptNumber();
        newReceipt.setReceiptNum(String.valueOf(GetReceiptNo));
    //    System.out.println(GetReceiptNo);

        valstat.setInt(1,GetReceiptNo);

        valstat.setString(2, ItemProducts);
        valstat.setDouble(3,newReceipt.getTotalPrice());

         String date = this.Date.toString();
        valstat.setString(4,date);
        valstat.executeUpdate();
       // this.list = newReceipt;
    }

    /**
     * returns a receipts given it's receipt number
     * @param num - string representing receipt list number
     * @return - receipts
     */
    public Receipts getReceipt(String num) throws SQLException
    {

        String sql = "SELECT * FROM RECEIPTS WHERE Receipt_No =" + num;

        if (this.list == null)
        {
            throw new RuntimeException("SaleList is empty!");
        }
        Receipts receipt = new Receipts(num);
        if (contain(num))
        {
            for (Receipts x : list)
            {
                if (x.getReceiptNum().compareTo(num) == 0)
                {
                    receipt = x;
                }
            }
        }
        return receipt;
    }
        /**
         * returns a receipts given it's receipt number
         * @return - receipts
         */
        public ArrayList<Receipts> todaysSales() throws SQLException
        {

            String sql = "SELECT * FROM Receipts where Date = '"+Date+"'";
            ResultSet results = stmt.executeQuery(sql);

            ResultSetMetaData rsmd= results.getMetaData();
            ArrayList<String> param ;
            ArrayList<Receipts> allReceipts = new ArrayList<Receipts>();
            Receipts newReceipt = null;

            while(results.next()){
                int i =1;
                param = new ArrayList<String>();
                while (i <= rsmd.getColumnCount()){
                    param.add(results.getString(i));
                    i++;
                }
                if (param.get(0) != null) {
                    allReceipts.add(getReceipt(param.get(0)));
                }
            }
            return allReceipts;
    }

    /**
     * checks if a receipt with num is in the list
     * @param num - string representation of receipt number
     * @return - true if receipt is in the list and false otherwise
     */

    private Boolean contain(String num){
        if(this.list == null){
            return false;
        }
        for(Receipts x: list){
            if(x.getReceiptNum().compareTo(num) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * the total sale of the day
     * param : Date in YYYY_MM_DD format
     * @return - the total sale price
     */
    public double DailySale(String Date) throws SQLException, SQLException
    {
        String todays_date = this.Date;
        String sql = "SELECT Total_Price from Receipts where Date = '"+Date+"'";
        ResultSet rs =  stmt.executeQuery(sql);

        double totalPrice =0 ;
       // totalPrice =  rs.getDouble("Total_Price");;
        double evryAdd;
        while(rs.next()){
            evryAdd = rs.getDouble("Total_Price");

            totalPrice = totalPrice + evryAdd;
        }

     //   System.out.println(totalPrice);

        return totalPrice;
//        if(this.list == null){
//            return 0;
//
//        }
//        double total = 0.0;
//        for(Receipts x : list){
//            total = total + x.getTotalPrice();
//        }
//        return total;

    }

    /**
     * returns the number of receipts
     * @return - number of receipts
     */
    public int countItems(){



        if(this.list == null){
            return 0;
        }
        return list.size();
    }

    public static void main(String[] args) throws SQLException {
        // write your code here

        Product p = new Product();
        p.setName("Lotion");

        Product sp = new Product();
        sp.setName("Soap");

        ReceiptItem RI = new ReceiptItem(p);
        ReceiptItem Rf = new ReceiptItem(sp);

        Receipts R = new Receipts();

//
        R.addItem(Rf);
        R.addItem(RI);

        System.out.println(R.getItems().get(0).getProduct().getName());
        SaleList S = new SaleList();
//
  //      S.addReceipt(R);

        S.DailySale("2022-03-28");
    //  System.out.println( S.generateReceiptNumber());
        System.out.println(S.Date);
    }

}
