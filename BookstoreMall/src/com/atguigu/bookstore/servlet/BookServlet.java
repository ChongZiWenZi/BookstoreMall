package com.atguigu.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.domain.Account;
import com.atguigu.bookstore.domain.Book;
import com.atguigu.bookstore.domain.ShoppingCart;
import com.atguigu.bookstore.domain.ShoppingCartItem;
import com.atguigu.bookstore.domain.User;
import com.atguigu.bookstore.service.AccountService;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.UsersServise;
import com.atguigu.bookstore.web.BookStoreWebUtils;
import com.atguigu.bookstore.web.CriteriaBook;
import com.atguigu.bookstore.web.Page;
import com.google.gson.Gson;

public class BookServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	private BookService bookService = new BookService();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String methodNameString = request.getParameter("method");
		Method method;
		try {
			// 返回一个Method对象
			method = getClass().getDeclaredMethod(methodNameString,
					HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = -1;
		Book book = null;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {

		}
		if (id > 0) {
			book = bookService.getBook(id);
		}
		if (book == null) {
			response.sendRedirect(request.getContextPath() + "/error-1.jsp");
			return;
		}
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(
				request, response);
	}

	protected void getBooks(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);

		} catch (NumberFormatException e) {
			// TODO: handle exception
		}

		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getpage(criteriaBook);
		request.setAttribute("bookpage", page);
		try {
			request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(
					request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	protected void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(sc);
		request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp").forward(
				request, response);
	}

	public void remove(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
		}
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.removeItemFromShoppingCart(sc, id);
		if (sc.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/pages/emptycart.jsp")
					.forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(
				request, response);
	}

	public void addToCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. 获取商品的 id
		String idString = request.getParameter("id");
		System.out.println("addToCart" + request.getParameter("title"));
		int id = -1;
		boolean flag = false;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
		}
		if (id > 0) {
			// 2. 获取购物车对象
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);

			// 3. 调用 BookService 的 addToCart() 方法把商品放到购物车中
			flag = bookService.addToCart(id, sc);
		}
		if (flag) {
			// 4. 直接调用 getBooks() 方法.
			getBooks(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/error-1.jsp");
	}

	protected void forwardPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp")
				.forward(request, response);
	}

	public void updateItemQuantity(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		String quantityString = request.getParameter("quantity");
		ShoppingCart sCart = BookStoreWebUtils.getShoppingCart(request);
		int id = -1;
		int quantity = -1;
		try {
			id = Integer.parseInt(idString);
			quantity = Integer.parseInt(quantityString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (id > 0 && quantity > 0) {
			bookService.updateItemQuantity(sCart, id, quantity);
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("bookNumber", sCart.getBookNumber());
		resultMap.put("totalMoney", sCart.getTotalMoney());
		Gson gson = new Gson();
		String jsonString = gson.toJson(resultMap);
		response.setContentType("text/javascript");
		response.getWriter().print(jsonString);
	}

	private UsersServise usersServise = new UsersServise();

	public void cash(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 简单验证 验证表单域的值是否符合基本的规范：是否为空 是否可以转int 是否是一个email
		String usernameString = request.getParameter("username");
		String accountId = request.getParameter("accountId");
		StringBuffer errorsbuffer = vaildateFromField(usernameString, accountId);
		//表单验证通过
		if(errorsbuffer.toString().equals("")){
			errorsbuffer=vaildateUser(usernameString, accountId);
			//帐号密码通过
			if(errorsbuffer.toString().equals("")){
				errorsbuffer=validateBookStoreNumber(request);
				//库存验证通过
				System.out.print(errorsbuffer+"库存验证通过");
				if(errorsbuffer.toString().equals("")){
					errorsbuffer=vaildateBalance(request,accountId);
					System.out.print(errorsbuffer+"yue验证通过");
				}
			}
		}
		
		if (!errorsbuffer.toString().equals("")) {
			request.setAttribute("errors", errorsbuffer);
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(
					request, response);
			return;
		}
	}
	
  private AccountService accountService=new AccountService();
	// 验证余额
	public StringBuffer vaildateBalance(HttpServletRequest request,String accountId) {
		StringBuffer errBuffer4=new StringBuffer("");
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		Account account=accountService.getAccount(Integer.parseInt(accountId));
		if(cart.getTotalMoney()>account.getBalance()){
			errBuffer4.append("余额不足");
		}
		return errBuffer4;
	}

	// 验证库存
	public StringBuffer validateBookStoreNumber(HttpServletRequest request) {
		StringBuffer errBuffer3=new StringBuffer("");
		ShoppingCart cart=BookStoreWebUtils.getShoppingCart(request);
		for(ShoppingCartItem srCartItem :cart.getitems()){
			int quantity = srCartItem.getQuantity();
			int storeNumber = bookService.getBook(srCartItem.getBook().getId()).getStoreNumber();
			
			if(quantity > storeNumber){
				errBuffer3.append(srCartItem.getBook().getTitle() + "库存不足<br>");
			}
		}
		return errBuffer3;
	}

	// 验证帐号名匹配
	public StringBuffer vaildateUser(String username, String accountId) {
		boolean flag = false;
		User user = usersServise.getUserByUserName(username);
		if (user != null) {
			int accountIdString = user.getAccountId();
			if (accountId.trim().equals("" + accountIdString)) {
				flag = true;
			}
		}
		StringBuffer errorsBuffer2 = new StringBuffer("");
		if (!flag) {
			errorsBuffer2.append("帐号不匹配!");
		}
		return errorsBuffer2;
	}

	// 验证表单空判断
	public StringBuffer vaildateFromField(String usernameString,
			String accountId) {
		StringBuffer errorsbuffer = new StringBuffer();
		if (usernameString == null || usernameString.equals("")) {
			errorsbuffer.append("用户名不能为空");
		}
		if (accountId == null || accountId.equals("")) {
			errorsbuffer.append("帐号不能为空");
		}
		return errorsbuffer;
	}

}
