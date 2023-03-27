<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>registration</title>
   <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/commonc.css">
    <script type="text/javascript" src="/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/js/lib/bootstrap.min.js"></script>
     <script type="text/javascript" src="/js/RegistrationPage.js"></script>
     <link rel="stylesheet" href="/css/LoginStyle.css">
        <!-- Boxicons CSS -->
        <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <%@include file="header.jsp" %>
     </head>
<body>
<h2 class="text-center" id = "successMessage1">${successMessage}</h2>
 <section class="login-container forms">
<div class="form signup">
                <div class="form-content">
                    <header>Signup</header>
                    <form name="form" id="form" method="post" onsubmit="sendDataToController()">
                    
                     <div class="field input-field">
                            <input type="text" name="name" id="name"   placeholder="Name" class="input">
                        </div>
                        <div class="field input-field">
                            <input type="email" name="email" id="email"   placeholder="Email" class="input">
                        </div>

                        <div class="field input-field">
                            <input type="password" name="password" id="password"  placeholder="Create password" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>

                        <div class="field input-field">
                            <input type="password"  name="password" id="password"  placeholder="Confirm password" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>
                        <input type="hidden" name = "role" id="role" class="form-control form-control-lg" value="ROLE_USER"/>

                        <div class="field button-field">
                            <button>Signup</button>
                        </div>
                    </form>

                    <div class="form-link">
                        <span>Already have an account? <a href="/login" class="">Login</a></span>
                    </div>
                </div>

                <div class="line"></div>

                <div class="media-options">
                    <a href="/oauth2/authorization/google" class="field google">
                        <img src="images/google.png" alt="" class="google-img">
                        <span>Login with Google</span>
                    </a>
                </div>

            </div>
            </section>
            
   <!--  <div class="container">
      <div class="col-md-4 col-md-offset-4">
        <form name="form" id="form" method="post" class="form-login" onsubmit="sendDataToController()" >
          <h2 class="text-center">Register</h2>
          <label for="username">Username:</label>
          <input type="text" name="username" id="username" required>

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required>
          
           <label for="email">Email:</label>
          <input type="email" name="email" id="email" required>
          
          <label for="username">contact Number:</label>
          <input type="tel" name="contactNumber" id="contactNumber" required>
          

          <input type="hidden" name = "role" id="role" class="form-control form-control-lg" value="ROLE_USER"/>
          
          <input type="submit" value="Register">
                
        </form>
          
      </div>
    </div> -->




<!-- <section class="vh-100" style="background-color: #508bfc;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12 col-md-8 col-lg-6 col-xl-5">
        <div class="card shadow-2-strong" style="border-radius: 1rem;">
          <div class="card-body p-5 text-center">

            <h3 class="mb-5">Sign in</h3>
<form name="form" id="form"
              			method="POST"  onsubmit="sendDataToController()">
              			<div class="form-outline mb-4">
              <input type="email" name = "email" id="typeEmailX-2" class="form-control form-control-lg" />
              <label class="form-label" for="typeEmailX-2">Email</label>
            </div>
            
            <div class="form-outline mb-4">
              <input type="text" name = "name" id="name" class="form-control form-control-lg" />
              <label class="form-label" for="typeEmailX-2">Username</label>
            </div>


              <input type="hidden" name = "role" id="role" class="form-control form-control-lg" value="USER"/>
              <input type="hidden" name = "enabled" id="enabled" class="form-control form-control-lg" value="true"/>
   
            <div class="form-outline mb-4">
              <input type="password" name="password" id="password" class="form-control form-control-lg" />
              <label class="form-label" for="typePasswordX-2">Password</label>
            </div>

            Checkbox
            <div class="form-check d-flex justify-content-start mb-4">
              <input class="form-check-input" type="checkbox" value="" id="form1Example3" />
              <label class="form-check-label" for="form1Example3"> Remember password </label>
            </div>

            <button class="btn btn-primary btn-lg btn-block" type="submit">Register First</button>

            <hr class="my-4">

      </form>

          </div>
        </div>
      </div>
    </div>
  </div>
</section> -->

<!-- JavaScript -->
      <script src="/js/LoginScript.js"></script>
    <%@include file="footer.jsp" %>
</body>
</html>