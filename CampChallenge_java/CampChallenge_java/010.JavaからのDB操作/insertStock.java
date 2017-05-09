/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kurikura
 */
public class insertStock extends HttpServlet {

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
            HttpSession session = request.getSession(false);
            
            String sessionName=(String)session.getAttribute("login");
            if(sessionName==null){ //ログインしていない時
                response.sendRedirect("login"); //ログインページに飛ばす
            }else{
                out.println(sessionName+" さんでログインしています。");
            }
            
            out.println("<h2>商品情報登録</h2>");
            out.println("<p><a href='stock'>商品在庫一覧</a>");
            out.println("<a href='logout'> ログアウト</a></p>");
                
            out.println("<form method='post' action='insertStock'>");
            out.println("商品名 <input type='text' name='name' required><br>");
            out.println("在庫数(半角数字) <input type='number' name='num' required><br>");
            out.println("<input type='submit' value='登録'>");
            out.println("</form>");

            request.setCharacterEncoding("UTF-8");
            String name=request.getParameter("name"); //formから取得
            if(name!=null && !"".equals(name)){ //formから値がある時
                String str=request.getParameter("num");
                int num=Integer.parseInt(str);
                
                Connection db_con = null;
                try{
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    db_con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db", "kurikura", "mimoriko");
                    
                    //商品が存在していなければinsert where not existsでカッコ内にデータがあればダミーテーブル(dual)に飛ばす(insertしない)
                    String sql="INSERT INTO itemData(name,number) select '"+name+"',"+num
                            +" from dual where not exists(select * from itemData where name='"+name+"');";
                    Statement statement = db_con.createStatement();
                    int n=statement.executeUpdate(sql);

                    if(n==0){ //insertできなかったとき
                        out.println("その商品はすでに登録されています");
                    }else{
                        sql="SELECT * from itemData where name='"+name+"';";
                        ResultSet rs = statement.executeQuery(sql);
                        while(rs.next()){ //insertしたとき
                            out.println("以下のデータを追加しました。<br>");
                            int itemID = rs.getInt("itemID");
                            String name2 = rs.getString("name");
                            int num2 = rs.getInt("number");
                            out.println("商品ID:"+itemID+" 商品名:"+name2+" 在庫数:"+num2);
                        }
                    }
                } catch (ClassNotFoundException cnfe) {
                    out.println("エラーが発生しました。" + cnfe.toString());
                } catch(SQLException sqle){
                    out.println("エラーが発生しました。"+sqle.toString());
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
