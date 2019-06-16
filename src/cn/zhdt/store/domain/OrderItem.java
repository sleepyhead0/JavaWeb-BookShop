package cn.zhdt.store.domain;

public class OrderItem {
       
	
	  private String itemid;     // id
	  private int quantity;      // 数量
	  private double total;      //小计
	  
	  // 1. 对象对应对象
	  // 2. product,order携带更多的数据
	  private Product product;    //携带pid
	  private Order order;        //携带uid
	  
	public String getItemid() {
		return itemid;
	}
	public void setItemid(String itemid) {
		this.itemid = itemid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	  
}  
