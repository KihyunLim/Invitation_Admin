<%@ page contentType="text/html; charset=UTF-8"%>
	<!-- Main Sidebar Container -->
	<aside class="main-sidebar sidebar-dark-primary elevation-4">
		<!-- Brand Logo -->
		<a href="../adminlte3/index3.html" class="brand-link">
			<img src="../adminlte3/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
			<span class="brand-text font-weight-light">Invitation Admin</span>
		</a>
	
		<!-- Sidebar -->
		<div class="sidebar">
			<!-- Sidebar Menu -->
			<nav class="mt-2" id="navSidebar">
				<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
					<!-- Add icons to the links using the .nav-icon class with font-awesome or any other icon font library -->
					<li class="nav-item">
						<a href="../member/memberList.do" class="nav-link" id="aMember">
							<i class="nav-icon fas fa-th"></i>
							<p>회원 관리</p>
						</a>
					</li>
					<li class="nav-item has-treeview">
						<a href="#" class="nav-link" id="aInvitation"> 
							<i class="nav-icon fas fa-table"></i>
							<p>
								청첩장 관리 <i class="fas fa-angle-left right"></i>
							</p>
						</a>
						<ul class="nav nav-treeview">
							<li class="nav-item">
								<a href="../invitation/invitationList.do" class="nav-link" id="aInvitation_List"> 
									<i class="far fa-circle nav-icon"></i>
									<p>청첩장 목록</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="../invitation/invitationDetail.do" class="nav-link" id="aInvitation_Detail"> 
									<i class="far fa-circle nav-icon"></i>
									<p>청첩장 상세</p>
								</a>
							</li>
							<li class="nav-item">
								<a href="../invitation/invitationAdd.do" class="nav-link" id="aInvitation_Add"> 
									<i class="far fa-circle nav-icon"></i>
									<p>청첩장 추가</p>
								</a>
							</li>
						</ul>
					</li>
				</ul>
			</nav>
			<!-- /.sidebar-menu -->
		</div>
		<!-- /.sidebar -->
	</aside>