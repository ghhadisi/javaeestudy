package com.hss.mall.servlet;

import com.hss.mall.domain.Category;
import com.hss.mall.service.CategoryServiceImpl;
import com.hss.mall.service.ICategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCategoryServlet extends BaseServlet {
    //findAllCats
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//获取全部分类信息
        ICategoryService CategoryService=new CategoryServiceImpl();
        List<Category> list= CategoryService.findAllCats();

        req.setAttribute("allCats", list);
        return "/admin/category/list.jsp";

    }
}
