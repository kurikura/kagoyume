<%-- 
    Document   : mydelete
    Created on : 2017/05/23, 13:41:02
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*" %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除確認 -かごゆめ</title>
    </head>
    <body>
        <ul>
            <li>名前：<%= udd.getName() %></li>
            <li>パスワード：非表示</li>
            <li>メールアドレス：<%= udd.getMail() %></li>
            <li>住所：<%= udd.getAddress() %></li>
            <li>購入金額：￥<%= udd.getTotal() %></li>
            <li>登録日時：<%= udd.getNewDate() %></li>
        </ul>
        <p>このユーザーを本当に削除しますか？</p>
        <a href="mydeleteresult">はい</a>
        <br><br>
        <a href="top.jsp">いいえ</a>
    </body>
</html>
