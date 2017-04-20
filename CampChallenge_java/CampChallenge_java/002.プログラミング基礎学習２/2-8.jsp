<%-- 
    Document   : 2-8
    Created on : 2017/04/20, 13:18:08
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
        <%@ page import="java.util.HashMap" %>
        <%
            
            HashMap<String,String>hashmap=new HashMap<String,String>();
            hashmap.put("1","AAA");
            hashmap.put("hello","world");
            hashmap.put("soeda","33");
            hashmap.put("20", "20");
            
            out.print(hashmap.get("1"));
        %>            
    </body>
</html>
