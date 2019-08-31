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
public class updatebook2 extends HttpServlet {
        static ResultSet rs;
        static Statement st;
        static Connection cn;   
        static int id1;
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
            id1=Integer.parseInt(author);
            st=cn.createStatement();
            String q="UPDATE book SET name='"+name+"',price='"+price+"',No_page='"+pages+"',subject='"+subject+"',id="+id1+" where ISSN='"+issn+"'";
            int i= st.executeUpdate(q);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updatebook2</title>");            
            out.println("</head>");
            out.println("<body>");
            if(i==1)
                {
                    out.println("<h3>Data UPDATED</h3>");
                    RequestDispatcher dis=request.getRequestDispatcher("viewbookdata");
                    dis.include(request, response);
                }
                else
                {
                    out.println("<h3>Data not UPDATED</h3>");
                    RequestDispatcher dis=request.getRequestDispatcher("viewbookdata");
                    dis.include(request, response);
                }
            out.println("</body>");
            out.println("</html>");
                
            }catch (ClassNotFoundException | SQLException ex) {
             Logger.getLogger(updatebook2.class.getName()).log(Level.SEVERE, null, ex);
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
