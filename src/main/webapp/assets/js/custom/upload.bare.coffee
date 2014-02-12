Dropzone.options.avatarUpload =
  maxFilesize: 3
  autoProcessQueue: false
  maxFiles: 1
  thumbnailWidth: 512
  thumbnailHeight: 512
  acceptedFiles: 'image/*'
  uploadMultiple: false
#previewContainer: null
#clickable: false
  accept: (file, done) ->
    console.log "Uploaded"
    done()
  init: ->
    this.on "maxfilesexceeded", (file) ->
      console.log "Reached limit" + file.name
    this.on "addedfile", (file) ->
      console.log "#{file.name} added"
      this.removeFile(this.files[0]) if this.files.length > 1
    this.on "success", (file) ->
      console.log "successfully uploaded"
      window.location.reload()
    this.on "thumbnail", (file, dataUrl) ->
      $("input#avatar-original-width").val(file.width)
      $("input#avatar-original-height").val(file.height)
      this.processQueue()
