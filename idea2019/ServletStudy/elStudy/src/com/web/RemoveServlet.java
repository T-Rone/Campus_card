package com.web;

import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @Author tdragon.
 * @Date 2020/11/8.
 * @Time 10:10
 * @Description:
 */

@WebServlet("/removeUser")
public class RemoveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        UserService userService=new UserService();
        String [] id=request.getParameterValues("id");
        System.out.println(Arrays.toString(id));
        if(id==null){
            out.write("<script type=\"text/javascript\">alert(\"请通过复选框选择用户！\")</script>");
            out.write("  <a href=\"javascript:history.go(-1)\">点击选择</a>");
        }
        else {
        for(String idd : id){
            System.out.print(idd+" ");
            userService.deleteOoneById(Integer.parseInt(idd));
        }
        response.sendRedirect("user");
        }
}
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
