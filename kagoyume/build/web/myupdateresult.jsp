<%-- 
    Document   : myupdateresult
    Created on : 2017/05/23, 13:40:44
    Author     : kurikura
--%>
<%@page import="jums.*"
        import="java.util.ArrayList"%>
<%
    HttpSession hs = request.getSession();
    UserData ud = (UserData)hs.getAttribute("ud");
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("udd");
    ArrayList<String> chkList=ud.chk();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>情報更新 -かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/mydata.jsp", udd,ud,hs)%>
        <% if(chkList.size()==0){ %>
            <% if(udd.getResult()==1){ %>
                <h2>更新完了</h2>
                <p>名前：<%= udd.getName()%></p>
                <p>メールアドレス：<%= udd.getMail()%></p>
                <p>パスワード：<%= udd.getPassword()%></p>
                <p>住所：<%= udd.getAddress()%></p>
                <p>上記の内容で更新しました。</p>
            <% }else{ %>
                <h1>データの更新に失敗しました</h1>
                <p>再度お試しください。</p>
            <% } %>
        <% }else{ %>
            <h2>未記入の項目があります</h2>
            <ul>
                <% for(String msg : chkList){ %>
                    <li><%= msg %>を入力してください</li>
                <% } %>
            </ul>
            <br>
            <form action="myupdate" method="POST">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
                <input type="submit" name="no" value="戻る">
            </form>
            <br><br>
        <% } %>
        <a href="mydata">登録情報一覧に戻る</a> / <%= ud.top()%>
    </body>
</html>
