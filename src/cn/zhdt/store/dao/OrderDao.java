package cn.zhdt.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.domain.Order;
import cn.zhdt.store.domain.OrderItem;
import cn.zhdt.store.domain.Student;

public interface OrderDao {
   int page_size=2;
	void saveOrder(Order order)throws Exception;

	void saveOrderItem(OrderItem orderItem)throws Exception;

	int findCount(Student student)throws SQLException;

	List<Order> findProductBycid(Student student, int currentPage)throws Exception;

}
