/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jack.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kurikura
 */
@WebServlet(name = "blackjack", urlPatterns = {"/blackjack"})
public class blackjack extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
   /* private static final long serialVersionUID=1L;
    public blackjack(){
        super();
    }*/
    
    abstract class Human{
        ArrayList<Integer> myCard=new ArrayList<>();
        abstract public int open();      
        abstract public void setCard(ArrayList<Integer> n);
        abstract public boolean checkSum();
    }
    
    class Dealer extends Human{
        ArrayList<Integer> cards=new ArrayList<>(); //山札
        private int i,sum,num;
        {
            for(i=1;i<=52;i++){ //山札を配列に入れる
                num=(i%13)+1; //13で割ったあまり+1
                if(num>10) num=10;//J,Q,Kは10として扱う
                cards.add(num);
                System.out.print(num+" ");
            }
            Collections.shuffle(cards);
        }
            
        public ArrayList deal(){
            ArrayList<Integer> deal=new ArrayList<>();
            deal.add(cards.get(0));
            deal.add(cards.get(1));
            cards.remove(0); //カードの重複を避ける
            cards.remove(1);          
            return deal;
        }
        
        public ArrayList hit(){ //カードを1枚引く
            ArrayList<Integer> hit=new ArrayList<>();
            hit.add(cards.get(0));
            cards.remove(0); //カードの重複を避ける
            return hit;
        }
        
        @Override
        public void setCard(ArrayList<Integer> n){ //引いたカードをセットする
          for(i=0;i<n.size();i++){
            myCard.add(n.get(i));
          }
        }
        
        @Override
        public boolean checkSum(){
            //カードの合計が17以下ならtrue
            return open()<=17;
        }
        
        @Override
        public int open(){
            sum=0;
            for(i=0;i<myCard.size();i++){
                sum+=myCard.get(i);
            }
            return sum;
        }
    }
    
    class User extends Human{
        int i,sum;
        @Override
        public void setCard(ArrayList<Integer> n){
          for(i=0;i<n.size();i++){
            myCard.add(n.get(i));
          }
        }
        
        @Override
        public boolean checkSum(){
            //カードの合計が17以下ならtrue
            return open()<=17;
        }
        
        @Override
        public int open(){
            sum=0;
            for(i=0;i<myCard.size();i++){
                sum+=myCard.get(i);
            }
            return sum;
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Dealer dealer=new Dealer();
            User user=new User();
            
            //まずはディーラーのカードをセット
            dealer.setCard(dealer.deal());
            dealer.checkSum();
            
            while(dealer.checkSum()==true){ //基準値に達するまでループ
                dealer.setCard(dealer.hit());
                dealer.open();
            }
            
            user.setCard(dealer.deal());
            while(user.checkSum()==true){
                user.setCard(dealer.hit());
                user.open();
            }
            out.println("<!DOCTYPE html");
            out.println("<html>");
            out.println("<head><title>BlackJack</title></head>");
            out.println("<body>");
            
            out.println("ディーラー:"+dealer.myCard+" 合計:"+dealer.open()+"<br>");
            out.println("ユーザー:"+user.myCard+" 合計:"+user.open()+"<br>");
            
            int uo=user.open();
            int deo=dealer.open();
            if(uo>21) uo=0; //バーストは0にする
            if(deo>21) deo=0;
            
            if(uo==deo){
                out.println("draw"); //引き分け
            }else if(uo==21){
                out.println("BlackJack!!"); //BJ
            }else if(uo>deo){
                out.println("win!");
            }else{
                out.println("lose…"); 
            }
            out.println("</body>");
            out.println("</html>");
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
