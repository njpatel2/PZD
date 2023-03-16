<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login - Pizza Delivery App---this is home page</title>
    
    <script type="text/javascript" src="/js/RegistrationPage.js"></script>
    <%@include file="Common.jsp" %>
  </head>
  <body>
  <h2 class="text-center" id ="successMsg">${successMessage}</h2>
    <div class="container">
      <div class="col-md-4 col-md-offset-4">
        <form method="post" action="/Dologin" class="form-login" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
          <h2 class="text-center" style="color:#121618">Please enter credentials</h2>
          <label for="username">Username:</label>
          <input type="text" name="username" id="username" required>

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required>

          <input type="submit" value="Login">
          <a href="/reg.jsp"><input type ="button" value="Register"></a>
          
        </form>
          
      </div>
    </div>
    <%@include file="footer.jsp" %>
  </body>
</html>
