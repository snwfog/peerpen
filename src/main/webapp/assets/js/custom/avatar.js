$(document).ready(function() {

    $original = $('div#avatar-original-container img');
    $preview = $('div#avatar-preview-container img');
    var showPreview = function(coords) {
        var rx = 100 / coords.w;
        var ry = 100 / coords.h;

        $preview.css({
            width: Math.round(rx * 512) + 'px',
            height: Math.round(ry * 512) + 'px',
            marginLeft: '-' + Math.round(rx * coords.x) + 'px',
            marginTop: '-' + Math.round(ry * coords.y) + 'px'
        });

        $('input[id="avatar-crop-x1"]').val(coords.x);
        $('input[id="avatar-crop-x2"]').val(coords.x2);
        $('input[id="avatar-crop-y1"]').val(coords.y);
        $('input[id="avatar-crop-y2"]').val(coords.y2);
        $('input[id="avatar-crop-width"]').val(coords.w);
        $('input[id="avatar-crop-height"]').val(coords.h);
//        alert("x1 " + coords.x + " y1 " + coords.y + " w " + coords.w);
//        alert("x2 " + coords.x2 + " y2 " + coords.y2 + " h " + coords.h);


    }
    // Initialize jcrop on ready
    // Put settings fr jcrop
    $original.Jcrop({
        bgColor: 'rgba(0, 0, 0, 0.4)',
        bgOpacity: 1,
        setSelect: [0, 0, 50, 50],
        aspectRatio: 1,
        onSelect: showPreview,
        onChange: showPreview
    });





});
//// Required for drag and drop file access
//jQuery.event.props.push('dataTransfer');
//
//// IIFE to prevent globals
//(function() {
//
//  var s;
//  var Avatar = {
//
//    settings: {
//      bod: $("body"),
//      img: $("#profile-avatar"),
//      img2: $("#photo"),
//      fileInput: $("#uploader")
//    },
//
//    init: function() {
//      s = Avatar.settings;
//      Avatar.bindUIActions();
//    },
//
//    bindUIActions: function() {
//
//      var timer;
//
//      s.bod.on("dragover", function(event) {
//        clearTimeout(timer);
//        if (event.currentTarget == s.bod[0]) {
//          Avatar.showDroppableArea();
//        }
//
//        // Required for drop to work
//        return false;
//      });
//
//      s.bod.on('dragleave', function(event) {
//        if (event.currentTarget == s.bod[0]) {
//          // Flicker protection
//          timer = setTimeout(function() {
//            Avatar.hideDroppableArea();
//          }, 200);
//        }
//      });
//
//      s.bod.on('drop', function(event) {
//        // Or else the browser will open the file
//        event.preventDefault();
//
//        Avatar.handleDrop(event.dataTransfer.files);
//      });
//
//      s.fileInput.on('change', function(event) {
//        Avatar.handleDrop(event.target.files);
//      });
//    },
//
//    showDroppableArea: function() {
//      s.bod.addClass("droppable");
//    },
//
//    hideDroppableArea: function() {
//      s.bod.removeClass("droppable");
//    },
//
//    handleDrop: function(files) {
//
//      Avatar.hideDroppableArea();
//
//      // Multiple files can be dropped. Lets only deal with the "first" one.
//      var file = files[0];
//
//      if (typeof file !== 'undefined' && file.type.match('image.*')) {
//
//        Avatar.resizeImage(file, 450, function(data) {
//          Avatar.placeImage(data);
//        });
//      } else {
//
//        alert("You need to insert an image");
//
//      }
//
//    },
//
//    resizeImage: function(file, size, callback) {
//
//      var fileTracker = new FileReader;
//      fileTracker.onload = function() {
//        Resample(
//          this.result,
//          size,
//          size,
//          callback
//        );
//      }
//      fileTracker.readAsDataURL(file);
//
//      fileTracker.onabort = function() {
//        alert("The upload was aborted.");
//      }
//      fileTracker.onerror = function() {
//        alert("An error occured while reading the file.");
//      }
//
//    },
//
//    placeImage: function(data) {
//      s.img.attr("src", data);
//      s.img2.attr("src", data);
//    }
//
//
//  }
//
//  Avatar.init();
//
//})();