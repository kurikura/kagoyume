<%-- 
    Document   : registration
    Created on : 2017/05/23, 13:38:16
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
        <title>会員登録 -かごゆめ</title>
    </head>
    <body>
        <p>全ての項目にご記入ください</p>
        <form action="registrationconfirm" method="post">
            名前：<input type="text" name="name" value="<% if(ud.getName()!=null){out.print(ud.getName());} %>"><br>
            メールアドレス：<input type="text" name="mail" value="<% if(ud.getMail()!=null){out.print(ud.getMail());} %>"><br>
            パスワード：<input type="password" name="password"><br>
            住所：<input type="text" name="address" value="<% if(ud.getAddress()!=null){out.print(ud.getAddress());} %>"><br><br>
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="btnSubmit" value="確認画面へ">
        </form>
        <%= ud.top()%>
    </body>
</html>
