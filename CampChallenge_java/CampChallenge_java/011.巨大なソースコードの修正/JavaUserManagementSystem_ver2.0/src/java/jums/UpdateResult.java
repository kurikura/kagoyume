package jums;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hayashi-s
 */
public class UpdateResult extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        try {
            /* TODO output your page here. You may use following sample code. */
            request.setCharacterEncoding("UTF-8");
            
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです"); //直リンク防止
            }
            UserDataDTO udd = (UserDataDTO)session.getAttribute("resultData");
            
            //生年月日をdate型に変換
            int y=Integer.parseInt((String)request.getParameter("year"));
            int m=Integer.parseInt((String)request.getParameter("month"))-1;
            int d=Integer.parseInt((String)request.getParameter("day"));
            
            Calendar cal = Calendar.getInstance();
            cal.set(y,m,d);
            
            udd.setName(request.getParameter("name"));
            udd.setBirthday(cal.getTime());
            udd.setType(Integer.parseInt(request.getParameter("type")));
            udd.setTell(request.getParameter("tell"));
            udd.setComment(request.getParameter("comment"));
            
            ArrayList<String> chkList = udd.chkproperties(); //未入力チェック
            if(chkList!=null){
                //DTOオブジェクトにマッピング。DB専用のパラメータに変換
                udd.UD2DTOMapping(udd);

                //DBへデータの更新
                int rs=UserDataDAO .getInstance().update(udd);
                udd.setResult(rs); //更新結果を保存
            }
            //結果画面での表示用に入力パラメータ―をリクエストパラメータとして保持
            request.setAttribute("resultData", udd);
            request.getRequestDispatcher("/updateresult.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } finally {
            out.close();
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
