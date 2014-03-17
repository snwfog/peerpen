// Generated by CoffeeScript 1.7.1

/*
Helper class used to temporarily save DOM nodes.
 */
var Clipboard;

Clipboard = (function() {
  function Clipboard() {
    this.items = void 0;
  }


  /*
  Saves the passed newItems jQuery selector
   */

  Clipboard.prototype.pushItems = function(newItems) {
    return this.items = $.extend(true, {}, newItems);
  };


  /*
  Returns the saved jQuery selector and removes it from the save.
   */

  Clipboard.prototype.popItems = function() {
    var results;
    results = this.items;
    this.items = void 0;
    if (results != null) {
      return results;
    } else {
      return [];
    }
  };

  return Clipboard;

})();