package com.hss.mall.servlet;

import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.Product;
import com.hss.mall.service.IProductService;
import com.hss.mall.service.ProductServiceImpl;
import com.hss.mall.utils.UUIDUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
public class ProductServlet extends BaseServlet {



    public String  findProductByPid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String pid = req.getParameter("pid");
        IProductService productService = new ProductServiceImpl();
        Product pro=productService.findProductByPid(pid);
        //将商品放入request,
        req.setAttribute("product", pro);

        String ranStr = UUIDUtils.getId();
        //在session存放一份随机字符串
        req.getSession().setAttribute("ranStr",ranStr);

        return "/jsp/product_info.jsp";
    }


    public String  findProductsWithCidAndPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String strNum = req.getParameter("num");
        int num = Integer.parseInt(strNum);
        String cid = req.getParameter("cid");
        req.setAttribute("cid",cid);

        IProductService productService = new ProductServiceImpl();
        PageModel pm=productService.findProductsByCidWithPage(cid,num);
        req.setAttribute("page",pm);

        return "/jsp/product_list.jsp";
    }

}
