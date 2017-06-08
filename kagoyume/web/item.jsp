<%-- 
    Document   : item
    Created on : 2017/05/23, 13:37:34
    Author     : kurikura
--%>
<%@page import="javax.servlet.http.HttpSession"
        import="jums.*"%>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    if(udd==null){
        udd=new UserDataDTO();
    }
    UserData ud= new UserData();
    yahooAPI api=(yahooAPI)hs.getAttribute("item");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%= api.getName() %>の詳細情報-かごゆめ</title>
    </head>
    <body>
        <%= ud.header("/item.jsp", udd,ud,hs)%>
        
        <p><img src="<%= api.getImg() %>" style="float: left;margin-right:10px;">
            <%= api.getHeadline() %><br>
            <b><%= api.getName() %></b><br>
            税込み <%= api.getValue() %>円<br>
            <br>
            <form action="add" method="post">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
                <input type="submit" value="カートに入れる">
            </form>
            <br style="clear: both;">
        </p>
        <%= ud.top() %>
    </body>
</html>
