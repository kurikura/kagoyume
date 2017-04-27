<%-- 
    Document   : session
    Created on : 2017/04/27, 16:08:07
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
        <%@page import="javax.servlet.http.HttpSession,java.util.Date" %>
        <%
            Date d=new Date();
            HttpSession hs=request.getSession(true);
            out.println("前回のログイン:"+hs.getAttribute("Lastlogin"));
            hs.setAttribute("Lastlogin",d.toString());
            
            
        %>    
    </body>
</html>
