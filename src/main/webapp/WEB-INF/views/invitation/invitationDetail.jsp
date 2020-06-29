<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<%@ include file="../include/adminlte3/head.jsp"%>
	<!-- daterange picker -->
	<link rel="stylesheet" href="../adminlte3/plugins/daterangepicker/daterangepicker.css">
	<!-- Ekko Lightbox -->
	<link rel="stylesheet" href="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.css">
	<!-- dataTable -->
	<link rel="stylesheet" href="../adminlte3/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
	
	<title>IA</title>
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
			<section class="content" id="sectionContent">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ 기본 정보 (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm btnModifyInvitation" data-category="invitation" >수정</button>
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
										<input type="text" id="inputId" />
										<button type="button" class="btn btn-default btn-sm" id="btnMemberSearch">검색</button>
									</div>
									<div class="col-md-2">
										<span>이름 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" id="inputName" disabled />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>게시 기간 : </span>
									</div>
									<div class="col-md-10">
										<input type="text" class="" id="inputDatePeriod" /> <label><input type="checkbox" name="checkboxVisibleYN" />체크 시 비공개로 등록</label>
									</div>
								</div>
							</div>
							<!-- /.card-body -->
						</div>
						<!-- /.card -->
						<div class="card" id="divHomeGroomBride">
							<div class="card-header">
								<h3 class="card-title header-padding-top">▶ Home, Groom &amp; Bride (필수)</h3>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-primary btn-sm btnModifyInvitation" data-category="mi" >수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>결혼 일자 및 일시 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="inputDateTime" id="inputDateTimeWedding" />
									</div>
									<div class="col-md-2">
										<span>장소 : </span>
									</div>
									<div class="col-md-4">
										<input type="text" class="w70p inputAddrWeddingPlace" id="infoWeddingPlace" disabled />
										<button type="button" class="btn btn-default btn-sm btnGetAddress">검색</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="contentGroom" style="resize:none;"></textarea>
									</div>
									<div class="col-md-2">
										<span>신부 간단 소개 : </span>
									</div>
									<div class="col-md-4">
										<textarea rows="5" class="form-control" id="contentBride" style="resize:none;"></textarea>
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>메인 사진 : </span>
									</div>
									<div class="col-md-4 uploadbox wrapUploadFile">
										<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/uploadImage.png" class="mb-2 img-thumnail-h100px">
										</a>
										<label for="imgMI1">업로드</label>
										<input type="file" class="btnUploadFile" id="imgMI1" accept="image/png, image/jpeg, image/jpg, image/gif" />
										<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
									</div>
									<div class="col-md-6">
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 &amp; 신부 사진 사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxEachImgYN" checked />
									</div>
								</div>
								<div class="row">
									<div class="col-md-2">
										<span>신랑 사진 : </span>
									</div>
									<div class="col-md-4 uploadbox wrapUploadFile">
										<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/uploadImage.png" class="mb-2 img-thumnail-h100px">
										</a>
										<label for="imgMI2">업로드</label>
										<input type="file" class="btnUploadFile" id="imgMI2" accept="image/png, image/jpeg, image/jpg, image/gif" />
										<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
									</div>
									<div class="col-md-2">
										<span>신부 사진 : </span>
									</div>
									<div class="col-md-4 uploadbox wrapUploadFile">
										<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
											<img src="../css/img/uploadImage.png" class="mb-2 img-thumnail-h100px">
										</a>
										<label for="imgMI3">업로드</label>
										<input type="file" class="btnUploadFile" id="imgMI3" accept="image/png, image/jpeg, image/jpg, image/gif" />
										<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
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
										<button type="button" class="btn btn-primary btn-sm btnModifyInvitation" data-category="ls" >수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxUseLSYN" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<span>●를 마우스로 끌어서 순서 변경 가능합니다.</span>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12" id="wrapListLS">
										<!-- start : tableRecordLoveStory -->
										<table id="tableRecordLoveStory" class="table table-valign-middle table-bordered dataTable itemLoveStory">
											<tbody class="uploadbox wrapUploadFile">
												<tr>
													<td rowspan="3" style="width:10%;" class="text-center">●</td>
													<td rowspan="3" style="width:20%;" class="text-center">
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</td>
													<td style="width:10%;" class="text-center">일자</td>
													<td style="width:60%;">
														<input type="text" class="inputDateLoveStory" />
													</td>
												</tr>
												<tr>
													<td class="text-center">제목</td>
													<td>
														<input type="text" class="w-100 inputTitleLS" />
													</td>
												</tr>
												<tr>
													<td rowspan="2" class="text-center">내용</td>
													<td rowspan="2">
														<textarea rows="3" class="form-control inputContentLS" style="resize:none;"></textarea>
													</td>
												</tr>
												<tr>
													<td class="text-center">
														<button type="button" class="btn btn-default btn-sm btnRemoveLS">삭제</button>
													</td>
													<td class="text-center">
														<label for="">업로드</label>
														<input type="file" class="btnUploadFile" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</td>
												</tr>
											</tbody>
										</table>
										<!-- end : tableRecordLoveStory -->
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 text-center">
										<button type="button" class="btn btn-default btn-sm" id="btnAddLS">추가</button>
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
										<button type="button" class="btn btn-primary btn-sm btnModifyInvitation" data-category="ww" >수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">결혼식</td>
												<td>
													<input type="checkbox" checked disabled />
													<span>(필수)</span>
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" id="inputDateTimeWedding_copy" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" class="w70p inputAddrWeddingPlace" disabled />
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="inputTitleWeddingWW" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="inputContentWeddingWW" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable">
											<tr>
												<td style="width:15%;" class="text-center">폐백</td>
												<td>
													<input type="checkbox" name="checkboxDoPyebaek" />
													<span>(선택)</span>
												</td>
											</tr>
											<tr>
												<td class="text-center">일자 및 일시</td>
												<td>
													<input type="text" class="inputDateTime" id="inputDatePyebaek" />
												</td>
											</tr>
											<tr>
												<td class="text-center">장소</td>
												<td>
													<input type="text" class="w70p" id="inputAddrPyebaek" disabled />
													<button type="button" class="btn btn-default btn-sm btnGetAddress">검색</button>
												</td>
											</tr>
											<tr>
												<td class="text-center">제목</td>
												<td>
													<input type="text" class="w-100" id="inputTitlePyebaekWW" />
												</td>
											</tr>
											<tr>
												<td class="text-center">내용</td>
												<td>
													<textarea rows="5" class="form-control" id="inputContentPyebaekWW" style="resize:none;"></textarea>
												</td>
											</tr>
										</table>
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
										<button type="button" class="btn btn-primary btn-sm btnModifyInvitation" data-category="gallery" >수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxUseG" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<table class="table table-valign-middle table-bordered dataTable" id="tableGallery">
											<tr>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG1">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG1" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG2">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG2" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG3">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG3" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG4">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG4" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG5">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG5" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
											</tr>
											<tr>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG6">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG6" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG7">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG7" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG8">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG8" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG9">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG9" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
												<td style="width:20%;" class="text-center uploadbox wrapUploadFile">
													<div>
														<a class="aFileData" href="" data-toggle="lightbox" data-title="image title">
															<img src="../css/img/uploadImage.png" class="img-thumnail-h100px">
														</a>
													</div>
													<div>
														<label for="imgG10">업로드</label>
														<input type="file" class="btnUploadFile" id="imgG10" accept="image/png, image/jpeg, image/jpg, image/gif" />
														<button type="button" class="btn btn-default btn-sm btnDeleteImage" style="display:none;">삭제</button>
													</div>
												</td>
											</tr>
										</table>
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
										<button type="button" class="btn btn-primary btn-sm btnModifyInvitation" data-category="sm" >수정</button>
									</div>
								</div>
							</div>
							<!-- /.card-header -->
							<div class="card-body">
								<div class="row">
									<div class="col-md-2">
										<span>사용 여부 : </span>
									</div>
									<div class="col-md-10">
										<input type="checkbox" name="checkboxUseSM" />
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<button type="button" class="btn btn-default btn-sm" id="">방명록 다운로드</button>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12 dataTables_wrapper">
										<table id="tableSweetMessageList" class="table table-bordered table-hover dataTable">
											<thead>
												<tr>
													<th style="width:5%;" class="text-center">순번</th>
													<th style="width:20%;" class="text-center">일시</th>
													<th style="width:10%;" class="text-center">이름</th>
													<th style="width:45%;" class="text-center">내용</th>
													<th style="width:10%;" class="text-center">비밀번호</th>
													<th style="width:10%;" class="text-center">삭제</th>
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
				
				<div class="modal fade" id="modal-invitationList">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title">청첩장 선택</h4>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<div class="modal-body" id="divMemberInvitationWrap">
								<div class="form-check" id="divRecordMemberInvitation">
									<label class="form-check-label">
										<input class="form-check-input" type="radio" name="radioInvitation">
										번호 : <span class="spanInvitationSeq"></span>
										&nbsp;&nbsp;/&nbsp;&nbsp;
										결혼 일자 및 일시 : <span class="spanInvitationDateWedding"></span>
									</label>
								</div>
							</div>
							<div class="modal-footer justify-content-between">
								<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
								<button type="button" class="btn btn-primary" id="btnChoose">선택</button>
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
	<!-- jQuery-ui -->
	<script src="../adminlte3/plugins/jquery-ui/jquery-ui.min.js"></script>
	<!-- date-range-picker -->
	<script src="../adminlte3/plugins/daterangepicker/moment.min.js"></script>
	<script src="../adminlte3/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- Ekko Lightbox -->
	<script src="../adminlte3/plugins/ekko-lightbox/ekko-lightbox.min.js"></script>
	<!-- DataTables -->
	<script src="../adminlte3/plugins/datatables/jquery.dataTables.js"></script>
	<script src="../adminlte3/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	
	<script type="text/javascript" src="../js/util.js"></script>
	<script type="text/javascript" src="../js/def.js"></script>
	<script type="text/javascript" src="../js/invitation/invitationDetail.js"></script>
</body>
</html>
