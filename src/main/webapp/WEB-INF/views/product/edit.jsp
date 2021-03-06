<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<c:set var="product" value="${result}" />
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <title>发布页</title>
    <link rel="stylesheet" href="${staticPath}/css/edit.css"/>
</head>
<body>
<div class="container">
        <h3>发布商品</h3>
        <form class="form-horizontal" role="form" action="${ctx}/product/save" method="post" style="margin-top: 40px;">
        	
        	<input type="hidden" name="id" value="${product.id}"/>
        	<div class="form-group">
                <label class="col-sm-2 control-label">商品标题</label>
                <div class="col-sm-8">
                    <input name="name" type="text" class="form-control J_name" maxlength="40" placeholder="不超过40个字" value="${product.name} "></div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">商品图片</label>
                <div class="col-sm-10">
                    <div class="J_product-img-upload">
                        <input type="file" name="file_upload" id="uploadProductImg" />
                        <div id="uploadProductImg-queue" class="uploadify-queue">
                    		<c:forEach items="${product.images}" var="image">
                   			<div class="item">
                   				<img class="img" src="${image}">    
                   				<div class="status" style="visibility: visible;"><span class="glyphicon glyphicon-trash" title="删除"></span></div>  
                       		</div>
                        	</c:forEach>
						</div> 
                    </div>
                   
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">文描</label>
                <div class="col-sm-8">
                    <p>
                        <button class="btn btn-default J_add-text" type="button" style="margin-right: 10px;">添加文字</button>
                        <button class="btn btn-default J_add-img" type="button">添加图片</button>
                    </p>
                    <div class="form-detail-con J_detail">
                    	<c:forEach items="${product.descs}" var="desc">
                    	<div class="item">  
							 <c:if test="${desc.type==0}">
                             <textarea class="form-control detail-text" cols="30" rows="3" style="display: inline-block; width: 80%; margin-top: 5px; vertical-align: top;">${desc.content}</textarea>                   
                             </c:if>
                             <c:if test="${desc.type==1}">
                             	<img style="margin-top: 10px;" src="${desc.content}">
                             </c:if>
                             <span class="glyphicon glyphicon-remove" style="cursor: pointer"></span>      
                    	</div>
                    	</c:forEach>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">价格</label>
                <div class="col-sm-2">
               
                    <input name="price" type="text" class="form-control J_price" value="<fmt:formatNumber pattern="#.##" value="${product.price}"></fmt:formatNumber>"></div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">推荐理由</label>
                <div class="col-sm-8">
                    <textarea name="recommand" class="form-control J_rcmd-reason" cols="30" rows="5">${product.recommand}</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">筛选Tag</label>
                <div class="col-sm-8 form-add-result">
                    <button class="btn btn-default J_btn-edit-tag" type="button" data-toggle="modal" data-target="#modalTag">编辑</button>
                    <dl class="well selected clearfix <c:if test="${empty product.tags}">Hide</c:if> J_tag">
                    
                        <dt>已选tag：</dt>
                        <dd>
                        <c:if test="${not empty product.tags}">
                        <c:forEach items="${product.tags}" var="tag">
                        
                            <p>${tag.group.name}：<span class="tag" data-tagid="${tag.id}">${tag.name} </span></p>
                        
                        </c:forEach>
                        </c:if>
                        </dd>
                    </dl>
                        
                    
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所属目的地</label>
                <div class="col-sm-8 form-add-result">
                    <button class="btn btn-default J_btn-edit-loc" type="button" data-toggle="modal" data-target="#modalLocation">编辑</button>
                    <dl class="well selected clearfix <c:if test="${empty product.dests}">Hide</c:if> J_loc">
                        <dt>已选目的地：</dt>
                       
                        <dd>
                          <c:if test="${not empty product.dests}">
                         <c:forEach items="${product.dests}"  var="dest">
                            <span data-cityid="${dest.id}">${dest.name}</span>
                         </c:forEach>
                         </c:if>
                        </dd>
                       
                        
                    </dl>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">购买链接</label>
                <div class="col-sm-8">
                    <input name="url" type="text" class="form-control J_buy-url" value="${product.url }"></div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="button" class="btn btn-primary J_submit">提交</button>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade modal-edit-tag" id="modalTag">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="false">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">编辑tag</h4>
                </div>
                <div class="modal-body">
                    <div class="clearfix">
                        <ul class="nav nav-tabs nav-stacked col-sm-2 J_tag-group" role="tablist">
                        	<c:forEach items="${tagGroups}" var="tagGroup" varStatus="tagGroupStatus">
                        	<li <c:if test='${tagGroupStatus.index==0}'>class="active"</c:if>>
                                <a href="#tagGroup-${tagGroup.id}" role="tab" data-toggle="tab">
                                    <span class="name">${tagGroup.name}</span>
                                    <span class="glyphicon glyphicon-ok Hide"></span>
                                </a>
                            </li>
                        	</c:forEach>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content col-sm-10 J_tag-con">
                        	<c:forEach items="${tagGroups}" var="tagGroup" varStatus="tagGroupStatus">
                        	  <div class="tab-pane clearfix <c:if test='${tagGroupStatus.index==0}'>active</c:if>" id="tagGroup-${tagGroup.id}">
                        	  	<c:forEach items="${tagGroup.tags}" var="tag" varStatus="tagStatus">
                                <label class="item"><input type="checkbox" class="input-tag" name="tag-${tagGroup.id}" data-tagid="${tag.id }"> ${tag.name}</label>
                                </c:forEach>
                            </div>
                        	</c:forEach>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary J_submit-tag" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade modal-edit-location" id="modalLocation">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">编辑目的地</h4>
                </div>
                <div class="modal-body clearfix">
                    <div class="block result">
                        <div class="search hidden">
                            <input type="text" class="form-control" class="J_input-search">
                            <p class="type">
                            <label class="item"><input type="radio" name="searchType" checked data-type="1"> 城市</label>
                            <label class="item"><input type="radio" name="searchType" data-type="2"> 国家</label></p>
                        </div>
                        <div class="list">
                            <ul class="list-unstyled J_list-loc">
                            	<c:forEach items="${dests}" var="dest">
                            		<li><a href="javascript:;" data-cityid="${dest.id}">${dest.name}</a></li>
                            	</c:forEach>
                            </ul>
                        </div>
                    </div>
                    <div class="block selected">
                        <p>已选城市</p>
                        <div class="cont">
                            <ul class="list-unstyled J_list-selected-loc">
                                <!-- <li><span class="name" data-cityid="1">北京</span><span class="glyphicon glyphicon-remove"></span></li> -->
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary J_submit-loc" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
    </div>

	
    <script>seajs.use('edit')</script>    

</body>
</html>