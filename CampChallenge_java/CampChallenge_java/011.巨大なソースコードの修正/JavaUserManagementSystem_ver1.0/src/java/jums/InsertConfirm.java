package jums;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * insertconfirm.jspと対応するサーブレット
 * フォーム入力された情報はここでセッションに格納し、以降持ちまわることになる
 * 直接アクセスした場合はerror.jspに振り分け
 * @author hayashi-s
 */
public class InsertConfirm extends HttpServlet {
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

        HttpSession session = request.getSession();
        UserDataBeans udb=(UserDataBeans)session.getAttribute("UserDataBeans");
        try{
            request.setCharacterEncoding("UTF-8");//セッションに格納する文字コードをUTF-8に変更
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){ //リクエストスコープとセッションスコープの値を比較
                throw new Exception("不正なアクセスです"); //直リンク防止
            }
            //フォームからの入力を取得
            String name = request.getParameter("name");
            String year = request.getParameter("year");
            String month = request.getParameter("month");
            String day = request.getParameter("day");
            String type = request.getParameter("type");
            String tell = request.getParameter("tell");
            String comment = request.getParameter("comment");
            
            //未入力の場合の処理
            String err="";
            if(name.equals("")) err+="・名前が未入力です<br>";
            if(year.equals("----") ||month.equals("--") ||day.equals("--")){
                err+="・生年月日が未入力です<br>";
            }
            if(type==null) err+="・種別が未選択です<br>";
            if(tell.equals("")) err+="・電話番号が未入力です<br>";
            if(comment.equals("")) err+="・自己紹介が未入力です<br>";
            
            //データをJavaBeansにセット
            udb.setName(name);
            udb.setYear(year);
            udb.setMonth(month);
            udb.setDay(day);
            udb.setType(type);
            udb.setTell(tell);
            udb.setCom(comment);
            udb.setErr(err); //未入力項目
            
            session.setAttribute("ac", (int) (Math.random() * 1000));
            session.setAttribute("UserDataBeans", udb); //スコープにオブジェクトを保存
            request.getRequestDispatcher("/insertconfirm.jsp").forward(request, response);
        }catch(IllegalStateException ise){ //セッション無効時に呼び出した場合
            request.setAttribute("error", ise.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }catch(ServletException se){
            request.setAttribute("error", se.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }catch(IOException ioe){
            request.setAttribute("error", ioe.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
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