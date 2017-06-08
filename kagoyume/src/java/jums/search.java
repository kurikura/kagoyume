/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jums;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.arnx.jsonic.*;

/**
 *
 * @author kurikura
 */
public class search extends HttpServlet {

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
        HttpSession hs=request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String word=request.getParameter("search"); //検索窓から取得
            yahooAPI api=new yahooAPI();
            if(word!=null){
                api.setWord(word);
                URL url=new URL(api.getBASE_URL()+"?appid="+api.getAPP_ID()+"&query="+URLEncoder.encode(word, "UTF-8")+"&hits="+10);
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
                        int total=Integer.valueOf((String) json.get("ResultSet").get("totalResultsAvailable"));//検索件数
                        if(total!=0){ //商品が見つかった時
                            ArrayList<yahooAPI>list=new ArrayList<>();
                            for(int i=0;i<10;i++){
                                yahooAPI stocklist=new yahooAPI();
                                Map<String, Object> result = 
                                        ((Map<String, Object>)((Map<String, Map<String, Object>>)json.get("ResultSet").get("0")).get("Result").get(String.valueOf(i)));
                                if(result==null) break; //商品リストの最後まで行ったらfor文を抜ける
                                stocklist.setName(result.get("Name").toString()); //商品名
                                stocklist.setID(result.get("Code").toString()); //商品URL
                                Map<String, Object> img=(Map<String, Object>)result.get("Image"); //商品画像
                                stocklist.setImg(img.get("Medium").toString());
                                Map<String, Object> value=(Map<String, Object>)result.get("PriceLabel"); //税込みの値段
                                stocklist.setValue(Integer.valueOf(value.get("DefaultPrice").toString()));
                                list.add(stocklist);
                            }
                            api.setTotal(total);
                            api.setList(list);
                        }                        
                    }
                }catch(IOException | JSONException e){
                    request.setAttribute("err",e);
                    request.getRequestDispatcher("/err.jsp").forward(request, response);
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
            hs.setAttribute("search",api);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        }catch(UnsupportedEncodingException | MalformedURLException e){
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
