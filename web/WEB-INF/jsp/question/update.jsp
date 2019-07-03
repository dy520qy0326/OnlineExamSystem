<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">修改试题</div>
				<div class="panel-body">
					<form role="form" id="updateFormExamQuestion"
						  class="form-horizontal"
						  action="${pageContext.request.contextPath}/exam/question/update"
						  method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-sm-2 control-label">ID</label>
							<div class="col-sm-10">
								<input class="form-control" name="id" value="${formData.id}"
									   readonly>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">题干</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入题干" name="name"
									   value="${formData.name}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">图片</label>
							<div class="col-sm-10">
								<input class="form-control" name="imageFile" type="file">
							</div>
							<div class="col-sm-12">
								<img alt=""
									 src="${pageContext.request.contextPath}/${formData.image}"
									 class="img-circle center-block" width="800px" height="500px">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">答案</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入答案" name="answer"
									   value="${formData.answer}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">知识点</label>
							<div class="col-sm-10">
								<select class="form-control" name="knowledgeId"
										value=${formData.knowledgeId }>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">描述</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入描述"
									   name="description" value="${formData.description}">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-12">
								<div class="card shadow mb-4">
									<div class="card-header py-3">
										<h6 class="m-0 font-weight-bold text-primary"
											style="text-align: center">Choice Table</h6>
										<!-- /.panel-heading -->
										<div class="panel-body">
											<div class="table-responsive " style="text-align: center">
												<table class="table table-bordered table-condensed" width="100%"
													   cellspacing="0" id="tableExamChoice">
													<thead>
													<tr>

														<th>Id</th>
														<th>No</th>
														<th>Description</th>
														<th>Operate</th>

													</tr>
													</thead>
												</table>
											</div>

										</div>
										<!-- /.table-responsive -->
									</div>
									<!-- /.panel-body -->
								</div>
								<!-- /.panel -->
							</div>
							<!-- /.col-lg-12 -->
						</div>

						<div class="form-group">
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<button type="submit" class="btn btn-primary" >提交</button>
								<button type="reset" class="btn btn-default">重置</button>
								<button type="button" class="btn btn-warning"
										onclick="location.href='${pageContext.request.contextPath}/'">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>



	<!-- 选项列表 -->


	<script type="text/javascript">
		$(document).ready(function () {

			// DataTable控件官网：https://datatables.net/
			var tableId = 'tableExamChoice';
			// 将DataTable获取的json数据中的属性，和列对应起来
			var cols = [
				{ "data": "id" , "orderable" : false}, // 设置第一列
				{ "data": "no" , "orderable" : false}, // 设置第一列
				{ "data": "description" , "orderable" : false}, // 设置第二列
				// 下面在操作列中添加了修改和删除按钮
				{ "data": null , "orderable" : false,
					"defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a>" +
							" <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>" +
							"&nbsp<a class='btn btn-primary btn-xs' name='up'>上移</a>" +
							"&nbsp<a class='btn btn-primary btn-xs' name='down'>下移</a>"
				}
			];
			// 获取上述排序列的索引
			var orderIndex = 0;
			for (var i = 0; i < cols.length; i ++) {
				if (cols[i].orderable == true) {
					orderIndex = i;
				}
			}
			// 初始化DataTable控件，并触发后台的json数据获取
			var table = $('#' + tableId).DataTable({
				"processing": true, // 显示处理进度
				"serverSide": true, // 从服务端获取数据
				"rowId" : 'id',// 设置主键字段名称
				"ajax": {
					"url": "${pageContext.request.contextPath}/exam/choice/listofquestion",
					"data":{"questionId": ${formData.id} } ,
					"type": "GET"  },
				"order": [ [ orderIndex, "asc" ] ] , // 排序
				"columns": cols
			});
			// 设置列表中按钮的事件
			$('#' + tableId + ' tbody').on( 'click', 'a', function () {
				// this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象:BookDto
				var data = table.row( $(this).parents('tr') ).data();
				// 调用修改方法
				if ($(this).attr('name') == 'update') {
					//location.href = "${pageContext.request.contextPath}/exam/choice/update?id=" + data.id;
					// 删除对话框
					$('#dialogExamChoice').remove();
					// 创建对话框
					$('body').append ('<div class="modal fade" id="dialogExamChoice" tabindex="-1" role="dialog"></div>');
					// 加载选项修改页面至对话框，并显示
					$('#dialogExamChoice').load ('${pageContext.request.contextPath}/exam/choice/update?id='+ data.id+'&description='+data.description, function () {
						// 显示选项修改页面对话框
						$('#dialogExamChoice').modal ('show');
					});
				}
				// 调用删除方法
				if ($(this).attr('name') == 'delete') {
					deleteExamChoice (data.id);
				}

				if ($(this).attr('name') == 'up') {
					upExamChoice (data.id);
				}
				if ($(this).attr('name') == 'down') {
					downExamChoice (data.id);
				}


			} );
			// 通过父元素代理子元素的事件，此时，此元素有可能还没有生成
			$('#' + tableId + ' tbody').on( 'click', 'tr', function () {
				// 切换样式info，如果有info样式则删除，没有则添加
				$(this).toggleClass('selected');
			} );

			// 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
			$('#' + tableId + '_filter').append (" <a class='btn btn-primary btn-sm' onclick='createExamChoice();'>创建</a> <a class='btn btn-primary btn-warning btn-sm' onclick='deleteExamChoices();'>批量删除</a>");
			// 显示创建选项对话框


			// 异步加载知识点
			$('#updateFormExamQuestion select').load ('${pageContext.request.contextPath}/exam/knowledge/options?selectedId=${selectedId}');

			// 将验证规则应用到表单的字段中
			$('#updateFormExamQuestion').bootstrapValidator({
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
						validators: {
							'notEmpty' : {
								message : '不允许为空'
							}
						}
					},
					answer : {
						validators: {
							'notEmpty' : {
								message : '不允许为空'
							}
						}
					},
					knowledgeId : {
						validators: {
							'notEmpty' : {
								message : '不允许为空'
							}
						}
					}
				}
			});
		});
		function createExamChoice() {
			// 删除对话框
			$('#dialogCreateExamChoice').remove();
			// 创建对话框
			$('body')
					.append(
							'<div class="modal fade" id="dialogCreateExamChoice" tabindex="-1" role="dialog"></div>');
			// 加载选项创建页面至对话框，并显示
			$('#dialogCreateExamChoice')
					.load(
							'${pageContext.request.contextPath}/exam/choice/create?questionId=${formData.id}',
							function() {
								// 显示角色对话框
								$('#dialogCreateExamChoice').modal('show');
							});
		}
		function deleteExamChoice(id) {
			if (!confirm('确定要删除选定的选项吗？')) {
				return;
			}
			$.post('${pageContext.request.contextPath}/exam/choice/delete', {
				id : id
			}, function(json) {
				if (json.success) {// 如果成功，则刷新考试修改页面
					location.reload();
				} else {// 如果失败，则显示错误信息
					alert(json.message);
				}
			});
		}
		function deleteExamChoices() {
			// 获取DataTable
			var table = $('#tableExamChoice').DataTable();
			// 查找样式为info的行，包含所有BookDto属性
			var rows = table.rows('.selected').data();
			console.log(rows);
			// 判断是否有选择
			if (rows.length == 0) {
				alert('请选择至少一行！');
				return;
			}
			// 确认删除
			if (!confirm('是否删除选中的数据？')) {
				return;
			}
			// 拼接id字符串，多个id逗号隔开
			var ids = '';
			for (var i = 0; i < rows.length; i++) {
				if (ids != '') {
					ids += ',';
				}
				ids += rows[i].id;
			}
			console.log(ids);
			// 发送ajax请求，如果成功则刷新本页面，否则提示操作失败
			$.post('${pageContext.request.contextPath}/exam/choice/delete', {
				id : ids
			}, function(result) {
				if (result.success) {
					location.reload();
				}
				alert(result.message);
			});
		}
		function upExamChoice(id) {

			$.post('${pageContext.request.contextPath}/exam/choice/up', {
				id : id
			}, function(json) {
				if (json.success) {// 如果成功，则刷新考试修改页面
					$('#tableExamChoice').DataTable ().ajax.reload();
				} else {// 如果失败，则显示错误信息
					alert(json.message);
				}
			});
		}
		function downExamChoice(id) {

			$.post('${pageContext.request.contextPath}/exam/choice/down', {
				id : id
			}, function(json) {
				if (json.success) {// 如果成功，则刷新考试修改页面
					$('#tableExamChoice').DataTable ().ajax.reload();
				} else {// 如果失败，则显示错误信息
					alert(json.message);
				}
			});
		}
		function updatesub() {
			// 获取表单数据
			var updata = $('#updateFormExamQuestion').serializeArray ();
			// 通过ajax-post提交表单数据
			$.post ('${pageContext.request.contextPath}/exam/question/update', updata, function (json) {
				alert(json.message);
				if (json.success) {

				location.href='${pageContext.request.contextPath}';
				}


			});

		}


	</script>

</div>