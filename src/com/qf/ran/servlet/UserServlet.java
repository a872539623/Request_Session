package com.qf.ran.servlet;

import com.qf.ran.entity.User;
import com.qf.ran.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ran
 * @since JDK 1.8
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决请求的乱码
        request.setCharacterEncoding("utf-8");
        //解决响应乱码
        response.setContentType("text/html;charset=utf-8");
        System.out.println("进来doPost");
        String action = request.getParameter("action");
        switch (action){
            case "register":
                //快速封装方法    --  ctrl+alt+m
                //注册
                toRegister(request, response);
                break;
            case "login":
                //登录
                toLogin(request, response);
                break;
            case "logout":
                toLogout(request,response);
                break;
            case "rePassword":
                toRePassword(request,response);
                break;
            default:
                break;
        }
    }

    private void toRePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取新密码(新旧密码判断在前端已经完成这里只需要偶作更改操作)
        String rePassword = request.getParameter("rePassword");
        Object username = (String)request.getSession().getAttribute("username");
        toLogout(request,response);
    }

    private void toLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //3-3.新建cookie对象传入"username"对象
        Cookie cookie = new Cookie("username","");
        //3-4.把cookie存活时间变成0
        cookie.setMaxAge(0);
        //3-5.响应给客户端
        response.addCookie(cookie);
        System.out.println("进来了tologout");
        //因为在toLogin中有session对象保存了username的值,因此注销时也要清除session中的username;
        request.getSession().removeAttribute("username");
        //3-6.重定向到指定页面
        response.sendRedirect("welcome.jsp");
    }

    private void toLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        //获取用户输入的验证码
        System.out.println("进来toLogin");
        String code = request.getParameter("code");
        //获取存放到数据库中的验证码并校验  --  不可行
       /* if(!DB.serverCode.equals(code)){
            request.setAttribute("msg","验证码有误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }*/

        //获取存放到请求作用域中的验证码并校验    --  不可行
        //String serverCode = (String)request.getAttribute("code");

        //获取存放到 session 作用域中的验证码并校验 --  可行
        String serverCode = (String) request.getSession().getAttribute("code");

        //获取存放在 ServletContext 作用域中的验证码并校验 -- 不可行
        //String serverCode = (String)getServletContext().getAttribute("code");


        if(!serverCode.equals(code)){
            request.setAttribute("msg","验证码有误");
            request.getRequestDispatcher("login.jsp").forward(request,response);
            return;
        }
        //根据login.jsp里输入框的name属性=username/password获取输入的值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //登录
        boolean flag = userService.login(username, password);
        if(flag){
            //2-2.获取用户勾选记住cookies的情况,勾选情况返回的是null 或on 用此条件判断
            String remember = request.getParameter("remember");
            if (remember != null) {
                //2-3.生成cookies对象 传入前端"username"的值 username;
                    //2-3-1.这样传入username会有乱码问题
//                Cookie cookie = new Cookie("username",username);
                    //2-3-2.这样去解决
                Cookie cookie = new Cookie("username",URLEncoder.encode(username,"UTF-8"));
                //2-4.设置cookie的存活时间/秒为单位
                cookie.setMaxAge(60*60);
                //2-5.在响应里添加cooki e 这里是服务器给到客户端 用响应response
                response.addCookie(cookie);
                //getSession()创建一个session对象,存放setAttribute()所设置的值
                request.getSession().setAttribute("username",username);



            }
            //跳转页面  --  转发
            response.sendRedirect("index.jsp");
        }else{
            //response.getWriter().write("用户名或者密码错误");
            //存放数据到请求中
            request.setAttribute("msg","用户名和密码错误");
            //转发到login.jsp页面并携带请求数据
            request.getRequestDispatcher("login.jsp").forward(request,response);
            //重定向 -- 请求的数据会丢失
            //response.sendRedirect("login.jsp");
        }
    }

    private void toRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //注册的业务代码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        //String hobbies = request.getParameter("hobbies");
        String[] hobbies = request.getParameterValues("hobbies");
        String addrs = request.getParameter("addrs");

        User user = new User(username, password, sex, hobbies, addrs);
        boolean flag = userService.register(user);
        if(flag){
            //跳转    --  重定向
            response.sendRedirect("login.jsp");
        }else{
            //用户名已存在，告诉用户
            //存放数据到请求作用域中
            request.setAttribute("msg","用户名已存在");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
