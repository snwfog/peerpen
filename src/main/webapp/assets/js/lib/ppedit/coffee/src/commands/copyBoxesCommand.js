// Generated by CoffeeScript 1.7.1
var CopyBoxesCommand,
  __hasProp = {}.hasOwnProperty,
  __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

CopyBoxesCommand = (function(_super) {
  __extends(CopyBoxesCommand, _super);

  function CopyBoxesCommand(editor, pageNum, boxesClones) {
    this.editor = editor;
    this.pageNum = pageNum;
    this.boxesClones = boxesClones;
    CopyBoxesCommand.__super__.constructor.call(this);
    this.newBoxes = [];
  }

  CopyBoxesCommand.prototype.execute = function() {
    var box, i, _i, _ref, _results;
    if (this.newBoxes.length === 0) {
      this.boxesClones.each((function(_this) {
        return function(index, boxItem) {
          var box, boxOptions;
          boxOptions = CSSJSON.toJSON(boxItem.style.cssText).attributes;
          box = new Box(_this.editor.areas[_this.pageNum].boxesContainer.element, boxOptions);
          return _this.newBoxes[index] = box;
        };
      })(this));
    }
    _results = [];
    for (i = _i = 0, _ref = this.newBoxes.length - 1; 0 <= _ref ? _i <= _ref : _i >= _ref; i = 0 <= _ref ? ++_i : --_i) {
      box = this.newBoxes[i];
      this.editor.areas[this.pageNum].boxesContainer.addBox(box);
      box.element.html(this.boxesClones.eq(i).html());
      this.editor.panel.addBoxRow(this.pageNum, box.element.attr('id'));
      _results.push(this.boxIds[i] = box.element.attr('id'));
    }
    return _results;
  };

  CopyBoxesCommand.prototype.undo = function() {
    var box, _i, _len, _ref, _results;
    _ref = this.newBoxes;
    _results = [];
    for (_i = 0, _len = _ref.length; _i < _len; _i++) {
      box = _ref[_i];
      this.editor.areas[this.pageNum].boxesContainer.removeBoxes([box.element.attr('id')]);
      _results.push(this.editor.panel.removeBoxRow([box.element.attr('id')]));
    }
    return _results;
  };

  CopyBoxesCommand.prototype.getType = function() {
    return 'Create';
  };

  CopyBoxesCommand.prototype.getPageNum = function() {
    return this.pageNum;
  };

  return CopyBoxesCommand;

})(Command);