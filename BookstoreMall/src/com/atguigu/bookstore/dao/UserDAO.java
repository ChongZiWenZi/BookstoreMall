package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.domain.User;


public interface UserDAO {

	/**
	 * 根据用户名查询用户的信息
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);

}

