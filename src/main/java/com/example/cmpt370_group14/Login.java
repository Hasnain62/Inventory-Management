package com.example.cmpt370_group14;

import java.sql.*;

/**
 * Login Class
 */

public class Login extends Sqltest{

    Connection connection = DriverManager.getConnection(url, user, password);
    Statement stmt = connection.createStatement();
    /**
     * Dictionary to users in system
     */

   // private Hashtable<Staff,String> listOfUsers = new Hashtable<>();;

    /**
     * Login Status
     */

    private boolean status;

    /**
     * staff database
     */

    private Employees_Database staffDatabase;

    /**
     * keeps track of current user ID
     */

    private String currentUser;

    /**
     * intialize an instance of login

     * @throws SQLException
     */

    public Login( ) throws SQLException {

    }
    /**
     * @return returns the username of the currently logged in user
     */
    public String getCurrentUser(){
        return currentUser;
    }
    /**
     * checks login status
     * @return returns true if someone is signed in and false otherwise
     */

    public boolean  isSignedIn(){
        return status;
    }

    /**
     * changes login status to false and set current user to no one
     */

    public void signOut(){
        status = false;
        currentUser = null;
    }

    /**
     * changes login status to true
     */

    private void signInStatus(){
        status = true;
    }



    public int getuserID() throws SQLException {

        String data = "select ID from Users where Username ='"+currentUser+"'";
        ResultSet rs = stmt.executeQuery(data);
        int ID = 0;

        while (rs.next()){

            ID = Integer.parseInt(rs.getString("ID"));
        }

        return ID;
    }

    public int getuserlevel() throws SQLException {

        String data = "select Level from Users where Username ='"+currentUser+"'";
        ResultSet rs = stmt.executeQuery(data);

        int level = 0;

        while (rs.next()){

            level = Integer.parseInt(rs.getString("Level"));
        }

        return level;
    }

    public String getName() throws SQLException {

        String data = "select Name from Users where Username ='"+currentUser+"'";
        ResultSet rs = stmt.executeQuery(data);

        String name = null;

        while (rs.next()){

            name = rs.getString("Name");
        }

        return name;

    }

    public String getuserName(){

        return currentUser;
    }

    /**
     * checks if a user exists in system
     * @param uname - username
     * @return returns true if user exists and false otherwise
     * @throws SQLException
     */
    private boolean userExists(String uname) throws SQLException{

        String data = "Select * from Users where Name ='"+uname+"'";
        ResultSet rs = stmt.executeQuery(data);
        String Sname = null;
        while (rs.next()){

            Sname= rs.getString("Name");
        }

        return Sname != null;



//        Set<Staff> keys = listOfUsers.keySet();
//        for(Staff key: keys){
//            if(key.getName().compareTo(name) == 0){
//                return true;
//            }
//        }
//        return false;

    }
//
//    /**
//     * checks that user's name matches password on system
//     * @param name - user's name
//     * @param password - user's password
//     * @return true if passwords matches and false otherwise
//     * @throws SQLException
//     */
//    private boolean isPassword(String name, String password) throws SQLException{
//        Set<Staff> keys = listOfUsers.keySet();
//        for(Staff key: keys){
//            if(key.getName().compareTo(name) == 0){
//                if(listOfUsers.get(key).compareTo(password) != 0){
//                    return false;
//                }
//            }
//        }
//        return true;
//
//
//    }

//    /**
//     * signs in a user
//     * @param name - username
//     * @param password - userpassword
//     * @throws SQLException
//     */

    public void signIn(String uname, String password) throws SQLException{
        String data = "select Username , Name, cast(aes_decrypt(Password, 'key1234') AS char) from Users where Username ='"+uname+"'";
        ResultSet rs = stmt.executeQuery(data);
        String pword = null;

        while (rs.next()){

            pword = rs.getString(3);

        }

        /**
         CATCH THIS EXCEPTION IN UI WHEN WRONG ID OR PASSWORD IS GIVEN
         **/

        if (pword == null ){

            throw new SQLException("Incorrect Username or Password");
        }

        if(!pword.equals(password)){
            throw new SQLException("Incorrect Username or Password");

        }
        System.out.println(pword);

        if (pword.equals(password)) {

            signInStatus();
            currentUser = uname;
            System.out.println("Succesfully signed in as "+uname);
        }
    }

//    /**
//     * creates a new user
//     * @param manager - Manager Staff
//     * @param newStaffName - new user's name
//     * @param newStaffId - new user's id
//     * @param newStaffLevel - new user's level
//     * @param newStaffPassword - new user's password
//     * @throws SQLException
//     */

//
//    public void createUser(Staff manager,String newStaffName, int newStaffId, int newStaffLevel, String newStaffPassword ) throws SQLException{
//        if(manager.getLevel() < 0){
//            throw new SQLException("You do not have the appropriate level to proceed");
//
//        }
//        staffDatabase.addStaff(newStaffId,newStaffName,newStaffLevel);
//        listOfUsers.put(new Staff(newStaffId,newStaffName,newStaffLevel),newStaffPassword);
//
//    }


    public static void main(String[] args) throws SQLException {

        Login Gohar = new Login();

        Gohar.signIn("maz423","Ah");
        System.out.println(  Gohar.currentUser);
    }

}
