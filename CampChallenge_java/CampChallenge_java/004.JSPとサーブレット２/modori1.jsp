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
            boolean profile(){
             boolean r=true;
             String user[]={"三森凌太","1994/08/18","千葉県出身です。"};
             return r;            
            }
        %>
        <%
            int i,j;
            boolean r=profile();
            if(r==true) out.println("この処理は正しく実行されました");
            else out.println("この処理は正しく実行されませんでした");
        %>
    </body>
</html>
