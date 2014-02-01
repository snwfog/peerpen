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
    $('#message').html('<i class="fa fa-spinner fa-spin fa-5x"></i>');
    $.post('/reset.do', {
      email : e
    }, function(responseText) {
      if(responseText == "True")
      {
        $('#message').html("<div class=\"alert alert-success\"><i class=\"fa fa-check-square fa-5x pull-left\"></i> An email has been sent to you! Check your inbox to get your new password. Click <a href='/'>here</a> to return to the homepage.</div>");
      }
      else
      {
        $('#message').html("<div class=\"alert alert-danger\"><i class=\"fa fa-times-circle-o fa-5x pull-left\"></i> It looks like you entered an invalid email address. Please try again or <a href='/register'>sign up</a> if you do not have an account.</div>");
      }
    });
  }
});