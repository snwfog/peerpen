// Generated by CoffeeScript 1.6.3
(function() {
  Dropzone.options.avatarUpload = {
    maxFilesize: 3,
    autoProcessQueue: true,
    maxFiles: 1,
    thumbnailWidth: 512,
    thumbnailHeight: 512,
    acceptedFiles: 'image/*',
    uploadMultiple: false,
    accept: function(file, done) {
      console.log("Uploaded");
      return done();
    },
    init: function() {
      this.on("maxfilesexceeded", function(file) {
        return console.log("Reached limit" + file.name);
      });
      this.on("addedfile", function(file) {
        console.log("" + file.name + " added");
        if (this.files.length > 1) {
          return this.removeFile(this.files[0]);
        }
      });
      return this.on("success", function(file) {
        console.log("successfully uploaded");
        return window.location.reload();
      });
    }
  };

}).call(this);
