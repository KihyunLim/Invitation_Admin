<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp" %>
	<title>로그인</title>
</head>

<body class="hold-transition login-page">
	<div class="login-box">
		<div class="login-logo">
			<span><b>Invitataion</b> Admin</span>
		</div>
		<!-- /.login-logo -->
		<div class="card">
			<div class="card-body login-card-body">
				<p class="login-box-msg">계정을 입력해주세요.</p>

				<div class="input-group mb-3">
					<input type="text" class="form-control" placeholder="아이디" id="inputId">
					<div class="input-group-append">
						<div class="input-group-text"></div>
					</div>
				</div>
				<div class="input-group mb-3">
					<input type="password" class="form-control" placeholder="비밀번호" id="inputPassword">
					<div class="input-group-append">
						<div class="input-group-text">
							<span class="fas fa-lock"></span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-8">
						<div class="icheck-primary">
							<input type="checkbox" id="checkboxSaveId"> <label for="checkboxSaveId"> 아이디 저장하기 </label>
						</div>
					</div>
					<!-- /.col -->
					<div class="col-4">
						<button type="submit" class="btn btn-primary btn-block" id="btnLogin">로그인</button>
					</div>
					<!-- /.col -->
				</div>
			</div>
			<!-- /.login-card-body -->
		</div>
	</div>
	<!-- /.login-box -->

	<%@ include file="../include/adminlte3/js.jsp" %>
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/login/login.js"></script>
</body>
</html>
