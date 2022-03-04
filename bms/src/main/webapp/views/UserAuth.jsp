<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="utf-8">
			<title>Login and Registration</title>
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<link rel="stylesheet" href="css/UserAuth.css">
			<link rel="stylesheet" href="/css/UserAuth.css">
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
				integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
				crossorigin="anonymous">
			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
				integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
				crossorigin="anonymous"></script>
			<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
				integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
				crossorigin="anonymous"></script>
		</head>

		<body>
			<c:if test="${sessionScope.isValidUser == true}">
				<% response.sendRedirect(request.getContextPath() + "/user/searchBus" ); %>
			</c:if>

			<div class="wrapper">
				<div class="title-text">
					<div class="title login">BMS Login</div>
					<div class="title signup">BMS SignUp</div>
				</div>
				<div class="form-container">
					<div class="slide-controls">
						<input type="radio" name="slide" id="login" checked> <input type="radio" name="slide"
							id="signup"> <label for="login" class="slide login">Login</label> <label for="signup"
							class="slide signup">Signup</label>
						<div class="slider-tab"></div>
					</div>
					<div class="form-inner">
						<form action="login" class="login" method="post">
							<div class="field">
								<input type="email" id="email" name="email" placeholder="Email Address"
									value="${email }" required>
							</div>
							<div class="field">
								<input type="password" id="password" name="password" placeholder="Password" required>
							</div>
							<div class="pass-link text-center">
								<span class="text-danger">${error_msg }</span>
							</div>
							<div class="field btn">
								<div class="btn-layer"></div>
								<input type="submit" value="Login">
							</div>
							<div class="signup-link">
								Not a member? <a href="">Signup now</a>
							</div>
						</form>
						<form action="/user/signUp" class="signup" method="post">
							<div class="field">
								<input type="text" id="name" name="name" placeholder="Full Name"
									pattern="^[a-zA-Z ]{2,50}$"
									oninvalid="this.setCustomValidity('Name can contain only alphabets ')"
									oninput="setCustomValidity('')" required>
							</div>
							<div class="field">
								<input type="text" id="mno" name="mobileNo" placeholder="Mobile Number"
									pattern="[6789]{1}[0-9]{9}"
									oninvalid="this.setCustomValidity('Please enter a valid mobile number ')"
									oninput="setCustomValidity('')" required>
							</div>
							<div class="field">
								<input type="email" id="email" name="email" placeholder="Email Address" required>
							</div>
							<div class="field">
								<input type="password" id="pass" name="password" placeholder="Password"
									pattern="^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,15}$"
									oninvalid="this.setCustomValidity('Password must be of minimum 8 and maximum of 15 characters and must contain one uppercase and one lowercase and one number ')"
									oninput="setCustomValidity('')" required>
							</div>
							<!-- <%-- <div><input type="checkbox" onclick="showPassword()">Show Password</div>--%> -->
							<div class="field">
								<input type="password" id="cpass" placeholder="Confirm password"
									onkeyup="matchPassword()" oninvalid="this.setCustomValidity('Password Don't Match')"
									required>
							</div>
							<div class="field btn">
								<div class="btn-layer"></div>
								<input type="submit" value="Sign Up">

							</div>
						</form>
					</div>
				</div>
			</div>
			<script src="/js/UserAuth.js"></script>

		</body>

		</html>