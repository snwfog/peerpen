// Generated by CoffeeScript 1.7.1

/*
the ControllerFactory determines which controller
to used based on the user's Operating System.
 */
var ControllerFactory;

ControllerFactory = (function() {
  function ControllerFactory() {}

  ControllerFactory.getController = function(root) {
    if (navigator.userAgent.match(/Macintosh/) !== null) {
      return new MacController(root);
    } else {
      return new PCController(root);
    }
  };

  return ControllerFactory;

})();
