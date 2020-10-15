<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>

    <title>Title</title>
    <script>
        // 3-2.location.href 点击后发送的请求地址 3-3在UserServlet
        function btn() {
            location.href="UserServlet?action=logout";
        }
    </script>
</head>
<body>
<a href="#">数据展示</a><br/>
<a href="rePassword.jsp">修改密码</a><br/>
<%--3-1.注销功能--%>
<input type="button" value="注销" onclick="btn()">
</body>
</html>