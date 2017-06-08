<%-- 
    Document   : registrationcomplete
    Created on : 2017/05/23, 13:39:14
    Author     : kurikura
--%>
<%@page import="jums.*" %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    UserData ud=new UserData();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録完了 -かごゆめ</title>
    </head>
    <body>
        <script language="JavaScript"><!--
            window.document.onkeydown = function ()
            {
                    if (event.keyCode === 116) 
                    {
                            event.keyCode = null;
                            return false;
                    }
            };
        //--></script>
        <% if(udd.getResult()==1){ %>
            <h2>登録完了</h2>
            <p>名前：<%= udd.getName()%></p>
            <p>メールアドレス：<%= udd.getMail()%></p>
            <p>パスワード：<%= udd.getPassword()%></p>
            <p>住所：<%= udd.getAddress()%></p>
            <p>上記の内容で登録しました。</p>
        <% }else{ %>
            <h1>データの登録に失敗しました</h1>
            <p>再度お試しください。</p>
        <% } %>
        <%= ud.top()%>
    </body>
</html>
