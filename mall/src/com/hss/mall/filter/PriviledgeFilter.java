package com.hss.mall.filter;

import com.hss.mall.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class PriviledgeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest myReq=(HttpServletRequest)req;
        User user = (User) myReq.getSession().getAttribute("loginUser");

        if (user != null){
            chain.doFilter(req,resp);
        }else {

            //如果不存在,转入到提示页面
            myReq.setAttribute("msg", "请用户登录之后再去访问");
            req.getRequestDispatcher("/jsp/info.jsp").forward(req, resp);
        }
    }
}
