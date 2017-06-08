/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;

/**
 *
 * @author kurikura
 */
public class cart extends HttpServlet {

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
            UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
            if(udd.getName()==null){ //未ログインの場合
                UserData ud= new UserData();
                ud.setUrl("cart");
                hs.setAttribute("ud",ud);
                request.getRequestDispatcher("login").forward(request, response);
            }else{
                yahooAPI cart=(yahooAPI)hs.getAttribute(Integer.toString(udd.getUserID()));
                if(cart!=null){
                    ArrayList<yahooAPI> list=cart.getCart();
                    if(!list.isEmpty()){ //かごが空でない場合
                        if((String)request.getParameter("no")!=null){ //削除ボタンが押されたとき
                            list.remove(Integer.parseInt(request.getParameter("no")));
                        }
                        for(int i=0;i<list.size();i++){ //かごの中身の最新情報を取得
                            yahooAPI api=(yahooAPI)list.get(i);
                            URL url=new URL(cart.getCODE_URL()+"?appid="+cart.getAPP_ID()+"&itemcode="+api.getID());
                            HttpURLConnection connection = null;
                            try {
                                connection=(HttpURLConnection)url.openConnection();
                                connection.connect(); //接続
                                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //接続成功
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                                    String rs=reader.readLine();
                                    reader.close();
                                    Map<String, Map<String, Object>> json =JSON.decode(rs); //取得したJSONをMap型に変換
                                    Map<String, Object> result=((Map<String, Object>)((Map<String, Map<String, Object>>)json.get("ResultSet").get("0")).get("Result").get("0"));
                                    api.setName(result.get("Name").toString()); //商品名
                                    Map<String, Object> img=(Map<String, Object>)result.get("Image"); //商品画像
                                    api.setImg(img.get("Small").toString());
                                    Map<String, Object> value=(Map<String, Object>)result.get("Price"); //税込みの値段
                                    api.setValue(Integer.valueOf(value.get("_value").toString()));
                                    list.set(i,api); //配列の中身を商品コードからオブジェクトに差し替え
                                }else{
                                    //接続失敗処理
                                }
                            }catch(IOException | JSONException e){
                                System.out.println(e);
                                String err="エラーが発生しました";
                                request.setAttribute("err",err);
                                request.getRequestDispatcher("/err.jsp").forward(request, response);
                            } finally {
                                if (connection != null) {
                                    connection.disconnect();
                                }
                            }
                        }
                    }
                    cart.setCart(list);
                }
                hs.setAttribute(Integer.toString(udd.getUserID()),cart);
                request.getRequestDispatcher("/cart.jsp").forward(request, response);
            }
        }catch(IOException | NumberFormatException | ServletException e){
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
