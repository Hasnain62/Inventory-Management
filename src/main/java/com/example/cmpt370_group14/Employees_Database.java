package com.example.cmpt370_group14;

import java.sql.*;
import java.util.ArrayList;

public class Employees_Database extends Sqltest {

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();


    Employees_Database() throws SQLException {

        try {
            String table = "CREATE TABLE IF NOT EXISTS Staff ( ID int , Name varchar(255) , Level int , PRIMARY KEY (ID) )  ";
            stmt.executeUpdate(table);
        } catch (SQLException e) {
            System.out.println("Oops, error !");
            e.printStackTrace();

        }

    }

    public void addStaff(int ID , String name , int level ) throws SQLException {

        String stat = "INSERT INTO Staff (ID , Name , Level) VALUES (? , ? , ?)";
        PreparedStatement valstat = connection.prepareStatement(stat);
        valstat.setInt(1, ID);
        valstat.setString(2,name);
        valstat.setInt(3,level);
        valstat.executeUpdate();

    }

    public Staff getStaff(int ID) throws SQLException {


            String getstat = " SELECT * FROM Staff WHERE ID = "+ ID;
            ResultSet rs = stmt.executeQuery(getstat);
            String name = null ;
            ResultSetMetaData rsmd= rs.getMetaData();
            ArrayList<String> param ;

            while (rs.next()){

                int i = 1;
                param = new ArrayList<String>();

                while (i <= rsmd.getColumnCount()){

                    param.add(rs.getString(i));
                    i++;
                }

                return new Staff(Integer.parseInt(param.get(0)), param.get(1), Integer.parseInt(param.get(2)) );
            }
               return null;
    }

    public Staff getStaff(String name) throws SQLException {

        String getstat = " SELECT * FROM Staff WHERE Name = '"+name+"'";
        ResultSet rs = stmt.executeQuery(getstat);
        String str = null ;
        ResultSetMetaData rsmd= rs.getMetaData();
        ArrayList<String> param ;

        while (rs.next()){

            int i = 1;
            param = new ArrayList<String>();

            while (i <= rsmd.getColumnCount()){

                param.add(rs.getString(i));
                i++;
            }

            return new Staff(Integer.parseInt(param.get(0)), param.get(1), Integer.parseInt(param.get(2)) );
        }
        return null;
    }

    public boolean hasStaffmember(int ID) throws SQLException {

        String getstat = " SELECT * FROM Staff WHERE ID = "+ ID;
        ResultSet rs = stmt.executeQuery(getstat);
        String name = null ;
        ResultSetMetaData rsmd= rs.getMetaData();
        ArrayList<String> param ;

        while (rs.next()){

            name = rs.getString("Name");
        }
        return name!=null;
    }

    public void removeStaff(int id) throws SQLException {

        String getstat = " SELECT * FROM Staff WHERE ID = "+id;
        ResultSet rs = stmt.executeQuery(getstat);
        String str = null ;
        ResultSetMetaData rsmd= rs.getMetaData();
        ArrayList<String> param ;

        while (rs.next()){
            str = rs.getString("Name");
        }

        if (str == null){
            return;
        }

        String remove = "DELETE FROM Staff WHERE ID = "+id;

        stmt.executeUpdate(remove);
    }

    public static void main(String[] args) throws SQLException {

        Employees_Database E = new Employees_Database();

        E.addStaff(216, "Gohar" , 1);

      //    System.out.println(E.getStaff("Hasnain").getID());

    }
}
