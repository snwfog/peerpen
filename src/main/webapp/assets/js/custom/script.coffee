$ ->
  # Section#signup
  $("div#scroll-down a").tooltip("show")

  # Create the odometer counter
  played = false
  od = new Odometer
    el: $('div#odometer')[0]
    value: "PEERPEN"

  $(window).scroll ->
    vHeight = $(this).scrollTop()
    od.update Math.floor(Math.random() * 100000) if vHeight >= 3000 && not played
    played = (vHeight >= 3000)
    if vHeight >= 230
      $("div#scroll-down a").tooltip("destroy")

