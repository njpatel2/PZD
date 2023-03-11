<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login - Pizza Delivery App---this is home page</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/login.css">
    <script type="text/javascript" src="/JS/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/JS/lib/bootstrap.min.js"></script>
    <script type="text/javascript" src="/JS/RegistrationPage.js"></script>
    <style>
     
    </style>
  </head>
  <body>
  <h2 class="text-center">${successMessage}</h2>
    <div class="container">
      <div class="col-md-4 col-md-offset-4">
        <form method="post" action="/Dologin" class="form-login" >
          <h2 class="text-center">Login to Pizza Delivery App</h2>
          <label for="username">Username:</label>
          <input type="text" name="username" id="username" required>

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required>

          <input type="submit" value="Login">
          <a href="/reg.jsp"><input type ="button" value="Register"></a>
          
        </form>
          
      </div>
    </div>
  </body>
</html>
