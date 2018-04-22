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
<%@ include file="/comons/queryCondition.jsp" %>	

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
	//ajax修改单个商品的数量
	//1.获取页面中所有的text，为其添加onChange响应函数：弹出确认对话框：确定要修改吗
	$(document).ready(function(){
	 $(":text").change(function(){
	  
	   var quantityVal = $.trim(this.value);
	   var flag=false;
	   var reg=/^\d+$/g;
	   var quantity=-1;
	  if(reg.test(quantityVal)){
	   	    quantity=parseInt(quantityVal);
	   	    if(quantity>=0){
	   	    	flag=true;
	   	    }
	   }
	   if(!flag){
	    alert("输入的数量不合法");
	    $(this).val($(this).attr("class"));
	    return;
	   }
	 var $tr = $(this).parent().parent();
	 var title = $.trim($tr.find("td:first").text());
	 if(quantity==0){
	   var flag2=confirm("确定要删除"+title+"数量吗");
	   if(flag2){
	   //得到a节点
	    var $a=$tr.find("td:last").find("a");
	    //执行a节点的onclick响应函数
	    //alert($a[0].onclick);
	      $a[0].onclick();
		return;
	   
	     }
	 
	   }
	   var flag = confirm("确定要修改" + title + "的数量吗?");
	   if(!flag){
				$(this).val($(this).attr("class"));
				return;
			}
	    //请求地址为bookServlets
	    var url="bookServlets";
	    //请求方法为method:updateItemQuantity,id,name属性值,
	    var idVal=$.trim(this.name);
	    var args={"method":"updateItemQuantity","id":idVal,"quantity":quantityVal, "time":new Date()};
	    //4. 在 updateItemQuantity 方法中, 获取 quanity, id, 再获取购物车对象, 调用 service 的方法做修改
		//5. 传回 JSON 数据: bookNumber:xx, totalMoney
			
		//6. 更新当前页面的 bookNumber 和 totalMoney
		$.post(url,args,function(data){
		  var bookNumber=data.bookNumber;
		  var totalMoney=data.totalMoney;
		  
		  $("#totalMoney").text("总金额￥"+totalMoney);
		  $("#bookNumber").text("您的购物车中共有" + bookNumber + "本书");
		},"JSON"); 
	});
	});
</script>

</head>

<body>
	<div id="bookNumber" align="center">您的购物车中共有 ${sessionScope.ShoppingCart.bookNumber } 本书</div>
	<br>
	
	<table cellpadding="10" align="center">
		<tr>
			<td>Title</td>
			<td>Quantity</td>
			<td>Price</td>
			<td>&nbsp;</td>
		</tr>
		<c:forEach items="${sessionScope.ShoppingCart.items }" var="item">
			<tr>
				<td>${item.book.title }</td>
				<td><input class="${item.quantity }" type="text" size="1" name="${item.book.id }" value="${item.quantity }"/></td>
				<td>${item.book.price }</td>
				<td><a
					href="bookServlets?method=remove&pageNo=${param.pageNo }&id=${item.book.id }"
					class="delect">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4"><div  id="totalMoney">总金额：${ sessionScope.ShoppingCart.totalMoney }</div></td>
		</tr>
		<tr>
			<td colspan="4"><a
				href="bookServlets?method=getBooks&pageNo=${param.pageNo }">继续购物</a>
				&nbsp;&nbsp; <a href="bookServlets?method=clear">清空购物车</a>
				&nbsp;&nbsp; <a href="bookServlets?method=forwardPage&page=cash">结账</a>
				<br> &nbsp;&nbsp;
		</tr>
		<br>
	</table>
</body>
</html>
