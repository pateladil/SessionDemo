/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bookinfo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
/**
 *
 * @author student74
 */
public class insertbook2 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ResultSet rs;
         Statement st;
         Connection cn;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String issn =request.getParameter("bissn");
            String name =request.getParameter("bname");
            String price =request.getParameter("bprice");
            String pages =request.getParameter("bpages");
            String subject =request.getParameter("bsubject");
            String author =request.getParameter("bauthor");
             
            ServletContext sc = getServletContext();  
            String value = sc.getInitParameter("classforname");
            String url = sc.getInitParameter("url");
            String user = sc.getInitParameter("user");
            String pass = sc.getInitParameter("password");
            Class.forName(value); 
            cn=DriverManager.getConnection(url,user,pass);
               String q="insert into book values(?,?,?,?,?,?)";
                PreparedStatement pst=cn.prepareStatement(q);
                pst.setString(1,issn);
                pst.setString(2,name);
                pst.setInt(3,Integer.parseInt(price));
                pst.setInt(4,Integer.parseInt(pages));
                pst.setString(5,subject);
                pst.setInt(6,Integer.parseInt(author));
                int no1=pst.executeUpdate();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet insertbook2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>BOOK INSERTED</h1>");
            RequestDispatcher dis=request.getRequestDispatcher("insertbookdata");
            dis.include(request, response);
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(insertbook2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
