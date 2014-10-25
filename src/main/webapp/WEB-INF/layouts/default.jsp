<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>逼斗士:<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<link rel="stylesheet" href="${staticPath}/common/css/bootstrap.css">
<link rel="stylesheet" href="${staticPath}/common/css/bootstrap-theme.css">

    <script src="${staticPath}/common/js/lib/jquery.js"></script>
    <script src="${staticPath}/common/js/lib/bootstrap.js"></script>
    <script src="${staticPath}/common/js/lib/sea.js"></script>
    <script src="${staticPath}/common/js/lib/jquery.uploadify.js"></script>
    <script>seajs.config({base:'<c:out value="${staticPath}"></c:out>'});</script>
</head>
<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
   
    <sitemesh:body/>
    <%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>