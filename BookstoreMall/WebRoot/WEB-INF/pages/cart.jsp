<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>


<script type="text/javascript">
	$(function() {
		$(".delect").click(function() {
			var $tr = $(this).parent().parent();
			var title = $.trim($tr.find("td:first").text());
			var flag = confirm("确定要删除" + title + "的信息吗？");
			if (flag) {
				return true;
			}
			return false;
		});
	});
	
</script>

</head>

<body>
	您的购物车中共有：${sessionScope.ShoppingCart.bookNumber}本书
	<br>
	<table cellpadding="10">
		<tr>
			<td>Title</td>
			<td>Quantity</td>
			<td>Price</td>
			<td>&nbsp;</td>
		</tr>

		<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
			<tr>
				<td>${item.book.title }</td>
				<td>${item.quantity }</td>
				<td>${item.book.price }</td>
				<td><a href="bookServlets?method=remove&pageNo=${param.pageNo }&id=${item.book.id }"
					class="delect">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4">总金额：${ sessionScope.ShoppingCart.totalMoney }</td>
		</tr>
		<tr>
			<td colspan="4"><a href="">继续购物</a> &nbsp;&nbsp; <a href="">清空购物车</a>
				&nbsp;&nbsp; <a href="">结账</a> <br> &nbsp;&nbsp;
		</tr>
		<br>

	</table>
</body>
</html>
