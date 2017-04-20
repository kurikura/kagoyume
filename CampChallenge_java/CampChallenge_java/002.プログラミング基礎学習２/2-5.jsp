<%-- 
    Document   : 2-5
    Created on : 2017/04/20, 11:58:30
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
            int i,j=0;
            for(i=0;i<=100;i++)j+=i;
            out.println(j);
        %>
    </body>
</html>
