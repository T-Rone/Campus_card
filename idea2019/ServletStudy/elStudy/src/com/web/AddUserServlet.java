package com.web;

import com.bean.User;
import com.dao.UserDao;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author tdragon.
 * @Date 2020/11/7.
 * @Time 22:43
 * @Description:
 */

@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private UserService userService =new UserService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        PrintWriter out=response.getWriter();
        User user=new User();
        if (!"".equals(username) && !"".equals(password))
        {
            user.setUsername(username);
            user.setPassword(password);
//            UserDao.insertUser(user);
            userService.addUser(user);
            out.write("<h1> " + "注册成功"+ " </h1>");
            out.write("<a href=\'user\'>"+"返回查看"+"</a>");
        }
        else{
            out.write("<h1> " + "添加失败，用户名或密码不能为空！"+ " </h1>");
        }
        System.out.println(user.toString());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
