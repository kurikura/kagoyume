<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jums.*" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)request.getAttribute("delete");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>削除結果画面</title>
    </head>
    <body>
    <% if(udd.getResult()==1){ %>
    <h1>削除確認</h1>
    削除しました。<br>
    <% }else{%>
    <h1>削除に失敗しました</h1>
    <% }%>
    <form action="SearchResult" method="POST">
        <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
        <input type="submit" name="btnSubmit" value="検索結果に戻る">
    </form>
    </body>
    <%=jh.home()%>
</html>
