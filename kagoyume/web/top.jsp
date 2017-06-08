<%-- 
    Document   : index
    Created on : 2017/05/23, 13:36:35
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*" %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    if(udd==null){
        udd=new UserDataDTO();
    }
    UserData ud= new UserData();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>top -かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/top.jsp", udd,ud,hs)%>
        <h1>かごゆめ</h1>
        <p>このサイトでは金銭取引は発生しません。好きなものを何でもたくさん購入できる(気分になれる)ECサイトです。</p>
        <form action="search" method="get">
            <input type="text" name="search" size="30"> <button type="submit" class="btn btn-default">検索</button>
        </form>
    </body>
</html>
