/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mp1_constantino_tiu_proj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

/**
 *
 * @author Neo Gabriel
 */
public class MP1_CONSTANTINO_TIU_Proj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
          
    try {
      String driver ="org.apache.derby.jdbc.ClientDriver";
      Class.forName(driver);
      System.out.println("LOADED DRIVER  ---> " + driver);

      String url = "jdbc:derby://localhost:1527/LoginDB";
      Connection con =
      DriverManager.getConnection (url, "app", "app");
      System.out.println("CONNECTED TO   ---> "+ url);

      // Use the Connection to create a Statement object
      Statement stmt = con.createStatement ();
      
      // Execute query using Statement, receive the ResultSet
      String qry ="SELECT * FROM USERS ORDER BY EMAIL";
      
      ///////
      
      String queryStr = "SELECT * FROM USERS WHERE PASSWORD = ? ";
      PreparedStatement ps = con.prepareStatement(queryStr);
      String password = in.nextLine();
      ps.setString(1, password);

      
      ////////
      // ResultSet rs = stmt.executeQuery(qry);
      ResultSet rs = ps.executeQuery();	

      System.out.println("EXECUTED QUERY ---> " + queryStr);

      // Print the results, row by row
      System.out.println("\nPROCESSING RESULTS:\n");
      while (rs.next()) {
        
        // display email
        System.out.println("  Email: " + 
        rs.getString("Email").trim());
       
        // display password
        System.out.println("  Password: " + 
        rs.getString("Password").trim());
        
        // display role
        System.out.println("  Role: " +
        rs.getString("UserRole").trim());
        
        System.out.println("");
      }
      rs.close();
      // stmt.close();
      ps.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

}