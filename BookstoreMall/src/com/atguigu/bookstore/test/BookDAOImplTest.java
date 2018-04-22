package com.atguigu.bookstore.test;

import static org.junit.Assert.fail;

 

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.dao.BookDAO;
import com.atguigu.bookstore.dao.imp.BookDAOImpl;
import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.web.CriteriaBook;
import com.atguigu.bookstore.web.Page;

public class BookDAOImplTest {
	BookDAO bookDAO=new BookDAOImpl();
	@Test
	public void testGetBook() {
		 
		Book book=bookDAO.getBook(5);
		System.out.println(book);
	}

	@Test
	public void testGetPage() {
		CriteriaBook cBook=new CriteriaBook(0,Integer.MAX_VALUE, 3);
		Page<Book> page=bookDAO.getPage(cBook);
		System.out.println("PageNo:"+page.getPageNo()+"");
		System.out.println("totalPageNumber:"+page.getTotalPageNumber());
		System.out.println("list:"+page.getList());
		System.out.println("prevpage:"+page.getPrevPage());
		System.out.println("nextPage"+page.getNextPage());
	}

	@Test
	public void testGetTotalBookNumber() {
		
	}

	@Test
	public void testGetPageList() {
		CriteriaBook cBook=new CriteriaBook(0,Integer.MAX_VALUE, 3);
		List<Book> bookList=bookDAO.getPageList(cBook, 3);
		System.out.println("bookList"+bookList);
	}

	@Test
	public void testGetStoreNumber() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchUpdateStoreNumberAndSalesAmount() {
		fail("Not yet implemented");
	}

}
