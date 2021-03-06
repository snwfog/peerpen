// Generated by CoffeeScript 1.3.3

$(function() {
  var od;
  $("a#upload-new-avatar").click(function(e) {
    e.preventDefault();
    return $("div#dropzone-container").fadeToggle();
  });
  $("section#signup").backstretch("/assets/images/index/office1.jpg");
  $("h2#msg-hey").delay(1000).fadeIn('slow', function() {
    return $("h1#msg-jumpstart").fadeIn('slow', function() {
      return $("section#signup form").fadeIn('slow');
    });
  });
  if ($('div.odometer').length > 0) {
    od = new Odometer({
      el: $('div.odometer')[0]
    });
  }
  $(window).scroll(function() {
    var vHeight;
    vHeight = $(this).scrollTop();
    if (vHeight >= 3040) {
      return od.update(123456);
    }
  });
  $("section#signup form button[type='submit']").click(function(e) {
    var parentForm;
    e.preventDefault();
    $("h2#msg-hey").fadeOut();
    $("h1#msg-jumpstart").fadeOut();
    parentForm = $(this).parents("form");
    parentForm.fadeOut();
    $("h1#msg-jumpstart").html("Your career is in good hands").fadeIn();
    return setTimeout((function() {
      return parentForm.submit();
    }), 2000);
  });
  $('.editor').ppedit({
    onload: function() {
      if ($('.editor').length) {
        return $.ajax(window.location.href.split("#")[0].replace(/edit/, ""), {
          accept: "application/json",
          success: function(data) {
            console.log(data);
            return $('.editor').ppedit('load', {
              hunks: data
            });
          }
        });
      }
    }
  });
  return $('button#submit').click(function(event) {
    var hunks, postUrl;
    hunks = $('.editor').ppedit('save');
    postUrl = window.location.href.split('#')[0].replace(/edit/, '');
    return $.ajax({
      type: "post",
      url: postUrl,
      data: JSON.stringify(hunks),
      dataType: "json",
      contentType: "application/json; charset=utf-8",
      success: function(event) {
        return console.log("Success saved to the remote database");
      }
    });
  });
});
