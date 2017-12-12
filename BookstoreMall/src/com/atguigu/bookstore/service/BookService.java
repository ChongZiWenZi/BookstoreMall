package com.atguigu.bookstore.service;

import com.atguigu.bookstore.dao.BookDAO;
import com.atguigu.bookstore.dao.imp.BookDAOImpl;
import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.web.CriteriaBook;
import com.atguigu.bookstore.web.Page;

public class BookService {
	private BookDAO bookDAO=new BookDAOImpl();
    public Page<Book> getpage(CriteriaBook criteriaBook){
    	return bookDAO.getPage(criteriaBook);
    }
}
