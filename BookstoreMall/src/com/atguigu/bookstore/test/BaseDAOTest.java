package com.atguigu.bookstore.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import com.atguigu.bookstore.dao.imp.BaseDAO;
import com.atguigu.bookstore.dao.imp.BookDAOImpl;

public class BaseDAOTest {
	 private BaseDAO baseDAO=new BaseDAO();
	 
	   BookDAOImpl bookDAOImpl=new BookDAOImpl();
	@Test
	public void testBaseDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		String sqlString="INSERT INTO trade (userid,tradetime) VALUES (?,?);";
		long id=baseDAO.insert(sqlString,1, new Date(new java.util.Date().getTime()));
		System.out.println(id+"");
	}

	@Test
	public void testUpdate() {
		String sql="UPDATE mybooks SET Title=? WHERE id =?";
		bookDAOImpl.update(sql, "安卓Android",1);
	}

	@Test
	public void testQuery() {
		fail("Not yet implemented");
	}

	@Test
	public void testQueryForList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSingleVal() {
		String sql="SELECT count(id) FROM mybooks";
		long count=bookDAOImpl.getSingleVal(sql);
		System.out.println(count);
	}

	@Test
	public void testBatch() {
		 String sqlString="UPDATE mybooks SET salesAmount=?,storeNumber=? WHERE id=?";
		 bookDAOImpl.batch(sqlString, new Object[]{1,1,1},new Object[]{2,2,2},new Object[]{3,3,3});
	}

}
