
package com.Servlets.Orders;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.Orders.GetOrderData;

/**
 * GetOrders
 */
public class GetOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        resp.setHeader("Access-Control-Allow-Origin", "http://192.168.0.102:8080");
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        resp.setHeader("Access-Control-Allow-Origin", "http://192.168.0.102:3000");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setHeader("Access-Control-Max-Age", "3600");

        System.out.println("orders");

        // Get the page and recordsPerPage parameters from the URL
        String pageParam = req.getParameter("page");
        String recordsPerPageParam = req.getParameter("recordsPerPage");

        // ,"device":"iphone","phoneno":"12345"}

        // Default values if parameters are not provided
        int page = 1;
        int recordsPerPage = 12;

        try {
            // Parse the parameters from the URL
            if (pageParam != null) {
                page = Integer.parseInt(pageParam);
            }
            if (recordsPerPageParam != null) {
                recordsPerPage = Integer.parseInt(recordsPerPageParam);
            }
        } catch (NumberFormatException e) {
            // Handle invalid parameter values here if needed
            e.printStackTrace();
        }

        GetOrderData g = new GetOrderData();
        
        try {
            resp.getWriter().println(g.getData("pending", page, recordsPerPage).toString());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // resp.getWriter().println("sdfsd");
    }

}
