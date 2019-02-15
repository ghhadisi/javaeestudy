package com.hss.mall.servlet;

import com.hss.mall.service.IUserService;
import com.hss.mall.service.UserServiceImpl;
import com.hss.mall.utils.Print;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

public class UserServlet extends BaseServlet {
    IUserService userService = new UserServiceImpl();
    public String registerUi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }


    public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String[]> params = req.getParameterMap();
        Print.paramap(params);

        return "/jsp/login.jsp";
    }


    public String checkUserNameCanUse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            boolean flag =  userService.checkUserNameCanUse(username);
            resp.getWriter().write(flag?"true":"false");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write("false");
        }
        return null;
    }
}
