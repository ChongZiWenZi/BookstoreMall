<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">
     $(function(){
        $("a").click(function(){
        var serializeVal=$(":hidden").serialize();
       /*  var href=this.href+"&"+serializeVal;
        window.location.href=href; */
        alert(serializeVal);
        return false;
      });
     })
    </script>
  </head>
  <input  type="hidden" name="minPrice" value="${param.minPrice}"/>
   <input  type="hidden" name="maxPrice" value="${param.maxPrice}"/>
  <body>
     <center>
       <br><br>
        <form action="bookServlets?method=getBooks" method="post">
       		<input type="text" size="1" name="minPrice"/>
       		<input type="text" size="1" name="maxPrice"/>
       		 <input type="submit" value="Submit"/>
        </form>
        <br>
        <br>
        <table cellpadding="10">
          <c:forEach items="${bookpage.list}" var="book">
            <tr>
              <td>
                <a href="">${book.title}</a>
                <br>
                ${book.author}
              </td>
              <td>
               ${book.price}
              </td>
               
              <td>
              <a href="">加入购物车</a>
              </td>
            </tr>
            
          </c:forEach>
        </table>
        <br><br>
          共${bookpage.totalPageNumber}页
        &nbsp;&nbsp;
        当前第${bookpage.pageNo}页
          &nbsp;&nbsp;
          <c:if test="${bookpage.hasPrev}">
             <a href="bookServlet?method=getBooks&pageNo=1">首页</a>
               &nbsp;&nbsp;
                   <a href="bookServlets?method=getBooks&pageNo=${bookpage.prevPage}">上一页</a>
                      &nbsp;&nbsp;
          </c:if>
          <c:if test="${bookpage.hasNext}">
             <a href="bookServlets?method=getBooks&pageNo=${bookpage.nextPage}">下一页</a>
               &nbsp;&nbsp;
                   <a href="bookServlets?method=getBooks&pageNo=${bookpage.totalPageNumber}">末页</a>
                      &nbsp;&nbsp;
          </c:if>  
     </center>
  </body>
</html>
