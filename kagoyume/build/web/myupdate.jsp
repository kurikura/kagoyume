<%-- 
    Document   : myupdate
    Created on : 2017/05/23, 13:40:29
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.UserData" %>
<%
    HttpSession hs=request.getSession();
    UserData ud=(UserData)hs.getAttribute("ud");
    if(ud==null){
        ud=new UserData();
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録情報更新 -かごゆめ</title>
    </head>
    <body>
        <p>全ての項目にご記入ください</p>
        <form action="myupdateresult" method="post">
            名前：<input type="text" name="name" value="<%= ud.getName() %>"><br>
            メールアドレス：<input type="text" name="mail" value="<%= ud.getMail() %>"><br>
            パスワード：<input type="password" name="password" value="<%= ud.getPassword() %>"><br>
            住所：<input type="text" name="address" value="<%= ud.getAddress() %>"><br><br>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="更新">
        </form>
        <%= ud.top()%>
    </body>
</html>
