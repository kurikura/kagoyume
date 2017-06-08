<%-- 
    Document   : cart
    Created on : 2017/05/23, 13:39:22
    Author     : kurikura
--%>
<%@page import="java.util.ArrayList"
        import="javax.servlet.http.HttpSession"
        import="jums.*"
        %>
<%
    HttpSession hs=request.getSession();
    UserDataDTO udd=(UserDataDTO)hs.getAttribute("udd");
    UserData ud= new UserData();
    yahooAPI cart=(yahooAPI)hs.getAttribute(Integer.toString(udd.getUserID()));
    ArrayList cartList=null;
    if(cart!=null){
        cartList=cart.getCart();
    }
    int num=0;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かご -かごゆめ</title>
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
        <%= ud.header("/top.jsp", udd,ud,hs)%>
        <h2>かごの中身</h2>
        <% if(cartList==null || cartList.isEmpty()){ %>
        かごが空っぽです！<br>
        <%  }else{
            for(int i=0;i<cartList.size();i++){
            yahooAPI api=(yahooAPI)cartList.get(i);
            num+=api.getValue();
        %>
        <p>
            <img src="<%= api.getImg() %>" style="float: left;margin-right:10px;">
            <a href="item?id=<%= api.getID()%>"><%= api.getName() %></a><br>
            <%= api.getValue() %>円<br>
        <form action="cart" method="post">
            <input type="hidden" name="no"  value="<%= i%>">
            <input type="submit" value="削除">
        </form>
        </p><br>
        <% } %>
        <p>
            合計金額：<%= num %>円
        </p>
        <form action="buyconfirm" method="post">
            <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            <input type="submit" name="submit" value="購入する">
        </form>
            <% } %>
        <%= ud.top()%>
    </body>
</html>
