/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author Kiara Yasmin
 */
public class KoneksiDatabase {
   private static Connection koneksi;
   
   public static Connection koneksiDB() throws SQLException{
       if(koneksi==null){
           new com.mysql.jdbc.Driver();
           koneksi = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/purchasing","root","");
       }
       return koneksi;
   }
}
