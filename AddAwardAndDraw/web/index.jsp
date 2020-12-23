<%--
  Created by IntelliJ IDEA.
  User: yangchen
  Date: 2020/11/25
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>欢迎</title>
    <meta charset="UTF-8">
    <style type="text/css">
      .link {
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
        background: yellow;
      }

      .link h1 {
        color: red;
      }

      .link a:link {
        text-decoration: none;
      }

      .link a:hover {
        text-decoration: none;
      }
    </style>
  </head>
  <body>
  <div class="link">
    <h1>请选择前往的页面</h1>
    <a href="index.jsp" style="text-decoration: none;">抽奖展示界面</a>
    <br><br><br>
    <a href="login.jsp" style="text-decoration: none;">抽奖管理界面</a>
  </div>
  </body>
</html>
