<%-- 
    Document   : hikisuu1
    Created on : 2017/04/20, 15:53:34
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
            String decision(int num){
              String a="";
              if(num%2==0) a="偶数";
              else a="奇数";
              return a;
            }
        %>
        <%
            int number=10;
            String result=decision(number);
            out.println("入力値:"+number);
            out.println(" "+result);
        %>
    </body>
</html>
