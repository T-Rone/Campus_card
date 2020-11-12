package com.web;

import com.bean.User;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tdragon.
 * @Date 2020/11/7.
 * @Time 22:42
 * @Description:
 */

@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
    private UserService userService=new UserService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            String id=request.getParameter("id");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            User user=new User(Integer.valueOf(id),username,password);
            userService.updateUser(user);
            System.out.println(user.toString());
            //服务器响应转发到 userservlet
            response.sendRedirect("user");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        User user=userService.FindOneById(Integer.valueOf(id));
        request.getSession().setAttribute("user",user);
        request.getRequestDispatcher("updateUser.jsp").forward(request,response);
    }
}
