package com.hss.mall.servlet;

import com.hss.mall.domain.User;
import com.hss.mall.service.IUserService;
import com.hss.mall.service.UserServiceImpl;
import com.hss.mall.utils.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class UserServlet extends BaseServlet {
    IUserService userService = new UserServiceImpl();

//    UserService UserService =(UserService)BeanFactory.createObject("UserService");

    public String registerUi(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "/jsp/register.jsp";
    }


    public String register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        Map<String, String[]> params = req.getParameterMap();
        Print.paramap(params);


        //注册自己的日期转换器
//            ConvertUtils.register(new MyDateConverter(), Date.class);
//            User user = new User();
//            BeanUtils.populate(user, params);
        //1_接受用户数据,
        User user = MyBeanUtils.populate(User.class, params);
        if (user != null) {
            user.setUid(UUIDUtils.getId());

            user.setState(0);
            user.setCode(UUIDUtils.getId());
            userService.register(user);
            //4_向客户端提示:用户注册成功,请激活,转发到提示页面
            req.setAttribute("msg", "用户注册成功,请激活!");
            return "/jsp/info.jsp";
        }


//        System.out.println(user.toString());


        return null;
    }


    public String checkUserNameCanUse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        try {
            boolean flag = userService.checkUserNameCanUse(username);
            resp.getWriter().write(flag ? "true" : "false");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.getWriter().write("false");
        }
        return null;
    }


    public String active(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //服务端获取到激活码,和数据库中已经存在的激活码对比,如果存在,激活成功,更改用户激活状态1,转发到登录页面,提示:激活成功,请登录.
        String code = req.getParameter("code");
        //调用业务层功能:根据激活码查询用户 select * from user where code=?
        User user01 = userService.userActive(code);
//如果用户不为空,激活码正确的,可以激活
        if (null != user01) {
            user01.setState(1);
            user01.setCode("");
            userService.updateUser(user01);
        }
        //转发到登录页面,提示:激活成功,请登录
        req.setAttribute("msg", "用户激活成功,请登录");
        return "/jsp/login.jsp";
    }


    public String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        //接受用户名和密码
        User user01 = MyBeanUtils.populate(User.class, req.getParameterMap());
        User user02 = userService.login(user01);
        if (user02 != null) {
            req.getSession().setAttribute("loginUser", user02);
            //在登录成功的基础上,判断用户是否选中自动登录复选框
            String autoLogin = req.getParameter("autoLogin");
            if (autoLogin == "yes") {
                //用户选中自动登录复选框

                Cookie ck = new Cookie("autoLogin", user02.getUsername() + "#" + user02.getPassword());
                ck.setPath("/");
                ck.setMaxAge(23423424);
                resp.addCookie(ck);
            }

            //remUser
            String remUser = req.getParameter("remUser");
            if ("yes".equals(remUser)) {
                //用户选中自动登录复选框
                Cookie ck = new Cookie("remUser", user02.getUsername());
                ck.setPath("/");
                ck.setMaxAge(23423424);
                resp.addCookie(ck);
            }
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } else {
            //登录失败,向request放入提示信息,转发到登录页面,显示提示userLogin
            req.setAttribute("msg", "用户名和密码不匹配!");
            return "/jsp/login.jsp";
        }

        return null;
    }


    public String logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //用户退出,清空用户session
        request.getSession().invalidate();
        Cookie ck= CookUtils.getCookieByName("autoLogin", request.getCookies());
        if(null!=ck){
            ck.setMaxAge(0);
            ck.setPath("/");
            response.addCookie(ck);
        }

        response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
        return null;
    }
}