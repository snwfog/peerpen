<%@ include file="/view/includes/static/header.jsp" %>

<script>
  $(document).ready(function() {
    $('#submit').click(function(event) {
      var e = $('#email').val();
      im = $("<img>");
      im.attr('src',"/assets/images/ajax-loader.gif");
      $('#message').html(im);
      $.get('reset.do', {
        email : e
      }, function(responseText) {
        if(responseText == "True")
        {
          $('#message').html("<div class=\"alert alert-success\">An email has been sent to you! Check your inbox to get your new password.</div>");
        }
        else
        {
          $('#message').html("<div class=\"alert alert-danger\">It looks like you entered an invalid email address. Please try again.</div>");
        }

      });
    });
  });
</script>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>
    </div>

</div>

<div class="container">
    <form action="" style="max-width:330px; margin:0 auto" >
      <h2>Changing your password is simple</h2>
      Please enter your email address to get instructions.
      <input type="text" class="form-control" id="email" name="email" style="margin:10px 0" placeholder="Email address" autofocus=""><div id="loading"></div>
      <input type="button" id="submit" value="Submit" class="btn btn-lg btn-primary btn-block"/>
        <br />
        <div id="message"></div>
    </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>
