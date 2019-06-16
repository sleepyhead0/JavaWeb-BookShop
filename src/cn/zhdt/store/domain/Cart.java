package cn.zhdt.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
   private double total;    // 总计 /积分
   Map<String,CartItem> map =new HashMap<String,CartItem>();   // key :pid   value: 购物项CartItem
  
   //添加购物项到购物车
   public void addCartItemToCart(CartItem cartItem){
	   /*分析：
	    *    先判断是否添加过该购物项：cartItem可以拿到pid ,map中的key中如果包含pid说明添加过，没有就没有添加过
	    *    没添加过： map.put(pid ,CartItem)
	    *    添加过，先找到原来pid所对应的cartItem中的数量 +新添加的购物项中的数量， 加起来给原来的购物项
	    */
	   String pid =cartItem.getProduct().getPid();
	   if(map.containsKey(pid)){
		   //说明原来的购物车中有该pid的购物项
		   CartItem oldCartItem=map.get(pid);
		   oldCartItem.setNum(oldCartItem.getNum()+cartItem.getNum());
	   }else{
		   map.put(pid, cartItem);
	   }
   }
   
   //清空购物车
   public void clearCart(){
	   map.clear();
   }
   
   //清楚购物项
   public void removeCartItem(String pid){
	   map.remove(pid);
   }
   
   //总计可以算出来 
public double getTotal() {
	total=0;
	//遍历所有map中的购物项cartItem,然后把所胡购物项中小计加起来
	Collection<CartItem> cartItems =map.values();
	for (CartItem cartItem : cartItems) {
		total+=cartItem.getSubTotal();
	}
	return total;
	
}

    //把购物项提取出来 
    public Collection<CartItem> getCartItems(){
    	return map.values();
    }

public void setTotal(double total) {
	this.total = total;
}
public Map<String, CartItem> getMap() {
	return map;
}
public void setMap(Map<String, CartItem> map) {
	this.map = map;
}
   
}
