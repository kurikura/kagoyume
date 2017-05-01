/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kurikura
 */
public class dbaccess extends HttpServlet {

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
            Connection db_con=null;
            out.println("<form method=\"post\" action=\"dbaccess\"><br>");
            out.println("profilesID:<input type=\"search\" name=\"id\"><br>");
            out.println("name:<input type=\"search\" name=\"name\"><br>");
            out.println("tell:<input type=\"search\" name=\"tell\"><br>");
            out.println("age:<input type=\"search\" name=\"age\"><br>");
            out.println("birthday:<input type=\"search\" name=\"bday\"><br>");
            out.println("<input type=\"submit\" value=\"insert\">");
            out.println("</form>");
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con=DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","kurikura","mimoriko");
                Statement statement=db_con.createStatement();
                request.setCharacterEncoding("UTF-8");
                
                String n=request.getParameter("id");
                String n1=request.getParameter("age");
                int nid=0,nage=0;
                Date date=Date.valueOf("1990-01-01");
                if(n==null || n.equals("")){}
                else{
                    nid=Integer.parseInt(n);
                }
                String nname=request.getParameter("name");
                String ntell=request.getParameter("tell");
                if(n1==null || "".equals(n1)){}
                else{
                    nage=Integer.parseInt(n1);
                }
                String nbd=request.getParameter("bday");
                if(nbd==null || "".equals(nbd)){}
                else{
                    date=Date.valueOf(nbd);
                }

                if(nid!=0){
                    String sql="INSERT INTO profiles VALUES("+nid+",'"+nname+"','"+ntell+"',"+nage+",'"+date+"');";
                    statement.executeUpdate(sql);
                }
                
                String sql="SELECT * FROM profiles ;";
                ResultSet rs =statement.executeQuery(sql);
                while(rs.next()){
                    int profilesID=rs.getInt("profilesID");
                    String name=rs.getString("name");
                    String tell=rs.getString("tell");
                    int age=rs.getInt("age");
                    Date birthday=rs.getDate("birthday");
                    out.println("<p>");
                    out.println("profilesID:"+profilesID);
                    out.println(" name:"+name);
                    out.println(" tell:"+tell);
                    out.println(" age:"+age);
                    out.println(" birthday:"+birthday);
                    out.println("</p>");
                }
                db_con.close();
            }catch(SQLException sqle){
                out.println("エラーが発生しました。"+sqle.toString());
            }catch(ClassNotFoundException cnfe){
                out.println("エラーが発生しました。"+cnfe.toString());
            }catch(NumberFormatException nfe){
                out.println("エラーが発生しました。"+nfe.toString());
            }catch(Exception e){
                out.println("エラーが発生しました。"+e.toString());
            }finally{
                if(db_con!=null){
                    try{
                        db_con.close();
                    }catch(Exception e_con){
                        System.out.println(e_con.getMessage());
                    }
                }
            }
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