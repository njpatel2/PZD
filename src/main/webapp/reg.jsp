<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>registration</title>
   <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/login.css">
    <script type="text/javascript" src="/JS/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/JS/lib/bootstrap.min.js"></script>
    <script type="text/javascript" src="/JS/RegistrationPage.js"></script>
     </head>
<body>
<h2 class="text-center" id = "successMessage1">${successMessage}</h2>
    <div class="container">
      <div class="col-md-4 col-md-offset-4">
        <form name="form" id="form" method="post" class="form-login" onsubmit="sendDataToController()" >
          <h2 class="text-center">Register</h2>
          <label for="username">Username:</label>
          <input type="text" name="username" id="username" required>

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required>
          
           <label for="email">Email:</label>
          <input type="email" name="email" id="email" required>

          <input type="hidden" name = "role" id="role" class="form-control form-control-lg" value="ROLE_USER"/>
          
          <input type="submit" value="Register">
                
        </form>
          
      </div>
    </div>




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
</body>
</html>