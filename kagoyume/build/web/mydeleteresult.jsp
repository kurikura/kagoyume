<%-- 
    Document   : mydeleteresult
    Created on : 2017/05/23, 13:41:15
    Author     : kurikura
--%>
<%@page import="jums.*"%>
<%
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("udd");
    UserData ud=new UserData();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除 -かごゆめ</title>
    </head>
    <body>
        <% if(udd.getResult()==1){ %>
            <p>削除が完了しました</p>
            <% hs.invalidate(); //セッション解放 %>
        <% }else{ %>
            <h1>データの更新に失敗しました</h1>
            <p>再度お試しください。</p>
        <% } %>
        <br>
        <%= ud.top()%>
    </body>
</html>
