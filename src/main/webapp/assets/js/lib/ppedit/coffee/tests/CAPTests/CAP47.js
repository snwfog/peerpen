// Generated by CoffeeScript 1.7.1
ppeditDescribe("A test for issue CAP-47 : As a user, I want to select and move aggregated elements in my workspace", function() {
  return it("can select and move elements in the workspace", function() {
    var boxes;
    addBox(3);
    boxes = $('.ppedit-box');
    moveBox(boxes.eq(0), {
      dx: 200,
      dy: 0
    });
    return setTimeout((function() {
      var canvas;
      moveBox(boxes.eq(1), {
        dx: 0,
        dy: 200
      });
      canvas = $('.ppedit-canvas');
      selectRectangle(canvas, {
        topLeft: {
          left: viewPortPosition(canvas).left + 49,
          top: viewPortPosition(canvas).top + 49
        },
        size: {
          width: 500,
          height: 100
        }
      });
      return expect($('.ppedit-box-selected')).toHaveLength(2);
    }), 300);
  });
});