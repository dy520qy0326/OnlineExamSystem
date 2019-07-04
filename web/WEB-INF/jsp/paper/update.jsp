<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.dream.www.sys.vo.WoUser"%>
<%@ page import="org.dream.www.sys.util.SysConstant"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	// 从session中获取用户信息
	WoUser u = (WoUser) session.getAttribute(SysConstant.SESSION_USER);
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
                                                            修改试卷
                    </div>
	                <div class="panel-body">
	                	<form role="form" id="updateFormExamPaper" class="form-horizontal"
							action="${pageContext.request.contextPath}/exam/paper/update"
							method="post">
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">ID</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" name="id" readonly value="${formData.id}">
			                    	<input class="form-control" name="createUserId" readonly value="<%=u.getId()%>" hidden>

			                    </div>
			                </div>
			            <div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input class="form-control" value="${formData.name}"
									   name="name">
							</div>
						</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">时长</label>
								<div class="col-sm-10">
									<input class="form-control" value="${formData.minutes}"
										   name="name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label"></label>
								<div class="col-sm-10">
									请设置各个知识点的题目数量：
								</div>
							</div>
							<c:forEach items="${pqkList}" var="k">
							<div class="form-group">
								<label class="col-sm-2 control-label">${k.knowledgeName }(${k.questionNum })</label>
								<div class="col-sm-10">
									<input class="form-control" name="knowledge_${k.knowledgeId }" value="${k.questionNeed }">
								</div>
							</div>
							</c:forEach>

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
	   			$('#updateFormExamPaper').bootstrapValidator({
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
	   	    	

		    });
   			</script>
	</div>