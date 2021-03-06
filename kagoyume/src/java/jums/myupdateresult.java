/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kurikura
 */
public class myupdateresult extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        HttpSession hs=request.getSession();
        try {
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                String err="不正なアクセスです。ブラウザの戻るや更新を押さないでください。";
                request.setAttribute("err",err);
                request.getRequestDispatcher("/err.jsp").forward(request, response);
            }
            
            //フォームからの入力を取得して、JavaBeansに格納
            UserData ud=new UserData();
            ud.setName(request.getParameter("name"));
            ud.setMail(request.getParameter("mail"));
            ud.setPassword(request.getParameter("password"));
            ud.setAddress(request.getParameter("address"));
            
            //UserDataのデータをDTOにマッピング
            UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
            ud.regiMapping(udd);
            ArrayList<String> chkList=ud.chk();
            if(chkList.isEmpty()){ //未記入の項目なしの場合
                int rs=UserDataDAO.getInstance().update(udd);
                udd.setResult(rs);
                hs.setAttribute("udd",udd);
            }
            hs.setAttribute("ud", ud);
            request.getRequestDispatcher("/myupdateresult.jsp").forward(request, response);
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
