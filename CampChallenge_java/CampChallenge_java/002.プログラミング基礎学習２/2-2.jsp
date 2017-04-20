<%-- 
    Document   : 2-2
    Created on : 2017/04/19, 17:06:21
    Author     : kurikura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>switch2-2</title>
    </head>
    <body>
        <%
            String msg="A";
            String a="";
            switch(msg){
                case "A":
                    a="英語";
                    break;
                case "あ":
                    a="日本語";
                    break;
            }
            out.println(a);
        %>
    </body>
</html>
