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
        <%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*,java.util.*,java.text.*" %>
        <%
            Calendar c = Calendar.getInstance();
            c.set(2016,10,4,10,0,0);
            Timestamp sample1 =new Timestamp(c.getTime().getTime());
            out.println(sample1);
        %>
    </body>
</html>
