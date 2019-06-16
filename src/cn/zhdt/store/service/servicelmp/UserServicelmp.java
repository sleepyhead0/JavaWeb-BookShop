package cn.zhdt.store.service.servicelmp;

import java.sql.SQLException;

import cn.zhdt.store.dao.UserDao;
import cn.zhdt.store.dao.daolmp.UserDaolmp;
import cn.zhdt.store.domain.Student;
import cn.zhdt.store.service.UserService;



public class UserServicelmp implements UserService {
	/**
	 * 用户注册
	 */
	public void insert(Student student) throws SQLException {
	
		UserDao userDao = new UserDaolmp();
		userDao.insert(student);		
	}

	/**
	 * 用户激活
	 */
	@Override
	public Boolean userActive(String code) throws SQLException {

		   UserDao us = new UserDaolmp();
		  Student student= us.findByCode(code);
		if(student!=null){
			//查找 到用户
			//修改状态和code的值
			student.setState(1);  //表示激活了
			student.setCode(null);  //激活后code必须鸿特精密人 code只能使用一次
			us.update(student);
			return true;
			
		}else{
			//无法查找到用户
			return false;
		}
	}


	/**
	 * 登录用户
	 * @return 
	 */
	@Override
	public Student loginUser(Student student) throws SQLException {
		UserDao logn = new UserDaolmp();
		Student st=logn.find(student);
	   //判断用户是否存在
		if(st==null){
			//该用户不存在 帐号和密码错误
			throw new RuntimeException("用户的帐号和密码不正确");
		}else if(st.getState()==0){
			//用户没有激活
			throw new RuntimeException("该用户没有激活");
		}else{
			//登录成功
			return st;
		}
	}
}

