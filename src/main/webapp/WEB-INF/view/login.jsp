<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login - Pizza Delivery App</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/RegistrationPage.js"></script>
    
  </head>
  <body>
  <h2 class="text-center">${successMessage}</h2>
    <div class="container">
      <div class="col-md-4 col-md-offset-4">
        <form method="post" action="login.jsp" class="form-login">
          <h2 class="text-center">Login to Pizza Delivery App</h2>
          <label for="username">Username:</label>
          <input type="text" name="username" id="username" required>

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required>

          <input type="submit" value="Login">
        </form>
      </div>
    </div>
  </body>
</html>
