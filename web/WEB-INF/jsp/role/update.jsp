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
                                                            修改角色
                    </div>
	                <div class="panel-body">
	                	<form role="form" id="updateFormSysRole" class="form-horizontal" 
							action="${pageContext.request.contextPath}/sys/role/update"
							method="post">
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">ID</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" name="id" readonly value="${formData.id}">
			                    </div>
			                </div>
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">名称</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入名称" name="name" value="${formData.name}">
			                    </div>
			                </div>
			               
			                <div class="form-group">
			                    <label class="col-sm-2 control-label">描述</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入描述" name="description" value="${formData.description}">
			                    </div>
			                </div>
			                <div class="form-group">
								<label class="col-sm-2 control-label">权限</label>
								<div class="input-group">
									<input type="hidden" name="permissionsId" value="${formData.permissionsId}">
		                            <input type="text" class="form-control" name="permissionsName" readonly value="${formData.permissionsName}">
		                            <span class="input-group-btn">
		                                <button class="btn btn-default" type="button"><i class="fa fa-search"></i>
		                                </button>
		                            </span>
	                            </div>
	                            <%
												if (uperror != null) {
											%>
											<div class="alert alert-danger">操作失败！</div>
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
	   			$('#updateFormSysRole').bootstrapValidator({
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
		            	name : {
		            		validators : {
		            			'notEmpty' : {
		            				message : '不允许为空'
		            			}
		            		}
		            	}
		            	
		            }
		        });
	   	    	
	   	    	// 关联对象选择器按钮点击事件
	   	    	$('#updateFormSysRole .input-group-btn').click (function () {
					// 删除对话框
					$('#dialogSysPermission').remove();
					// 创建对话框
	            	$('body').append ('<div class="modal fade" id="dialogSysPermission" tabindex="-1" role="dialog"></div>');
	            	// 显示对话框
	            	$('#dialogSysPermission').load ('${pageContext.request.contextPath}/sys/permission/selector', {
	            		formId : 'updateFormSysRole',
	            		idField : 'permissionsId',
	            		nameField : 'permissionsName',
	            		singleSelect : false
	            	}, function () {
	            		// 显示角色对话框
	            		$('#dialogSysPermission').modal ('show');
	            	});
				});
		    });
   			</script>
	</div>