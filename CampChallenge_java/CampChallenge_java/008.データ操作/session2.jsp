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
            HttpSession hs=request.getSession(true);
            if(hs.getAttribute("gender")!=null){
            out.println("前回の名前:"+hs.getAttribute("name")+"<br>"); 
            out.println("前回の性別:"+hs.getAttribute("gender")+"<br>"); 
            out.println("前回の趣味:"+hs.getAttribute("syumi")+"<br>"); 
            }
        %>    
        <form action="./data" method="post">
            名前 <input type="text" name="name"><br>
            性別 <input type="radio" name="gender" value="man">男
            <input type="radio" name="gender" value="woman">女<br>
            趣味<textarea name="syumi" rows="3" cols="50" wrap="hard"></textarea><br>
            <input type="submit" value="送信">
        </form>
    </body>
</html>
