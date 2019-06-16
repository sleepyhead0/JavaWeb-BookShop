package cn.zhdt.store.dao;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.domain.Category;

public interface CategoryDao {
  /**
   * 商品详细信息
   * @return
   * @throws Exception
   */
	List<Category> findCategor()throws Exception;
	
}