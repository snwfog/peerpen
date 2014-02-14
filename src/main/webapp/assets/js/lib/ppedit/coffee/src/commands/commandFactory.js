// Generated by CoffeeScript 1.7.1

/*
This class is responsible for creating and providing commands on the fly.
 */
var CommandFactory;

CommandFactory = (function() {
  function CommandFactory() {}

  CommandFactory.prototype.createChangeFontSizeCommand = function(editor, boxesSelector, newFontSize) {
    return new ChangeStyleCommand(editor, boxesSelector, {
      'font-size': newFontSize
    });
  };

  CommandFactory.prototype.createChangeFontTypeCommand = function(editor, boxesSelector, newFontType) {
    return new ChangeStyleCommand(editor, boxesSelector, {
      'font-family': newFontType
    });
  };

  CommandFactory.prototype.createChangeFontWeightCommand = function(editor, boxesSelector, enable) {
    var fontWeightValue;
    fontWeightValue = enable ? 'bold' : 'normal';
    return new ChangeStyleCommand(editor, boxesSelector, {
      'font-weight': fontWeightValue
    });
  };

  CommandFactory.prototype.createChangeItalicFontCommand = function(editor, boxesSelector, enable) {
    var styleValue;
    styleValue = enable ? 'italic' : 'normal';
    return new ChangeStyleCommand(editor, boxesSelector, {
      'font-style': styleValue
    });
  };

  CommandFactory.prototype.createChangeUnderlineFontCommand = function(editor, boxesSelector, enable) {
    var styleValue;
    styleValue = enable ? 'underline' : 'none';
    return new ChangeStyleCommand(editor, boxesSelector, {
      'text-decoration': styleValue
    });
  };

  CommandFactory.prototype.createRightAlignmentCommand = function(editor, boxesSelector) {
    return new ChangeStyleCommand(editor, boxesSelector, {
      'text-align': 'right'
    });
  };

  CommandFactory.prototype.createLeftAlignmentCommand = function(editor, boxesSelector) {
    return new ChangeStyleCommand(editor, boxesSelector, {
      'text-align': 'left'
    });
  };

  CommandFactory.prototype.createCenterAlignmentCommand = function(editor, boxesSelector) {
    return new ChangeStyleCommand(editor, boxesSelector, {
      'text-align': 'center'
    });
  };

  CommandFactory.prototype.createChangeTextColorCommand = function(editor, boxesSelector, newColor) {
    return new ChangeStyleCommand(editor, boxesSelector, {
      'color': '#' + newColor
    });
  };

  CommandFactory.prototype.createChangeOpacityCommand = function(editor, editPage, boxId, prevVal, newVal) {
    return new ChangeBoxOpacityCommand(editor, editPage, boxId, prevVal, newVal);
  };

  CommandFactory.prototype.createMoveBoxCommand = function(box, toPosition, fromPosition) {
    return new MoveBoxCommand(box, toPosition, fromPosition);
  };

  CommandFactory.prototype.createRemoveBoxesCommand = function(editor, pageNum, boxesSelector) {
    return new RemoveBoxesCommand(editor, pageNum, boxesSelector);
  };

  CommandFactory.prototype.createCopyBoxesCommand = function(editor, editPage, boxesClones) {
    return new CopyBoxesCommand(editor, editPage, boxesClones);
  };

  CommandFactory.prototype.createCreateBoxesCommand = function(editor, editContainer, optionsList) {
    return new CreateBoxesCommand(editor, editContainer, optionsList);
  };

  CommandFactory.prototype.createCreateChangeBoxContentCommand = function(box, prevContent, newContent) {
    return new ChangeBoxContentCommand(box, prevContent, newContent);
  };

  CommandFactory.prototype.createMoveUpCommand = function(editor, pageNum, boxSelector) {
    return new ChangeDepthCommand(editor, pageNum, boxSelector, true);
  };

  CommandFactory.prototype.createMoveDownCommand = function(editor, pageNum, boxSelector) {
    return new ChangeDepthCommand(editor, pageNum, boxSelector, false);
  };

  CommandFactory.prototype.createLoadBoxesCommand = function(editor, jsonBoxes) {
    return new LoadBoxesCommand(editor, jsonBoxes);
  };

  CommandFactory.prototype.createAddPageCommand = function(editor) {
    return new AddOrRemoveCommand(editor, true);
  };

  return CommandFactory;

})();
