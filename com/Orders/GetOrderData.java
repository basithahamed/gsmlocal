package com.Orders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.lang.model.util.ElementScanner6;

import org.json.simple.*;


import com.Database.Connect;
import com.mysql.cj.xdevapi.PreparableStatement;

public class GetOrderData {
    public JSONArray getData(String orderType, int page, int recordsPerPage) throws ClassNotFoundException  {
        // Connection c = Connect.getConnection();
        System.out.println("sioef");
        JSONArray result = new JSONArray();
        try {
            System.out.println("soimething");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gsm","todoadmins","todo@111");
            // Connection c = Connect.getConnection();
            

            PreparedStatement stmt = c.prepareStatement("SELECT * FROM orders WHERE status = ? LIMIT ?, ?");
            stmt.setString(1, orderType);
            stmt.setInt(2, (page - 1) * recordsPerPage); // Calculate the starting row
            stmt.setInt(3, recordsPerPage);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                JSONObject js = new JSONObject();
                js.put("orderid", rs.getString("orderid"));
                js.put("customername", rs.getString("customername"));
                js.put("phoneno", rs.getString("phoneno"));
                js.put("device", rs.getString("device"));
                js.put("problem", rs.getString("problem"));
                System.out.println(js);
                result.add(js);
            }
            System.out.println("res:"+result);
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // insert into orders(orderid,customername,phoneno,device,problem) values
    // (127,"something",123456,"iphone","EDHO ONNU");

    public static void main(String[] args) {
        
        // System.out.println(new GetOrderData().getData("pending", 6, 12));
        // Connection c = Connect.getConnection();
        // PreparedStatement stmt;

        // try {
        //     for (int i = 128; i < 200; i++) {
        //         // Corrected INSERT statement with placeholders
        //         stmt = c.prepareStatement(
        //                 "INSERT INTO orders(orderid,customername,phoneno,device,problem) VALUES (?, ?, ?, ?, ?)");
        //         stmt.setInt(1, i);
        //         stmt.setString(2, "something");
        //         stmt.setInt(3, 12345);
        //         stmt.setString(4, "iphone");
        //         stmt.setString(5, "therilaBha");

        //         // Execute the statement
        //         stmt.executeUpdate();
        //         System.out.println("Inserted row for i = " + i);

        //         // Close the statement
        //         stmt.close();
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace(); // Properly handle exceptions by printing the stack trace
        // } finally {
        //     // Close the connection in the finally block to ensure it's always closed
        //     if (c != null) {
        //         try {
        //             c.close();
        //         } catch (SQLException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }



    }

}