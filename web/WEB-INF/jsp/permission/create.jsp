<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="org.dream.www.sys.dto.PermissionDto"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">创建权限</div>
				<div class="panel-body">
					<form role="form" id="createFormSysPermission" class="form-horizontal"
						action="${pageContext.request.contextPath}/sys/permission/create"
						method="post">
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入权限名称"
									name="name">
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入描述"
									name="description">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入类型"
									name="type">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">页面地址</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入JSP地址"
									name="url">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">图标</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入图表类型"
									name="icon">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">页面分级</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入Level，两位一级 父类：1000 子类 :1010"
									name="level">
							</div>
						</div>
						
						<div class="form-group">
								<label class="col-sm-2 control-label">父类权限</label>
								<div class="input-group">
									<input type="hidden" name="parentId">
		                            <input type="text" class="form-control" name="parentName" placeholder="请选择权限" readonly>
		                            <span class="input-group-btn">
		                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i>
		                                </button>
		                            </span>
	                            </div>
	                        </div>

						<%-- div class="form-group">
							<label class="col-sm-2 control-label">选择角色</label>
							<div class="col-sm-10">
								<c:forEach items="${rList}" var="r">

									<input type="checkbox" value="${r.id }" name="roleId">${r.name }
								
							</c:forEach>
							</div>
						</div> --%>

						<div class="form-group">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<button type="button" class="btn btn-primary" onclick="create()">提交</button>
								<button type="reset" class="btn btn-default">重置</button>
								<button type="button" class="btn btn-warning"
									onclick="location.href='${pageContext.request.contextPath}/'">返回</button>
							</div>
							</div>
							
							</form>
						</div>
					
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>

<script >
	
function create() {
	
	createFormSysPermission.submit();

}
// 关联对象选择器按钮点击事件
	$('#createFormSysPermission .input-group-btn').click (function () {
	// 删除对话框
	$('#dialogSysPermissionParent').remove();
	// 创建对话框
	$('body').append ('<div class="modal fade" id="dialogSysPermissionParent" tabindex="-1" role="dialog"></div>');
	// 显示对话框
	$('#dialogSysPermissionParent').load ('${pageContext.request.contextPath}/sys/permission/selectorparent', {
		formId : 'createFormSysPermission',
		idField : 'parentId',
		nameField : 'parentName',
		singleSelect : true
	}, function () {
		// 显示权限对话框
		$('#dialogSysPermissionParent').modal ('show');
	});
});
	
</script>
