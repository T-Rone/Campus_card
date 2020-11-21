package com.web;

import com.bean.User;
import com.service.UserService;
import com.util.PageUtil;

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
    private PageUtil page=new PageUtil();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageCurrent =request.getParameter("PageCurrent");
        if ( pageCurrent!=null){
            int current=Integer.parseInt(pageCurrent);
            if (current > page.getTotalSize()) {
                current=page.getTotalSize();
            }
            if (current<1)
            {
                current=1;
            }
            page.setPageCurent(current);
        }
        //调用service
//        List<User> users= userService.showUsers();
        //分页查询
       List<User> users=userService.showPageUsers(page);
        //把集合存入域对象
        request.getSession().setAttribute("users",users);
        request.getSession().setAttribute("page",page);
        //转发到jsp页面
        request.getRequestDispatcher("user.jsp").forward(request,response);
    }
}
