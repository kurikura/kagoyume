<%-- 
    Document   : search
    Created on : 2017/05/23, 13:37:24
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*"
        import="java.util.ArrayList"%>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    if(udd==null){
        udd=new UserDataDTO();
    }
    UserData ud= new UserData();
    yahooAPI api=(yahooAPI)hs.getAttribute("search");
    ArrayList<yahooAPI> list=api.getList();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= api.getWord() %>の検索結果 -かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/search.jsp", udd,ud,hs)%>
        
        <form action="search" method="get">
            <input type="text" name="search" size="30" value="<%= api.getWord() %>"> <input type="submit" value="検索">
        </form>
        <br>
        <% if(!api.getWord().equals("")){ %>
            検索件数：<%= api.getTotal() %>件<br>
            上位10件まで表示します<br>
            <%  if(api.getTotal()>0){
                for(int i=0;i<list.size();i++){ 
                api=(yahooAPI)list.get(i);
            %>
            <p><img src="<%= api.getImg() %>" style="float: left;margin-right:10px;">
                <a href="item?id=<%= api.getID()%>"><%= api.getName() %></a><br>
                <%= api.getValue() %>円
                <br style="clear: both;">
            ------------------------------------------------------------------------<br>
            </p>
            <% } %>
            <% } %>
        <% } %>
        <br>
        <%= ud.top()%>
    </body>
</html>
