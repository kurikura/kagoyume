<%-- 
    Document   : 2-3
    Created on : 2017/04/20, 11:47:22
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
            int num=8;
            int i;
            for(i=0;i<20;i++){
                num=num*8;
                out.println(num);
            }
        %>
    </body>
</html>
