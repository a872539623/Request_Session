<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/9/21 0021
  Time: 上午 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <!--    get提交方式
        <form action="UserServlet">
        <input type="hidden" name="action" value="register">-->
    <!--表单非空校验，如果未填写完毕则提示用户并无法提交表单-->
    <%--自动获取作用域的内容--%>
<%--    在UserServlet中设置了用户已存在发送到msg作用域--%>
<%--    request.setAttribute("msg","用户名已存在");--%>
<%--    使用一下EL表达式可以直接获取并展示--%>
    ${msg}
    <form action="UserServlet?action=register" method="post">
        用户名:<input type="text" name="username"/><br/>
        密码:<input type="text" name="password"/><br/>
        性别:<input type="radio" name="sex" value="man">男
        <input type="radio" name="sex" value="women">女<br/>
        爱好:<input type="checkbox" name="hobbies" value="eat">吃
        <input type="checkbox" name="hobbies" value="drink">喝
        <input type="checkbox" name="hobbies" value="play">玩<br/>
        地址:
        <select name="addrs">
            <option value="guangdong">广东</option>
            <option value="guangxi">广西</option>
            <option value="fujian">福建</option>
        </select><br/>
        <input type="submit" value="注册"/>
    </form>
</body>
</html>
