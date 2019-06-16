package cn.zhdt.store.service;

import java.sql.SQLException;
import java.util.Map;

import cn.zhdt.store.domain.Student;

public interface UserService {
	/**
	 * 用户注册
	 * @param user
	 * @throws SQLException
	 */
	void insert(Student student) throws SQLException;
	
    /**
     * 用户激活
     * @param code
     * @return
     */
	Boolean userActive(String code)throws SQLException;
   
	/**
	 * 用户登录
	 * @param par
	 * @throws SQLException
	 */

	Student loginUser(Student student)throws SQLException;
        
	
	
}
