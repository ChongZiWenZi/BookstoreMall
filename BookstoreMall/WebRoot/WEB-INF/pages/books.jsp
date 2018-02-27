<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%request.setCharacterEncoding ("UTF-8");%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Insert title here</title>

 <!-- <script type="text/javascript" src="http://qh-20171125ijky:8080/BookstoreMall/jquery-1.7.2.min.js"></script>  -->
 
 <script type="text/javascript" src="jquery-1.7.2.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("a").click(function() {

			var serializeVal = $(":hidden").serialize();
			var href = this.href + "&" + serializeVal;
			window.location.href = href;
			return false;
		});

		$("#pageNo").change(function() {
							var val = $(this).val();
							val = $.trim(val);

							//校验val是否为数字  
							var flag = false;
							var reg = /^\d+$/g;
							var pageNo=0;
							if (reg.test(val)) {
							  pageNo = parseInt(val);
							//校验val在一个合法的范围内：
							 if (pageNo>=1&&pageNo<=parseInt("${bookpage.totalPageNumber}")) {
							 	flag = true;
							 }
							}
							

							if (!flag) {
								alert("输入的不是合法的页码");
								$(this).val("");
								return;
							}
							//页面跳转
						     var href = "bookServlets?method=getBooks&pageNo="
									+ pageNo + "&"
									+ $(":hidden").serialize();  
									alert(href);
									alert(window.location.href);
							window.location.href = href;  
							
						});
	})
</script>
</head>
<input type="hidden" name="minPrice" value="${param.minPrice}" />
<input type="hidden" name="maxPrice" value="${param.maxPrice}" />
<body>
	<center>
	
	    <c:if test="${param.title != null}">
			您已经将 ${param.title} 放入到购物车中. 
			<br><br>
		</c:if>
		
	    <c:if test="${!empty sessionScope.ShoppingCart.books }">
			您的购物车中有 ${sessionScope.ShoppingCart.bookNumber } 本书, <a href="bookServlets?method=forwardPage&page=cart&pageNo=${bookpage.pageNo }">查看购物车</a>
		</c:if>
	   
	  
		<br> <br>
		<form action="bookServlets?method=getBooks" method="post">
			<input type="text" size="1" name="minPrice" /> <input type="text"
				size="1" name="maxPrice" /> <input type="submit" value="Submit" />
		</form>
		<br> <br>
		<table cellpadding="10">
			<c:forEach items="${bookpage.list}" var="book">
				<tr>
					<td><a href="bookServlets?method=getBook&pageNo=${bookpage.pageNo}&id=${book.id}">${book.title}</a> <br> ${book.author}</td>
					<td>${book.price}</td>

					<td><a href="bookServlets?method=addToCart&pageNo=${bookpage.pageNo }&id=${book.id}&title=${book.title}">加入购物车</a></td>
				</tr>

			</c:forEach>
		</table>
		<br> <br> 共${bookpage.totalPageNumber}页 &nbsp;&nbsp;
		当前第${bookpage.pageNo}页 &nbsp;&nbsp;
		<c:if test="${bookpage.hasPrev}">
			<a href="bookServlets?method=getBooks&pageNo=1">首页</a>
               &nbsp;&nbsp;
                   <a
				href="bookServlets?method=getBooks&pageNo=${bookpage.prevPage}">上一页</a>
                      &nbsp;&nbsp;
          </c:if>
		<c:if test="${bookpage.hasNext}">
			<a href="bookServlets?method=getBooks&pageNo=${bookpage.nextPage}">下一页</a>
               &nbsp;&nbsp;
                   <a
				href="bookServlets?method=getBooks&pageNo=${bookpage.totalPageNumber}">末页</a>
                      &nbsp;&nbsp;
          </c:if>
		&nbsp;&nbsp; &nbsp;&nbsp; 转到 <input type="text" size="1" id="pageNo" />页
	</center>
</body>
</html>
