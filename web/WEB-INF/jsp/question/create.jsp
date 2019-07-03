<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
				<div class="panel panel-default">
                	<div class="panel-heading">
                                                            创建试题
                    </div>
	                <div class="panel-body">
	                	<form role="form" id="createFormExamQuestion" class="form-horizontal" 
							action="${pageContext.request.contextPath}/exam/question/create"
							method="post" enctype="multipart/form-data">
			               <div class="form-group">
			                    <label class="col-sm-2 control-label">题干</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入题干" name="name">
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="col-sm-2 control-label">图片</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" name="imageFile" type="file">
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="col-sm-2 control-label">答案</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入答案" name="answer">
			                    </div>
			                </div>
			                <div class="form-group">
                                <label class="col-sm-2 control-label">知识点</label>
                                <div class="col-sm-10">
                                	<select class="form-control" name="knowledgeId">
                                	</select>
                                </div>
                            </div>
			                <div class="form-group">
			                    <label class="col-sm-2 control-label">描述</label>
			                    <div class="col-sm-10">
			                    	<input class="form-control" placeholder="请输入描述" name="description">
			                    </div>
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
	   	    	// 异步加载知识点
	   	    	$('#createFormExamQuestion select').load ('${pageContext.request.contextPath}/exam/knowledge/options');
	   	    	
	   	  		// 将验证规则应用到表单的字段中
	   			$('#createFormExamQuestion').bootstrapValidator({
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
   			</script>
	</div>