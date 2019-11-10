/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SQLiteDB;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Dell
 */
public class Sqlite {
    private static String fileName = "alphacare.db";
    private static Connection connection;
   
    public static Connection getConnection()
    {
         String url = "jdbc:sqlite:" + fileName;
         try{
             connection = DriverManager.getConnection(url);
             System.out.println("Opened database successfully");
                
          }
          catch (SQLException e) {
             System.out.println(e.getMessage());
          }
          return connection;
    }
}
