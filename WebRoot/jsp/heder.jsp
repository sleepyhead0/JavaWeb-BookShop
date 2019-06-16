<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    

  </head>
  
  <body>
    <!--
            	描述：菜单栏
            -->
			<div class="container-fluid">
				<div class="col-md-4">
					<img src="${pageContext.request.contextPath}/img/logo2.png" width="150" height="50"/>
				</div>
				<div class="col-md-5">
					<img src="${pageContext.request.contextPath}/img/header.png" />
				</div>
				<div class="col-md-3" style="padding-top:20px">
					<ol class="list-inline">
					 <c:choose>
					    <c:when test="${empty student}">
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=loginUi">登录</a></li>
						<li><a href="${pageContext.request.contextPath}/UserServlet?method=registUi">注册</a></li>
						</c:when>
						<c:otherwise>
						<li>你好${student.username }&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/UserServlet?method=loginOut">退出</a></li>
						<li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
						<li><a href="${pageContext.request.contextPath}/OrderServlet?method=findMyOrders&currentPage=1">我的订单</a></li>
						</c:otherwise>
					 </c:choose>
					</ol>
				</div>
			</div>
			<!--
            	描述：导航条
            -->
			<div class="container-fluid">
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
								<span class="sr-only">Toggle navigation</span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
								<span class="icon-bar"></span>
							</button>
							<a class="navbar-brand" href="#">首页</a>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav" id="myUL"  >
							   
							 
	                         
								</ul>
							<form class="navbar-form navbar-right" role="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="Search">
								</div>
								<button type="submit" class="btn btn-default">Submit</button>
							</form>

						</div>
						<!-- /.navbar-collapse -->
					</div>
					<!-- /.container-fluid -->
				</nav>
			</div>
  </body>
 
  <script >
  $(function(){
        //向服务端CategoryServlet-->getAllCats发起ajax请求服务端经过处理，
        //将所有分类信息以JSoN格式的数据返回，获取返回的所有分类绑定在页面的显示要烦 区域
        //$.post(url,obj,function(data){}"json");
        var url="/BookShop1733/CategoryServlet";
        var obj={"method":"findAllCatst"};
        $.post(url,obj,function(data){
        $.each(data,function(i,obj){
        var li="<li><a href='${pageContext.request.contextPath}/ProductServlet?method=findProductCid&currentPage=1&cid="+obj.cid+"'>"+obj.cname+"</a></li>";
        $("#myUL").append(li);
  })
  
      },"json");
      
  });
  </script>
  
</html>
