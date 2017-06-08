<%-- 
    Document   : newjspadd
    Created on : 2017/05/23, 13:37:41
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*" %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    UserData ud= new UserData();
    yahooAPI api=(yahooAPI)hs.getAttribute("search");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごに追加 -かごゆめ</title>
    </head>
    <script language="JavaScript"><!--
            window.document.onkeydown = function ()
            {
                    if (event.keyCode === 116) 
                    {
                            event.keyCode = null;
                            return false;
                    }
            };
        //--></script>
    <body>
        <%= ud.header("/top.jsp", udd,ud,hs)%>
        <p>商品をかごに追加しました。</p>
        <a href="search?search=<%= api.getWord() %>">検索に戻る</a> / <%= ud.top()%> / <a href="cart">かごを確認する</a>
    </body>
</html>
