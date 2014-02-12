$(document).on("click", ".confirmDeleteCommentDialog", function (e)
{
  e.preventDefault();
  var commentId = $(this).data('id');
  $("#commentid").val(commentId);
  $($(this).attr('href')).modal('show');
});

$(document).on("click", ".confirmDeleteBroadcastDialog", function (e)
{
    e.preventDefault();
    var broadcastId = $(this).data('id');
    $("#broadcastid").val(broadcastId);
    $($(this).attr('href')).modal('show');
});