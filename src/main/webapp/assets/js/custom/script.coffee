$ ->

  # Upload the avatar in the avatar upload
  #$('button#avatar-upload-submit').click (e) ->
  #  e.preventDefault()
  #  $('form#avatar-upload').submit()

  $("a#upload-new-avatar").click (e) ->
    e.preventDefault()
    $("div#dropzone-container").fadeToggle()

  # Backstretch background
  $("section#signup").backstretch "/assets/images/index/office1.jpg"
  # Set the background of the signup section to the same height as window height
  # Minus padding and navbar height, min between section heightindow height
  # $("section#signup").css
  #   "min-height": (Math.min(($(window).height() - 50), 600)) + "px"

  # Fade in the hey, there
  $("h2#msg-hey").delay(1000).fadeIn 'slow', ->
    $("h1#msg-jumpstart").fadeIn 'slow', ->
      $("section#signup form").fadeIn 'slow'

  # Create the odometer counter
  if $('div.odometer').length > 0
    od = new Odometer
      el: $('div.odometer')[0]

  $(window).scroll ->
    vHeight = $(this).scrollTop()
    #od.update Math.floor(Math.random() * 100000) if vHeight >= 3910
    od.update 123456 if vHeight >= 3040
    #if vHeight >= 230
    #  $("div#scroll-down a").tooltip("destroy")


  # Delay signup with the message
  $("section#signup form button[type='submit']").click (e) ->
    e.preventDefault()
    $("h2#msg-hey").fadeOut()
    $("h1#msg-jumpstart").fadeOut()
    parentForm = $(this).parents("form")
    parentForm.fadeOut()

    $("h1#msg-jumpstart").html("Your career is in good hands").fadeIn()
    setTimeout ( -> parentForm.submit() ), 2000

  console.log("Loading ppedit")
  $('.editor').ppedit()
