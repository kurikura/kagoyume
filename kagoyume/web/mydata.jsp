<%-- 
    Document   : mydata
    Created on : 2017/05/23, 13:40:01
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*" %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    UserData ud=new UserData();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>会員情報-かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/top.jsp", udd,ud,hs)%>
        <h2>登録情報</h2>
        <ul>
            <li>名前：<%= udd.getName() %></li>
            <li>パスワード：非表示</li>
            <li>メールアドレス：<%= udd.getMail() %></li>
            <li>住所：<%= udd.getAddress() %></li>
            <li>購入金額：￥<%= udd.getTotal() %></li>
            <li>登録日時：<%= udd.getNewDate() %></li>
        </ul>
        <a href="myhistory">購入履歴</a> / <a href="myupdate">登録情報更新</a> / <a href="mydelete">退会する</a>
        <br>
        <%= ud.top()%>
    </body>
</html>
