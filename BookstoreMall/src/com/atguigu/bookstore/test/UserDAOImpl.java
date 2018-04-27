package com.atguigu.bookstore.test;

import com.atguigu.bookstore.dao.UserDAO;
import com.atguigu.bookstore.dao.imp.BaseDAO;
import com.atguigu.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		String sql="select userId,username,accountId from userinfo where username=?";
		
		return query(sql, username);
	}

}
