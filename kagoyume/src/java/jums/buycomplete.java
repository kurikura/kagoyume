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
public class buycomplete extends HttpServlet {

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
        HttpSession hs = request.getSession();
        try{
            /* TODO output your page here. You may use following sample code. */
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){
                String err="不正なアクセスです。ブラウザの戻るや更新を押さないでください。";
                request.setAttribute("err",err);
                request.getRequestDispatcher("/err.jsp").forward(request, response);
            }
            //購入データをDTOにマッピング
            UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
            if(udd.getName()==null){ //未ログインの場合
                UserData ud= new UserData();
                ud.setUrl("cart");
                hs.setAttribute("ud",ud);
                request.getRequestDispatcher("login").forward(request, response);
            }else{
                String type_s=request.getParameter("select");
                if(type_s==null){
                    request.setAttribute("err","err");
                    request.getRequestDispatcher("/buyconfirm.jsp").forward(request, response);
                }
                int type=Integer.parseInt(type_s);
                yahooAPI cart=(yahooAPI)hs.getAttribute(Integer.toString(udd.getUserID()));
                ArrayList<yahooAPI> list=cart.getCart();
                if(!list.isEmpty()){
                    int num=0; //合計金額
                    int rs=0;
                    for(int i=0;i<list.size();i++){
                        yahooAPI api=(yahooAPI)list.get(i);
                        api.setType(type);
                        num+=api.getValue();
                        api.buyMapping(udd);
                        rs+=UserDataDAO.getInstance().buy(udd);
                    }
                    udd.setTotal(num);
                    rs+=UserDataDAO.getInstance().total(udd);
                    udd.setResult(rs-1);
                    hs.removeAttribute(Integer.toString(udd.getUserID())); //購入したらカートを削除する
                }
                hs.setAttribute("udd",udd);
                request.getRequestDispatcher("/buycomplete.jsp").forward(request, response);
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
