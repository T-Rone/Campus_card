package com.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author tdragon.
 * @Date 2020/11/5.
 * @Time 23:16
 * @Description: 测试 html注入
 */

@WebServlet(name = "resultServlet",urlPatterns = "/res")
public class resultServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name=request.getParameter("username");
        String message=request.getParameter("message");
        String text=request.getParameter("text");
        request.setAttribute("name",name);
        request.setAttribute("message",message);
        request.setAttribute("text",text);
        request.getRequestDispatcher("result.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
