# Update of the cropping preview

showPreview = (coords) ->
  $preview = $('div#avatar-preview-container img')
  $previewContainer = $('div#avatar-preview-container')
  previewHeight = $previewContainer.height()
  previewWidth = $previewContainer.width()

  # Take the first, conflict with jcrop
  $original = $('div.dz-preview img:first')
  originalHeight = $original.height()
  originalWidth = $original.width()

  rx = previewWidth / coords.w
  ry = previewHeight / coords.h

  $preview.css
    width: Math.round(rx * originalWidth) + 'px'
    height: Math.round(ry * originalHeight) + 'px'
    marginLeft: '-' + Math.round(rx * coords.x) + 'px'
    marginTop: '-' + Math.round(ry * coords.y) + 'px'

  $('input#avatar-crop-x1').val(coords.x)
  $('input#avatar-crop-x2').val(coords.x2)
  $('input#avatar-crop-y1').val(coords.y)
  $('input#avatar-crop-y2').val(coords.y2)
  $('input#avatar-crop-width').val(coords.w)
  $('input#avatar-crop-height').val(coords.h)

# Initiate the dropzone api
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
    this.on "thumbnail", (file, dataURI) ->
      $preview = $('img#avatar-preview').attr('src', dataURI)
      $original = $('div.dz-preview img')
      $original.Jcrop
        bgColor: 'rgba(0, 0, 0, 0.4)'
        bgOpacity: 1
        setSelect: [0, 0, 50, 50]
        aspectRatio: 1
        onSelect: showPreview
        onChange: showPreview

