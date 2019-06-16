package cn.zhdt.store.service.servicelmp;

import java.sql.SQLException;
import java.util.List;


import cn.zhdt.store.dao.CategoryDao;
import cn.zhdt.store.dao.daolmp.CategoryDaoImp;
import cn.zhdt.store.domain.Category;
import cn.zhdt.store.service.CategoryService;

public class CategoryServiceImp implements CategoryService {

	@Override
	public List<Category> findCategory() throws Exception{
	     CategoryDao categoryDaoImp = new CategoryDaoImp();
	       return  categoryDaoImp.findCategor();
		 
	    
			}

}
