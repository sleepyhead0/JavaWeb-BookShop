package cn.zhdt.store.dao.daolmp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.zhdt.store.dao.UserDao;
import cn.zhdt.store.domain.Student;
import cn.zhdt.store.utils.JDBCUtils;

public class UserDaolmp implements UserDao {
      /**
       * 用户注册
       */
	public void insert(Student student) throws SQLException {
	
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update("insert into user values ( ?,?,?,?,?,?,?,?,?,?)",
				student.getUid(),student.getUsername(),student.getPassword(),student.getName(),student.getEmail(),student.getTelephone(),student.getBirthday(),student.getSex(),student.getState(),student.getCode());
	
	
}
  /**
   * 根据code去查寻user
 * @throws SQLException 
   */
	@Override
	public Student findByCode(String code) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query("select *from user where code=?", 
				      new BeanHandler<Student>(Student.class) ,
				  code);
		
	}
	
@Override
    public void update(Student student) throws SQLException {
	
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	qr.update("update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?",
			student.getUsername(),student.getPassword(),student.getName(),student.getEmail(),student.getTelephone(),student.getBirthday(),student.getSex(),student.getState(),student.getCode(),student.getUid()
			);
}
 

@Override
   public Student find(Student student) throws SQLException {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	    return qr.query("select*from user where username=? and password=?", 
			new BeanHandler<Student>(Student.class),
			student.getUsername(),student.getPassword());
	
}


	
}
