<%-- 
    Document   : datajsp
    Created on : 2017/04/27, 15:19:37
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
            String name=request.getParameter("name");
            String gender=request.getParameter("gender");
            String syumi=request.getParameter("syumi");
            
            out.println("送信しました<br>");
            out.println("名前:"+name+"<br>");
            out.println("性別:"+gender+"<br>");
            out.println("趣味:"+syumi);
        %>
    </body>
</html>
