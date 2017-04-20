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
            String[] data(int id){
              String[] data1={"0","三森","1994/08/18","千葉県"};
              String[] data2={"1","田中","1994/06/18","東京都"};
              String[] data3={"2","鈴木","1894/08/18","北海道"};
              switch(id){
                  case 0:
                      return data1;
                  case 1:
                      return data2;
                  case 2:
                      return data3;                      
              }
              return null;
            }
        %>
        <%
            String[] pd=data(2);
            out.println("名前:"+pd[1]+"<br>");
            out.println("生年月日:"+pd[2]+"<br>");
            out.println("住所:"+pd[3]+"<br>");
        %>
    </body>
</html>
