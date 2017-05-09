/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kurikura
 */
public class stock extends HttpServlet {

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
            Connection db_con = null;
            HttpSession session = request.getSession(false);
            
            String sessionName=(String)session.getAttribute("login");
            if(sessionName==null){ //ログインしていない時
                response.sendRedirect("login"); //ログインページに飛ばす
            }else{
                out.println(sessionName+" さんでログインしています。");
            }
            
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db", "kurikura", "mimoriko");
                String sql = "SELECT *FROM itemData";
                Statement statement = db_con.createStatement();
                ResultSet rs = statement.executeQuery(sql);

                out.println("<h2>商品在庫一覧</h2>");
                out.println("<p><a href='insertStock'>商品情報登録</a>");
                out.println("<a href='logout'> ログアウト</a></p>");
                out.println("<table border='1'>");
                out.println("<tr><th>商品ID</th><th width='60%'>商品名</th><th width='20%'>在庫数</th></tr>");
                while (rs.next()) { //DBの中身を全て書き出す
                    int itemID = rs.getInt("itemID");
                    String name = rs.getString("name");
                    int num = rs.getInt("number");

                    out.println("<tr>");
                    out.println("<td>" + itemID + "</td>");
                    out.println("<td>" + name + "</td>");
                    out.println("<td>" + num + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            } catch (ClassNotFoundException cnfe) {
                out.println("エラーが発生しました。" + cnfe.toString());
            } catch (Exception e) {
                out.println("エラーが発生しました。" + e.toString());
            } finally {
                if (db_con != null) {
                    try {
                        db_con.close();
                    } catch (Exception e_con) {
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
