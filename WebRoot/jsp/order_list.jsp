<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
      <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员登录</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
		</style>
	</head>

	<body>
	<%@ include file="/jsp/heder.jsp" %>
     
		<div class="container">
		        <c:forEach items="${page.list}" var="o">
		
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong>我的订单</strong>
					<table class="table table-bordered">
						<tbody>
							<tr class="success">
								<th colspan="5">订单编号:${o.oid}  &nbsp;
								订单时间：<fmt:formatDate value="${o.ordertime}" pattern="yyyy/MM/dd HH:mm:ss"/> &nbsp;
								总金额：<span style="color: red;">￥${o.total}元</span> &nbsp;
								订单状态：
								<c:if test="${o.state==1}">
								<a href="${pageContext.request.contextPath}/OrderServlet?method=findOrderByOid&oid=${o.oid}">等侍付款</a>
								</c:if>
								<c:if test="${o.state==2}">
								未发货
								</c:if>
								<c:if test="${o.state==3}">
								签收
								</c:if>
								<c:if test="${o.state==4}">
								结束
								</c:if>
								</th>	
							</tr>
							<tr class="warning">
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>数量</th>
								<th>小计</th>
							</tr>
							<c:forEach items="${o.list}" var="item">
							<tr class="active">
								<td width="60" width="40%">
									<input type="hidden" name="id" value="22">
									<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
								</td>
								<td width="30%">
									<a target="_blank">${item.product.pname}</a>
								</td>
								<td width="20%">
									￥${item.product.shop_price}
								</td>
								<td width="10%">
									${item.quantity}
								</td>
								<td width="15%">
									<span class="subtotal">￥${item.total}</span>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
			</div>
			</c:forEach>
	 	<div style="text-align: center;">
				<ul class="pagination">
				<p>
				   第${page.currentPage }/${page.pageSize }   每页显示${page.totalPage}条    总的记录数${page.totalSize }
				   
				  <a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders&cid=${cid}&currentPage=1" >首页</a> 
				  <a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders&cid=${cid}&currentPage=${page.currentPage-1 }" >上一页</a> 
				    
				  <c:forEach var="i" begin="1" end="${page.totalPage }"> 
				   <c:if test="{page.currentPage ==i }">
				   
				   </c:if>
				   <c:if test="{page.currentPage !=i}">
				         <a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders&cid=${cid}&currentPage=${i}"></a>
				   </c:if>
				  </c:forEach>
				  
				  <a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders&cid=${cid}&currentPage=${page.currentPage+1 }" >下一页</a> |
				  <a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders&cid=${cid}&currentPage=${page.totalPage }" >尾页</a> 
			</p>
				</ul>
			</div>
		</div> 

		<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 智绘点途 版权所有
		</div>
	</body>

</html>