package cn.zhdt.store.service;

import java.sql.SQLException;
import java.util.List;

import cn.zhdt.store.domain.Category;


public interface CategoryService {

	List<Category> findCategory()throws Exception;

}
