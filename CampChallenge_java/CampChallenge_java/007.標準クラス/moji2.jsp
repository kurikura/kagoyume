<%-- 
    Document   : moji
    Created on : 2017/04/26, 15:33:44
    Author     : kurikura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String moji="karasummori@yahoo.co.jp";
            out.println(moji.substring(moji.indexOf("@")+1));
        %>
    </body>
</html>
