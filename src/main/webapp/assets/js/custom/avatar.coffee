$ ->
  $avatar = $("#avatar")
  if $avatar.height() > $avatar.width()
    $avatar.css
      'height': '100%'
  else
    $avatar.css
      'width': '100%'

  # Update of the cropping preview
  showPreview = (coords) ->
    $preview = $('div#avatar-preview-container img')
    $previewContainer = $('div#avatar-preview-container')
    previewHeight = $previewContainer.height()
    previewWidth = $previewContainer.width()

    $original = $('div#avatar-original-area img')
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

  $cropArea = $('div#avatar-original-area img')
  $cropArea.Jcrop
    bgColor: 'rgba(0, 0, 0, 0.4)'
    bgOpacity: 1
    setSelect: [0, 0, 50, 50]
    aspectRatio: 1
    onSelect: showPreview
    onChange: showPreview
