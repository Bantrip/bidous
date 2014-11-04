<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <title>发布页</title>
    <link rel="stylesheet" href="${staticPath}/css/list.css"/>
</head>
<body>
<div class="container">
        <!-- <form action="" class="form-horizontal">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-4 control-label">业务类型：</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control">
                        </div>
                    </div>
                </div>
            </div>
        </form> -->
        <table class="table table-bordered table-condensed table-striped J_list">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>名称</th>
                    <th>图片</th>
                    <th>价格</th>
                    <th>来源</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${ products.content}" var="product" >
                <tr>
                    <td class="id">${product.id}</td>
                    <td>${product.name}</td>
                    <td><img src="${product.defaultPic}" alt=""></td>
                    <td><fmt:formatNumber pattern="#.##" value="${product.price}"></fmt:formatNumber></td>
                    <td>第三方</td>
                    <td>
                    <c:choose>
                    <c:when test="${product.status==0}">
                    编辑中
                    </c:when>
                    <c:when test="${product.status==1}">
                    待审核
                    </c:when>
                    <c:when test="${product.status==2}">
                    已发布
                    </c:when>
                    </c:choose>   
                    </td>
                    <td>
                        <a href="${ctx}/product/update/${product.id}" class="btn btn-link">编辑</a>
                        <div class="btn-group">
                            <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown">操作<span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li>
                                    <a class="J_ope" data-type="del" >删除</a>
                                    <a class="J_ope" data-type="audit">审核</a>
                                    <a class="J_ope" data-type="reject">驳回</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
	
    <script>seajs.use('list')</script>    

</body>
</html>