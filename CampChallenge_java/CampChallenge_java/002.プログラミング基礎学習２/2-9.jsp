<%-- 
    Document   : 2-9.
    Created on : 2017/04/20, 13:26:32
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
            String num = request.getParameter("data");
            int n= Integer.parseInt(num); //String型をint型に変換
            out.println("元の値:"+n);
            String a="",b=""; //aが1桁の素因数、bがその他
            
            int i=2;
            while(i<=n){
                if(n%i==0){
                    if(i<10) a+=i+",";
                    else b+=i+",";
                    n=n/i;
                    i=2;
                }else i++;
            }
            out.println("1桁の素因数:"+a);
            out.println("その他:"+b);
        %>
    </body>
</html>
