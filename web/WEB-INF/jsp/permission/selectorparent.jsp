<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-dialog modal-lg" role="document">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<h4 class="modal-title">选择父类权限</h4>
		</div>
		<!-- /.row -->
		<div class="modal-body">
			<table width="100%"
				class="table table-striped table-bordered table-hover"
				id="tableSysPermissionSelectorParent">
				<thead>
					<tr>

						<th>Id</th>
						<th>Name</th>
						<th>Level</th>
						<th>Url</th>
						<th>ParentID</th>
						<th>Description</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<script>
        	// 构造DataTable
			var table = $('#tableSysPermissionSelectorParent').DataTable ({
				"processing": true, // 显示处理进度
                "serverSide": true, // 从服务端获取数据
                "rowId" : 'id',// 设置主键字段名称
                "ajax": {
                    "url": "${pageContext.request.contextPath}/sys/permission/selector/list", // 请求列表数据的url
                    "type": "GET" // http方法
                },
                // "order": [ [ 1, "desc" ] ] , // 排序
                "columns": [
                	
                	
                	{data : 'id', orderable : false}, 
                	{data : 'name', orderable : false}, 
                	{data : 'level', orderable : true},
                	{data : 'url', orderable : false},
                	{data : 'parentId', orderable : false},
                	{data : 'description', orderable : false},
    
                ]
			});
			// 传过来的参数
        	var selectorParams = ${selectorParams};
        	// 存放用户选择的权限数据
        	var selectedData = {};
        	// 初始化已经选择的数据
        	var idField = $('#' + selectorParams.formId + ' input[name=' + selectorParams.idField + ']');
        	var nameField = $('#' + selectorParams.formId + ' input[name=' + selectorParams.nameField + ']');
        	
        		selectedData.id = idField.val().split(',');         	
        		selectedData.name = nameField.val().split(',');
        	
        	if (!selectedData.id[0]) {
        		selectedData.id = [];
        		selectedData.name = [];
        	}
        	// 滤除空字符串
        	
        	// 设置选中行样式
        	table.on( 'draw', function () {
        		$('#tableSysPermissionSelectorParent tbody tr').each (function () {
                	var data = table.row($(this)).data();
                	
                	if (data) {
                    	if ($.inArray(data.id.toString(), selectedData.id) > -1) {
                    		$(this).addClass('selected');
                    	}
                	}
                });
			} );
			// 设置多选样式：通过父元素代理子元素的事件，此时，此元素有可能还没有生成
            $('#tableSysPermissionSelectorParent tbody').on( 'click', 'tr', function () {
            	// 切换样式info，如果有info样式则删除，没有则添加
            	$(this).toggleClass('selected');
            	// 获取行数据
            	var r = table.row ($(this)).data();
            	
            	var index = -1;
            
            	for (var i = 0; i < selectedData.id.length; i ++) {
            		var id = selectedData.id[i];
            		if (id == r.id) {
            			index = i;
            			break;
            		}
            	}
            	// 添加或者删除元素
            	if (index == -1) {
            		// 如果是单选，则清除其他行的选中状态
            		if (selectorParams.singleSelect) {
            			$(this).siblings().removeClass('selected');
            			selectedData.id = [];
                		selectedData.name = [];
            		}
            		// 添加元素
            		selectedData.id.push(r.id);
            		selectedData.name.push(r.name);
            		
            	} else {
            		// 删除元素
            		selectedData.id.splice(index, 1);
            		selectedData.name.splice(index, 1);
            	}
            	console.log (selectedData);
            } );
			
	
		</script>
</div>