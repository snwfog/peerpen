// Generated by CoffeeScript 1.7.1

/*
Graphic containing the image of a grid to hide/display.
 */
var Grid,
  __hasProp = {}.hasOwnProperty,
  __extends = function(child, parent) { for (var key in parent) { if (__hasProp.call(parent, key)) child[key] = parent[key]; } function ctor() { this.constructor = child; } ctor.prototype = parent.prototype; child.prototype = new ctor(); child.__super__ = parent.prototype; return child; };

Grid = (function(_super) {
  __extends(Grid, _super);

  function Grid(root) {
    this.root = root;
    Grid.__super__.constructor.call(this, this.root);
  }

  Grid.prototype.buildElement = function() {
    return this.element = $('<div class="ppedit-grid"> <svg width="100%" height="100%"> <defs> <pattern id="smallGrid" width="8" height="8" patternUnits="userSpaceOnUse"> <path d="M 8 0 L 0 0 0 8" fill="none" stroke="gray" stroke-width="0.5"/> </pattern> <pattern id="grid" width="80" height="80" patternUnits="userSpaceOnUse"> <rect width="80" height="80" fill="url(#smallGrid)"/> <path d="M 80 0 L 0 0 0 80" fill="none" stroke="gray" stroke-width="1"/> </pattern> </defs> <rect width="100%" height="100%" fill="url(#grid)" /> </svg> </div>');
  };


  /*
  Hides/show the grid.
   */

  Grid.prototype.toggleGrid = function() {
    return this.element.toggle();
  };

  return Grid;

})(Graphic);