package com.hss.mall.servlet;

import com.hss.mall.domain.Category;
import com.hss.mall.service.CategoryServiceImpl;
import com.hss.mall.service.ICategoryService;
import com.hss.mall.utils.UUIDUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AdminCategoryServlet extends BaseServlet {
    //findAllCats
    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//获取全部分类信息
        ICategoryService CategoryService = new CategoryServiceImpl();
        List<Category> list = CategoryService.findAllCats();

        req.setAttribute("allCats", list);
        return "/admin/category/list.jsp";

    }

    public String addCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/admin/category/add.jsp";
    }

    public String addCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取分类名称
        String cname=req.getParameter("cname");
        //创建分类ID
        String id= UUIDUtils.getId();
        Category c=new Category();
        c.setCid(id);
        c.setCname(cname);
        //调用业务层添加分类功能
        ICategoryService CategoryService=new CategoryServiceImpl();
        CategoryService.addCategory(c);
        //重定向到查询全部分类信息
        resp.sendRedirect("/adminCategoryServlet?method=findAllCats");
        return null;

    }

    public String editCategoryUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String cid = req.getParameter("cid");
        ICategoryService categoryService = new CategoryServiceImpl();
        Category category = categoryService.findCategory(cid);
        req.setAttribute("category",category);
        return "/admin/category/edit.jsp";
    }


    public String saveCategory(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //获取分类名称
        String cname=req.getParameter("cname");
        String cid= req.getParameter("cid");
        Category category = new Category();
        category.setCid(cid);
        category.setCname(cname);
        ICategoryService CategoryService=new CategoryServiceImpl();
        CategoryService.saveCat(category);
        //重定向到查询全部分类信息
        resp.sendRedirect("/adminCategoryServlet?method=findAllCats");
        return null;

    }

}
