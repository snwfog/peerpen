<%@ include file="/view/includes/static/header.jsp" %>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand">PeerPen</a>
    </div>
  </div>

</div>
<div class="register">
  <h1>Complete your profile <button class="pull-right btn btn-primary" type="submit">Next (1/2)</button></h1>
  <div class="well">
    <div class="description pull-left">
      <h4>Describe yourself</h4>
      It is suggested to complete this part to enhance your expertise
    </div>
    <form style=" max-width:330px; margin-left:50%" action="/login.do" method="post">
      <input type="text" class="form-control" name="dob" style="margin:10px 0" placeholder="Date of Birth" autofocus="">
      <input type="password" class="form-control" name="gender" style="margin:10px 0" placeholder="Gender">
      <input type="text" class="form-control" name="country" style="margin:10px 0" placeholder="Country">
      <input type="text" class="form-control" name="industry" style="margin:10px 0" placeholder="Industry">
      <input type="text" class="form-control" name="yoe" style="margin:10px 0" placeholder="Years of Experience">
    </form>
  </div>
</div>



<%@ include file="/view/includes/static/footer.jsp" %>