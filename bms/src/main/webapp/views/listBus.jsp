<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="/css/util.css" />
<link rel="stylesheet" href="/css/listBus.css" />
<link rel="stylesheet" href="/css/navbar.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title>Available Bus</title>
</head>

<body>
	<%@ include file="userSecurity.jsp"%>
	<!-- NAVBAR -->
	<%@ include file="userNavbar.jsp"%>

	<c:choose>
		<c:when test="${busFound}">

			<!-- Result Page  -->
			<section class="container result-page">
				<!-- Number of buses found from abc to xyz -->
				<div class="row">
					<span class="font-weight-bold busFound">${buses.size()} Bus
					</span> <span>&nbsp;found from</span> <span
						class="font-weight-bold busFound">&nbsp;${buses.get(0).getRoute().getStartName()}
					</span> <span>&nbsp;to</span> <span class="font-weight-bold busFound">&nbsp;${buses.get(0).getRoute().getStopName()}
					</span> <span>&nbsp;(${buses.get(0).getRoute().getDistance()} kms)</span>
					<span>&nbsp;on</span> <span class="font-weight-bold busFound">&nbsp;${travelDate}</span>
				</div>
			</section>

			<!-- Found Buses Details -->
			<section class="container" id="listsection">
				<c:forEach var="bus" items="${buses}">
					<a
						style="margin-bottom: 2%; box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;"
						class="card"
						href="/ticket/bookTicket/${bus.getRegistrationNo()}/${travelDate}/${bus.getAvailableSeats()}">
						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<div class="row">
										<div class="col-md-6">
											<h4>
												<i class="fas fa-bus text-bms-primary"></i>
												${bus.getBusName()}
												<c:choose>
													<c:when test="${bus.getFacilities() == 'AC'}">
													    <span class="badge badge-pill badge-success my-auto">${bus.getFacilities()}</span>
													  </c:when>
													<c:otherwise>
													    <span class="badge badge-pill badge-warning my-auto">${bus.getFacilities()}</span>
													  </c:otherwise>
												</c:choose>
											</h4>
										</div>
										<div id="pnr" class="col-md-6">
											<h4 class="float-md-right text-danger">Fare Per Ticket:
												Rs.&nbsp;${bus.getFare() *
												bus.getRoute().getDistance()}</h4>
										</div>
									</div>
								</li>
								<li class="list-group-item">
									<div class="row">
										<div class="col-md-6">
											<h5>
												<i class="fas fa-chair"></i> Available Seats :
												${bus.getAvailableSeats()}
											</h5>
										</div>

										<div class="col-md-6">
											<h5 class="float-md-right">
												<i class="fa fa-clock"></i>Departure Time :
												${fn:substring(bus.getStartTime(),0,5)}
											</h5>
										</div>
									</div>
								</li>

							</ul>
						</div>
					</a>
				</c:forEach>
			</section>

		</c:when>
		<c:otherwise>
			<section class="container">
				<div
					class="row no-bus-found-parent justify-content-lg-center justify-content-md-center">
					<div class="card">
						<div class="card-body">No bus found in this Route. Kindly
							select a different route and plan your journey accordingly.</div>
					</div>
				</div>
			</section>
		</c:otherwise>
	</c:choose>

	<%@ include file="userFooter.jsp"%>
</body>

</html>