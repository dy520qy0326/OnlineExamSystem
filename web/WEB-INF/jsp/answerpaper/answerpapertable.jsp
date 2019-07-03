<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--author 秦烨 邓烨  -->
	<%@ page import="org.dream.www.sys.vo.WoUser"%>
	<%@ page import="org.dream.www.sys.util.SysConstant"%>
	<%
	// 从session中获取用户信息
	WoUser u = (WoUser) session.getAttribute(SysConstant.SESSION_USER);
	
	
%>
		<div id="page-wrapper">
			<div class="row">
                <div class="col-lg-12">
                    <h4 style="text-align: center">AnswerPaper Manger</h4>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <table width="90%" class="table table-striped table-bordered table-hover" 
                            	id="tableAnswerPaper" style="text-align: center">
                                <thead>
                                    <tr>
                                       
                                        <th>编号</th>
                                        <th>用户名</th>
                                        <th>角色</th>
                                        <th>试卷名</th>
                                        <th>分数</th>
                                        <th>开始时间</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                            </table>
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
            	var tableId = 'tableAnswerPaper';
           		var userId = <%=u.getId()%>
            	// 将DataTable获取的json数据中的属性，和列对应起来
            	var cols = [
                	
                	{ "data": "id" , "orderable" : false}, // 设置编号/id列
                	{ "data": "userName" , "orderable" : false}, // 设置用户名/userName列
                	{ "data": "roleName" , "orderable" : false}, // 设置角色/roleName列
                	{ "data": "paperName" , "orderable" : false}, // 设置试卷名/paperName列
                	{ "data": "score" , "orderable" : false}, // 设置分数/score列
                	{ "data": "startTime" , "orderable" : true},
                	{ "data": null,"targets":6,"orderable" : false,"defaultContent": "<select id='singleoption' name='singleoption'  ><option value='0'>答题</option><option value='1'>查看</option><option value='2'>成绩单</option></select>" }// 设置操作/singleoption列
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
                        "url": "${pageContext.request.contextPath}/exam/answerpaper/list",
                       "data":{"userId":<%=u.getId().toString()%>},
                        "type": "GET" // http方法
                    },
                    "order": [ [ orderIndex, "desc" ] ] , // 排序
                    "columns": cols
                });
             
                // 设置列表中按钮的事件
                 $('#' + tableId + ' tbody').on( 'click', 'tr', function () {
                	 var data = table.row( $(this) ).data();
                 	//选中
                 	
                 		$(this).toggleClass('selected');
                 	
                   });
                  
                $('#' + tableId + ' tbody').on( 'click', 'select', function () {
                	// this表示input标签对应dom，$(this)将其转为jQuery对象，获取按钮所在行的json对象:BookDto
                    var data = table.row( $(this).parents('tr') ).data();
                   var vl = $(this).children('option:selected').val();
                   
                	 // 跳转到答题界面
                	if (vl =='0') {
                		AnswerPage (data.id);
                	}
                	// 跳转到查看试卷结果界面
					if (vl=='1') {
						QuerryAnswerPaperPage (data.id);
                	}
                	//跳转到成绩单界面
					if (vl=='2') {
						QuerryReport (data.id);
                	}  
                } );
                // 添加工具栏按钮，找到设置分页的div，并添加创建和批量删除按钮的a标签
                $('#' + tableId + '_length').append (null);
            });
             	
               

            function AnswerPage (id) {
            	location.href = "${pageContext.request.contextPath}/sys/answerpaper/answer?id="+id;
            };
            
            function QuerryAnswerPaperPage (id) {
            	location.href = "${pageContext.request.contextPath}/sys/answerpaper/queryanswer?id="+id;
            };
            
            function QuerryReport (id) {
            	location.href = "${pageContext.request.contextPath}/sys/answerpaper/querySocer?id="+id;
            };
            
		    </script>
		</div>