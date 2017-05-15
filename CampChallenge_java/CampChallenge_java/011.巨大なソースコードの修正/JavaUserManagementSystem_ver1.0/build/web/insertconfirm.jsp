<%@page import="javax.servlet.http.HttpSession" %>
<%@page import="jums.*" %>
<%
    HttpSession hs = request.getSession();
    UserDataBeans udb=(UserDataBeans)hs.getAttribute("UserDataBeans"); //セッションに保存したbeansを呼び出す
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS登録確認画面</title>
    </head>
    <body>
        <% if(!udb.getErr().equals("")){ %> 
        <h1>未入力の情報があります！</h1>
            <%= udb.getErr() %> <%-- 未入力の項目を表示 --%>
        <% }else{ %>
        <h1>登録確認</h1>
        名前:<%= udb.getName()%><br>
        生年月日:<%= udb.getYear()+"年"+udb.getMonth()+"月"+udb.getDay()+"日"%><br>
        種別:<%= udb.getType_st()%><br>
        電話番号:<%= udb.getTell()%><br>
        自己紹介:<%= udb.getCom()%><br><br>
        上記の内容で登録します。よろしいですか？
        <form action="insertresult" method="POST">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>"> <%-- 次ページに飛ぶための乱数生成 --%>
            <input type="submit" name="yes" value="はい">
        </form>
            <% } %>
        <form action="insert" method="POST">
            <input type="submit" name="no" value="登録画面に戻る">
        </form>
    </body>
</html>
