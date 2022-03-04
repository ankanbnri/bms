<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" />
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
    crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
    crossorigin="anonymous"></script>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="/css/util.css" />
  <link rel="stylesheet" href="/css/myTickets.css" />
  <link rel="stylesheet" href="/css/navbar.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <title>My Tickets</title>
</head>
<body>
<% response.setHeader("Cache-Control", "no-cache, no-store, no-revalidate" ); // HTTP 1.1
                    response.setHeader("Pragma", "no-cache" ); // HTTP 1.0 
                    response.setHeader("Expires", "0" );  //Proxies  %>

                    <c:if test="${sessionScope.isValidUser != true}">
                        <%response.sendRedirect("/user/login");%>
                    </c:if>
                    
		<!-- NAVBAR -->
	  <nav class="navbar navbar-expand-lg navbar-light bg-bms-primary">
	    <div class="container nav-container">
	      <div class="brand">
	        <a class="navbar-brand text-bms-yellow font-weight-bold" href="/user/welcome">BMS</a>
	      </div>
	
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
	        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	      </button>
	
	      <div class="collapse navbar-collapse" id="navbarSupportedContent">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active mx-2">
	            <a class="nav-link item" href="/user/searchBus">Plan Journey</a>
	          </li>
	          <li class="nav-item mx-2">
	            <a class="nav-link" href="/user/myTickets">My Tickets</a>
	          </li>
	        </ul>
	        <form class="form-inline my-2 my-lg-0" action="/user/logout">
	          <button class="btn bg-bms-primary my-2 my-sm-0 logout-btn text-bms-secondary font-weight-bold" type="submit">
	            LOG OUT
	          </button>
	        </form>
	      </div>
	    </div>
	  </nav>
	 
	 <!-- UPCOMING TICKET SECTION -->
	 <section class="container upcoming-ticket-parent">
	 	<div class="row"><h3>Upcoming Tickets</h3></div>
	 	<c:choose>
	 	<c:when test="${upcomingTicketFound}">
	 	<c:forEach var="upcomingTicket" items="${upcomingTickets}">
	 		<div class="row">
            <div class="card details">
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-6">
                                    <h4><i class="fas fa-bus text-bms-primary"></i> ${upcomingTicket.getBus().getBusName()} (${upcomingTicket.getBus().getRegistrationNo()})</h4>
                                </div>
                                <div id="pnr" class="col-md-6">
                                    <h4 class="float-md-right">PNR NO:(${upcomingTicket.getPnrNo()})</h4>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-6">
                                    <h5><i class="fa fa-city"></i> From : ${upcomingTicket.getBus().getRoute().getStartName()}</h5>
                                </div>

                                <div class="col-md-6">
                                    <h5 class="float-md-right"><i class="fa fa-city"></i> To : ${upcomingTicket.getBus().getRoute().getStopName()}</h5>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h5><i class="fa fa-calendar-alt"></i> Date: ${upcomingTicket.getDateOfTravel()}</h5>
                                </div>

                                <div class="col-md-6">
                                    <h5 class="float-md-right"><i class="fa fa-clock"></i> Time : ${upcomingTicket.getBus().getStartTime()}</h5>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h5><i class="fas fa-chair"></i> Seats Booked : ${upcomingTicket.getSeatsBooked()}</h5>
                                </div>

                                <div class="col-md-6">
                                    <h5 class="float-md-right"><i class="fas fa-money-bill"></i> Total Paid: Rs. ${upcomingTicket.getTotalAmount()}</h5>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col text-center">
                                	<form action="/ticket/cancel">
                                    	<button class="btn btn-primary">Cancel Ticket</button>
                                    </form>
                                </div>
                            </div>

                        </li>
	
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </c:forEach>    
	 	</c:when>
	 	<c:otherwise>No upcoming ticket yet</c:otherwise>
	 	</c:choose>
        
    </section>
	 
	 <!-- EXPIRED TICKET SECTION -->
	 <section class="container expired-ticket-parent">
	 	<div class="row"><h3>Expired Tickets</h3></div>
	 	<c:choose>
	 	<c:when test="${oldTicketFound}">
	 	<c:forEach var="oldTicket" items="${oldTickets}">
	 		<div class="row">
            <div class="card details">
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-6">
                                    <h4><i class="fas fa-bus text-bms-primary"></i> ${oldTicket.getBus().getBusName()} (${oldTicket.getBus().getRegistrationNo()})</h4>
                                </div>
                                <div id="pnr" class="col-md-6">
                                    <h4 class="float-md-right">PNR NO:(${oldTicket.getPnrNo()})</h4>
                                </div>
                            </div>
                        </li>
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-md-6">
                                    <h5><i class="fa fa-city"></i> From : ${oldTicket.getBus().getRoute().getStartName()}</h5>
                                </div>

                                <div class="col-md-6">
                                    <h5 class="float-md-right"><i class="fa fa-city"></i> To : ${oldTicket.getBus().getRoute().getStopName()}</h5>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h5><i class="fa fa-calendar-alt"></i> Date: ${oldTicket.getDateOfTravel()}</h5>
                                </div>

                                <div class="col-md-6">
                                    <h5 class="float-md-right"><i class="fa fa-clock"></i> Time : ${oldTicket.getBus().getStartTime()}</h5>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <h5><i class="fas fa-chair"></i> Seats Booked : ${oldTicket.getSeatsBooked()}</h5>
                                </div>

                                <div class="col-md-6">
                                    <h5 class="float-md-right"><i class="fas fa-money-bill"></i> Total Paid: Rs. ${oldTicket.getTotalAmount()}</h5>
                                </div>
                            </div>
                            
                        </li>
	
	                    </ul>
	                </div>
	            </div>
	        </div>
	    </c:forEach>    
	 	</c:when>
	 	<c:otherwise>No expired ticket yet</c:otherwise>
	 	</c:choose>
    </section>
	 
	 <footer class="page-footer font-small lighten-5">
	    <div class="">
	      <div class="container">
	        <div class="row py-4 d-flex align-items-center justify-content-center">
	          <div class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">
	            Copyright <i class="fa fa-copyright" aria-hidden="true"></i> Bus Management System 2022 
	          </div>
	        </div>
	      </div>
	    </div>
  </footer>
 
</body>
</html>