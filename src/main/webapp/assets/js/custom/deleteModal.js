jQuery(document).ready(function($) {
  $('#tabs').tab();
});
$(document).on("click", ".confirmDeleteCommentDialog", function (e)
{
  e.preventDefault();
  var commentId = $(this).data('id');
  $("#commentid").val(commentId);
  $($(this).attr('href')).modal('show');
});