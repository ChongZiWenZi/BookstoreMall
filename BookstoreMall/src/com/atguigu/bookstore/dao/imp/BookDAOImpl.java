package com.atguigu.bookstore.dao.imp;

import java.util.Collection;
import java.util.List;

import com.atguigu.bookstore.dao.BookDAO;
import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.domain.ShoppingCartItem;
import com.atguigu.bookstore.web.CriteriaBook;
import com.atguigu.bookstore.web.Page;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO{
        
	@Override
	public Book getBook(int id) {
		String sql="SELECT id,author,title,price,publishingDate,salesAmount,storeNumber,remark FROM mybooks WHERE id=?";
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page page1=new Page<>(cb.getPageNo());
		page1.setTotalItemNumber((int)getTotalBookNumber(cb));
		page1.setList(getPageList(cb, 3));
		return page1;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql="SELECT count(id) "
				+ "FROM mybooks WHERE price>=? AND price <=?";
		return getSingleVal(sql, cb.getMinPrice(),cb.getMaxPrice());
	}

	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql="SELECT id,author,title,price,publishingDate,"
				+ "salesAmount,storeNumber,remark "
				+ "FROM mybooks WHERE price>=? AND price <=?"
				+"LIMIT ?,?";
		return queryForList(sql, cb.getMinPrice(),cb.getMaxPrice(),(cb.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public int getStoreNumber(Integer id) {
		String sql="SELECT storeNumber From mybooks WHERE id=?";
		return getSingleVal(sql, id);
	}

	@Override
	public void batchUpdateStoreNumberAndSalesAmount(
			Collection<ShoppingCartItem> items) {
		// TODO Auto-generated method stub
		
	}
	

}
