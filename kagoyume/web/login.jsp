<%-- 
    Document   : login
    Created on : 2017/05/23, 13:37:59
    Author     : kurikura
--%>
<%@page import="jums.*" %>
<%
    HttpSession hs=request.getSession();
    UserData ud=new UserData();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログイン -かごゆめ</title>
    </head>
    <body>
        <% if(request.getAttribute("err").equals("nodata")){ %><%--ログイン失敗時のエラー文表示--%>
        ログインに失敗しました。<br>
        <% }else if(request.getAttribute("err").equals("delete")){ %>
        このアカウントは削除されています。<br>
        <% }else if(request.getAttribute("err").equals("null")){ %>
        未入力の項目があります。<br>
        <% } %>
        <form action="loginchk" method="post">
            メールアドレス： <input type="text" name="mail"><br>
            パスワード: <input type="password" name="password"><br>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" value="ログイン">
        </form>
        <br>
        <form action="registration" method="post">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" value="会員登録はこちら">
        </form>
        <%= ud.top()%>
    </body>
</html>
