package com.atguigu.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
	private Map<Integer, ShoppingCartItem> books = new HashMap<>();

	/*
	 * 修改制定购物项的数量
	 */
	public void updateItemQuantuty(Integer id, int quantity) {
		ShoppingCartItem srcCartItem = books.get(id);
		if (srcCartItem != null) {
			srcCartItem.setQuantity(quantity);
		}
	}

	/*
	 * 移除指定的购物项
	 */
	public void removeItem(Integer id) {
		books.remove(id);
	}

	/*
	 * 清空购物车
	 */
	public void clear() {
		books.clear();
	}

	/*
	 * 返回购物车是否为空
	 */
	public boolean isEmpty() {
		return books.isEmpty();
	}

	/*
	 * 获取购物车中所有的商品的总的钱数
	 */
	public float getTotalMoney() {
		float total = 0;
		for (ShoppingCartItem srcCartItem : getitems()) {
			total+=srcCartItem.getItemMoney();
		}
		return total;
	}
    /*
     * 获取购物车中的所有的 ShoppingCartItem 组成的集合
     */
	public Collection<ShoppingCartItem> getitems() {
		// TODO Auto-generated method stub
		return books.values();
	}
	
	/*
	 * 返回购物车中饭商品的总数量
	 */
	public int getBookNumber(){
		int total=0;
		for (ShoppingCartItem shoppingCartItem:books.values()) {
			total+=shoppingCartItem.getQuantity();
		}
		return total;
	}
	
	public Map<Integer, ShoppingCartItem> getBooks() {
		return books;
	}
	
	/**
	 * 检验购物车中是否有 id 指定的商品		
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id){
		return books.containsKey(id);
	}
	/**
	 * 向购物车中添加一件商品		
	 * @param book
	 */
	public void addBook(Book book){
		//1. 检查购物车中有没有该商品, 若有, 则使其数量 +1, 若没有, 
	    //新创建其对应的 ShoppingCartItem, 并把其加入到 books 中
		ShoppingCartItem src=books.get(book.getId());
		if(src==null){
			src=new ShoppingCartItem(book);
			books.put(book.getId(), src);
		}else{
			src.increment();
		}
	}
}
