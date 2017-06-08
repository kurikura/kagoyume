<%-- 
    Document   : myhistory
    Created on : 2017/05/23, 13:40:15
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*"
        import="java.util.*"%>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    UserData ud= new UserData();
    ArrayList<UserDataDTO> history=(ArrayList)request.getAttribute("history");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入履歴 -かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/myhistory.jsp", udd,ud,hs)%>
        <h2>購入履歴</h2>
        <table border=0>
            <tr>
                <th nowrap>商品ID</th>
                <th nowrap>購入日時</th>
            </tr>
                <% for(int i=0;i<history.size();i++){ 
                    udd=(UserDataDTO)history.get(i);
                %>
                    <tr>
                        <td nowrap><%= udd.getBuyID()%></td>
                        <td nowrap><%= udd.getBuyDate()%></td>
                    </tr>
                <% } %>
        </table>
        <br>
        <a href="mydata.jsp">登録情報に戻る</a> / <%= ud.top()%>
    </body>
</html>
