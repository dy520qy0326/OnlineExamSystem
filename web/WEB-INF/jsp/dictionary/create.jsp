<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="org.dream.www.sys.dto.DictionaryDto"%>

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">创建数据字典</div>
				<div class="panel-body">
					<form role="form" id="createFormSysDictionary" class="form-horizontal"
						action="${pageContext.request.contextPath}/sys/dictionary/create"
						method="post">
						<div class="form-group">
							<label class="col-sm-2 control-label">名称</label>
							<div class="col-sm-10">
								<input class="form-control" placeholder="请输入名称"
									name="name">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">值</label>
							<div class="col-sm-10">
								<input  class="form-control" placeholder="请输入值"
									name="value">
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">类型</label>
							<div class="col-sm-10">
								<input  class="form-control" placeholder="请输入类型"
									name="type">
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
							<div class="col-sm-2"></div>
							<div class="col-sm-10">
								<button type="button" class="btn btn-primary" onclick="create()">提交</button>
								<button type="reset" class="btn btn-default">重置</button>
								<button type="button" class="btn btn-warning"
									onclick="location.href='${pageContext.request.contextPath}/'">返回</button>
							</div>
							</div>
							</form>
						</div>
					
				</div>
			</div>
		</div>
		<!-- /.panel-body -->
	</div>

<script >
	
function create() {
	createFormSysDictionary.submit();
}
</script>
