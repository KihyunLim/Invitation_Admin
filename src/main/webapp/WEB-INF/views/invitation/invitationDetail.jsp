<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-select/css/select.bootstrap4.css">
	
	<title>청첩장 상세</title>
</head>

<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="../include/adminlte3/navbar.jsp"%>
		<%@ include file="../include/adminlte3/sidebar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>청첩장 상세</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ 기본 정보 (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>아이디 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
										<button type="button" class="btn btn-default btn-sm" id="">검색</button>
									</div>
									<div class="col-md-2">
										<span>이름 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>게시 상태 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="" disabled />
										<input type="checkbox" id="" />
										<label>체크 시 비공개로 전환</label>
									</div>
									<div class="col-md-2">
										<span>게시 시간 : </span>
									</div>
									<div class="col-md-4">
										<!-- 브라우저 안가리게 걍 datepicker를 써야할까 -->
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Home, Groom &amp; Bride (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Love story (선택)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ When Where (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Gallery (선택)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Sweet Message (선택)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm" id="">수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<%@ include file="../include/adminlte3/footer.jsp"%>
	</div>
	<!-- ./wrapper -->

	<%@ include file="../include/adminlte3/js.jsp"%>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationDetail.js"></script>
</body>
</html>
