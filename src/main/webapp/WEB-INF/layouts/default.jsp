<!DOCTYPE html>
<html>
<head>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>QuickStart示例:<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<link type="image/x-icon" href="${staticPath}/common/images/favicon.ico" rel="shortcut icon">
<link href="${staticPath}/common/lib/bootstrap/2.3.2/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${staticPath}/common/lib/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link href="${staticPath}/common/styles/default.css" type="text/css" rel="stylesheet" />
<script src="${staticPath}/common/lib/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${staticPath}/common/lib/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${staticPath}/common/lib/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>


<sitemesh:head/>
</head>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
	<script src="${staticPath}/common/lib/bootstrap/2.3.2/js/bootstrap.min.js" type="text/javascript"></script>
</body>
</html>