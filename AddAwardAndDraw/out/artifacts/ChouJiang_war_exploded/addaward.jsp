<%--
  Created by IntelliJ IDEA.
  User: yangchen
  Date: 2020/11/25
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加奖项</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/manage.css">
</head>
<body>
<div class="header">
    <h1>综合管理界面</h1>
</div>
<div class="navbar">
    <a href="drawing.jsp">进行抽奖</a>
    <a href="addaward.jsp"><b>>奖项添加</b></a>
    <a href="temp.jsp">保险用</a>
</div>
<div>
    <form name="form" action="addaward.do" onsubmit="return checkForm();" method="post">
        <tr>
            <td>奖项名：</td>
            <input type="text" name="awardname" value="" />
        </tr>
        <br>
        <tr>
            <td>领导名：</td>
            <input type="text" name="leader" value="" />
        </tr>
        <br>
        <tr>
            <td>单位奖项：</td>
            <input type="text" name="moneyperperson" value="" />
        </tr>
        <br>
        <tr>
            <td>选择奖项类型：</td>
            <input type="radio" name="print" id="t" value="0" onclick="divClick()" checked="true">输入总人数
            <input type="radio" name="print" id="s" value="1" onclick="divClick()">输入各部门人数
        </tr>
        <div>
            <div style="display: block;" id="div1">
                <input type="text" name="0000" id="0000" placeholder="请输入人数" value="">
            </div>
            <div style="display: none;" id="div2">
                <input type="text" name="0001" id="0001" placeholder="请输入人数" value="">
                <br>
                <input type="text" name="0002" id="0002" placeholder="请输入人数" value="">
            </div>
        </div>
        <br>
        <input type="submit" value="提交">
        <input type="reset" value="重置">
    </form>
</div>
</body>
<script type="text/javascript">
    function checkForm() {
        if (document.form.awardname.value==="") {
            window.alert("请填写奖项名！");
            document.form.awardname.focus();
            return false;
        } else if (document.form.moneyperperson.value==="") {
            window.alert("请填写单位数额！");
            document.form.moneyperperson.focus();
            return false;
        }

        if (document.getElementById("0000").value==="") {
            document.getElementById("0000").value = 0;
        }
        if (document.getElementById("0001").value==="") {
            document.getElementById("0001").value = 0;
        }
        if (document.getElementById("0002").value==="") {
            document.getElementById("0002").value = 0;
        }

        return true;
    }

    function divClick() {
        if (document.getElementById("t").checked) {
            document.getElementById("div1").style.display = "block";
            document.getElementById("div2").style.display = "none";
        } else if (document.getElementById("s").checked) {
            document.getElementById("div1").style.display = "none";
            document.getElementById("div2").style.display = "block";
        } else {
            document.getElementById("div1").style.display = "none";
            document.getElementById("div2").style.display = "none";
        }
    }
</script>
</html>
