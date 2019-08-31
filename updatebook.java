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
public class updatebook extends HttpServlet {

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
         ResultSet rs,rs1;
         Statement st,st1;
         Connection cn;
        try (PrintWriter out = response.getWriter()) {
            String issn =request.getParameter("issn");
            ServletContext sc = getServletContext();  
            String value = sc.getInitParameter("classforname");
            String url = sc.getInitParameter("url");
            String user = sc.getInitParameter("user");
            String pass = sc.getInitParameter("password");
            Class.forName(value); 
            cn=DriverManager.getConnection(url,user,pass);
            st=cn.createStatement();
            String q="SELECT b.ISSN,b.name,b.Price,b.No_page,b.subject,a.name,a.id from author a,book b WHERE a.id=b.id AND b.ISSN='"+issn+"'";
            rs = st.executeQuery(q);
            st1=cn.createStatement();
            String q1="select id,name from author";
            rs1 = st1.executeQuery(q1);
            if(rs.next())
                {
                    String issn1=rs.getString(1);
                    String name=rs.getString(2);
                    int price=rs.getInt(3);
                    int pages=rs.getInt(4);
                    String subject=rs.getString(5);
                    String author=rs.getString(6);
                    int id=rs.getInt(7);
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet updatebook</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<form action='updatebookdata2'>");
                    out.println("ISSN :<input type=text name=bissn value="+issn1+" readonly /><br><br>");
                    out.println("Name :<input type=text name=bname value="+name+" /><br><br>");
                    out.println("Price :<input type=text name=bprice value="+price+" /><br><br>");
                    out.println("Pages :<input type=text name=bpages value="+pages+" /><br><br>");
                    out.println("Subject :<input type=text name=bsubject value="+subject+" /><br><br>");

                    out.println("Author :<select name=bauthor  >");

                    out.println("<option value="+id+">"+author+"</option>");
                    while(rs1.next())
                    {
                        if(rs1.getInt(1)==id)
                        {

                        }
                        else
                        {
                            out.println("<option value="+rs1.getInt(1)+">"+rs1.getString(2)+"</option>");
                        }

                    }

                    out.println("</select><br><br>");

                    out.println("<a href=index.jsp>Logout</a>");
                    out.println("<input type=submit value='Update Book' /><br>");
                    out.println("</form>");
                    out.println("</body>");
                    out.println("</html>");
                }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(updatebook.class.getName()).log(Level.SEVERE, null, ex);
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
