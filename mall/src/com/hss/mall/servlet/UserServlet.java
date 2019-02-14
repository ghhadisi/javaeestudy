package com.hss.mall.servlet;

import com.hss.mall.utils.Print;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class UserServlet extends BaseServlet {

    public String registerUi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        return "/jsp/register.jsp";
    }


    public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        Map<String, String[]> params= req.getParameterMap();
        Print.paramap(params);

        return "/jsp/login.jsp";
    }
}
