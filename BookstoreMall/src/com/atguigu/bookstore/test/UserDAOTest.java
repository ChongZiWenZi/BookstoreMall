package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.dao.UserDAO;
import com.atguigu.bookstore.domain.User;

public class UserDAOTest {
  UserDAO dao=new UserDAOImpl();
	@Test
	public void testGetUser() {
		 User user=dao.getUser("Tom");
		 System.out.println(user);
	}

}
