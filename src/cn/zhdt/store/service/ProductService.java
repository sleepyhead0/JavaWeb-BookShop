package cn.zhdt.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.domain.Page;
import cn.zhdt.store.domain.Product;

public interface ProductService {
   /**
    * 查询热门商品
    * @return
    * @throws Exception
    */
	List<Product> findByHot()throws SQLException ;
   /**
    * 查询最新商品
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
	Page<Product> findByCid(String cid, int currentPage)throws SQLException ;
	

}
