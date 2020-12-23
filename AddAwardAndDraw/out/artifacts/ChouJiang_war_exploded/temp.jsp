<%--
  Created by IntelliJ IDEA.
  User: yangchen
  Date: 2020/12/23
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>保险页面</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/manage.css">
</head>
<body>
<div class="header">
    <h1>综合管理界面</h1>
</div>
<div class="navbar">
    <a href="drawing.jsp">进行抽奖</a>
    <a href="addaward.jsp">奖项添加</a>
    <a href="temp.jsp"><b>>保险用</b></a>
</div>
<div>
    <form name="form" action="jump.do" method="post">
        <tr>
            <td>选择要跳转的页面：</td>
            <input type="radio" name="page" value="0" checked/>抽奖页面
            <input type="radio" name="page" value="1"/>添加页面
        </tr>
        <input type="submit" value="跳转">
    </form>
</div>
</body>
</html>
