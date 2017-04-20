<%-- 
    Document   : 2-4
    Created on : 2017/04/20, 11:55:44
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
            String msg="";
            int i;
            for(i=0;i<30;i++){
                msg+="A";
            }
            out.println(msg);
        %>
    </body>
</html>
