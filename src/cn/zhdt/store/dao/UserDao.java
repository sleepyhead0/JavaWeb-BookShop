package cn.zhdt.store.dao;

import java.sql.SQLException;

import cn.zhdt.store.domain.Student;

public interface UserDao {
   /**
    * 用户注册
    * @param student
    * @throws SQLException
    */
	void insert(Student student) throws SQLException;

	/**
	 * 用户激活
	 * @param code
	 * @return
	 */
	Student findByCode(String code)throws SQLException;
   
	/**
	 * 修改用户的信息
	 * @param student
	 * @throws SQLException
	 */
	void update(Student student)throws SQLException;

	
	
	/**
	 * 查找用户信息
	 * @param student
	 * @throws SQLException
	 */
	Student find(Student student)throws SQLException;


      
	
}
