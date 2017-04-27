<%-- 
    Document   : class5
    Created on : 2017/04/27, 10:24:05
    Author     : kurikura
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@page contentType="text/html" pageEncoding="UTF-8" import="java.io.*,java.util.*,java.text.*" %>
        <%
            File file = new File("d:test.txt");
            FileWriter filewriter = new FileWriter(file);
            Calendar start = Calendar.getInstance();
            filewriter.write(start.getTime().toString() + " 開始<br>");

            double num = 144.85;
            out.println("数字:" + num + "<br>");
            out.println("平方根" + Math.sqrt(num) + "<br>");
            out.println("乱数" + Math.random() * num + "<br>");
            out.println("四捨五入" + Math.rint(num) + "<br>");
            out.println("切り上げ" + Math.ceil(num) + "<br>");
            out.println("サイン" + Math.sin(Math.toRadians(num)) + "<br>");

            Calendar end = Calendar.getInstance();
            filewriter.write(end.getTime().toString() + " 終了");
            filewriter.close();

            File fileread = new File("d:test.txt");
            FileReader filereader = new FileReader(fileread);
            int ch = filereader.read();
            out.println("ログ出力");
            while (ch != -1) {
                out.print((char) ch);
                ch = filereader.read();
            }
            filereader.close();
        %>

    </body>
</html>
