<%-- 
    Document   : file
    Created on : 2017/04/26, 16:51:38
    Author     : kurikura
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page contentType="text/html" pageEncoding="UTF-8" import="java.io.*" %>
        <%
            File file = new File("d:¥¥test.txt");
            FileReader filereader = new FileReader(file);
            int ch = filereader.read();
            while(ch != -1){
                out.print((char)ch);
                ch = filereader.read();
            }
        %>
    </body>
</html>