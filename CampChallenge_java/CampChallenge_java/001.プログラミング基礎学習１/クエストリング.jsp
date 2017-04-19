<%-- 
    Document   : クエストリング
    Created on : 2017/04/19, 14:32:29
    Author     : kurikura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>クエストリング</title>
    </head>
    <body>
        <%
            String num1 = request.getParameter("data1");
            String num2 = request.getParameter("data2");
            String num3 = request.getParameter("data3");
            
            int n1= Integer.parseInt(num1); //String型をint型に変換
            int n2= Integer.parseInt(num2);
            int n3= Integer.parseInt(num3);
            
            if(n3==0) out.println("商品種別:雑貨");
            if(n3==1) out.println("商品種別:生鮮食品");
            if(n3==2) out.println("商品種別:その他");
            
            out.print("総額:"+num1+"円");
            out.println(" 1個辺りの値段:"+(n1/n2)+"円");
            
            int pt=0; //ポイント
            
            if(n1>=3000){
                if(n1>=5000){
                    pt=n1*5/100;
                }
                else{
                    pt=n1*3/100;
                }
                out.println("付与ポイント:"+pt+"ポイント");
            }
        %>
    </body>
</html>
