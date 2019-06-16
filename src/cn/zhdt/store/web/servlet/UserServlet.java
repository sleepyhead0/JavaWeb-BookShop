package cn.zhdt.store.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhdt.store.domain.Student;
import cn.zhdt.store.service.UserService;
import cn.zhdt.store.service.servicelmp.UserServicelmp;
import cn.zhdt.store.utils.MailUtils;
import cn.zhdt.store.utils.MyBeanUtils;
import cn.zhdt.store.utils.UUIDUtils;
import cn.zhdt.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {

	
	public String registUi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/register.jsp";
	}
	
    public String loginUi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/jsp/login.jsp";
	}
	/**
	 * 注册用户
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
   public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		      try{        
	       //接收参数
	             Map<String, String[]>  map = request.getParameterMap();
	                   //创建Student对象
	             Student student = new Student();
	             //使用MybeanUtils
	             MyBeanUtils.populate(student, map);
	             student.setUid(UUIDUtils.getId());
	             student.setCode(UUIDUtils.getCode());
	             student.setState(0);
	         //    System.out.println(student);
	             
	            UserService userService = new UserServicelmp();
	            userService.insert(student);
	            //注册成功，向用户邮箱发送信息，跳转到提示页面
	            //发送邮件
                MailUtils.sendMail(student.getEmail(), student.getCode());
	            request.setAttribute("msg", "用户注册成功，请激活");
	            
		      }catch(Exception e){
		    	  //注册徐峰，跳转到提示页面
		    	  request.setAttribute("msg", "用户注册失败，请重新注册");
		      }
		return "/jsp/info.jsp";
	}
   
   /**
    * 激活用户
    * @param request
    * @param response
    * @return
    * @throws ServletException
    * @throws IOException
    * @throws SQLException
    */
   public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		//获取激活码
	   String code = request.getParameter("code");
	     
	   //调用service激活是否成功
	       UserService us = new UserServicelmp();
	       Boolean flag =  us.userActive(code);
	       
	     if(flag)  {
	    	 //如果成功，提示信息并跳转到登录界面
	    	 request.setAttribute("msg", "激活成功，请登录 ");
	    	 return "/jsp/login.jsp";
	     }else{
	    	 
	    	  //如果失败，提示激活失败
	    	 request.setAttribute("msg", "激活失败，请重新激活");
	    	 
	    	 return "/jsp/info.jsp";
	    	 
	     }
	}

   /**
    * 登录用户
    * @param request
    * @param response
    * @return
    * @throws ServletException
    * @throws IOException
    */
   public String userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
			//获得帐号和密码进行封装
			     Map<String, String[]> par = request.getParameterMap();
			 //创建集合
			  Student student = new Student();
			  MyBeanUtils.populate(student, par);
			  System.out.println(student);
			  UserService userService = new UserServicelmp();
			  Student st=userService.loginUser(student);
              //登录成功
              //保存到域对象
			  request.getSession().setAttribute("student", st);
			  response.sendRedirect(request.getContextPath()+"/index.jsp");
			  return null;
	    } catch (Exception e) {
		//登录失败
	    	  request.setAttribute("msg", e.getMessage());
	    		return "/jsp/login.jsp";
		  }
	
	}
   
  
   /**
    * 退出用户
    */
   public String loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取域对象
	   HttpSession se = request.getSession();
	    //判断对象中是否有数据， 有清楚
	   if(se!= null){
		   se.removeAttribute("student");
	   }
		return "/jsp/login.jsp";
	}
}
