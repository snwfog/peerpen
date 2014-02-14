function join(formId)
{
  $("form#"+formId).submit(function(e)
  {
    var postData = $(this).serializeArray();
    $.ajax(
      {
        url : "/group.do",
        type: "POST",
        data : postData,
        success:function(data, textStatus, jqXHR)
        {
          $(".joined-"+formId).text(parseInt($(".joined-"+formId).text(),10)+1);
          $(".group-"+formId).html(data);
        },
        error: function(jqXHR, textStatus, errorThrown)
        {
          //if fails
        }
      });
    e.preventDefault(); //STOP default action
  });

  $("form#"+formId).submit();
}

function leave(formId)
{
  $("form#"+formId).submit(function(e)
  {
    var postData = $(this).serializeArray();
    $.ajax(
      {
        url : "/group.do",
        type: "POST",
        data : postData,
        success:function(data, textStatus, jqXHR)
        {
          $(".joined-"+formId).text(parseInt($(".joined-"+formId).text(),10)-1);
          $(".group-"+formId).html(data);
        },
        error: function(jqXHR, textStatus, errorThrown)
        {
          //if fails
        }
      });
    e.preventDefault(); //STOP default action
  });

  $("form#"+formId).submit();
}

function updateCountdown() {
  var remaining = 200 - $('.group-description').val().length;
  if (remaining <= 20 ){
    $('.countdown').css('color', 'red');
    $('.countdown').text(remaining + ' characters remaining.');
  }
  else{
    $('.countdown').css('color', 'black');
    $('.countdown').text(remaining + ' characters remaining.');
  }
}

$(document).ready(function($) {
  $('.group-description').change(updateCountdown);
  $('.group-description').keyup(updateCountdown);
});