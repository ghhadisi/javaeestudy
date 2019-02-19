package com.hss.mall.servlet;

import com.hss.mall.domain.Cart;
import com.hss.mall.domain.CartItem;
import com.hss.mall.domain.Product;
import com.hss.mall.domain.User;
import com.hss.mall.service.IProductService;
import com.hss.mall.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CartServlet extends BaseServlet {

    public String  addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        //判断用户是否登录,没有登录跳转到login.jsp页面,提示:请登录后在购物
       User user = (User) req.getSession().getAttribute("loginUser");
       if (user ==null){
            req.setAttribute("msg", "请登录后在购买商品");
            return "/jsp/login.jsp";
       }
        //获取表单中随机字符串
        //String ranStr01=request.getParameter("ranStr");

        //获取session中的随机字符串
        //String ranStr02=(String)request.getSession().getAttribute("ranStr");
        //System.out.println(ranStr01+"........01");
        //System.out.println(ranStr02+"..........02");


        //if(!ranStr01.equals(ranStr02)){
        //request.setAttribute("msg", "不要重复提交数据");
        //return "/jsp/info.jsp";
        //}


        String pid=req.getParameter("pid");
        String number=req.getParameter("number");
        int num = Integer.parseInt(number);
        IProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setNum(num);
        //获取购物车:从session中获取,获取到直接使用,

        //获取购物车:从session中获取,获取到直接使用,
        Cart cart=(Cart)req.getSession().getAttribute("cart");
        //如果获取不到,创建一份,并且在session在绑定一份
        if(null==cart){
            cart=new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addCart(cartItem);

        //重定向到购物车页面jsp/cart.jsp
        return "/jsp/cart.jsp";
//        resp.sendRedirect(req.getContextPath()+"/jsp/cart.jsp");
//        return null;
    }


    public String delCartItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        return  null;
    }
}
