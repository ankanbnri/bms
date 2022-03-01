<!DOCTYPE html>
<html lang="en">

<head>
   <meta charset="utf-8">
   <title>Login and Registration</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="css/UserAuth.css">
   <link rel="stylesheet" href="/css/UserAuth.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
        integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</head>

<body>
   <div class="wrapper">
      <div class="title-text">
         <div class="title login">
            BMS Login
         </div>
         <div class="title signup">
            BMS SignUp
         </div>
      </div>
      <div class="form-container">
         <div class="slide-controls">
            <input type="radio" name="slide" id="login" checked>
            <input type="radio" name="slide" id="signup">
            <label for="login" class="slide login">Login</label>
            <label for="signup" class="slide signup">Signup</label>
            <div class="slider-tab"></div>
         </div>
         <div class="form-inner">
            <form action="login" class="login" method="post">
               <div class="field">
                  <input type="email" id="email" name="email" placeholder="Email Address" value="${email }" required>
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
            <form action="#" class="signup">
               <div class="field">
                  <input type="text" id="name" placeholder="Full Name" required>
               </div>
               <div class="field">
                  <input type="text" id="mno" placeholder="Mobile Number" required>
               </div>
               <div class="field">
                  <input type="text" id="email" placeholder="Email Address" required>
               </div>
               <div class="field">
                  <input type="password" id="password" placeholder="Password" required>
               </div>
               <div class="field">
                  <input type="password" id="password" placeholder="Confirm password" required>
               </div>
               <div class="field btn">
                  <div class="btn-layer"></div>
                  <input type="submit" value="Signup">
               </div>
            </form>
         </div>
      </div>
   </div>
   <script>
      const loginText = document.querySelector(".title-text .login");
      const loginForm = document.querySelector("form.login");
      const loginBtn = document.querySelector("label.login");
      const signupBtn = document.querySelector("label.signup");
      const signupLink = document.querySelector("form .signup-link a");
      signupBtn.onclick = (() => {
         loginForm.style.marginLeft = "-50%";
         loginText.style.marginLeft = "-50%";
      });
      loginBtn.onclick = (() => {
         loginForm.style.marginLeft = "0%";
         loginText.style.marginLeft = "0%";
      });
      signupLink.onclick = (() => {
         signupBtn.click();
         return false;
      });
   </script>
</body>

</html>