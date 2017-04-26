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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date d1,d2;
            
            d1=sdf.parse("2015/01/01 00:00:00");
            d2=sdf.parse("2015/12/31 23:59:59");
            
            long sample1=d1.getTime();
            long sample2=d2.getTime();
            
            long res=(sample2-sample1);
            out.println(d1+"<br>");
            out.println(d2+"<br>");
            out.println("差:"+res);
        %>
    </body>
</html>
