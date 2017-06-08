<%-- 
    Document   : err
    Created on : 2017/06/08, 10:14:54
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
        <title>エラー -かごゆめ</title>
    </head>
    <body>
        <h2>エラーが発生しました</h2>
        <%= request.getAttribute("err")%><br><br>
        <%= ud.top()%>
    </body>
</html>
