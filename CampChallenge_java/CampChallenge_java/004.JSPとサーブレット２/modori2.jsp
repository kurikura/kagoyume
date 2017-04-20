<%-- 
    Document   : modori2
    Created on : 2017/04/20, 17:02:24
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
        <%!
            String[] data(){
              String[] data={"0","三森","1994/08/18","千葉県"};
              return data;
            }
        %>
        <%
            String[] pd=data();
            out.println("名前:"+pd[1]+"<br>");
            out.println("生年月日:"+pd[2]+"<br>");
            out.println("住所:"+pd[3]+"<br>");
        %>
    </body>
</html>
