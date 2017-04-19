<%-- 
    Document   : 1-5
    Created on : 2017/04/19, 14:26:50
    Author     : kurikura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>1-5</title>
    </head>
    <body>
        <%
            int x=2;
            if(x==1) out.println("1です!");
            else if(x==2) out.println("プログラムキャンプ!");
            else out.println("その他です!");
        %>
    </body>
</html>
