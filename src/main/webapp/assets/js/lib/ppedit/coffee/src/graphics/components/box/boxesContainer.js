// Generated by CoffeeScript 1.7.1

/*
Graphic acting as a container of boxes.
 */
var BoxesContainer,
  __hasProp = {}.hasOwnProperty,
  __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

BoxesContainer = (function(_super) {
  __extends(BoxesContainer, _super);

  BoxesContainer.CLICK_TIME_INTERVAL = 200;

  function BoxesContainer(root) {
    this.root = root;
    BoxesContainer.__super__.constructor.call(this, this.root);
    this.boxes = {};
  }

  BoxesContainer.prototype.buildElement = function() {
    this.element = $('<div></div>').addClass('ppedit-box-container');
    this.element.append('<p class="hDotLine"></p>');
    return this.element.append('<p class="vDotLine"></p>');
  };

  BoxesContainer.prototype.bindEvents = function() {
    var box, id, _ref, _results;
    this.element.mousedown((function(_this) {
      return function(event) {
        event.preventDefault();
        return _this.unSelectAllBoxes();
      };
    })(this)).dblclick((function(_this) {
      return function(event) {
        var boxCssOptions;
        boxCssOptions = _this.getPointClicked(event);
        if (_this.getSelectedBoxes().length === 0) {
          return _this.element.trigger('addBoxRequested', [boxCssOptions]);
        }
      };
    })(this)).click((function(_this) {
      return function(event) {
        return _this.root.trigger('unSelectBoxes');
      };
    })(this));
    _ref = this.boxes;
    _results = [];
    for (id in _ref) {
      box = _ref[id];
      _results.push(box.bindEvents());
    }
    return _results;
  };


  /*
  Selects the boxes contained in the passed rect.
  The rect position is relative to the root.
   */

  BoxesContainer.prototype.selectBoxesInRect = function(rect) {
    var selectRect;
    selectRect = {
      topLeft: {
        left: rect.topLeft.left + this.element.scrollLeft(),
        top: rect.topLeft.top + this.element.scrollTop()
      },
      size: rect.size
    };
    if (selectRect.size.width < 0) {
      selectRect.topLeft.left -= Math.abs(selectRect.size.width);
      selectRect.size.width = Math.abs(selectRect.size.width);
    }
    if (selectRect.size.height < 0) {
      selectRect.topLeft.top -= Math.abs(selectRect.size.height);
      selectRect.size.height = Math.abs(selectRect.size.height);
    }
    return this.getAllBoxes().each((function(_this) {
      return function(index, box) {
        if (Geometry.rectContainsRect(selectRect, _this.boxBounds($(box)))) {
          return _this.boxes[box.id].select();
        }
      };
    })(this));
  };


  /*
  Returns the bounding rectangle of the box matching the
  passed box selector.
   */

  BoxesContainer.prototype.boxBounds = function(boxSelector) {
    var result;
    return result = {
      topLeft: {
        left: boxSelector.position().left + this.element.scrollLeft(),
        top: boxSelector.position().top + this.element.scrollTop()
      },
      size: {
        width: boxSelector.width(),
        height: boxSelector.height()
      }
    };
  };


  /*
  Adds the passed Box Object to the Box List
   */

  BoxesContainer.prototype.addBox = function(box) {
    if (box.element == null) {
      box.buildElement();
    }
    this.element.append(box.element);
    box.bindEvents();
    return this.boxes[box.element.attr('id')] = box;
  };


  /*
  Given an array of box ids, deletes all box objects
  with those ids.
   */

  BoxesContainer.prototype.removeBoxes = function(boxIds) {
    var id, _i, _len, _results;
    _results = [];
    for (_i = 0, _len = boxIds.length; _i < _len; _i++) {
      id = boxIds[_i];
      this.boxes[id].element.removeClass('ppedit-box-selected').removeClass('ppedit-box-focus').remove();
      _results.push(delete this.boxes[id]);
    }
    return _results;
  };


  /*
  Returns an array of Box objects corresponding to the
  passed boxIds.
   */

  BoxesContainer.prototype.getBoxesFromIds = function(boxIds) {
    var id;
    return (function() {
      var _i, _len, _results;
      _results = [];
      for (_i = 0, _len = boxIds.length; _i < _len; _i++) {
        id = boxIds[_i];
        if (this.boxes[id] != null) {
          _results.push(this.boxes[id]);
        }
      }
      return _results;
    }).call(this);
  };


  /*
  Returns an list of box objects corresponding to the
  passed selector matching box elements.
   */

  BoxesContainer.prototype.getBoxesFromSelector = function(selector) {
    var box, results, _i, _len, _ref;
    results = {};
    _ref = selector.toArray();
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      box = _ref[_i];
      results[box.id] = this.boxes[box.id];
    }
    return results;
  };


  /*
  Returns a selector matching all boxes
   */

  BoxesContainer.prototype.getAllBoxes = function() {
    return this.element.find('.ppedit-box');
  };


  /*
  Returns a selector to the currently selected boxes
   */

  BoxesContainer.prototype.getSelectedBoxes = function() {
    return this.element.find('.ppedit-box:focus, .ppedit-box-selected, .ppedit-box-focus');
  };


  /*
  Returns a selector to the currently selected boxes,
  excluding the focused one, if any.
   */

  BoxesContainer.prototype.getNotFocusedSelectedBoxes = function() {
    return this.element.find('.ppedit-box-selected');
  };


  /*
  Changes the opacity of one box

  @param boxid [Int] the id of the box to change
  @param opacityVal [Int] the value of the opacity to change the box to.
   */

  BoxesContainer.prototype.changeBoxOpacity = function(boxid, opacityVal) {
    return this.boxes[boxid].element.css("opacity", opacityVal);
  };


  /*
  Unselects all boxes.
   */

  BoxesContainer.prototype.unSelectAllBoxes = function() {
    var box, id, _ref, _results;
    _ref = this.boxes;
    _results = [];
    for (id in _ref) {
      box = _ref[id];
      box.stopMoving();
      _results.push(box.element.removeClass('ppedit-box-focus').blur());
    }
    return _results;
  };


  /*
  Returns the position relative to the top left corner
  of the element from the passed mouseEvent.
   */

  BoxesContainer._rectContainsRect = function(outerRect, innerRect) {
    return innerRect.topLeft.x >= outerRect.topLeft.x && innerRect.topLeft.y >= outerRect.topLeft.y && innerRect.topLeft.x + innerRect.size.width <= outerRect.topLeft.x + outerRect.size.width && innerRect.topLeft.y + innerRect.size.height <= outerRect.topLeft.y + outerRect.size.height;
  };


  /*
  Returns the mouse coordinates of the passed mouseEvent
  relative to the boxes Container position.
   */

  BoxesContainer.prototype.getPointClicked = function(mouseEvent) {
    return {
      left: event.offsetX + this.element.scrollLeft(),
      top: event.offsetY + this.element.scrollTop()
    };
  };


  /*
  Returns a JSON object containing a description of
  all the boxes currently existing in this container.
   */

  BoxesContainer.prototype.getAllHunks = function() {
    var box, boxId;
    return (function() {
      var _ref, _results;
      _ref = this.boxes;
      _results = [];
      for (boxId in _ref) {
        box = _ref[boxId];
        _results.push({
          id: boxId,
          html: box.element.wrap("<div></div>").parent().html()
        });
      }
      return _results;
    }).call(this);
  };

  return BoxesContainer;

})(Graphic);