package cn.zhdt.store.web.servlet;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.zhdt.store.domain.Category;
import cn.zhdt.store.service.CategoryService;
import cn.zhdt.store.service.servicelmp.CategoryServiceImp;
import cn.zhdt.store.utils.JedisUtils;
import cn.zhdt.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;

@SuppressWarnings("serial")
public class CategoryServlet extends BaseServlet {

	public String findAllCatst(HttpServletRequest request, HttpServletResponse response) throws Exception {
//版本2
//		       去service拿Category的list集合
//		  CategoryService categoryservice = new CategoryServiceImp();
//		  List<Category> list= categoryservice.findCategory();
//		  System.out.println(list);
//		        把集合转换成json格式
//		  String jsonStr = JSONArray.fromObject(list).toString();
//		  System.out.println(jsonStr);
//		      把json发送给header页面
//		  response.setContentType("application/json;charset=utf-8");
//		  response.getWriter().print(jsonStr);
//		
//		  return null;
////版本3
//		
//		//判断redis中是否有缓存，有直接取，没有云数据库中找，在发送给浏览器
	    Jedis jedis = JedisUtils.getJedis();
	    String jsonStr = jedis.get("allCats");
	    if(jsonStr==null || jsonStr.equals("")){
//	    	
//			//redis中没有缓存
//			//去service拿Category的list集合
		    CategoryService categoryservice = new CategoryServiceImp();
		    List<Category> list= categoryservice.findCategory();
//		    
//		    
//		    //把集合转换成json类型
		    jsonStr = JSONArray.fromObject(list).toString();
		    System.out.println(jsonStr);
//		    
//		    
//		    //把获取到的json储存到redis中
		    jedis.set("allCats", jsonStr);
		    System.out.println("缓存中没有数据");
//		    
//		    
//		    //将部分类信息响应到客户端
//		    //告诉浏览器本次响应的数据是json格式的字符串
		    response.setContentType("application/json;charset=utf-8");
		    response.getWriter().print(jsonStr);    
//		    
	}else{
			//将部分类信息响应到客户端
		    //告诉浏览器本次响应的数据是json格式的字符串
			System.out.println("缓存中有数据");
		    response.setContentType("application/json;charset=utf-8");
	        response.getWriter().print(jsonStr);
	
	    }
		return null;

	}

}
