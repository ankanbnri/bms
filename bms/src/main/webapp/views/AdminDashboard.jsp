<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
<link rel="stylesheet" href="/css/util.css">
<link rel="stylesheet" href="/css/adminBus.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://unpkg.com/aos@next/dist/aos.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>BMS ADMIN | DASHBOARD</title>
</head>
<!-- NAVAR AND SIDEBAR -->

<body class="fixed-nav sticky-footer bg-dark" id="page-top">

	<%@ include file="adminSecurity.jsp"%>

	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark" id="mainNav">
		<a class="navbar-brand" href="/admin/dashboard">Bus Management
			System </a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav navbar-sidenav " id="exampleAccordion">
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Dashboard"><a class="nav-link" href="/admin/dashboard">
						<i class="fa fa-fw fa-sign-in-alt"></i> <span
						class="nav-link-text">Dashboard</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link"
					href="/admin/displayBusInformation"> <i
						class="fa fa-fw fa-table"></i> <span class="nav-link-text">Display
							Bus Information</span>
				</a>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Tables"><a class="nav-link" href="AdminAddBus.html">
						<i class="fa fa-fw fa-wrench"></i> <span class="nav-link-text">Add
							New Bus</span>
				</a></li>
				</li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Example Pages"><a class="nav-link"
					href="/admin/download/revenueReport.xlsx"
					data-parent="#exampleAccordion"> <i class="fa fa-fw fa-file"></i>
						<span class="nav-link-text">Generate Revenue Report</span>
				</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">

				<li class="nav-item"><a class="nav-link" data-toggle="modal"
					data-target="#exampleModal"> <i class="fa fa-fw fa-sign-out"></i>Logout
				</a></li>
			</ul>
		</div>
	</nav>
	<div class="content-wrapper">
		<div class="container-fluid">
			<!-- Breadcrumbs-->
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
				<li class="breadcrumb-item active">Admin Dashboard</li>
			</ol>
			<!-- Icon Cards-->
			<div class="row">
				<div class="col-xl-3 col-sm-6 mb-3">
					<div class="card text-white bg-primary o-hidden h-100">
						<div class="card-body">
							<div class="card-body-icon">
								<i class="fa fa-fw fa-comments"></i>
							</div>
							<div class="mr-5">${busCount } Buses Currently Running</div>
						</div>
						<a class="card-footer text-white clearfix small z-1" href="#">
							<span class="float-left"></span> <span class="float-right">
						</span>
						</a>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 mb-3">
					<div class="card text-white bg-warning o-hidden h-100">
						<div class="card-body">
							<div class="card-body-icon">
								<i class="fa fa-fw fa-list"></i>
							</div>
							<div class="mr-5">${routeCount } Routes Currently Serving</div>
						</div>
						<a class="card-footer text-white clearfix small z-1" href="#">
							<span class="float-left"></span> <span class="float-right">
						</span>
						</a>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 mb-3">
					<div class="card text-white bg-success o-hidden h-100">
						<div class="card-body">
							<div class="card-body-icon">
								<i class="fa fa-fw fa-shopping-cart"></i>
							</div>
							<div class="mr-5">${userCount } Users Served Till Date</div>
						</div>
						<a class="card-footer text-white clearfix small z-1" href="#">
							<span class="float-left"></span> <span class="float-right">
						</span>
						</a>
					</div>
				</div>
				<div class="col-xl-3 col-sm-6 mb-3">
					<div class="card text-white bg-danger o-hidden h-100">
						<div class="card-body">
							<div class="card-body-icon">
								<i class="fa fa-fw fa-support"></i>
							</div>
							<div class="mr-5">${ticketCount } Tickets Sold</div>
						</div>
						<a class="card-footer text-white clearfix small z-1" href="#">
							<span class="float-left"></span> <span class="float-right">
						</span>
						</a>
					</div>
				</div>
			</div>
			<!-- Example DataTables Card-->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-table"></i> Route Information
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Route Code</th>
									<th>Start Name</th>
									<th>Stop Name</th>
									<th>Distance(in KM)</th>
									<th>Total Revenue till Date(in Rupees)</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${routeRevenues }" var="route">
									<tr>
										<td>${route.routecode }</td>
										<td>${route.source }</td>
										<td>${route.destination }</td>
										<td>${route.distance }</td>
										<td>${route.totalrevenue }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="card-footer small text-muted">
					Last Updated at
					<%=(new java.util.Date()).toString()%></div>
			</div>
		</div>
		<!-- /.container-fluid-->
		<!-- /.content-wrapper-->
		<footer class="sticky-footer">
			<div class="container">
				<div class="text-center">
					<small>Copyright © Bus Management System 2022</small>
				</div>
			</div>
		</footer>
		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fa fa-angle-up"></i>
		</a>
		<!-- Logout Modal-->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Ready to
							Leave?</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">Ã—</span>
						</button>
					</div>
					<div class="modal-body">Select "Logout" below if you are
						ready to end your current session.</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Cancel</button>
						<a class="btn btn-primary" href="/admin/logout">Logout</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>


</html>