package cn.zhdt.store.service.servicelmp;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.dao.OrderDao;
import cn.zhdt.store.dao.ProductDao;
import cn.zhdt.store.dao.daolmp.OrderDaoImp;
import cn.zhdt.store.dao.daolmp.ProductDaoImp;
import cn.zhdt.store.domain.Order;
import cn.zhdt.store.domain.OrderItem;
import cn.zhdt.store.domain.Page;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.domain.Student;
import cn.zhdt.store.service.OrderService;
import cn.zhdt.store.utils.JDBCUtils;

public class OrderServiceImp implements OrderService {

	@Override
	public void saveOrder(Order order) throws SQLException {
		
		//去dao层保存订单和订单项到数据库中，要求必须同时成功才可以，如果有一个不成功name就失败，就回滚
		
		try {
			JDBCUtils.startTransaction();  //开开启事务功能
			OrderDao orderDao = new OrderDaoImp();
			//保存订单到数据库
			orderDao.saveOrder(order);
			//保存订单到数据库
			for (OrderItem orderItem : order.getList()) {
				orderDao.saveOrderItem(orderItem);
			}
			JDBCUtils.commitAndClose();   //提交事务
		} catch (Exception e) {
			JDBCUtils.rollbackAndClose();  //回滚事务只要有异常
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public Page<Order> findMyorders(int currentPage, Student student) throws Exception {
		Page<Order> page = new Page<Order>();
		    //当前页  currentPage
		    //总页数  totalPage
		    //每页记录数  pageSize
		     //总的记录数  totalSize
		    //当前页的学生集合   List<T> list; 
		
		//当前页 currentPage;
		page.setCurrentPage(currentPage);
		
		
		//每页记录数  pageSize;   
		int pageSize=ProductDao.page_size;
		page.setPageSize(pageSize);
		
		
	    //总的记录数   totalSize;      
		OrderDao orderDao = new OrderDaoImp();
		int totalSize =orderDao.findCount(student);
		page.setTotalSize(totalSize);	
		
		
		 //总页数 totalPage;
		//totalSize/  pageSize 
		int totalPage=totalSize % pageSize ==0 ?  totalSize / pageSize : (totalSize / pageSize)+1;
		page.setTotalPage(totalPage);
		
		
	    //当前页的学生集合  List<T> list; 
		List<Order> list =orderDao.findProductBycid(student,currentPage);
		page.setList(list);
		return page;
	}

}
