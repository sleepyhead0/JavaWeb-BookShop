<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    
  

  </head>
  <body>
   <form action="${pageContext.request.contextPath}/UserServlet?method=addStu" method="post">
		用户<input type="text" name="username"/><br/>
		<button>提交</button>
	</form>
	
	<a href="${pageContext.request.contextPath}/UserServlet?method=updateStu">点我更新</a>
    
  </body>
</html>
