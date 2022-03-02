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
  <link rel="stylesheet" href="/css/listBus.css" />
  <link rel="stylesheet" href="/css/navbar.css" />
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
  <title>Available Bus</title>
</head>
<body>
<!-- NAVBAR -->
  <nav class="navbar navbar-expand-lg navbar-light bg-bms-primary">
    <div class="container nav-container">
      <div class="brand">
        <a class="navbar-brand text-bms-yellow font-weight-bold" href="${pageContext.request.contextPath}/user/welcome">BMS</a>
      </div>

      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active mx-2">
            <a class="nav-link item" href="${pageContext.request.contextPath}/user/searchBus">Plan Journey</a>
          </li>
          <li class="nav-item mx-2">
            <a class="nav-link" href="#">My Tickets</a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <button class="btn bg-bms-primary my-2 my-sm-0 logout-btn text-bms-secondary font-weight-bold" type="submit">
            LOG OUT
          </button>
        </form>
      </div>
    </div>
  </nav>
 
 <c:choose>
    <c:when test="${busFound}">
        
		  <!-- Result Page  -->
		  <section class="container result-page">
		    <!-- Number of buses found from abc to xyz -->
		    <div class="row">
		      <span class="font-weight-bold busFound">${buses.size()} Bus </span>
		      <span>&nbsp;found from</span>
		      <span class="font-weight-bold busFound">&nbsp;${buses.get(0).getRoute().getStartName()} </span>
		      <span>&nbsp;to</span>
		      <span class="font-weight-bold busFound">&nbsp;${buses.get(0).getRoute().getStopName()} </span>
		      <span>&nbsp;on</span>
		      <span class="font-weight-bold busFound">&nbsp;${travelDate}</span>
		    </div>
		  </section>
		  
		  <!-- Found Buses Details -->
		  <section class="container">
		  <c:forEach var="bus" items="${buses}">
		    <div class="d-flex justify-content-between card-body details">
		      <div class="col-sm-2 text-center my-auto">
		        <p class="font-weight-bold h4 my-auto">${bus.getBusName()}</p>
		        <p class="badge badge-pill badge-success my-auto">${bus.getFacilities()}</p>
		      </div>
		      <div class="col-sm-2 text-center my-auto h4">
		      	<p class=" h4 my-auto">Departure Time</p>
		      	<p class=" h4 my-auto">${bus.getStartTime()}</p>
		      </div>
		      <div class="col-sm-2 text-center text-danger my-auto h4">
		      	<p class="h4 my-auto">Available Seats</p>
		      	<p class="h4 my-auto">${bus.getSeatCount()}</p>
		      </div>
		      <div class="col-sm-2 text-center my-auto h4">
		      	<p class="h4 my-auto">Distance</p>
		      	<p class="h4 my-auto">${bus.getRoute().getDistance()}&nbsp;km</p>
		      </div>
		      <div class="col-sm-2 b text-center my-auto font-weight-bold h4">
		      	<p class="h4 my-auto">Fare Per Ticket</p>
		      	<p class="h4 my-auto">Rs.&nbsp;${bus.getFare() * bus.getRoute().getDistance()}</p>
		      </div>
		    </div>
			</c:forEach>
		  </section>
		  
    </c:when>    
    <c:otherwise>
        <section class="container">
	        <div class="row no-bus-found-parent justify-content-lg-center justify-content-md-center">
	            <div class="card">
	            	<div class="card-body">
	            		No bus found in this Route. Kindly select a different route and plan your journey accordingly.
	            	</div>
	            </div>
	        </div>
        </section>
    </c:otherwise>
</c:choose>
 

  
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