<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="org.dream.www.sys.dto.UserDto"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">创建用户</div>
				<div class="panel-body">
					<form role="form" id="createFormSysUser" class="form-horizontal"
						action="${pageContext.request.contextPath}/sys/user/create"
						method="post">
						<div class="form-group">
							<label class="col-sm-2 control-label">登录名</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入登录名"
									name="loginName">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" placeholder="请输入密码"
									name="password">
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
								<label class="col-sm-2 control-label">角色</label>
								<div class="input-group">
									<input type="hidden" name="rolesId">
		                            <input type="text" class="form-control" name="rolesName" readonly>
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
	
	createFormSysUser.submit();

}
// 关联对象选择器按钮点击事件
	$('#createFormSysUser .input-group-btn').click (function () {
	// 删除对话框
	$('#dialogSysRole').remove();
	// 创建对话框
	$('body').append ('<div class="modal fade" id="dialogSysRole" tabindex="-1" role="dialog"></div>');
	// 显示对话框
	$('#dialogSysRole').load ('${pageContext.request.contextPath}/sys/role/selector', {
		formId : 'createFormSysUser',
		idField : 'rolesId',
		nameField : 'rolesName',
		singleSelect : true
	}, function () {
		// 显示角色对话框
		$('#dialogSysRole').modal ('show');
	});
});
	
</script>
