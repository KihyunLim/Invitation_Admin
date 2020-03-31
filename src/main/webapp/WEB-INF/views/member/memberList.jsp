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
										<button type="button" class="btn btn-primary" id="btnMemberRegister" data-toggle="modal" data-target="#modal-memberRegister">추가</button>
									</div>
									<div class="col-md-6">
										<div class="dataTables_filter">
											<select id="selectCondition">
												<option value="id">아이디</option>
												<option value="name">이름</option>
												<option value="phone">핸드폰</option>
											</select>
											<input type="text" id="inputKeyword" />
											<button type="button" class="btn btn-default" id="btnSearch">검색</button>
										</div>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
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

				<div class="modal fade" id="modal-memberRegister">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">회원 추가</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form role="form">
									<div class="form-group">
										<label>아이디</label> 
									</div>
									<div class="input-group mb-3">
										<input type="text" class="form-control resetInput" id="inputRegisterId" maxlength="20">
										<span class="input-group-append">
											<button type="button" class="btn btn-info btn-flat" id="btnOverlapCheck">중복확인</button>
										</span>
									</div>
									<div class="form-group mb-3">
										<label>비밀번호</label> 
										<input type="text" class="form-control resetInput" id="inputRegisterPassword" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>이름</label> 
										<input type="text" class="form-control resetInput" id="inputRegisterName" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>전화번호</label> 
										<input type="number" class="form-control resetInput" id="inputRegisterPhone" maxlength="11">
									</div>
								</form>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary" id="btnRegist">추가</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<div class="modal fade" id="modal-memberModify">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">회원 상세 및 수정</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body">
								<form role="form">
									<div class="form-group mb-3">
										<label>아이디</label> 
										<input type="text" class="form-control resetInput" id="inputModifyId" disabled>
									</div>
									<div class="form-group mb-3">
										<label>비밀번호</label> 
										<input type="text" class="form-control resetInput" id="inputModifyPassword" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>이름</label> 
										<input type="text" class="form-control resetInput" id="inputModifyName" maxlength="20">
									</div>
									<div class="form-group mb-3">
										<label>전화번호</label> 
										<input type="text" class="form-control resetInput" id="inputModifyPhone" maxlength="11">
									</div>
									<div class="form-group mb-3">
										<label>가입일</label> 
										<input type="text" class="form-control resetInput" id="inputModifyRegDate" disabled>
									</div>
									<div class="form-group mb-3">
										<label>청첩장 게시일</label> 
										<input type="text" class="form-control resetInput" id="inputModifyLatestInvitation" disabled>
									</div>
								</form>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary" id="btnModify">수정</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
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
