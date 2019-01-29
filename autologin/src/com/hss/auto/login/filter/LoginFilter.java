package com.hss.auto.login.filter;

import com.hss.auto.login.bean.UserBean;
import com.hss.auto.login.service.UserService;
import com.hss.auto.login.service.impl.UserServiceImpl;
import com.hss.auto.login.utils.CookieUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.err.println("doFilter");


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //先判断，现在session中还有没有那个userBean.
        UserBean userBean = (UserBean) request.getSession().getAttribute("userBean");

        if (userBean != null){

            System.err.println("filter  request.getSession().getAttribute(userBean) !=null");

            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //代表session失效了。

            //2. 看cookie。

            //1. 来请求的时候，先从请求里面取出cookie , 但是cookie有很多的key-value
            Cookie[] cookies = request.getCookies();
            //2. 从一堆的cookie里面找出我们以前给浏览器发的那个cookie
            Cookie cookie = CookieUtil.findCookie(cookies, "auto_login");

            //第一次来
            if(cookie  == null){

                System.err.println("filter  cookie  == null doFilter");

                filterChain.doFilter(request, servletResponse);
            }else{
                String value = cookie.getValue();
                String username = value.split("#")[0];
                String password = value.split("#")[1];

                //完成登录
                UserBean user = new UserBean();
                user.setUsername(username);
                user.setPassword(password);
                UserService userService = new UserServiceImpl();
                try {
                    UserBean userResult = userService.login(user);
                    //使用session存这个值到域中，方便下一次未过期前还可以用。
                    request.getSession().setAttribute("userBean", userResult);
                    filterChain.doFilter(request, servletResponse);
                    System.err.println("filter cookie  != null equest.getSession().setAttribute");

                } catch (SQLException e) {
                    e.printStackTrace();
                    filterChain.doFilter(request, servletResponse);
                }

            }
        }
    }

    @Override
    public void destroy() {

    }
}
