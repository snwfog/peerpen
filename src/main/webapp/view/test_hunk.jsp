<%@ include file="/view/includes/static/header.jsp" %>

<script>
$(document).ready(function() {
    $('#submit').click(function(event) {
        var ajax_value = $('#json_ajax').val();
        alert(ajax_value);
        $.post('hunk.do', {
            json_ajax : ajax_value
        }, function(responseText) {
            $('#json_ajax').text(responseText);
        });
    });
});
</script>


<form action="" method="" >
    <br />
    <textarea rows="10" cols="50" name="json_ajax" id="json_ajax">
{
modified: [{id:box1, etag:111, html:content}],
created: [{id:box1, etag:111, html:content1},{id:box2, etag:111, html:content}],
deleted: [{id:box1, etag:111, html:content}]
}
    </textarea>
    <br />
    <input type="button" id="submit" value="send via ajax" />
</form>

</html>