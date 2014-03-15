<jsp:include page="/view/includes/static/header.jsp">
  <jsp:param name="title" value="Reset" />
</jsp:include>

<%@ include file="/view/includes/static/navbar.jsp" %>

<div class="container">
    <form action="" style="max-width:330px; margin:0 auto" >
      <h2>Changing your password is simple</h2>
      Please enter your email address to get instructions.
      <input type="text" class="form-control" id="email" placeholder="Email address">
      <br />
      <input type="button" id="submit" value="Submit" class="btn btn-lg btn-primary btn-block" />
        <br />
        <div id="message"></div>
    </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>
