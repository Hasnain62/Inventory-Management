package com.example.cmpt370_group14;

import java.sql.*;

public class Register_a_user extends Sqltest {

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();

    public Register_a_user(int ID, String username ,String password ) throws SQLException {

//
//        staffDatabase.addStaff(id,name,level);
        /**
         * staff database
         */
        Employees_Database staffDatabase = new Employees_Database();
        if (!staffDatabase.hasStaffmember(ID)){

                return;
        }

        Staff Member = staffDatabase.getStaff(ID);

        // Encrypt the password

      //  password = "aes_encrypt( "+password+" ) ,";

        String table = "CREATE TABLE IF NOT EXISTS Users ( ID int , Username varchar(255) ,Name varchar(255) , Level int , Password blob , PRIMARY KEY (ID) )  ";
        stmt.executeUpdate(table);

        String values = "INSERT INTO Users( ID , Username ,Name , Level , Password ) VALUES ( ? , ? , ? , ? , aes_encrypt ('"+password+"' , 'key1234') ) "; // Password encryption
        PreparedStatement valstat = connection.prepareStatement(values);
        valstat.setInt(1, ID);

        String data = "select Username from Users where ID =" +ID;
        ResultSet rs = stmt.executeQuery(data);
        String uname = null;

        while (rs.next()){

            uname = rs.getString("Username");
        }

        if (uname!=null){

            System.out.println("Username already exists");
            return;
        }

        valstat.setString(2,username);
        valstat.setString(3, Member.getName());
        valstat.setInt(4,Member.getLevel());
       // valstat.setString(4,password);

        valstat.executeUpdate();

    }

    public static void main(String[] args) throws SQLException {

        Register_a_user user = new Register_a_user(216, "maz423","Ath");
    }
}
