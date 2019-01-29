package com.hss.auto.login.servlet;

import com.hss.auto.login.bean.UserBean;
import com.hss.auto.login.dao.UserDao;
import com.hss.auto.login.dao.impl.UserDaoImpl;
import com.hss.auto.login.service.UserService;
import com.hss.auto.login.service.impl.UserServiceImpl;
import com.hss.auto.login.utils.MyDateConverter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.err.println("LoginServlet doGet ");

        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String autoLogin = req.getParameter("auto_login");


        //注册自己的日期转换器
        ConvertUtils.register(new MyDateConverter(), Date.class);
        Map map = req.getParameterMap();

        UserBean user = new UserBean();
        try {
            BeanUtils.populate(user, map);
            System.err.println("BeanUtils.populate");
        } catch (IllegalAccessException e) {
            e.printStackTrace();

            user.setUsername(userName);
            user.setPassword(password);
        } catch (InvocationTargetException e) {
            e.printStackTrace();

            user.setUsername(userName);
            user.setPassword(password);
        }




        UserService userService = new UserServiceImpl();
        try {
            UserBean userBean = userService.login(user);
            if (userBean !=null){
                if (autoLogin !=null && autoLogin.equals("on")){
                    Cookie cookie = new Cookie("auto_login", userName+"#"+password);
                    cookie.setMaxAge(60*60*24*7);//7天有效期
//                    cookie.setPath("/AutoLoginDemo");
                    cookie.setPath(req.getContextPath());
                    resp.addCookie(cookie);
                    System.err.println("resp.addCookie(cookie)");
                }


                req.getSession().setAttribute("userBean",userBean);
                req.getRequestDispatcher("/index.jsp").forward(req,resp);

            }else {
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
