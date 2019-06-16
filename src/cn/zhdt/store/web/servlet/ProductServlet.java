package cn.zhdt.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhdt.store.domain.Page;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.service.ProductService;
import cn.zhdt.store.service.servicelmp.ProductServiceImp;
import cn.zhdt.store.web.base.BaseServlet;

public class ProductServlet extends BaseServlet {

	
	public String finProductId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获得商品的id 
		String pid = request.getParameter("pid");
		
		//调用service层查询商品详情
		  ProductService productService = new ProductServiceImp();
		  Product  product=productService.findByXing(pid);
		  
		//保存到域对象
	    request.setAttribute("allCats", product);
		return "/jsp/product_info.jsp";
	}
	public String findProductCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获得分类的id和分页信息
		  String cid = request.getParameter("cid");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
	    //调用service查获取Page对象
		   ProductService pro = new ProductServiceImp();
		   Page<Product> page=pro.findByCid(cid ,currentPage);
		   System.out.println(page);
		//保存到域对象
		   request.setAttribute("page",page);
		return "jsp/product_list.jsp";
		
	}

}
