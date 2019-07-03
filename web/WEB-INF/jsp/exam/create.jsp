<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="org.dream.www.sys.vo.WoUser" %>
<%@ page import="org.dream.www.sys.util.SysConstant" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	WoUser uu = (WoUser)session.getAttribute(SysConstant.SESSION_USER);
%>
	<div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
				<div class="panel panel-default">
                	<div class="panel-heading">
                                                            创建考试
                    </div>
	                <div class="panel-body">
	                	<form role="form" id="createFormExamExam" class="form-horizontal" 
							action="${pageContext.request.contextPath}/exam/exam/create"
							method="post">
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">创建人</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control"  name="createUserName" required value='<%=uu.getLoginName()%>' readonly >
			                    	<input type='hidden' class="form-control"  name="createUserId" required value='<%=uu.getId()%>' readonly >
			                    </div>
			               </div>
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">考试名</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入考试名" name="name" required  >
			                    </div>
			               </div>
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">描述</label>
			                    <div class="col-sm-10">
			                    	<input  class="form-control" placeholder="请输入描述" name="description" >
			                    </div>
			               </div>
			                 <div class="form-group">
								<label class="col-sm-2 control-label">请选择试卷</label>
								<div class="col-sm-10">
									<input type="hidden"  name="papersId" >
		                            <input id = "papers" type="text" class="form-control" required placeholder="请选择试卷" name="papersName" readonly >
	                            </div>
	                        </div>
	                        
			                 <div class="form-group">
								<label  class="col-sm-2 control-label">参与考试的学生</label>
								<div class="col-sm-10">
									<input type="hidden"  name="studentsId" value="${studentsId}" >
		                            <input id = "students" type="text" class="form-control" value="${studentsName}" required placeholder="请选择学生" name="studentsName" readonly>
	                            </div>
	                        </div>
							<div class="form-group">
				                <div class="col-sm-2"></div>
				                <div class="col-sm-10">
				                <button type="submit" class="btn btn-primary">提交</button>
				                <button type="button" onclick='addUsers();' class="btn btn-primary">选择上次考试学生</button>
				                <button type="button" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/'">返回</button>
				                </div>
			                </div>
			            </form>
                    </div>
	                </div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <script type="text/javascript">
	   	    $(document).ready(function () {
            	$('#students').click (function () {
					// 删除对话框
					$('#dialogSysRole1').remove();
					$('#dialogSysRole2').remove();
					// 创建对话框
	            	$('body').append ('<div class="modal fade" id="dialogSysRole1" tabindex="-1" role="dialog"></div>');
	            	// 显示对话框
	            	$('#dialogSysRole1').load ('${pageContext.request.contextPath}/school/student/selector', {
	            		formId : 'createFormExamExam',
	            		idField : 'studentsId',
	            		nameField : 'studentsName',
	            		singleSelect : false
	            	}, function () {
	            		// 显示角色对话框
	            		$('#dialogSysRole1').modal ('show');
	            	});
	            	
				}); 
            	$('#papers').click (function () {
					// 删除对话框
					$('#dialogSysRole1').remove();
					$('#dialogSysRole2').remove();
					// 创建对话框
	            	$('body').append ('<div class="modal fade" id="dialogSysRole2" tabindex="-1" role="dialog"></div>');
	            	// 显示对话框
	            	$('#dialogSysRole2').load ('${pageContext.request.contextPath}/exam/paper/selector', {
	            		formId : 'createFormExamExam',
	            		idField : 'papersId',
	            		nameField : 'papersName',
	            		singleSelect : false
	            	}, function () {
	            		// 显示角色对话框
	            		$('#dialogSysRole2').modal ('show');
	            	});
	            	
				}); 
        		var lodingselectedData = {};
        		lodingselectedData.id = "${studentsId}".split(',');
        		lodingselectedData.name = "${studentsName}".split(',');
        	    if (!lodingselectedData.name[0]) {
        	    	lodingselectedData.id = [];
        	    	lodingselectedData.name = [];
            	}
            	loding = function loding(){
            		var index = -1;
                	for (var i = 0; i < lodingselectedData.id.length; i ++) {
                		var id = lodingselectedData.id[i];
                		var name = lodingselectedData.name[i];
                		$("#students").after(" <a id='"+id+name+"' class='btn btn-primary btn-sm' onclick='delStudent(\""+id+"\",\""+name+"\");'>"+name+"</a>");
                		
                	}
            	}
            	loding();
				addUsers = function addUsers () {
            		var iddata = $('#createFormExamExam input[name=studentsId]');//.val().split(',');
            		var namedate = $('#createFormExamExam input[name=studentsName]');//.val().split(',');
                	if (!confirm ('确定选择上次考试学生吗？注意会清空已选择的学生')) {
                		return;
                	}
                	$('#createFormExamExam a').remove();
                	iddata.val("");
            		namedate.val("");
            		var studentsData = {};
                	$.post ('${pageContext.request.contextPath}/exam/exam/getpreviousoccasionexamstudents',function (ids) {
                		studentsData.id = ids.split('getpreviousoccasionexamstudents');
                		iddata.val(studentsData.id[0]);
                		namedate.val(studentsData.id[1]);
                		$('#createFormExamExam a').remove();
                		var selectedData = {};
                		selectedData.id = studentsData.id[0].split(',');
                	    selectedData.name = studentsData.id[1].split(',');
                		var index = -1;
                    	for (var i = 0; i < selectedData.id.length; i ++) {
                    		var id = selectedData.id[i];
                    		var name = selectedData.name[i];
                    		//alert(selectedData.name[i]);
                    		$("#students").after(" <a id='"+id+name+"' class='btn btn-primary btn-sm' onclick='delStudent(\""+id+"\",\""+name+"\");'>"+name+"</a>");
                    		
                    	}
    		    	});
                }
	   	    	delStudent = function delStudent (id,name) {
	   	    		if (!confirm ('确定删除学生'+name+'吗？')) {
                		return;
                	}
	   	    		var iddata = $('#createFormExamExam input[name=studentsId]');//.val().split(',');
            		var namedate = $('#createFormExamExam input[name=studentsName]');//.val().split(',');
            		var ids = ','+iddata.val()+',';
            		var names = ','+namedate.val()+',';
            		var newids = ids.replace(','+id+',',',');
            		var newnames = names.replace(','+name+',',',');
            		var newids = newids.substr(0, newids.length-1).substr(1);
            		var newnames = newnames.substr(0, newnames.length-1).substr(1);
            		iddata.val(newids);
            		namedate.val(newnames);
            		$('#'+id+name+'').remove();
	            }
	   	    	$('#reset').click (function () {
                	$('#createFormExamExam a').remove();
                	loding();
	   	    	}) ;
		    });
   			</script>	
   			
	