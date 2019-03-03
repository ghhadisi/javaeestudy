package com.hss.mall.servlet;

import com.hss.mall.domain.Category;
import com.hss.mall.domain.PageModel;
import com.hss.mall.domain.Product;
import com.hss.mall.service.CategoryServiceImpl;
import com.hss.mall.service.ICategoryService;
import com.hss.mall.service.IProductService;
import com.hss.mall.service.ProductServiceImpl;
import com.hss.mall.utils.UUIDUtils;
import com.hss.mall.utils.UploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminProductServlet extends BaseServlet {


    public String findAllProductsWithPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String strNum = req.getParameter("num");
        int num = Integer.parseInt(strNum);

        IProductService productService = new ProductServiceImpl();
        PageModel pm=productService.findProductsWithPage(num);
        req.setAttribute("page",pm);

        return "/admin/product/list.jsp";
    }

    public String addProductUI(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        ICategoryService categoryService = new CategoryServiceImpl();
        List<Category> allCats = categoryService.findAllCats();
        req.setAttribute("allCats",allCats);

        return "/admin/product/add.jsp";
    }

    public String addProduct(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //存储表单中数据
        Map<String,String> map = new HashMap<>();
        Product product = new Product();

        //利用req.getInputStream();获取到请求体中全部数据,进行拆分和封装
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload  fileUpload = new ServletFileUpload(factory);
        List<FileItem> list = fileUpload.parseRequest(req);
       for (FileItem item: list){
           if (item.isFormField()){
               //5_如果当前的FileItem对象是普通项
               //将普通项上name属性的值作为键,将获取到的内容作为值,放入MAP中
               // {username<==>tom,password<==>1234}
               map.put(item.getFieldName(),item.getString("utf-8"));
           }else {
               //6_如果当前的FileItem对象是上传项

               //获取到原始的文件名称
               String oldFileName=item.getName();
               //获取到要保存文件的名称   1222.doc  123421342143214.doc
               String newFileName= UploadUtils.getUUIDName(oldFileName);

               //通过FileItem获取到输入流对象,通过输入流可以获取到图片二进制数据
               InputStream is=item.getInputStream();
               //获取到当前项目下products/3下的真实路径
               //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3

               String realPath=getServletContext().getRealPath("/products/3/");
               String dir=UploadUtils.getDir(newFileName); // /f/e/d/c/4/9/8/4
               String path=realPath+dir; //D:\tomcat\tomcat71_sz07\webapps\store_v5\products\3/f/e/d/c/4/9/8/4
               //内存中声明一个目录
               File newDir=new File(path);
               if(!newDir.exists()){
                   newDir.mkdirs();
               }
                //在服务端创建一个空文件(后缀必须和上传到服务端的文件名后缀一致)
               File finalFile=new File(newDir,newFileName);
               if(!finalFile.exists()){
                   finalFile.createNewFile();
               }
               //建立和空文件对应的输出流
               OutputStream os=new FileOutputStream(finalFile);
               //将输入流中的数据刷到输出流中
               IOUtils.copy(is, os);
               //释放资源
               IOUtils.closeQuietly(is);
               IOUtils.closeQuietly(os);
               map.put("pimage", "products/3"+dir+"/"+newFileName);

           }
       }
        //7_利用BeanUtils将MAP中的数据填充到Product对象上
        BeanUtils.populate(product, map);
        product.setPid(UUIDUtils.getId());
        product.setPdate(new Date());
        product.setPflag(0);

        //8_调用servcie_dao将user上携带的数据存入数据仓库,重定向到查询全部商品信息路径
        IProductService ProductService=new ProductServiceImpl();
        ProductService.saveProduct(product);
        resp.sendRedirect("/adminProductServlet?method=findAllProductsWithPage&num=1");
        return null;
    }
}
