<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="c"%>

<!-- <script src="sb-admin2/vendor/jquery/jquery.min.js"></script> -->
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h4 style="text-align: center">Knowledge Manger</h4>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="card shadow mb-4">
				<div class="card-header py-3">
					<h6 class="m-0 font-weight-bold text-primary"
						style="text-align: center">Knowledge Table</h6>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="table-responsive " style="text-align: center">
							<table class="table table-bordered table-condensed" width="100%"
								cellspacing="0" id="tableExamKnowledge">
								<thead>
									<tr>
										<th>ID</th>
										<th>编号</th>
										<th>名称</th>
										<th>描述</th>
										<th>操作</th>

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
	<script>
        $(document).ready(function() {
            // DataTable控件官网：https://datatables.net/
            var tableId = 'tableExamKnowledge';
            // 将DataTable获取的json数据中的属性，和列对应起来
            var cols = [
                { "data": "id" , "orderable" : true}, // 设置第2列
                { "data": "no" , "orderable" : false},// 设置第3列
                { "data": "name" , "orderable" : false}, // 设置第4列
                { "data": "description" , "orderable" : false}, // 设置第3列
                // 下面在操作列中添加了修改和删除按钮
                { "data": null , "orderable" : false,
                    "defaultContent": "<a class='btn btn-primary btn-xs' name='update'>修改</a>" +
                        " <a class='btn btn-primary btn-warning btn-xs' name='delete'>删除</a>"
                }
            ];
            // 获取上述排序列的索引
            var orderIndex = 0;
            for (var i = 0; i < cols.length; i ++) {
                if (cols[i].orderable == true) {
                    orderIndex = i;
                }
            }
            // 1.初始化DataTable控件，并触发后台的json数据获取
            var table = $('#' + tableId).DataTable({
                "processing": true, // 显示处理进度
                "serverSide": true, // 从服务端获取数据
                "rowId" : 'id',// 设置主键字段名称
                "ajax": {
                    "url": "${pageContext.request.contextPath}/exam/knowledge/list", // 请求列表数据的url
                    "type": "GET" // http方法
                },
                "order": [ [ orderIndex, "asc" ] ] , // 排序
                "columns": cols
            });
            // 2.通过父元素代理子元素的事件，设置列表中按钮的事件
            $('#' + tableId + ' tbody').on( 'click', 'a', function () {
                // this表示a标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象:BookDto
                // datatables.net
                var data = table.row( $(this).parents('tr') ).data();
                // 调用修改方法
                if ($(this).attr('name') == 'update') {
                    location.href = "${pageContext.request.contextPath}/exam/knowledge/update?id=" + data.id;
                }
                // 调用删除方法
                if ($(this).attr('name') == 'delete') {
                    deleteExamKnowledge (data.id);
                }
            } );
            // 3.通过父元素代理子元素的事件，此时，此元素有可能还没有生成
            $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                // 切换样式info，如果有info样式则删除，没有则添加
                $(this).toggleClass('selected');
            } );

            // 4.添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
            $('#' + tableId + '_length').append (" <a class='btn btn-primary btn-sm' onclick='createExamKnowledge();'>创建</a>" 
            	+ " <a class='btn btn-primary btn-warning btn-sm' onclick='deleteExamKnowledges();'>批量删除</a>"
            	+ " <a class='btn btn-primary btn-sm' onclick='showExamKnowledgeQuestions();'>试题统计</a>");
        });

        
        // 5.设置按钮点击事件
        // 试题统计按钮点击
        function showExamKnowledgeQuestions () {
        	location.href = "${pageContext.request.contextPath}/exam/knowledge/questions";
        }
        
        // 创建按钮点击事件
        function createExamKnowledge () {
            location.href = "${pageContext.request.contextPath}/exam/knowledge/create";
        }

        // 删除按钮点击事件
        function deleteExamKnowledge (id) {
            if (!confirm ('确定要删除选定的数据吗？')) {
                return;
            }
            
            $.post('${pageContext.request.contextPath}/exam/knowledge/delete', {
				id : id
			}, function(result) {
				if (result.success) {
					var table = $('#tableExamKnowledge').DataTable();
					// 删除选中的行
					table.rows('.selected').remove().draw(false);
				}
				alert(result.message);
			});
        }

        // 批量删除按钮点击事件
        function deleteExamKnowledges () {
            // 获取DataTable
            var table = $('#tableExamKnowledge').DataTable();
            // 查找样式为info的行，包含所有Dto属性
            var rows = table.rows('.selected').data();
            console.log (rows);
            // 判断是否有选择
            if (rows.length == 0) {
                alert ('请选择至少一行！');
                return;
            }
            // 确认删除
            if (!confirm ('是否删除选中的数据？')) {
                return;
            }
            // 拼接id字符串，多个id逗号隔开
            var ids = '';
            for (var i = 0; i < rows.length; i ++) {
                if (ids != '') {
                    ids += ',';
                }
                ids += rows[i].id;
            }
            console.log (ids);
            // 发送ajax请求，如果成功则刷新本页面，否则提示操作失败
            $.post ('${pageContext.request.contextPath}/exam/knowledge/delete', {id : ids}, function (result) {
                if (result.success) {
                    var table = $('#tableExamKnowledge' ).DataTable();
                    // 删除选中的行
                    table.rows('.selected').remove().draw(false);
                }
                alert (result.message);
            });
        }
    </script>
</div>
