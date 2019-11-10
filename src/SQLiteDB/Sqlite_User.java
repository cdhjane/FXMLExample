/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLiteDB;

import fxmlexample.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class Sqlite_User {
    
    public  void createTable(Connection conn){
       String sql = "CREATE TABLE IF NOT EXISTS User " +
                   " (FirstName      CHAR(30)    NOT NULL, " + 
                   " LastName       CHAR(30)    NOT NULL, " + 
                   " Username       CHAR(30)    NOT NULL, " + 
                   " Password       CHAR(50), " + 
                    "Emailaddress    CHAR(60), " +
                   " UserType        char(20))";
       try (
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            stmt.close();
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public  void insertTable(Connection conn,User user){
         
        try (
            Statement stmt = conn.createStatement()) {
           
           String sql = "INSERT INTO User (FirstName,LastName,Username,Password,Emailaddress,UserType) " +
                   "VALUES (+"
                     + "'" + user.getFirstName() + "', " 
                     + "'" + user.getLastName() + "', " 
                     + "'" + user.getUsername() + "', "
                     + "'" + user.getPassword() + "', "
                     + "'" + user.getEmailaddress() + "', "
                     + "'" + user.getType() + "'); ";
                 //    + "'doctor' );"; 
            stmt.execute(sql);
             
            stmt.close();
            System.out.println("Table inserted successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
     }
     
     public ArrayList<User> selectTable(Connection conn){
           
           ArrayList<User> user = new ArrayList<>();
           try (
            Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery( "SELECT * FROM User;" );
           
            while ( rs.next() ) {
             String  FirstName = rs.getString("FirstName");
             String  LastName = rs.getString("LastName");
             String  Username = rs.getString("Username");
             String  Password = rs.getString("Password");
             String  Emailaddress = rs.getString("Emailaddress");
             String  UserType = rs.getString("UserType");
             user.add(new User(FirstName,LastName,Username,Emailaddress,Password,UserType));
           }
            rs.close();
            stmt.close();
            System.out.println("Table selected successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;  
      }
     
      public  Boolean selectTableByUsername(Connection conn, String username){
           Boolean userExist = false;
           ResultSet rs =null;
           ArrayList<User> user = new ArrayList<>();
           try (
            Statement stmt = conn.createStatement()) {
             rs = stmt.executeQuery( "SELECT * FROM User where Username =" + "'" + username + "';" );
           if(rs.next()){
             userExist= true;
           }
           else{
             userExist= false;
           }
            rs.close();
            stmt.close();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userExist;  
      }
      
        public  User loginCheck(Connection conn, String username,String password){
           User user=null;
           ResultSet rs =null;
         
           try (
            Statement stmt = conn.createStatement()) {
           
             rs = stmt.executeQuery( "SELECT * FROM User where Username =" + "'" + username + 
                                                               "' AND Password =" + "'" + password  +"';" );
            if(rs.next()){
              
             String  FirstName = rs.getString("FirstName");
             String  LastName = rs.getString("LastName");
             String  Username = rs.getString("Username");
             String  Password = rs.getString("Password");
             String  Emailaddress = rs.getString("Emailaddress");
             String  UserType = rs.getString("UserType");
             user = new User(FirstName,LastName,Username,Emailaddress,Password,UserType);
            
           }
           
            rs.close();
            stmt.close();
          
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;  
      }
     
       public  void deleteTable(Connection conn){
          try (
           Statement stmt = conn.createStatement()) {
           String sql = "DELETE from User;";
           stmt.execute(sql);
           stmt.close();
           System.out.println("Table deleted successfully"); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
      }
       
        public  void dropTable(Connection conn){
          try (
           Statement stmt = conn.createStatement()) {
           String sql = "Drop table User;";
           stmt.execute(sql);
           stmt.close();
           System.out.println("Table dropped successfully"); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }       
      }
}
