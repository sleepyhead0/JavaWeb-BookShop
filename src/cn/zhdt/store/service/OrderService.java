package cn.zhdt.store.service;

import java.sql.SQLException;

import cn.zhdt.store.domain.Order;
import cn.zhdt.store.domain.Page;
import cn.zhdt.store.domain.Student;

public interface OrderService {

	void saveOrder(Order order)throws SQLException ;

	Page<Order> findMyorders(int currentPage, Student student)throws Exception;

}
