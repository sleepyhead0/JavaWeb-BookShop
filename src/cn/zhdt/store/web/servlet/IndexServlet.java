package cn.zhdt.store.web.servlet;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhdt.store.domain.Category;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.service.CategoryService;
import cn.zhdt.store.service.ProductService;
import cn.zhdt.store.service.servicelmp.CategoryServiceImp;
import cn.zhdt.store.service.servicelmp.ProductServiceImp;
import cn.zhdt.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

public class IndexServlet extends BaseServlet {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		      //获取对象
		     //  CategoryService categoryservice = new CategoryServiceImp();
		    //  List<Category> list= categoryservice.findCategory();
		   // System.out.println(list);
		  //保存到域对象
	     //request.setAttribute("allCats", list);
		//转发
		    
		
		//调用service层
		  ProductService productService = new ProductServiceImp();
		  //查询热门商品
		  List<Product> list= productService.findByHot();
		  //查询最新商品
		  List<Product> list1= productService.findByNew();
		  
		  //保存到域对象
		  request.setAttribute("allCats", list);
		  request.setAttribute("allCats1", list1);
	 return "/jsp/index.jsp";
	}
  
}
