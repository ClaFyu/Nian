<%--
  Created by IntelliJ IDEA.
  User: yangchen
  Date: 2020/11/25
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <meta charset="UTF-8">
    <script type="text/ecmascript" src="javascript/encoding.js"></script>
    <style type="text/css">
        .login {
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            text-align: center;
            width: 480px;
            height: 200px;
            background: #c900ff;
        }

        .login h1 {
            color: red;
        }
    </style>
</head>
<body>
<div class="login">
    <h1>管理员登录</h1>
    <form id="login_form" action="login.do" method="post">
        <tr>
            <td>用户名</td>
            <input type="text" name="username" id="username" value=""/>
        </tr>
        <br>
        <tr>
            <td>密码</td>
            <input type="password" id="pwd" value=""/>
            <input type="hidden" name="password" id="sha1_pwd" value=""/>
        </tr>
        <br>
        <tr>
            <td><input type="checkbox" id="remember" name="remember"></td>
            <td>记住我</td>
        </tr>
        <br>
        <input type="submit" onclick="form_submit();" value="提交"/>
        <input type="reset" value="重置"/>
    </form>
</div>
</body>
<script type="text/javascript">
    function form_submit() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("pwd").value;
        var sha1_pwd = document.getElementById("sha1_pwd");
        var remember = document.getElementById("remember");

        sha1_pwd.value = hex_sha1(username + hex_sha1(password));

        setCookie("username", username, 24 * 7, "/");

        if (remember) {
            setCookie(username, password, 24 * 7, "/");
        } else {
            setCookie(username, "", 0, "/");
        }
        theForm.submit();
    }
</script>
</html>
