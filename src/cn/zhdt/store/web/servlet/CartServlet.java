package cn.zhdt.store.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhdt.store.domain.Cart;
import cn.zhdt.store.domain.CartItem;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.service.ProductService;
import cn.zhdt.store.service.servicelmp.ProductServiceImp;
import cn.zhdt.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {

	
	public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
         //先判断session中是否存在cart参数
		Cart cart =(Cart)request.getSession().getAttribute("cart");
		  if(cart==null){
			   cart = new Cart();
	          request.getSession().setAttribute("cart", cart);
		  }
		  //获取pid和数量
		  String pid = request.getParameter("pid");
          int num = Integer.parseInt(request.getParameter("quantity"));
//          System.out.println(pid);
//          System.out.println(num);
          
          //获取Product对象
          ProductService productService = new ProductServiceImp();
          Product product=productService.findByXing(pid);
         
          
          //添加参数到购物项中
          CartItem cartItem = new CartItem();
          cartItem.setProduct(product);
          cartItem.setNum(num);
          
          //添加到购物车
          cart.addCartItemToCart(cartItem);
        
          //重定向到jsp
          response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
          
		return null;
	}
    
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	      //获取删除商品pid
		String pid = request.getParameter("pid");
		  //获取到购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		  //调用购物车 删除购物项方法
		cart.removeCartItem(pid);
		  //重定向到/jsp/cart.jsp
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");

		return null;

}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		   //获取购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
           //调用购物车上的清空购物车方法
		cart.clearCart();
		   //重定向到jsp/cart.jsp
        response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
	
        return null;

	}
}
