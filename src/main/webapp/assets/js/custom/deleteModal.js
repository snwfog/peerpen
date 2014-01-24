jQuery(document).ready(function($) {
  $('#tabs').tab();
});
$(document).on("click", ".confirmDeleteCommentDialog", function ()
{
  var commentId = $(this).data('id');
  $(".modal-footer #commentid").val(commentId);
});