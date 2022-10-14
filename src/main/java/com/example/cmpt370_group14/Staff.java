package com.example.cmpt370_group14;

import java.sql.*;

public class Staff extends Sqltest {

    private String name;
    private int ID;
    private int level;

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();

    // constructor
    public Staff(int ID , String name , int level) throws SQLException {



//        this.name = name;
        this.ID = ID;
//        this.level = level;
    }

    public void setName(String name) throws SQLException {

        String stat = " UPDATE Staff SET Name = " +name +" WHERE ID = " + this.ID ;
        stmt.executeUpdate(stat);
        this.name = name;
    }

    public String getName() throws SQLException {

        String getstat = " SELECT Name FROM Staff WHERE ID = "+ this.ID;
        ResultSet rs = stmt.executeQuery(getstat);
        String name = null ;

        while (rs.next()){
            name = rs.getString("Name");
            System.out.println(name);

        }
        assert name != null;
        return name;
    }

    public void setID(int ID) throws SQLException {

        String stat = " UPDATE Staff SET ID = " +ID +" WHERE ID = " + this.ID ;
        stmt.executeUpdate(stat);
        this.ID = ID;
    }

    public int getID() {

        return ID;
    }

    public void setLevel(int level) throws SQLException {
        String stat = " UPDATE Staff SET Level = " +level +" WHERE ID = " + this.ID ;
        stmt.executeUpdate(stat);
    }

    public int getLevel() throws SQLException {

        String getstat = " SELECT Level FROM Staff WHERE ID = "+ this.ID;
        ResultSet rs = stmt.executeQuery(getstat);
        int level = 0;

        while (rs.next()){
            level = rs.getInt("Level");
            //System.out.println(d);

        }
        return level;

    }
}
