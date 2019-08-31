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
 * @author student74
 */
public class viewbook extends HttpServlet {

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
            ServletContext sc = getServletContext();  
            String value = sc.getInitParameter("classforname");
            String url = sc.getInitParameter("url");
            String user = sc.getInitParameter("user");
            String pass = sc.getInitParameter("password");
            Class.forName(value); 
            cn=DriverManager.getConnection(url,user,pass);
                st=cn.createStatement();
                String q="SELECT b.ISSN,b.name,b.Price,b.No_page,b.subject,a.name from author a,book b WHERE a.id=b.id";
                rs = st.executeQuery(q);
                out.println("<h1> BOOKS DATA</h1>");
            out.println("<table border=1>");
            out.println("<tr><th> ISSN </th>"
                    + "<th>Name</th>"
                    + "<th>Price</th>"
                    + "<th>Page</th>"
                    + "<th>subject</th>"
                    + "<th>Author</th></tr>");
            
            while(rs.next())
            {
                String issn=rs.getString(1);
                String name=rs.getString(2);
                int price=rs.getInt(3);
                int pages=rs.getInt(4);
                String subject=rs.getString(5);
                String author=rs.getString(6);
                
                out.println("<tr><td> "+issn+" </td>"
                    + "<td> "+name+" </td>"
                    + "<td> "+price+" </td>"
                    + "<td> "+pages+" </td>"
                    + "<td> "+subject+" </td>"
                    + "<td> "+author+" </td>"
                    + "<td><a href=updatebookdata?issn="+issn+">update</a> </td>"
                    + "<td><a href=deletebook?issn="+issn+">delete</a> </td></tr>");
               
            }
            out.println("</table>");
            out.println("<br>  <a href=index.jsp>logout</a>");
            
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(viewbook.class.getName()).log(Level.SEVERE, null, ex);
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
