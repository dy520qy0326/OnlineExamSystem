<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
	
	String uperror = null;
	 if(request.getAttribute("uperror")!=null){
		 uperror = request.getAttribute("uperror").toString(); 
		 int begin = uperror.indexOf(":")+1;
		 int last  = uperror.length();
		 uperror = uperror.substring(begin, last);
	 };
	
%>
	<div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
				<div class="panel panel-default">
                	<div class="panel-heading">
                                                            修改用户
                    </div>
	                <div class="panel-body">
	                	<form role="form" id="updateFormSysUser" class="form-horizontal" 
							action="${pageContext.request.contextPath}/sys/user/update"
							method="post">
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">ID</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" name="id" readonly value="${formData.id}">
			                    </div>
			                </div>
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">登录名</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入登录名" name="loginName" value="${formData.loginName}">
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="col-sm-2 control-label">密码</label>
			                    <div class="col-sm-10">
			                    	<input type="password" class="form-control" placeholder="请输入密码" name="password" >
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="col-sm-2 control-label">描述</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入描述" name="description" value="${formData.description}">
			                    </div>
			                </div>
			                <div class="form-group">
								<label class="col-sm-2 control-label">角色</label>
								<div class="input-group">
									<input type="hidden" name="rolesId" value="${formData.rolesId}">
		                            <input type="text" class="form-control" name="rolesName" readonly value="${formData.rolesName}">
		                            <span class="input-group-btn">
		                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i>
		                                </button>
		                            </span>
	                            </div>
	                            <%
												if (uperror != null) {
											%>
											<div class="alert alert-danger">用户名重复！</div>
											<%
												}
											%>
	                        </div>
							<div class="form-group">
				                <div class="col-sm-2"></div>
				                <div class="col-sm-10">
				                <button type="submit" class="btn btn-primary">提交</button>
				                <button type="reset" class="btn btn-default">重置</button>
				                <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/'">返回</button>
				                </div>
			                </div>
			            </form>
	                </div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <script type="text/javascript">
	   	    $(document).ready(function () {
	   	    	// 设置各字段的验证规则
	   	  		// 将验证规则应用到表单的字段中
	   			$('#updateFormSysUser').bootstrapValidator({
		            // 默认错误消息
	   				message: '输入值不合法',
	   				// 设置验证成功或者失败的图标
		            feedbackIcons: {
		                valid: 'glyphicon glyphicon-ok',
		                invalid: 'glyphicon glyphicon-remove',
		                validating: 'glyphicon glyphicon-refresh'
		            },
		            // 设置不同字段的验证规则和错误信息
		            fields: {
		            	loginName : {
		            		validators : {
		            			'notEmpty' : {
		            				message : '不允许为空'
		            			}
		            		}
		            	}
		            	
		            }
		        });
	   	    	
	   	    	// 关联对象选择器按钮点击事件
	   	    	$('#updateFormSysUser .input-group-btn').click (function () {
					// 删除对话框
					$('#dialogSysRole').remove();
					// 创建对话框
	            	$('body').append ('<div class="modal fade" id="dialogSysRole" tabindex="-1" role="dialog"></div>');
	            	// 显示对话框
	            	$('#dialogSysRole').load ('${pageContext.request.contextPath}/sys/role/selector', {
	            		formId : 'updateFormSysUser',
	            		idField : 'rolesId',
	            		nameField : 'rolesName',
	            		singleSelect : false
	            	}, function () {
	            		// 显示角色对话框
	            		$('#dialogSysRole').modal ('show');
	            	});
				});
		    });
   			</script>
	</div>