<%-- 
    Document   : buyconfirm
    Created on : 2017/05/23, 13:39:38
    Author     : kurikura
--%>
<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.*"
        %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    UserData ud= new UserData();
    yahooAPI cart=(yahooAPI)hs.getAttribute(Integer.toString(udd.getUserID()));
    ArrayList cartList=cart.getCart();
    int num=0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認 -かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/buyconfirm.jsp", udd,ud,hs)%>
        <p style="color: red">
            <% if(request.getAttribute("err").equals("err")){ %>
            発送方法を選択してください。<br>
        <% } %></p>
        <h2>以下の商品を購入します</h2>
        <% for(int i=cartList.size()-1;i>=0;i--){
            yahooAPI api=(yahooAPI)cartList.get(i);
            num+=api.getValue();
        %>
        <p>
            <img src="<%= api.getImg() %>" style="float: left;margin-right:10px;">
            <%= api.getName() %><br>
            <%= api.getValue() %>円<br>
        </p><br>
        <% } %>
        <p>
            合計金額：<%= num %>円
        </p>
        <p>発送方法を選択してください。</p>
        <form action="buycomplete" method="post">
            <input type="radio" name="select" value="1">ヤマト宅急便 /
            <input type="radio" name="select" value="2">佐川急便 /
            <input type="radio" name="select" value="3">日本郵便 
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="submit" value="購入">
        </form>
        <form action="cart" method="post">
            <input type="submit" name="submit" value="カートに戻る">
        </form>
        <%= ud.top()%>
    </body>
</html>
