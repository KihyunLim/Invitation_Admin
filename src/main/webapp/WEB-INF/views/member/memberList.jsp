<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- dataTable-select -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
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
						<div class="card dataTables_wrapper">
							<div class="card-header">
								<!-- <h3 class="card-title">추가/삭제/검색 영역</h3> -->
								<div class="row">
									<div class="col-md-6">
										<button type="button" class="btn btn-danger">삭제</button>
										<button type="button" class="btn btn-primary">추가</button>
									</div>
									<div class="col-md-6">
										<div class="dataTables_filter">
											<select id="selectCondition">
												<option value="id">아이디</option>
												<option value="name">이름</option>
												<option value="phone">핸드폰</option>
											</select>
											<input type="text" id="inputKeyword" />
											<button type="button" class="btn btn-default">검색</button>
										</div>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<!-- iframe으로 여기만 따로 잡아주는 것두?? -->
							
								<table id="tableMemberList" class="table table-bordered table-hover dataTable">
									<thead>
										<tr>
											<th style="width:5%;"></th>
											<th style="width:25%;">아이디</th>
											<th style="width:20%;">이름</th>
											<th style="width:30%;">핸드폰</th>
											<th style="width:20%;">게시상태</th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
								
								<div class="row">
									<div class="col-md-12" id="divPagingWrap" style="text-align:center;">
										<ul class="pagination" style="display:inline-flex;">
											<li class="paginate_button page-item first disabled" id="tableMemberList_first">
												<a href="#" aria-controls="tableMemberList" aria-label="First" data-dt-idx="0" tabindex="0" class="page-link">«</a>
											</li>
											<li class="paginate_button page-item previous disabled" id="tableMemberList_previous">
												<a href="#" aria-controls="tableMemberList" aria-label="Previous" data-dt-idx="1" tabindex="0" class="page-link">‹</a>
											</li>
											
											<li class="paginate_button page-item" id="liRecord" style="display:none">
												<a href="#" aria-controls="tableMemberList" data-dt-idx="2" tabindex="0" class="page-link">1</a>
											</li>
											
											<!-- 이거로 반복 돌리고 제어ㄱㄱ -->
											<li class="paginate_button page-item active">
												<a href="#" aria-controls="tableMemberList" data-dt-idx="2" tabindex="0" class="page-link">1</a>
											</li>
											<li class="paginate_button page-item">
												<a href="#" aria-controls="tableMemberList" data-dt-idx="3" tabindex="0" class="page-link">2</a>
											</li>
											<li class="paginate_button page-item">
												<a href="#" aria-controls="tableMemberList" data-dt-idx="4" tabindex="0" class="page-link">3</a>
											</li>
											<li class="paginate_button page-item">
												<a href="#" aria-controls="tableMemberList" data-dt-idx="5" tabindex="0" class="page-link">4</a>
											</li>
											<li class="paginate_button page-item">
												<a href="#" aria-controls="tableMemberList" data-dt-idx="6" tabindex="0" class="page-link">5</a>
											</li>
											
											<li class="paginate_button page-item next disabled" id="tableMemberList_next">
												<a href="#" aria-controls="tableMemberList" aria-label="Next" data-dt-idx="7" tabindex="0" class="page-link">›</a>
											</li>
											<li class="paginate_button page-item last disabled" id="tableMemberList_last">
												<a href="#" aria-controls="tableMemberList" aria-label="Last" data-dt-idx="8" tabindex="0" class="page-link">»</a>
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
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	<script src="../adminlte3/plugins/datatables-select/js/dataTables.select.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/member/memberList.js"></script>
</body>
</html>
