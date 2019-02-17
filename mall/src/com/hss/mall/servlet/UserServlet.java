package com.hss.mall.servlet;

import com.hss.mall.domain.User;
import com.hss.mall.service.IUserService;
import com.hss.mall.service.UserServiceImpl;
import com.hss.mall.utils.MyBeanUtils;
import com.hss.mall.utils.MyDateConverter;
import com.hss.mall.utils.Print;
import com.hss.mall.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
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
            if (user != null){
                user.setState(0);
                user.setCode(UUIDUtils.getId());
                userService.register(user);
            //4_向客户端提示:用户注册成功,请激活,转发到提示页面
                req.setAttribute("msg", "用户注册成功,请激活!");
                return  "/jsp/info.jsp";
            }


//        System.out.println(user.toString());


        return null;
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
