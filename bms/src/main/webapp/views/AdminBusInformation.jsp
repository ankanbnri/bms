<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.nrifintech.bms.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
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

	<c:url var="sortLinkRegistrationNumber"
		value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.REGISTRATION_NO)%>" />
	</c:url>
	<c:url var="sortLinkBusName" value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.BUS_NAME)%>" />
	</c:url>
	<c:url var="sortLinkSeatCount" value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.SEAT_COUNT)%>" />
	</c:url>
	<c:url var="sortLinkFare" value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.FARE)%>" />
	</c:url>
	<c:url var="sortLinkStatus" value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.IS_ACTIVE)%>" />
	</c:url>
	<c:url var="sortLinkFrom" value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.FROM)%>" />
	</c:url>
	<c:url var="sortLinkTo" value="/admin/displayBusInformation">
		<c:param name="sort"
			value="<%=Integer.toString(AdminBusSortingUtils.TO)%>" />
	</c:url>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-lg navbar-dark" id="mainNav">
		<a class="navbar-brand" href="index.html">Bus Management System </a>
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
					title="Tables"><a class="nav-link" href="/admin/addBus"> <i
						class="fa fa-fw fa-wrench"></i> <span class="nav-link-text">Add
							New Bus</span>
				</a></li>
				</li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Example Pages"><a class="nav-link"
					href="/admin/download/revenueReport.xlsx"
					data-parent="#exampleAccordion"> <i class="fa fa-fw fa-file"></i>
						<span class="nav-link-text">Generate Revenue Report</span>
				</a></li>
				<li class="nav-item" data-toggle="tooltip" data-placement="right"
					title="Example Pages"><a class="nav-link"
					href="/admin/download/underUtilizedBusReport.xlsx"
					data-parent="#exampleAccordion"> <i class="fa fa-fw fa-file"></i>
						<span class="nav-link-text">Generate Under Utilized Bus Report</span>
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
				<li class="breadcrumb-item"><a href="/admin/dashboard">Dashboard</a></li>
				<li class="breadcrumb-item active">Displaying All Bus
					Information</li>
			</ol>
			<!-- Example DataTables Card-->
			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-table"></i> Bus Information
				</div>
				<c:if test="${fn:length(busAddMsg) > 0}">
					<div class="alert alert-success alert-dismissible fade show"
						role="alert">
						${busAddMsg}
						<button type="button" class="close" data-dismiss="alert"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
				</c:if>
				<div class="card-body">
					<table class="table align-middle mb-0 bg-white">
						<thead class="bg-light">
							<tr>
								<th title="Sort Alphabetically By Registration Number"><a href="${sortLinkRegistrationNumber}">Registration
										number<i class="fas fa-sort"></i>
								</a></th>
								<th title="Sort Alphabetically By Bus Name"><a href="${sortLinkBusName}">Bus Name<i
										class="fas fa-sort"></i></a></th>
								<th title="Sort Alphabetically By Source"><a href="${sortLinkFrom}">From<i
										class="fas fa-sort"></i></a></th>
								<th title="Sort Alphabetically By Destination"><a href="${sortLinkTo}">To<i
										class="fas fa-sort"></i></a></th>
								<th title="Sort By Seat Count"><a href="${sortLinkSeatCount}">Seat Count<i
										class="fas fa-sort"></i></a></th>
								<th title="Sort By Fare"><a href="${sortLinkFare}">Fare(per KM)<i
										class="fas fa-sort"></i></a></th>
								<th title="Show Disabled Buses First"><a href="${sortLinkStatus}">Status<i
										class="fas fa-sort"></i></a></th>
								<th>Actions</th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${busFound}">
								<tbody>
									<c:forEach var="bus" items="${buses}">
										<tr>
											<td>
												<p class="fw-bold mb-1">${bus.getRegistrationNo() }</p>
												<p class="text-muted mb-0 small">${bus.getFacilities()}</p>
											</td>
											<td>
												<p class="fw-bold mb-1">${bus.getBusName() }</p>
											</td>
											<td>
												<p class="fw-bold mb-1">${bus.route.getStartName() }</p>
											</td>
											<td>
												<p class="fw-bold mb-1">${bus.route.getStopName() }</p>
											</td>
											<td>
												<p class="fw-bold mb-1">${bus.getSeatCount() }</p>
											</td>
											<td>
												<p class="fw-bold mb-1">${bus.getFare() }</p>
											</td>
											<td><c:choose>
													<c:when test="${bus.getActiveStatus() eq 'YES'}">
														<span class="badge badge-success rounded-pill">ACTIVE</span>
													</c:when>
													<c:otherwise>
														<span class="badge badge-warning rounded-pill">DISABLED</span>
													</c:otherwise>
												</c:choose></td>
											<td><c:choose>
													<c:when test="${bus.getActiveStatus() eq 'YES'}">
														<a href="/admin/ticketList/${bus.getRegistrationNo()}"
															title="Download PNR Report for ${tmrDate }"><span
															class="fa fa-download pr-2"></span></a>
														<a href="/admin/disableBus/${bus.getRegistrationNo() }"
															title="Disable the bus"><span
															class="fas fa-toggle-on"></span></a>
													</c:when>
													<c:otherwise>
														<a href="/admin/enableBus/${bus.getRegistrationNo() }"
															title="Enable the bus"><span
															class="fas fa-toggle-off"></span></a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
								</tbody>
							</c:when>
							<c:otherwise>
								<tbody>
									<tr>
										<td>
											<p class="fw-bold mb-1">NO DATA FOUND</p>
										</td>
									</tr>
								</tbody>
							</c:otherwise>
						</c:choose>
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
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">Ã—</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="/admin/logout">Logout</a>
				</div>
			</div>
		</div>
	</div>

</body>


</html>