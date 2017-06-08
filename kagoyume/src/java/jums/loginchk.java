/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kurikura
 */
public class loginchk extends HttpServlet {

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
        HttpSession hs = request.getSession();
        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                String err="不正なアクセスです。ブラウザの戻るや更新を押さないでください。";
                request.setAttribute("err",err);
                request.getRequestDispatcher("/err.jsp").forward(request, response);
            }
            
            //フォームからの入力を取得して、JavaBeansに格納
            UserData ud=(UserData)hs.getAttribute("ud");
            ud.setMail(request.getParameter("mail"));
            ud.setPassword(request.getParameter("password"));
            if(ud.getMail().equals("") || ud.getPassword().equals("")){ //未入力チェック
                request.setAttribute("err", "null");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }else{
                UserDataDTO udd=new UserDataDTO();
                ud.loginMapping(udd);

                UserDataDAO.getInstance().login(udd);
                hs.setAttribute("udd", udd);
                if(udd.getDeleteFlg()==1){ //ユーザ削除済みの場合
                    System.out.println("削除");
                    request.setAttribute("err", "delete");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }else if(udd.getName()!=null){
                    System.out.println("成功");
                    request.getRequestDispatcher(ud.getUrl()).forward(request, response); 
                }else{ //ログインに失敗した場合
                    System.out.println("失敗");
                    request.setAttribute("err", "nodata");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }
        }catch(IOException | NumberFormatException | SQLException | ServletException e){
            System.out.println(e);
            String err="エラーが発生しました";
            request.setAttribute("err",err);
            request.getRequestDispatcher("/err.jsp").forward(request, response);
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
