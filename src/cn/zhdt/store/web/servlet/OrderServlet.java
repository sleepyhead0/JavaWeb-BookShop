package cn.zhdt.store.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhdt.store.domain.Cart;
import cn.zhdt.store.domain.CartItem;
import cn.zhdt.store.domain.Order;
import cn.zhdt.store.domain.OrderItem;
import cn.zhdt.store.domain.Page;
import cn.zhdt.store.domain.Student;
import cn.zhdt.store.service.OrderService;
import cn.zhdt.store.service.servicelmp.OrderServiceImp;
import cn.zhdt.store.utils.UUIDUtils;
import cn.zhdt.store.web.base.BaseServlet;

public class OrderServlet extends BaseServlet {

    /*
     *保存订单和订单项到数据库
     */
	public String saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		
		//获取用户和购物车
		Student student  = (Student)request.getSession().getAttribute("student");
		if(null==student){
			request.setAttribute("msg","请先登录");
			return "/jsp/info.jsp";
		}
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		//创建订单对象
		Order order = new Order();
		order.setOid(UUIDUtils.getCode());
		order.setOrdertime(new Date());
		order.setState(1);
		order.setTotal(cart.getTotal());
		order.setStudent(student);
		
		//遍历购物项，将第一个购物项的内容放入订单项中
		for (CartItem item : cart.getCartItems()) {
			
			//创建订单项
			OrderItem orderItem = new OrderItem();
			orderItem.setItemid(UUIDUtils.getCode());
			orderItem.setQuantity(item.getNum());
			orderItem.setTotal(item.getSubTotal());
			orderItem.setProduct(item.getProduct());
			orderItem.setOrder(order);
			//每创建一个订单项，就往订单的List<OrderItem> list中去放
			order.getList().add(orderItem);
		}
		 //访问业务层同时保存订单和订单项
		OrderService orderService = new OrderServiceImp();
		orderService.saveOrder(order);
		
		//清空购物车
		cart.clearCart();
		
		//保存订单到域对象中
		request.getSession().setAttribute("order", order);
		
		//转发到jsp页面
		return "/jsp/order_info.jsp";
		
	}

	public String findMyOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接收当前页码
		String cid = request.getParameter("cid");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		Student student = (Student)request.getSession().getAttribute("student");
		System.out.println(student);
		
		OrderService orderService = new OrderServiceImp();
		Page<Order> page=orderService.findMyorders(currentPage,student);
		System.out.println(page);
		
		//保存到域对象
		request.setAttribute("page", page);
		request.setAttribute("cid", cid);
		return "/jsp/order_list.jsp";
	}
}
