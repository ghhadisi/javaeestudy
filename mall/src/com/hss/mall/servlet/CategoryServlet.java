package com.hss.mall.servlet;

import com.hss.mall.domain.Category;
import com.hss.mall.service.CategoryServiceImpl;
import com.hss.mall.service.ICategoryService;
import com.hss.mall.utils.JedisUtils;
import com.hss.mall.utils.TextUtils;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryServlet extends BaseServlet {


    public String findAllCats(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("application/json;charset=utf-8");
        String str = null;
        Jedis j = JedisUtils.getJedis();
        str = j.get("allCats");
        if (TextUtils.isEmpty(str)){
            System.out.println("缓存中没有数据");
            //查询所有分类
            ICategoryService categoryService = new CategoryServiceImpl();
            List<Category> list = categoryService.findAllCats();
            str = JSONArray.fromObject(list).toString();
            j.set("allCats", str);
        }else {
            System.out.println("缓存中有数据");
        }
        resp.getWriter().write(str);
        JedisUtils.closeJedis(j);
        return null;
    }

}