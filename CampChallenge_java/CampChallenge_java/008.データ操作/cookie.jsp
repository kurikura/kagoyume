<%-- 
    Document   : cookie
    Created on : 2017/04/27, 15:57:47
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
        <%@page import="javax.servlet.http.Cookie,java.util.Date" %>
        <%
            Date d=new Date();
            Cookie c=new Cookie("Lastlogin",d.toString());
            response.addCookie(c);
            
            Cookie cs[]=request.getCookies();
            if(cs!=null){
                for(int i=0;i<cs.length;i++){
                    if(cs[i].getName().equals("Lastlogin")){
                        out.println("前回のログイン:"+cs[i].getValue());
                    }
                }
            }
        %>
    </body>
</html>
