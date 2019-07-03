<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title">修改选项</h4>
		</div>
		<!-- /.row -->
		<div class="modal-body">
			<form role="form" id="updateFormExamChoice" class="form-horizontal">
				<div class="form-group">
					<label class="col-sm-2 control-label">描述</label>
					<div class="col-sm-10">
						<input  class="form-control" name="description" value="${formData.description }">
						<input type="hidden" name="id" 			value="${formData.id}" readonly>
						<input type="hidden" name="questionId"  value="${formData.questionId}" readonly>
						<input type="hidden" name="no"  value="${formData.no}" readonly>

					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-2"></div>
					<div class="col-sm-10">
						<button type="button" class="btn btn-primary" onclick="updateExamChoice();">提交</button>
						<button type="reset" class="btn btn-default">重置</button>
						<button type="button" class="btn btn-warning" data-dismiss="modal"  >退出</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		function updateExamChoice() {
			// 获取表单数据
			var data = $('#updateFormExamChoice').serializeArray ();
			// 通过ajax-post提交表单数据
			$.post ('${pageContext.request.contextPath}/exam/choice/update', data, function (json) {
				if (json.success) {
					// 隐藏对话框
					$('#updateFormExamChoice').parents('.modal').modal ('hide');
					// 刷新列表
					$('#tableExamChoice').DataTable ().ajax.reload();
				}
				alert (json.message);
			});
		}
	</script>
</div>