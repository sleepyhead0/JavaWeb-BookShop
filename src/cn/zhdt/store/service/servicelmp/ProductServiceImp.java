package cn.zhdt.store.service.servicelmp;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.dao.ProductDao;
import cn.zhdt.store.dao.daolmp.ProductDaoImp;
import cn.zhdt.store.domain.Page;
import cn.zhdt.store.domain.Product;
import cn.zhdt.store.service.ProductService;

public class ProductServiceImp implements ProductService {

	public List<Product> findByHot()throws SQLException {
		ProductDao productDao = new ProductDaoImp();
		return productDao .findByHot();
		
	}

	@Override
	public List<Product> findByNew() throws SQLException  {
		ProductDao productDao = new ProductDaoImp();
		return productDao .findByNew();
	}

	@Override
	public Product findByXing(String pid) throws SQLException {
		ProductDao productDao = new ProductDaoImp();
	    return productDao .findByXing(pid);
	}

	@Override
	public Page<Product> findByCid(String cid, int currentPage) throws SQLException {
		// 创建page对象
		Page<Product> page=new Page<Product>();
		
		
		//当前页 currentPage;
		page.setCurrentPage(currentPage);
		
		
		//每页记录数  pageSize;   
		int pageSize=ProductDao.page_size;
		page.setPageSize(pageSize);
		
		
	    //总的记录数   totalSize;      
		//select count(*) from product where cid =?
		ProductDao productDao = new ProductDaoImp();
		int totalSize =productDao.findCount(cid);
		page.setTotalSize(totalSize);
		
		
		 //总页数 totalPage;
		//totalSize/  pageSize 
		int totalPage=totalSize % pageSize ==0 ?  totalSize / pageSize : (totalSize / pageSize)+1;
		page.setTotalPage(totalPage);
		
		
	    //当前页的学生集合  List<T> list; 
		List<Product> list =productDao.findProductBycid(cid,currentPage);
		page.setList(list);
//		page.setList(list);
		
		return page;
	}

	

	


}
