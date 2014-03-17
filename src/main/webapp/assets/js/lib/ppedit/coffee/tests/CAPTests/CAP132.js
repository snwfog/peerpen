// Generated by CoffeeScript 1.7.1
ppeditDescribe('A test for issue CAP-132 : "Add a load content API on PPedit"', function() {
  var boxObjects, singleBoxObject;
  singleBoxObject = [
    {
      1234: '<div class="ppedit-box" tabindex="0" contenteditable="true" id="1234" style="left: 54px; top: 90px; width: 163px; height: 119px; font-family: \'Times New Roman\'; font-size: 100%; font-weight: normal; text-decoration: none; font-style: normal; z-index: 0; text-align: left; vertical-align: bottom;"></div>'
    }
  ];
  boxObjects = [
    {
      1383319360353: '<div class="ppedit-box" tabindex="0" contenteditable="true" id="1383319360353" style="left: 54px; top: 90px; width: 163px; height: 119px; font-family: \'Times New Roman\'; font-size: 100%; font-weight: normal; text-decoration: none; font-style: normal; z-index: 0; text-align: left; vertical-align: bottom;"><div>Hello world.</div></div>',
      1383319393238: '<div class="ppedit-box" tabindex="0" contenteditable="true" id="1383319393238" style="left: 852px; top: 83px; width: 125px; height: 50px; font-family: \'Times New Roman\'; font-size: 100%; font-weight: bold; text-decoration: none; font-style: normal; z-index: 1; text-align: left; vertical-align: bottom;">This is a bold text at the right of the page</div>'
    }, {
      1383319427231: '<div class="ppedit-box" tabindex="0" contenteditable="true" id="1383319427231" style="left: 294px; top: 233px; width: 202px; height: 50px; font-family: \'Times New Roman\'; font-size: 100%; font-weight: normal; text-decoration: underline; font-style: italic; z-index: 2; text-align: left; vertical-align: bottom;">This is a Italic and underline text</div>',
      1383319782815: '<div class="ppedit-box" tabindex="0" contenteditable="true" id="1383319782815" style="left: 56px; top: 350px; width: 199px; height: 64px; font-family: \'Times New Roman\'; font-size: 100%; font-weight: normal; text-decoration: none; font-style: normal; z-index: 3; text-align: center; vertical-align: bottom;">This is a center-aligned text</div>'
    }
  ];
  it("can load 1 box", function() {
    expect($('.ppedit-box')).toHaveLength(0);
    $('.editor').ppedit('load', {
      hunks: JSON.stringify(singleBoxObject)
    });
    return expect($('.ppedit-box')).toHaveLength(1);
  });
  it("can move a loaded box around", function() {
    var box;
    $('.editor').ppedit('load', {
      hunks: JSON.stringify(singleBoxObject)
    });
    box = $('.ppedit-box');
    return moveBox(box, {
      dx: 200,
      dy: 0
    });
  });
  it("can enter content on a loaded box", function() {
    var box;
    $('.editor').ppedit('load', {
      hunks: JSON.stringify(singleBoxObject)
    });
    box = $('.ppedit-box');
    return enterText(box, "Lorem ipsum dolor sin amet");
  });
  it("does not remove the box when requesting undo right after loading it.", function() {
    $('.editor').ppedit('load', {
      hunks: JSON.stringify(singleBoxObject)
    });
    $('.ppedit-box-container').simulate("key-combo", {
      combo: "meta+z"
    });
    $('.ppedit-box-container').simulate("key-combo", {
      combo: "ctrl+z"
    });
    return expect($('.ppedit-box')).toHaveLength(1);
  });
  return it("can load 4 boxes with some contents in each", function() {
    $('.editor').ppedit('load', {
      hunks: JSON.stringify(boxObjects)
    });
    expect($('.ppedit-box')).toHaveLength(4);
    return expect($('.ppedit-box-container')).toContainHtml(boxObjects['1383319393238']);
  });
});