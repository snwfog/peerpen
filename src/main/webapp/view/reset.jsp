<%@ include file="/view/includes/static/header.jsp" %>
<script>
  $(document).ready(function() {
    $('#submit').click(function(event)
    {
      email = $('#email').val();
      submit(email);
    });
    $('#email').keydown("keypress", function(event)
    {
      if(event.keyCode == 13)
      {
        event.preventDefault();
        email = $('#email').val();
        submit(email);
      }
    });
    function submit(email)
    {
      var e = email;
      im = $('<img>');
      im.attr('src', '/assets/images/ajax-loader.gif');
      $('#message').html(im);
      $.post('reset.do', {
        email : e
      }, function(responseText) {
        if(responseText == "True")
        {
          $('#message').html("<div class=\"alert alert-success\"><span class=\"glyphicon glyphicon-ok\"></span> An email has been sent to you! Check your inbox to get your new password. Click <a href='/'>here</a> to return to the homepage</div>");
        }
        else
        {
          $('#message').html("<div class=\"alert alert-danger\"><span class=\"glyphicon glyphicon-remove\"></span> It looks like you entered an invalid email address. Please try again.</div>");
        }
      });
    }
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
      <div class="input-group">
      <span class="input-group-addon">@</span>
      <input type="text" class="form-control" id="email" placeholder="Email address">
      </div>
      <br />
      <input type="button" id="submit" value="Submit" class="btn btn-lg btn-primary btn-block" />
        <br />
        <div id="message"></div>
    </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>
