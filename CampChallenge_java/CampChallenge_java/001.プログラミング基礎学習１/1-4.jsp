<%-- 
    Document   : 1-4
    Created on : 2017/04/19, 14:14:24
    Author     : kurikura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>四則演算</title>
    </head>
    <body>
        <%
            int x=15;
            final int y=10;
            out.println("x+y="+x+y);
            out.println("x-y="+(x-y));
            out.println("x*y="+x*y);
            out.println("x/y="+x/y);
        %>
    </body>
</html>
