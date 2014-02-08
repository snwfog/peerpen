<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar_profile.jsp" %>

<%
  Boolean error = false;
  if(request.getParameter("status") != null)
  {
//    if(request.getParameter("status") == "error")
      error = true;
  }
%>

<div class="container" parsley-validate>
    <form id="login-form" style="max-width:330px; margin:0 auto" action="/login" method="post" parsley-validate>
        <h2>Please sign in</h2>
        <input type="text" class="form-control parsley-validated" name="username" id="login-username" style="margin:10px 0" placeholder="Username" autofocus="" parsley-trigger="change keyup"/>
        <input type="password" class="form-control parsley-validated" id="login-password" name="password" style="margin:10px 0" placeholder="Password" parsley-trigger="change keyup"/>
        <div >
          <% if (error){ %>
            <div class="alert alert-danger">
              <span class="glyphicon glyphicon-remove"></span>
              The username/password you entered is incorrect. Please try again or <a href="/register">sign up</a>!
            </div>
          <% } %>
        <a href="/reset" >Forgot password? </a>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
    <div id="errorContainer">
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>
