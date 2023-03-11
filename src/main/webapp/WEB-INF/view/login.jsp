<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login - Pizza Delivery App</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/JS/RegistrationPage.js"></script>
    <style>
      body {
        background-image: url("pizza-background.jpg");
        background-size: cover;
      }
      .form-login {
        background-color: #ffffff;
        padding: 20px;
        margin-top: 100px;
        border-radius: 5px;
        box-shadow: 0px 0px 20px #c1c1c1;
      }
      .form-login input[type=text], .form-login input[type=password] {
        width: 100%;
        padding: 10px;
        margin: 5px 0 10px 0;
        border: none;
        border-radius: 3px;
        box-shadow: 0px 0px 5px #c1c1c1;
      }
      .form-login input[type=submit] {
        background-color: #007bff;
        color: #ffffff;
        font-size: 16px;
        font-weight: bold;
        border: none;
        border-radius: 3px;
        padding: 10px;
        width: 100%;
        margin-top: 10px;
      }
      .form-login input[type=submit]:hover {
        background-color: #0069d9;
      }
    </style>
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
