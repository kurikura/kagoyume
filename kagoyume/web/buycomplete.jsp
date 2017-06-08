<%-- 
    Document   : buycomplete
    Created on : 2017/05/23, 13:39:52
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
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入完了 -かごゆめ</title>
    </head>
    <body>
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
        <%= ud.header("/cart.jsp", udd,ud,hs)%>
        <h2>購入完了</h2>
        <%= udd.getResult() %>つの商品を購入しました。
        ありがとうございました。<br>
        <br>        
        <%= ud.top()%>
    </body>
</html>
