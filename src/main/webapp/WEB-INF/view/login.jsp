<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Login - Pizza Delivery App</title>
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    
    <script type="text/javascript" src="/js/RegistrationPage.js"></script>
     <link rel="stylesheet" href="/css/LoginStyle.css">
        <!-- Boxicons CSS -->
        
    <%@include file="header.jsp" %>
  </head>
  <body>
  <h2 class="text-center" id ="successMsg">${successMessage}</h2>
   <section class="login-container forms">
            <div class="form login">
                <div class="form-content">
                    <header>Login</header>
                    <form method="post" action="/Dologin" >
                        <div class="field input-field">
                            <input type="email" name="email" placeholder="Email" class="input">
                        </div>

                        <div class="field input-field">
                            <input type="password" name = "password" placeholder="Password" class="password">
                            <i class='bx bx-hide eye-icon'></i>
                        </div>

                        <div class="form-link">
                            <a href="/getForgotPasswordPage" class="forgot-pass">Forgot password?</a>
                        </div>

                        <div class="field button-field">
                            <button>Login</button>
                        </div>
                    </form>

                    <div class="form-link">
                        <span>Don't have an account? <a href="/getRegistrationPage" class="">Signup</a></span>
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
            
    <%-- <div class="container">
      <div class="col-md-4 col-md-offset-4">
        <form method="post" action="/Dologin" class="form-login" >
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        
          <h2 class="text-center" style="color:#121618">Please enter credentials</h2>
          <label for="email">User Email:</label>
          <input type="email" name="email" id="email" required>

          <label for="password">Password:</label>
          <input type="password" name="password" id="password" required>

          <input type="submit" value="Login">
          <a href="/reg.jsp"><input type ="button" value="Register"></a>
          
        </form>
        
        <a href="/oauth2/authorization/google">Login with Google</a>
          
      </div>
    </div> --%>
    
     <!-- JavaScript -->
        <script src="/js/LoginScript.js"></script>
    <%@include file="footer.jsp" %>
  </body>
</html>
