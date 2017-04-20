<%-- 
    Document   : profile
    Created on : 2017/04/20, 15:38:23
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
            String[] profile(){
             String user[]={"三森凌太","1994/08/18","千葉県出身です。"};
             return user;            
            }
        %>
        <%
            int i,j;
            for(i=0;i<10;i++){
                String[] myprofile=profile();
                for(j=0;j<myprofile.length;j++){
                    out.println(myprofile[j]+"<br>");
                }
            }
        %>
    </body>
</html>
