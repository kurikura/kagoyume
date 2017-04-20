<%-- 
    Document   : hikisuu2
    Created on : 2017/04/20, 16:45:01
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
            int calc(int a,int b,boolean type){
              a=a*b;
              if(type==true){
                  a=a*a;
              }
              return a;
            }
         %>
         <%
             int a=10,result;
             result=calc(a,5,false);
             out.println(result);
        %>
    </body>
</html>
