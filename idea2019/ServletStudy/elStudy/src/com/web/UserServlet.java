package com.web;

import com.bean.User;
import com.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    //引入service对象
    private UserService userService =new UserService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service
        List<User> users= userService.showUsers();
        //把集合存入域对象
        request.getSession().setAttribute("users",users);
        //转发到jsp页面
        request.getRequestDispatcher("user.jsp").forward(request,response);
    }
}
