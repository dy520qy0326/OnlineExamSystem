<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="org.dream.www.sys.util.SysConstant"%>
<%
	// 从session中获取试题ID
	String id = session.getAttribute(SysConstant.SESSION_CHOICE_CREATE_QUESTIONID).toString();

%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">创建选项</div>
				<div class="panel-body">
					<form role="form" id="createFormExamChoice" class="form-horizontal"
						  action="${pageContext.request.contextPath}/exam/choice/create"
						  method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-sm-2 control-label">编号</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入编号" name="no">
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
							<label class="col-sm-2 control-label">试题Id</label>
							<div class="col-sm-10">
								<input class="form-control" value="<%=id %>" readonly
									   name="questionId">
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<button type="button" class="btn btn-primary" onclick="createExamChoice();">提交</button>
								<button type="reset" class="btn btn-default">重置</button>
								<button type="button" class="btn btn-warning"
										onclick="location.href='${pageContext.request.contextPath}/exam/question/update?id=<%=Integer.parseInt(id) %>'">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>
	<script type="text/javascript">


		// 将验证规则应用到表单的字段中
		$('#createFormExamChoice').bootstrapValidator({
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
				no : {
					validators: {
						'notEmpty' : {
							message : '不允许为空'
						}
					}
				},
				description : {
					validators: {
						'notEmpty' : {
							message : '不允许为空'
						}
					}
				}

			}
		});
		function createExamChoice() {
			// 获取表单数据
			var data = $('#createFormExamChoice').serializeArray ();
			// 通过ajax-post提交表单数据
			$.post ('${pageContext.request.contextPath}/exam/choice/create', data, function (json) {
				if (json.success) {
					// 隐藏对话框
					$('#createFormExamChoice').parents('.modal').modal ('hide');
					// 刷新列表
					$('#tableExamChoice').DataTable ().ajax.reload();

				}

				alert (json.message);
			});
		}

	</script>
</div>