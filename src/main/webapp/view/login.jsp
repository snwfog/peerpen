<%@ include file="/view/includes/static/header.jsp" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>
    </div>

</div>
<%
  Boolean error = false;
  if(request.getParameter("status") != null)
  {
//    if(request.getParameter("status") == "error")
      error = true;
  }
%>

<div class="container">
    <form style="max-width:330px; margin:0 auto" action="/login.do" method="post">
        <h2>Please sign in</h2>
        <input type="text" class="form-control" name="username" style="margin:10px 0" placeholder="Username" autofocus="">
        <input type="password" class="form-control" name="password" style="margin:10px 0" placeholder="Password">
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
</div>

<%--below is the old code--%>
<%--<div class="row">--%>
<%--<div class="col-xs-6 col-md-4"></div>--%>
<%--<div class="col-xs-6 col-md-4">--%>
<%--<h2>LoginController</h2>--%>

<%--<form role="form" action="/login.do" method="post">--%>
<%--<fieldset>--%>
<%--<div class="clearfix">--%>
<%--<input type="text" id="username" name="username"--%>
<%--placeholder="Username">--%>
<%--</div>--%>
<%--<div class="clearfix">--%>
<%--<input type="password" id="password" name="password"--%>
<%--placeholder="Password">--%>
<%--</div>--%>
<%--<button class="btn btn-primary" type="button submit">Sign in</button>--%>
<%--</fieldset>--%>
<%--</form>--%>
<%--</div>--%>
<%--<div class="col-xs-6 col-md-4"></div>--%>
<%--</div>--%>


<%@ include file="/view/includes/static/footer.jsp" %>
