// Generated by CoffeeScript 1.3.3

$(function() {
  var od, played;
  skrollr.init();
  played = false;
  od = new Odometer({
    el: $('div#odometer')[0],
    value: "PEERPEN"
  });
  return $(window).scroll(function() {
    var vHeight;
    vHeight = $(this).scrollTop();
    if (vHeight >= 3000 && !played) {
      od.update(Math.floor(Math.random() * 100000));
    }
    return played = vHeight >= 3000;
  });
});
