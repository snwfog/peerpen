// Generated by CoffeeScript 1.7.1

/*
This class is used to trigger graphicContentChanged Events
for a particular box. This event is fired whenever
the html of the corresponding graphic has changed
 */
var BoxHelper;

BoxHelper = (function() {
  function BoxHelper(graphic) {
    this.graphic = graphic;
    this.controller = void 0;
    this.content = void 0;
  }

  BoxHelper.prototype.bindEvents = function() {
    this.controller = ControllerFactory.getController(this.graphic.element);
    this.graphic.element.on('requestUndo', (function(_this) {
      return function(event) {
        _this._checkNewContent(false);
        return event.stopPropagation();
      };
    })(this)).focus((function(_this) {
      return function(event) {
        return _this._checkNewContent(true);
      };
    })(this)).blur((function(_this) {
      return function(event) {
        return _this._checkNewContent(true);
      };
    })(this));
    return this.controller.bindEvents();
  };


  /*
  Checks that the content of the graphic has changed and if it did,
  fire the graphicContentChanged event.
  if saveNewContent is true, the content new content will be saved
  for the next time this function will be called.
   */

  BoxHelper.prototype._checkNewContent = function(saveNewContent) {
    var graphicHtml;
    graphicHtml = this.graphic.element.html();
    if ((this.content != null) && this.content !== graphicHtml) {
      this.graphic.element.trigger('graphicContentChanged', [
        {
          graphic: this.graphic,
          prevContent: this.content,
          newContent: graphicHtml
        }
      ]);
    }
    return this.content = saveNewContent ? graphicHtml : void 0;
  };

  return BoxHelper;

})();