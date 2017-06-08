<%-- 
    Document   : registrationconfirm
    Created on : 2017/05/23, 13:38:37
    Author     : kurikura
--%>
<%@page import="jums.UserData"
        import="java.util.ArrayList"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("ud");
    ArrayList<String> chkList=ud.chk();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録確認 -かごゆめ</title>
    </head>
    <body>
        <% if(chkList.size()==0){ %>
            <p>名前：<%= ud.getName()%></p>
            <p>メールアドレス：<%= ud.getMail()%></p>
            <p>パスワード：<%= ud.getPassword()%></p>
            <p>住所：<%= ud.getAddress()%></p>
            <p>上記の内容で登録します。よろしいですか？</p>
            <form action="registrationcomplete" method="POST">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="yes" value="はい">
            </form>
        <% }else{ %>
            <h2>未記入の項目があります</h2>
            <ul>
                <% for(String msg : chkList){ %>
                    <li><%= msg %>を入力してください</li>
                <% } %>
            </ul>
        <% } %>
        <form action="registration" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
    </body>
</html>
