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
            String[][] data(){
              String[] data1={"0","三森","1994/08/18","千葉県"};
              String[] data2={"1","田中","1994/06/18",null};
              String[] data3={"2","鈴木","1894/08/18","北海道"};
              String[][] data={data1,data2,data3};
              return data;
            }
        %>
        <%
            String[][] pd=data();
            int i;
            for(i=0;i<pd.length;i++){
                out.println("<br>名前:"+pd[i][1]+"<br>");
                out.println("生年月日:"+pd[i][2]+"<br>");
                if(pd[i][3]!=null){
                    out.println("住所:"+pd[i][3]+"<br>");
                }else out.println("住所:continue<br>");
            }
            
        %>
    </body>
</html>
