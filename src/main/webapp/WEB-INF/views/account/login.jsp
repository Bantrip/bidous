<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>登录页</title>
</head>

<body>
<div class="container">
	<form id="loginForm" action="${ctx}/login" method="post"  class="form-horizontal" role="form" >
	<div class="form-group">
    	<label class="col-sm-2 control-label" for="email">Email:</label>
    	<div class="col-sm-10">
          <input type="email" id="email" name="username"  value="${username}" class="form-control input-medium required"/>
  		</div>
  	</div>
  	<div class="form-group">
    	<label class="col-sm-2 control-label" for="password">密码:</label>
    	<div class="col-sm-10">
          <input type="password" id="password" name="password"  value="${password}" class="form-control input-medium required"/>
  		</div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-primary">登录</button> <a class="btn btn-link" href="${ctx}/register">注册</a>
        </div>
    </div>
	</form>
</div>
</body>
</html>
