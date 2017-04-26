<%-- 
    Document   : class
    Created on : 2017/04/26, 14:07:43
    Author     : kurikura
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,java.text.*" %>
        <%
            Calendar cal=Calendar.getInstance();
            cal.set(2016,0,1,0,0,0);
            out.println(cal.getTime());
        %>
    </body>
</html>
