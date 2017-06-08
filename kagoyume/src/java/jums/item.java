package jums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
public class item extends HttpServlet {

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
        try{
            /* TODO output your page here. You may use following sample code. */
            String id=request.getParameter("id"); //URLからIDを取得
            yahooAPI api=new yahooAPI();
            api.setID(id);
            if("".equals(id)){
                String err="不正なアクセスです。ブラウザの戻るや更新を押さないでください。";
                request.setAttribute("err",err);
                request.getRequestDispatcher("/err.jsp").forward(request, response);
            }else{
                URL url=new URL(api.getCODE_URL()+"?appid="+api.getAPP_ID()+"&itemcode="+id);
                HttpURLConnection connection = null;
                try {
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect(); //接続
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) { //接続成功
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
                        String rs=reader.readLine();
                        reader.close();
                        Map<String, Map<String, Object>> json =JSON.decode(rs); //取得したJSONをMap型に変換
                        Map<String, Object> result = 
                                        ((Map<String, Object>)((Map<String, Map<String, Object>>)json.get("ResultSet").get("0")).get("Result").get("0"));
                        api.setName(result.get("Name").toString()); //商品名
                        api.setHeadline(result.get("Headline").toString()); //キャッチコピー
                        Map<String, Object> img=(Map<String, Object>)result.get("Image"); //商品画像
                        api.setImg(img.get("Small").toString());
                        Map<String, Object> value=(Map<String, Object>)result.get("Price"); //税込みの値段
                        api.setValue(Integer.valueOf(value.get("_value").toString()));  
                    }else{
                        String err="読み込みに失敗しました。再度お試しください。";
                        request.setAttribute("err",err);
                        request.getRequestDispatcher("/err.jsp").forward(request, response);
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
            hs.setAttribute("item",api);
            hs.setAttribute("ac", (int) (Math.random() * 1000));
            request.getRequestDispatcher("/item.jsp").forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
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
