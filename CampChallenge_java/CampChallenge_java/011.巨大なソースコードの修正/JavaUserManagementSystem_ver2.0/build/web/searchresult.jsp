<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.util.ArrayList" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    UserDataDTO udd = (UserDataDTO)session.getAttribute("udd");
    ArrayList<UserDataDTO> resultList=udd.getResultList();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS検索結果画面</title>
    </head>
    <body>
        <h1>検索結果</h1>
        <% if(resultList.size()>0){ %>
        <p>検索結果：<%= resultList.size() %>件</p>
        <table border=1>
            <tr>
                <th nowrap>名前</th>
                <th nowrap>生年月日</th>
                <th nowrap>種別</th>
                <th nowrap>登録日時</th>
            </tr>
                <% for(int i=0;i<resultList.size();i++){ 
                    udd=(UserDataDTO)resultList.get(i);
                %>
                    <tr>
                        <td nowrap><a href="ResultDetail?id=<%= udd.getUserID()%>"><%= udd.getName()%></a></td>
                        <td nowrap><%= udd.getBirthday()%></td>
                        <td nowrap><%= jh.exTypenum(udd.getType())%></td>
                        <td nowrap><%= udd.getNewDate()%></td>
                    </tr>
                <% } %>
        </table>
        <% }else{ %>
        <p>検索結果：0件</p>
        <% } %>
        <br>
        <form action="Search" method="POST">
            <input type="submit" name="btnSubmit" value="戻る">
        </form>
    </body>
    <%=jh.home()%>
</html>
