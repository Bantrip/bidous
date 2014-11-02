<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>伴游发布系统:<sitemesh:title/></title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<link rel="stylesheet" href="${staticPath}/css/lib/bootstrap.css"/>
    <script src="${staticPath}/js/lib/jquery.js"></script>
    <script src="${staticPath}/js/lib/jquery.uploadify.js"></script>
     <script>(function(global){
    	 var ori=global.SWFUpload.prototype.initSettings;
    	 SWFUpload.prototype.initSettings=function(){
    		 ori.call(this);
    		 this.settings.flash_url='${staticPath}/js/lib/uploadify.swf'+'?ds='+new Date().getTime();
    	 }
    //	 global.SWFUpload.prototype.initSettings=fucntion(){};
    	/* 	 ori();
    		 this.settings.flash_url='${staticPath}/js/uploadify.swf';
    	 } */
     })(this);</script>
    <script src="${staticPath}/js/lib/bootstrap.js"></script>
    <script src="${staticPath}/js/lib/sea.js"></script>
    <script src="${staticPath}/js/lib/lodash.js"></script>
    <script>seajs.config({base:'<c:out value="${staticPath}"></c:out>/js'});</script>
    <script>(function(global,byname,und){
    	if(global[byname])return;
    	var commonObj={};
    	commonObj
    	global[byname]=commonObj;
    })(this,'_by');</script>
    <sitemesh:head/>
</head>
<body>
	<%@ include file="/WEB-INF/layouts/header.jsp"%>
   
    <sitemesh:body/>
    <%@ include file="/WEB-INF/layouts/footer.jsp"%>
</body>
</html>