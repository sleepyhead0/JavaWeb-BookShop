package cn.zhdt.store.domain;

public class CartItem {
    private Product product;//商品对象
    private int num;        //数量
    private double subTotal;//小计
    
    //小计可以通过模型计算出来 
    public double getSubTotal() {
		return product.getShop_price()*num;
	}
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
    
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
