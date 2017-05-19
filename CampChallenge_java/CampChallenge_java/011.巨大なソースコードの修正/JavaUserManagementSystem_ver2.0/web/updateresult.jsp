<%@page import="jums.JumsHelper"
        import="jums.UserDataDTO"
        import="java.text.SimpleDateFormat"
        import="java.util.ArrayList" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs = request.getSession();
    UserDataDTO udd = (UserDataDTO)request.getAttribute("resultData");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    ArrayList<String> chkList = udd.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS更新結果画面</title>
    </head>
    <body>
        <% if(chkList.size()==0 && udd.getResult()!=0){ %>
            <h1>変更結果</h1><br>
            名前:<%= udd.getName()%><br>
            生年月日:<%= sdf.format(udd.getBirthday())%><br>
            種別:<%= jh.exTypenum(udd.getType())%><br>
            電話番号:<%= udd.getTell()%><br>
            自己紹介:<%= udd.getComment()%><br>
            以上の内容で登録しました。<br>
            <a href="ResultDetail?id=<%= udd.getUserID()%>">詳細画面に戻る</a>
        <% }else if(chkList.size()!=0){ %>
            <h1>入力が不完全です</h1>
            <%=jh.chkinput(chkList) %>
            <form action="Update" method="POST">
                <input type="submit" name="no" value="登録画面に戻る">
                <input type="hidden" name="ac"  value="<%= hs.getAttribute("ac")%>">
            </form>
        <% }else{ %>
            <h1>更新に失敗しました</h1>
        <% } %>
    </body>
    <%=jh.home()%>
    </body>
</html>
