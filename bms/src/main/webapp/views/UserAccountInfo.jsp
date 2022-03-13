<%@page import="org.springframework.web.context.annotation.SessionScope"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	rel="stylesheet">
<link rel="stylesheet" href="/css/util.css">
<!-- <link rel="stylesheet" href="/css/searchBus.css"> -->
<link rel="stylesheet" href="/css/UserAuth.css">
<link rel="stylesheet" href="/css/navbar.css">
<script src="/js/searchBus.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<title><c:out value="${sessionScope.name}" /> | Edit Account</title>
</head>

<body>
	<%@ include file="userSecurity.jsp"%>
	<!-- NAVBAR -->
	<%@ include file="userNavbar.jsp"%>

	<div class="edit-info-section">
		<div class="wrapper mx-auto">
			<div class="title-text">
				<div class="title login">Edit Account</div>
			</div>
			<div class="form-container">
				<div class="form-inner">
					<form action="/user/editaccount" class="signup" method="post">
						<div class="field">
							<input type="email" id="email" name="email" value="${email }"
								placeholder="Email Address" required disabled>
						</div>
						<div class="field">
							<input type="text" id="name" name="name" placeholder="Full Name" value="${name }"
								pattern="^[a-zA-Z ]{2,50}$"
								oninvalid="this.setCustomValidity('Name can contain only alphabets ')"
								oninput="setCustomValidity('')" required>
						</div>
						<div class="field">
							<input type="text" id="mno" name="mobileNo" value="${mobile}"
								placeholder="Mobile Number" pattern="[6789]{1}[0-9]{9}"
								oninvalid="this.setCustomValidity('Please enter a valid mobile number ')"
								oninput="setCustomValidity('')" required>
						</div>
						<div class="pass-link text-center">
							<span class="text-danger">${error_msg }</span>
						</div>
						<div class="field btn">
							<div class="btn-layer"></div>
							<input type="submit" value="Submit">

						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="container mx-auto" style="margin: 5%;">
			<c:if test="${validChange == 'YES'}">
				<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
					  You account information is updated successfully.
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
				    <span aria-hidden="true">&times;</span>
				  </button>
				</div>
			</c:if>
		</div>
	</div>


	<script src="/js/UserAuth.js"></script>
	<div style="position: absolute; bottom: 0; width: 100%">
		<%@ include file="userFooter.jsp"%>
	</div>
	
</body>

</html>