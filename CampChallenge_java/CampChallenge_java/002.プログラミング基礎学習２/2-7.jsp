<%-- 
    Document   : 2-7
    Created on : 2017/04/20, 13:01:11
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
            String a[]={"10","100","soeda","hayashi","20","118","END"};
            int i;
            for(i=0;i<a.length;i++) out.println(a[i]+" ");
        %>
    </body>
</html>
