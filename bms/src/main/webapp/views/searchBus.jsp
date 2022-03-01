<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/util.css">
    <link rel="stylesheet" href="/css/searchBus.css">
    <link rel="stylesheet" href="/css/navbar.css">

    <script src="/js/searchBus.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <title>BMS | Plan your journey</title>
</head>

<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, no-revalidate");	// HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0"); // Proxies
	%>
	
	<c:if test="${sessionScope.isValidUser != true}">
		<%response.sendRedirect(request.getContextPath()+"/user/login");%>
	</c:if>

    <!-- NAVBAR -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container nav-container">
            <div class="brand">
                <a class="navbar-brand text-bms-yellow font-weight-bold" href="#">BMS</a>
            </div>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active mx-2">
                        <a class="nav-link item" href="#">Plan Journey</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" href="#">My Tickets</a>
                    </li>
                </ul>
                <form class="form-inline my-2 my-lg-0" action="/user/logout">
                    <button class="btn bg-bms-primary my-2 my-sm-0 logout-btn text-bms-secondary font-weight-bold"
                        type="submit">LOG OUT</button>
                </form>
            </div>
        </div>
    </nav>

    <!-- SEARCH SECTION -->
    <section class="container-fluid search-section">
        <div class="container">
            <form class="search-form">
                <div class="input-group mb-3 my-auto search-element">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i
                                class="fas fa-bus form-icon text-bms-primary"></i></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Source" aria-describedby="basic-addon1">
                </div>
                <div class="input-group mb-3 my-auto search-element">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i
                                class="fa fa-solid fa-building form-icon text-bms-primary"></i></span>
                    </div>
                    <input type="text" class="form-control" placeholder="Destination" aria-describedby="basic-addon1">
                </div>

                <div class="input-group mb-3 my-auto search-element">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1"><i
                                class="fas fa-calendar-alt form-icon text-bms-primary"></i></span>
                    </div>
                    <input type="date" class="form-control" placeholder="Date" aria-describedby="basic-addon1">
                </div>
                <button type="submit" class="btn bg-bms-yellow search-btn"><i
                        class="fas fa-search form-icon"></i></button>
            </form>
        </div>
    </section>

    <!-- PROMISE SECTION  -->
    <section class="promise-section">
        <div class="container">
            <h1 class="text-center promise-heading">We Promise to Deliver</h1>
            <div class="row my-auto mx-auto card-section justify-content-center">
                <div class="card-deck">
                    <div class="card col-lg-4 col-sm-6 col-md-6 text-center text-secondary">
                        <i class="fas fa-pump-medical card-icon text-bms-primary"></i>
                        <div class="card-body">
                            <h5 class="card-title">safety+</h5>
                            <p class="card-text">With Safety+ we have brought in a set of measures like Sanitized buses,
                                mandatory masks etc. to ensure you travel safely.</p>
                        </div>
                    </div>
                    <div class="card col-lg-4 col-sm-6 col-md-6 text-center text-secondary">
                        <i class="fas fa-headset card-icon text-bms-primary"></i>
                        <div class="card-body">
                            <h5 class="card-title">SUPERIOR CUSTOMER SERVICE</h5>
                            <p class="card-text">We put our experience and relationships to good use and are available
                                to solve your travel issues.</p>
                        </div>
                    </div>
                    <div class="card col-lg-4 col-sm-6 col-md-6 text-center text-secondary">
                        <i class="fas fa-rupee-sign card-icon text-bms-primary"></i>
                        <div class="card-body">
                            <h5 class="card-title">LOWEST PRICES</h5>
                            <p class="card-text">We always give you the lowest price with the best partner offers.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>
    <footer class="page-footer font-small lighten-5">
        <div class="">
            <div class="container">
                <div class="row py-4 d-flex align-items-center">
                    <div class="col-md-6 col-lg-5 text-center text-md-left mb-4 mb-md-0">
                        Copyright © Bus Management System 2022
                    </div>
                </div>
            </div>
        </div>
    </footer>
</body>

</html>