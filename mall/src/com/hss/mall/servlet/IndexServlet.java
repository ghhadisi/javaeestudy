package com.hss.mall.servlet;

import com.hss.mall.domain.Category;
import com.hss.mall.domain.Product;
import com.hss.mall.service.CategoryServiceImpl;
import com.hss.mall.service.ICategoryService;
import com.hss.mall.service.IProductService;
import com.hss.mall.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class IndexServlet extends BaseServlet {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

//        ICategoryService categoryService = new CategoryServiceImpl();
//        List<Category> list=categoryService.findAllCats();

        //放入request域对象
//        req.setAttribute("allCats", list);
        IProductService ProductService=new ProductServiceImpl();
//        //获取最新9件商品
        List<Product> list01=ProductService.findNewProducts();
//        //获取最热9件商品
        List<Product> list02=ProductService.findHotProducts();
//        //将最新商品放入request
        req.setAttribute("news", list01);
//        //将最热商品放入request
        req.setAttribute("hots", list02);

        return "/jsp/index.jsp";
    }
}
