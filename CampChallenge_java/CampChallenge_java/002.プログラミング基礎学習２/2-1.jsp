<%-- 
    Document   : 2-1
    Created on : 2017/04/19, 15:42:06
    Author     : kurikura
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>switch文1</title>
    </head>
    <body>
        <%
            int x=3;
            String msg="";
            switch(x){
                case 1:
                    msg="one";
                    break;
                case 2:
                    msg="two";
                    break;
                default:
                    msg="想定外";
                    break;
            }
            out.println(msg);
        %>
            
    </body>
</html>
