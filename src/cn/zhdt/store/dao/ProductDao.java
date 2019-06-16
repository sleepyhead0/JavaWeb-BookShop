package cn.zhdt.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.domain.Product;

public interface ProductDao {
	int page_size=12;
	  /**
	    * 查询热门商品
	    * @return
	    * @throws Exception
	    */
	List<Product> findByHot()throws SQLException ;
	  /**
	    * 查询新商品
	    * @return
	    * @throws Exception
	    */
	List<Product> findByNew()throws SQLException ;
	/**
	 * 查询详细商品信息
	 * @param pid
	 * @return
	 */
	Product findByXing(String pid)throws SQLException ;
	/**
	 * 商品分类
	 * @param cid
	 * @return
	 */
	int findCount(String cid)throws SQLException ;
	
	
	List<Product> findProductBycid(String cid, int currentPage)throws SQLException ;


}
