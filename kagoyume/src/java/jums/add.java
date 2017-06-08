/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.IOException;
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
public class add extends HttpServlet {

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
        try{
            yahooAPI api=(yahooAPI)hs.getAttribute("item");
            UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
            String accesschk = request.getParameter("ac");
            if(api.getID()==null || accesschk ==null || (Integer)hs.getAttribute("ac")!=Integer.parseInt(accesschk)){ //直リンク防止
                String err="不正なアクセスです。ブラウザの戻るや更新を押さないでください。";
                request.setAttribute("err",err);
                request.getRequestDispatcher("/err.jsp").forward(request, response);
            }else if(udd==null || udd.getName()==null){ //未ログインの場合
                UserData ud= new UserData();
                ud.setUrl("add");
                hs.setAttribute("ud",ud);
                request.getRequestDispatcher("login").forward(request, response);
            }else{ //かごに商品を追加
                yahooAPI cart=(yahooAPI)hs.getAttribute(Integer.toString(udd.getUserID()));
                if(cart==null){
                    cart=new yahooAPI();
                }
                ArrayList<yahooAPI> list=cart.getCart();
                if(list==null){
                    list=new ArrayList<>();
                }
                list.add(api);
                cart.setCart(list);
                hs.setAttribute(Integer.toString(udd.getUserID()),cart); //各ユーザのかごに保存する
                request.getRequestDispatcher("/add.jsp").forward(request, response);
            }
        }catch(IOException | ServletException e){
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
