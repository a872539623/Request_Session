<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2020/9/23
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function btn() {
            //获取三个输入值
            var oldPassword = document.getElementById("oldPassword");
            var newPassword = document.getElementById("newPassword");
            var rePassword = document.getElementById("rePassword");
            //只要有一个为空
            if (oldPassword.value == "" || newPassword.value == "" || rePassword.value == "") {
                //进行提示
                alert("密码不能为空");
                //返回false  (不允许提交)
                return false;
            }
            if (newPassword.value != rePassword.value) {
                alert("两次密码输入不一致");
                return false;
            }
            if (oldPassword.value == newPassword.value) {
                alert("新旧密码不能一致")
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form action="UserServlet?action=rePassword" method="post" onclick="btn()">
    旧密码:<input type="text" name="oldPassword" id="oldPassword">
    新密码:<input type="text" name="newPassword" id="newPassword">
    确认密码:<input type="text" name="rePassword" id="rePassword">
    <input type="submit" value="确认修改">


</form>
</body>
</html>
