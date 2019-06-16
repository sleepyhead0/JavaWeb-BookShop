package cn.zhdt.store.dao.daolmp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.zhdt.store.dao.ProductDao;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.utils.JDBCUtils;
import redis.clients.jedis.ScanParams;

public class ProductDaoImp implements ProductDao {

	@Override
	public List<Product> findByHot() throws SQLException {
		
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select *from product where is_hot=1 order by pdate desc limit 8", 
				      new BeanListHandler<Product>(Product.class) 
				  );
		
		
	}

	@Override
	public List<Product> findByNew() throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select *from product where  pflag =0 order by pdate desc limit 8", 
				      new BeanListHandler<Product>(Product.class) 
				  );
		
	}

	@Override
	public Product findByXing(String pid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select *from product where pid=?", 
			      new BeanHandler<Product>(Product.class),
			  pid);
	}

	@Override
	public int findCount(String cid) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long totalSize = (Long) qr.query("SELECT count(*) FROM product WHERE cid=?", 
				new ScalarHandler(),cid);	
		return totalSize.intValue();
	}

	@Override
	public List<Product> findProductBycid(String cid, int currentPage) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return  qr.query("select * from product where cid =? limit ?,?", 
				new BeanListHandler<Product>(Product.class),
				cid,(currentPage-1)*page_size,page_size);
		
	}

	
}
