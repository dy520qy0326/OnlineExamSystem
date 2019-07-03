<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String name = "";

	String checked = "";
	String error = null;
	 if(request.getAttribute("error")!=null){
		error = request.getAttribute("error").toString(); 
		 int begin = error.indexOf(":")+1;
		 int last  = error.length();
		 error = error.substring(begin, last);
	 };
	

	try {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("loginName")) {
					String value = cookies[i].getValue();
					if (value != null && !"".equals(value)) {
						name = cookies[i].getValue();
						checked = "checked";

					}
				}
				request.setAttribute("name", name);
			}
		} else {
			checked = "";
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>User Login</title>

<!-- Custom fonts for this template-->
<link href="sb-admin2/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="sb-admin2/css/sb-admin-2.min.css" rel="stylesheet">
<link href="sb-admin2/vendor/bootstrap-validator/css/bootstrapValidator.min.css"
	rel="stylesheet" type="text/css">
<!-- Bootstrap core JavaScript-->
<script src="sb-admin2/vendor/jquery/jquery.min.js"></script>
<script src="sb-admin2/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="sb-admin2/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="sb-admin2/js/sb-admin-2.min.js"></script>

	<script src="sb-admin2/vendor/bootstrap-validator/js/bootstrapValidator.min.js"></script>

</head>

<body class="bg-gradient-primary">

	<div class="container">

		<!-- Outer Row -->
		<div class="row justify-content-center">

			<div class="col-xl-10 col-lg-12 col-md-9">

				<div class="card o-hidden border-0 shadow-lg my-5">
					<div class="card-body p-0">
						<!-- Nested Row within Card Body -->
						<div class="row">
							<div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
							<div class="col-lg-6">
								<div class="p-5">
									<div class="text-center">
										<h1 class="h4 text-gray-900 mb-4">Welcome Back!</h1>
									</div>
									<form class="user" id="userForm" action="syslogin"
										method="post">
										<div class="form-group" id="loginNamediv" width="80%">
											<input type="text" class="form-control form-control-user"
												id="loginName" aria-describedby="loginName" name="loginName"
												placeholder="Enter loginName" value="<%=name%>"  />
										</div>
										<div class="form-group">
											<input type="password" class="form-control form-control-user"
												id="password" name="password" placeholder="password">
										</div>
										<div class="small">
											<div class="custom-checkbox">
												<input type="radio" id="customCheck1" name="currentRole"
													value="administrator" class="custom-checkbox"> <label
													for="customCheck1">Administrator</label> <input
													type="radio" id="customCheck2" name="currentRole"
													value="headmaster" class="custom-checkbox"> <label
													for="customCheck2">Headmaster</label> <input type="radio"
													id="customCheck3" name="currentRole" value="teacher"
													class="custom-checkbox"> <label for="customCheck3">Teacher</label>
												<input type="radio" id="customCheck4" name="currentRole"
													value="student" class="custom-checkbox"> <label
													for="customCheck4">Student</label>
											</div>
										</div>
										<div class="form-group">
											<div class="custom-control custom-checkbox small">
												<input type="checkbox" class="custom-control-input"
													<%=checked%> id="customCheck5" name="remberloginName">
												<label class="custom-control-label" for="customCheck5">Remember
													Me</label>
											</div>
											<%
												if (error != null) {
											%>
											<div class="alert alert-danger"><%=error%></div>
											<%
												}
											%>
										</div>
										<a href="javascript:;" onclick="document:userForm.submit()"
											class="btn btn-primary btn-user btn-block"> Login </a>
										<hr>
										<a href="javascript:;"
											class="btn btn-primary btn-user btn-block"> Create an
											Account! </a> <a href="javascript:;"
											class="btn btn-google btn-user btn-block"> Forgot
											Password? </a>
									</form>
									<hr>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>

		</div>

	</div>
       <script type="text/javascript">
	   	    $(document).ready(function () {
	   	    	// 设置各字段的验证规则
	   	  		// 将验证规则应用到表单的字段中
	   			$('#userForm').bootstrapValidator({
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
		            	loginName : {
		            		validators : {
		            			'notEmpty' : {
		            				message : '不允许为空'
		            			}
		            		}
		            	},
		            	password : {
		            		validators : {
		            			'notEmpty' : {
		            				message : '不允许为空'
		            			}
		            		}
		            	}
		            }
		        });
	   	    	
	   			$('#userForm .input-group-btn').click (function () {
					// 删除对话框
					$('#dialogSysRole').remove();
					// 创建对话框
	            	$('body').append ('<div class="modal fade" id="dialogSysRole" tabindex="-1" role="dialog"></div>');
	            	// 显示对话框
	            	$('#dialogSysRole').load ('${pageContext.request.contextPath}/sys/role/selector', {
	            		formId : 'updateFormSysUser',
	            		idField : 'rolesId',
	            		nameField : 'rolesName',
	            		singleSelect : false
	            	}, function () {
	            		// 显示角色对话框
	            		$('#dialogSysRole').modal ('show');
	            	});
				});
	   	    	
		    });
   			</script>


</body>
</html>
