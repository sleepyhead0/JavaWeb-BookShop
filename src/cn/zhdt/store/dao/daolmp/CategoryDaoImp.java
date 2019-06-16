package cn.zhdt.store.dao.daolmp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.zhdt.store.dao.CategoryDao;
import cn.zhdt.store.domain.Category;
import cn.zhdt.store.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {

	@Override
	public List<Category> findCategor() throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select * from category", 
				new BeanListHandler<Category>(Category.class));
	}

	



	
		

}
