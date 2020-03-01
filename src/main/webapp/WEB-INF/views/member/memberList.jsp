<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-select/css/select.bootstrap4.css">
	
	<title>회원 관리</title>
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
							<h1>회원 관리</h1>
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
								<h3 class="card-title">추가/삭제/검색 영역</h3>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<table id="tableMemberList" class="table table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th></th>
											<th>아이디</th>
											<th>이름</th>
											<th>핸드폰</th>
											<th>게시상태</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
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
	<script src="../adminlte3/plugins/datatables-select/js/dataTables.select.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>
</body>
</html>
