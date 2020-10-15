package com.qf.ran.servlet;

import com.qf.ran.db.DB;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author Ran
 * @since JDK 1.8
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width = 100;
        int height = 50;
        //1.创建带缓冲的图片对象，需要传递宽，高，图片颜色的类型
        BufferedImage bImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.通过图片对象获取画笔
        Graphics graphics = bImage.getGraphics();
        //3.给画笔设置颜色
        graphics.setColor(Color.PINK);
        //4.填充背景颜色
        graphics.fillRect(0,0,width,height);
        //5.再给画笔设置颜色
        graphics.setColor(Color.BLUE);
        //6.设置字体
        graphics.setFont(new Font("黑体",Font.BOLD,20));
        //7.生成随机数当成验证码
        Random ran = new Random();
        int code = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0;i < 4;i++){
            code = ran.nextInt(10);
            sb.append(code);
            // 把随机数添加到图片中
            graphics.drawString(code+"",15+20*i,30);
        }

        //把验证码存储到数据库中   --  不可行
        //DB.serverCode = sb.toString();

        //把验证码存放到请求作用域中 --  不可行
        //request.setAttribute("code",sb.toString());

        //把验证码存放到 session 作用域中   --  可行
        HttpSession session = request.getSession();
        session.setAttribute("code",sb.toString());

        //把验证码存放到 ServletContext作用域中 -- 不可行
        //ServletContext servletContext = getServletContext();
        //servletContext.setAttribute("code",sb.toString());

        //8.改变画笔的颜色
        graphics.setColor(Color.RED);
        //9.绘制干扰线
        for (int i = 0; i < 10; i++) {
            graphics.drawLine(ran.nextInt(width),ran.nextInt(height),ran.nextInt(width),ran.nextInt(height));
        }
        //10.把绘制好的图片对象响应回请求的客户端
        ImageIO.write(bImage,"jpg",response.getOutputStream());
    }
}
