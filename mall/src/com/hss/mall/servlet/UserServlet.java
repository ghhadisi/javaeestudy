package com.hss.mall.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends BaseServlet {

    public String registerUi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        return "/jsp/register.jsp";
    }
}
