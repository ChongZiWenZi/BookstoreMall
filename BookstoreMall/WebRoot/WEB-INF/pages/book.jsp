<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript">
	
	$(function(){
		$("a").each(function(){
			this.onclick = function(){
				var serializeVal = $(":hidden").serialize();
				var href = this.href + "&" + serializeVal;
				window.location.href = href;			
				return false;
			};
		});
	});	
	
</script>


    <title>My JSP 'book.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script type="text/javascript"
	src="http://qh-20171125ijky:8080/BookstoreMall/jquery-1.7.2.min.js"></script>
 
  <%--  <script type="text/javascript" src="<%=basePath%>script/jquery-1.7.2.min.js"></script>  --%>
  </head>
  
  <body>
 <input type="hidden" name="minPrice" value="${param.minPrice }"/>
<input type="hidden" name="maxPrice" value="${param.maxPrice }"/>
   <center>
      <br> <br>
      Title:${book.title }
       <br> <br>
       Author:${book.author }
        <br> <br>
        Price:${book.price }
         <br> <br>
       PublishingDate:${book.publishingDate }
       <br> <br>
       Remark:${book.remark }
        <br> <br> 
        <a  href="bookServlets?method=getBooks&pageNo=${param.pageNo}" >继续购物</a>
   </center>
  </body>
</html>
