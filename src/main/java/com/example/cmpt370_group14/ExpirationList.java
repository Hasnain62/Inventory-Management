package com.example.cmpt370_group14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class ExpirationList extends Sqltest
{
    private ArrayList<Expiration> expirations;
    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    ExpirationList() throws SQLException {

        String table = "CREATE TABLE IF NOT EXISTS Expirations ( Date DATE , PID int , Name varchar(255) )  ";
        stmt.executeUpdate(table);

        expirations = new ArrayList<>();
    }

    //TODO Expirations dont go inside database

    //  public void addExpiration(Product product, String expDate) throws SQLException { }



    //adds an expiration to the list of expirations
    public void addExpiration(Product product, String expDate) throws SQLException {


        for (Expiration exp: expirations)
        {
            if(exp.getExpiryDate().equals(expDate)){
                exp.addProducts(product);
                return;
            }
        }
        Expiration newExpiration = new Expiration(expDate);
        newExpiration.addProducts(product);
        expirations.add(newExpiration);
        sortExpirations();

    }

    //TODO Make changes to this function in order to fetch from database

    /**
     *  checks if items expire today
     *  output -   true - if an item expires today
     *  false - if no item expires today
    **/
    public boolean checkTodayExpiration() throws ParseException
    {
        if(expirations.size()>0){
            //remove front of expirations if date has passed
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date today = dateFormat.parse(expirations.get(0).getTodaysDate());
            Date date = dateFormat.parse(expirations.get(0).getExpiryDate());
            if(today.after(date)){
                expirations.remove(0);
                sortExpirations();
            }
            if(expirations.get(0).getExpiryDate().equals(expirations.get(0).getTodaysDate())){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }

    //returns an arraylist of all products that expire today
    public ArrayList<Product> getTodayExpirations() throws ParseException
    {
        if(checkTodayExpiration()){
            return expirations.get(0).getProducts();
        }else{
            ArrayList<Product> emptyList = new ArrayList<>();
            return emptyList;
        }
    }
    //sorts expirations by their date
    public void sortExpirations(){
        Collections.sort(expirations, new Comparator<Expiration>()
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            @Override
            public int compare(Expiration o1, Expiration o2)
            {
                try
                {
                    return dateFormat.parse(o1.getExpiryDate()).compareTo(dateFormat.parse(o2.getExpiryDate()));
                } catch (ParseException e)
                {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }

}
