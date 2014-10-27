<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="staticPath" value="${pageContext.request.contextPath}/static" />
<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
    <meta charset="UTF-8">
    <title>发布页</title>
    <link rel="stylesheet" href="${staticPath}/css/edit.css"/>
</head>
<body>
<div class="container">
        <h3>发布商品</h3>
        <form class="form-horizontal" role="form" action="../${action}" method="post" style="margin-top: 40px;">
        	<input type="hidden" name="id" value="${product.id}"/>
            <div class="form-group">
                <label class="col-sm-2 control-label">商品标题</label>
                <div class="col-sm-8">
                    <input name="name" type="text" class="form-control J_name" maxlength="40" placeholder="不超过40个字" value="${product.name} "></div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">商品图片</label>
                <div class="col-sm-10">
                    <div>
                        <input type="file" name="file_upload" id="uploadProductImg" />
                    </div>
                    <div class="J_product-img-list"> </div>
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
                    <textarea name="recommand" class="form-control J_rcmd-reason" cols="30" rows="5">${product.recommand }</textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">筛选Tag</label>
                <div class="col-sm-8 form-add-result">
                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#modalTag">编辑</button>
                    <dl class="well selected clearfix Hide J_tag">
                        <dt>已选tag：</dt>
                        <dd>
                            <p>人群：<span class="tag" data-tagid="1">男士</span></p>
                        </dd>
                    </dl>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所属目的地</label>
                <div class="col-sm-8 form-add-result">
                    <button class="btn btn-default" type="button" data-toggle="modal" data-target="#modalLocation">编辑</button>
                    <dl class="well selected clearfix Hide J_loc">
                        <dt>已选目的地：</dt>
                        <dd></dd>
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
                    <button type="submit" class="btn btn-primary J_submit">提交</button>
                </div>
            </div>
        </form>
    </div>

    <div class="modal fade modal-edit-tag" id="modalTag">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </button>
                    <h4 class="modal-title">编辑tag</h4>
                </div>
                <div class="modal-body">

                    <div class="clearfix">
                        <ul class="nav nav-tabs nav-stacked col-sm-2 J_tag-group" role="tablist">
                            <li class="active">
                                <a href="#home" role="tab" data-toggle="tab">
                                    <span class="name">人群</span>
                                    <span class="glyphicon glyphicon-ok Hide"></span>
                                </a>
                            </li>
                            <li>
                                <a href="#profile" role="tab" data-toggle="tab">
                                    <span class="name">Profile</span>
                                    <span class="glyphicon glyphicon-ok Hide"></span>
                                </a>
                            </li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content col-sm-10 J_tag-con">
                            <div class="tab-pane clearfix active" id="home">
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 男士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-1" data-tagid="1"> 女士</label>
                            </div>
                            <div class="tab-pane clearfix" id="profile">
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 1男士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                                <label class="item">
                                    <input type="radio" class="input-tag" name="tag-2" data-tagid="1"> 女士</label>
                            </div>
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
                        <div class="search">
                            <input type="text" class="form-control" class="J_input-search">
                            <p class="type"><label class="item"><input type="radio" name="searchType" checked data-type="1"> 城市</label><label class="item"><input type="radio" name="searchType" data-type="1"> 国家</label></p>
                        </div>
                        <div class="list">
                            <ul class="list-unstyled J_list-loc">
                                <li><a href="javascript:;" data-cityid="1">北京</a></li>
                                <li><a href="javascript:;" data-cityid="1">北京</a></li>
                                <li><a href="javascript:;" data-cityid="1">北京</a></li>
                                <li><a href="javascript:;" data-cityid="1">北京</a></li>
                                <li><a href="javascript:;" data-cityid="1">北京</a></li>
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