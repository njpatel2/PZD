<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forget Password</title>
 <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/commonc.css">
    <link rel="stylesheet" href="/css/LoginStyle.css">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
     <script type="text/javascript" src="/js/RegistrationPage.js"></script>
     
     <script type="text/javascript" src="/js/home.js"></script>
        <!-- Boxicons CSS -->
        
    <%@include file="header.jsp" %>
    <style>
    .hhkl{
        height: -1vh!important;
    }
    </style>
</head>
<body>
<h2 class="text-center" id = "isOTPValid">${isOTPValid}</h2>
<section class="login-container forms hhkl">

<div class="form signup">
                <div class="form-content">
                    <header>Forgot Password?</header>
                    <form name="form" id="form" method="post" action="/verifyOTP">
                        <div class="field input-field">
                            <input type="email" name="email" id="email"   placeholder="Email" class="input">
                        </div>

						<div id="otpField" class="field input-field" style="color: black; display: none;">Shell Acton - 
                            <input type="number" name="OTP" id="OTP"   placeholder="Code" class="input" style="width: 73.5%;">
                        </div>
                        
                        
                      <!--   <div class="field input-field">
                            <input type="password" name="password" id="password"  placeholder="Create password" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>

                        <div class="field input-field">
                            <input type="password"  name="password" id="password"  placeholder="Confirm password" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div> -->
                        <div class="field button-field" id = "verifyButton"  style="display: none; color: green;">
                            <button type="submit" >Verify</button>
                        </div>
                        
                    </form>
                    <div class="field button-field">
                            <button id = "sendButton" onclick="sendForgotPasswordEmail()">Send</button>
                        </div>
                        
                        
                    <div class="form-link">
                        <span><a href="/login" class="">Login</a></span>
                    </div>
                </div>

            </div>
            </section>
            <%@include file="footer.jsp"%>
</body>
</html>