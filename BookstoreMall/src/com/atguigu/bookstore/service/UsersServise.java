package com.atguigu.bookstore.service;

import com.atguigu.bookstore.dao.UserDAO;
import com.atguigu.bookstore.domain.User;
import com.atguigu.bookstore.test.UserDAOImpl;

public class UsersServise {
	private UserDAO dao = new UserDAOImpl();

	public User getUserByUserName(String username) {
           
		return dao.getUser(username);
	}
}
