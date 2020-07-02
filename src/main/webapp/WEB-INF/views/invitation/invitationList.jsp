<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- daterange picker -->
	<link rel="stylesheet" href="../adminlte3/plugins/daterangepicker/daterangepicker.css">
	<!-- dataTable -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	
	<title>청첩장 목록</title>
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
							<h1>청첩장 목록</h1>
						</div>
					</div>
				</div>
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-12">
						<div class="card dataTables_wrapper">
							<div class="card-body">
								<div class="row">
									<table class="table table-valign-middle table-bordered dataTable">
										<tbody>
											<tr>
												<td class="text-center" style="width:10%;">아이디</td>
												<td class="" style="width:40%;">
													<input type="text" id="inputSearchId" />
												</td>
												<td class="text-center" style="width:10%;">이름</td>
												<td class="" style="width:40%;">
													<input type="text" id="inputSearchName" />
												</td>
											</tr>
											<tr>
												<td class="text-center">결혼일자</td>
												<td class="">
													<input type="text" id="inputSearchWeddingDate" />
												</td>
												<td colspan="2"></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-default btn-sm" id="btnSearch">검색</button>
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
				<div class="row">
					<div class="col-12">
						<div class="card dataTables_wrapper">
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table id="tableInvitationList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;">순번</th>
													<th style="width:13%;">아이디</th>
													<th style="width:10%;">이름</th>
													<th style="width:10%;">게시 상태</th>
													<th style="width:15%;">게시 시작일</th>
													<th style="width:15%;">게시 종료일</th>
													<th style="width:15%;">결혼식 일시</th>
													<th style="width:7%;">상세</th>
													<th style="width:7%;">청첩장</th>
												</tr>
											</thead>
											<tbody></tbody>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="" data-dt-idx="" tabindex="" class="page-link"></a>
											</li>
										</ul>
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
	<!-- jQuery-ui -->
	<script src="../adminlte3/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- date-range-picker -->
	<script src="../adminlte3/plugins/daterangepicker/moment.min.js"></script>
	<script src="../adminlte3/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationList.js"></script>
</body>
</html>
