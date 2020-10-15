<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/21 0021
  Time: 上午 11:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        //让点击的img的scr每次点击都带有不同的参数
        function btn(obj) {
            obj.src = "CheckServlet?date=" + new Date();
        }

        function btn2() {
            var img = document.getElementsByTagName("img")[0];
            img.src = "CheckServlet?date=" + new Date();
        }
    </script>
</head>
<body>
<%
    //2-6.获取cookie判断是否存在username的key,存在则跳转到index.jsp(2-5 在UserServlet)
    Cookie[] cookies = request.getCookies();
    //2-6-1.先判断cookies是否存在 否则遍历会出问题
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                response.sendRedirect("index.jsp");

            }

        }
    }

%>
<%
    //编写java代码
    //获取存储在请求中的数据
    String msg = (String) request.getAttribute("msg");
%>
<%=
//展示java代码
        msg == null ? "" : msg
%>
<form action="UserServlet?action=login" method="post">
    用户名:<input type="text" name="username"><br/>
    密码:<input type="text" name="password"><br/>
    <%--            img添加点击事件刷新验证码--%>
    <img src="CheckServlet" onclick="btn(this)"/><input type="text" name="code"><br/>
    <input type="submit" value="登录">
    <a href="#" onclick="btn2()">看不清</a>
<%--    //2-1--%>
    <input type="checkbox" name="remember">保存cookies
</form>
</body>
</html>
