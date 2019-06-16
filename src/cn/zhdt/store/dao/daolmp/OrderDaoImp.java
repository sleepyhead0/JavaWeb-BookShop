package cn.zhdt.store.dao.daolmp;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.zhdt.store.dao.OrderDao;
import cn.zhdt.store.domain.Order;
import cn.zhdt.store.domain.OrderItem;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.domain.Student;
import cn.zhdt.store.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao {

	@Override
	public void saveOrder(Order order) throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
          qr.update("insert into orders values(?,?,?,?,?,?,?,?)",
          order.getOid(),order.getOrdertime(),order.getTotal(),
          order.getState(),order.getAddress(),order.getName(),
          order.getTelephone(),order.getStudent().getUid()
          );
		
	}

	@Override
	public void saveOrderItem(OrderItem orderItem) throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		  qr.update("insert into orderitem values(?,?,?,?,?)",
				  orderItem.getItemid(),
				  orderItem.getQuantity(),
				  orderItem.getTotal(),
				  orderItem.getProduct().getPid(),
				  orderItem.getOrder().getOid()
		    );
	}

	@Override
	public int findCount(Student student) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long totalSize=(Long)qr.query("select count(*) from orders where uid=?",new ScalarHandler(), student.getUid());
		return totalSize.intValue();
	}

	@Override
	public List<Order> findProductBycid(Student student, int currentPage) throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query("select * from orders where uid=? limit ?,?", new BeanListHandler<Order>(Order.class),
				student.getUid(),(currentPage-1)*page_size,page_size
				);
		for (Order order : list) {
			//遍历第一个order，找到对应的orderItem对象,放入order中
			String oid =order.getOid();
			String sql="select * from orderitem o,product p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list01=qr.query(sql, new MapListHandler(),oid);
			
			for (Map<String, Object> map : list01) {
				OrderItem orderItem = new OrderItem();
				Product product = new Product();
				
				// 方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
				// 1_创建时间类型的转换器
				DateConverter dt = new DateConverter();
				// 2_设置转换的格式
				dt.setPattern("yyyy-MM-dd");
				// 3_注册转换器
				ConvertUtils.register(dt, java.util.Date.class);
				//就相当于将map中的各个value的值，通过setXXX() 方法放入对应的对象中
				BeanUtils.populate(orderItem, map);
				BeanUtils.populate(product, map);
				
				orderItem.setProduct(product);
				order.getList().add(orderItem);
			}
		}
		
		
		return list;
	}

}
