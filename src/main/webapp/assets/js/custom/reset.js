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
    $('#message').html('<i class="fa fa-spinner fa-spin"></i>');
    $.post('/reset.do', {
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