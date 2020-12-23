<%@ page language="java" import="com.zte.model.FrontAllAwardInfoNow" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%
    @SuppressWarnings("unchecked")
    List<FrontAllAwardInfoNow> awardList = (List<FrontAllAwardInfoNow>)request.getAttribute("awardList");
%>
<%--
  Created by IntelliJ IDEA.
  User: yangchen
  Date: 2020/11/25
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<html>
<head>
    <title>抽奖进行</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/manage.css">
</head>
<body>
<div class="header">
    <h1>综合管理界面</h1>
</div>
<div class="navbar">
    <a href="drawing.jsp"><b>>进行抽奖</b></a>
    <a href="addaward.jsp">奖项添加</a>
    <a href="temp.jsp">保险用</a>
</div>
<div id="main" style="width: 100%">
    <div id="left" style="width: 30%; float: left;">
        <form name="form" action="draw.do" onsubmit="return checkForm();" method="post">
            <tr>
                <td>选择奖项</td>
                <select name="awardAttribution">
                    <option value="" selected="selected">请选择...</option>
                    <%
                        if (awardList != null) {
                            for (FrontAllAwardInfoNow frontAllAwardInfoNow: awardList) {%>
                                <option value="<%=frontAllAwardInfoNow.getAbbr()%>"><%=frontAllAwardInfoNow.getName()%></option>
                            <%}
                        }
                    %>
                </select>
            </tr>
            <br>
            <tr>
                <td>选择抽奖人数</td>
                <select name="getNums" id="sel-opt">
                    <option value="" selected="selected">请选择...</option>
                    <option value="01">1</option>
                    <option value="10">10</option>
                    <option value="30">30</option>
                    <option value="1000">剩余全部</option>
                </select>
            </tr>
            <br>
            <input type="submit" value="提交">
            <input type="reset" value="重置">
        </form>
    </div>
    <div id="right" style="width: 70%; float: left;">
        <table>
            <tr>
                <th>奖项名称</th>
                <th>剩余人数</th>
            </tr>
            <%
                if (awardList != null) {
                    for (FrontAllAwardInfoNow frontAllAwardInfoNow: awardList) {%>
                        <tr>
                            <th><%=frontAllAwardInfoNow.getName()%></th>
                            <th><%=frontAllAwardInfoNow.getRestNum()%></th>
                        </tr>
                    <%}
                }
            %>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    function checkForm() {
        if (document.form.departmentAttribution.value==="") {
            window.alert("请选择奖项！");
            document.form.departmentAttribution.focus();
            return false;
        } else if (document.form.getNums.value==="") {
            window.alert("请选择人数！");
            document.form.getNums.focus();
            return false;
        } else {
            return true;
        }
    }
</script>
</html>
