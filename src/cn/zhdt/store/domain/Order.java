package cn.zhdt.store.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	
	
      private String oid ;        //订单编号 
      private Date ordertime;     //下单时间
      private double total;       //总计
      private int state ;         //状态
      private String address;     //收货人地址
      private String name ;       //收货人地址
      private String telephone;   //收货人姓名
     
      // private String uid;
      // 1.程序 对象和对象发生关系，而不是对象和对象的属性发生关系
      // 2.设计Order目的：让order携带订单上的向service,dao传参，user对象是可以携带更多的数据 
      private Student student;
      
      //程序中体现订单对象和订单项之间关系
      private List<OrderItem> list = new ArrayList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<OrderItem> getList() {
		return list;
	}

	public void setList(List<OrderItem> list) {
		this.list = list;
	}
      
      
}
