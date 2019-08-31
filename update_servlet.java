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
import javax.servlet.ServletContext;
/**
 *
 * @author shah rumit
 */
public class update_servlet extends HttpServlet {

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
            String id =request.getParameter("id");
            ServletContext sc = getServletContext();  
            String value = sc.getInitParameter("classforname");
            String url = sc.getInitParameter("url");
            String user = sc.getInitParameter("user");
            String pass = sc.getInitParameter("password");
            Class.forName(value); 
            cn=DriverManager.getConnection(url,user,pass);
                st=cn.createStatement();
                String q="select * from author where id="+id;
                rs = st.executeQuery(q);
                if(rs.next())
                    {
                        String id1=rs.getString(1);
                        String name=rs.getString(2);
                        String ct=rs.getString(3);
                        String phone=rs.getString(4);
                    
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet update_servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
        out.println("<form action='up_s'>");
            out.println("ID :<input type=text name=a_id value="+id1+" readonly /><br><br>");
            out.println("Name :<input type=text name=a_name value="+name+" /><br><br>");
            out.println("City :<input type=text name=a_city value="+ct+" /><br><br>");
            
            out.println("phone :<input type=text name=a_ph value="+phone+"  /><br><br>");
            
             out.println("<a href=index.jsp>Logout</a>");
            out.println("<input type=submit value='Update' /><br>");
        out.println("</form>");
            out.println("</body>");
            out.println("</html>");
                    }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(update_servlet.class.getName()).log(Level.SEVERE, null, ex);
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
