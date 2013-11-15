<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<%--<%--%>
    <%--Peer peer = (Peer) session.getAttribute( "user" );--%>
    <%--if (peer != null)--%>
    <%--{--%>
<%--%>--%>

<script>
$(document).ready(function() {
    $('#submit').click(function(event) {
        var ajax_value = $('#json_ajax').val();
        var owner_value = document.getElementById('isOwner').checked;
            alert(ajax_value);
        alert(owner_value);
        $.post('hunk.do', {
            json_ajax : ajax_value,
            isOwner: owner_value
        }, function(responseText) {
            $('#json_ajax').text(responseText);
        });
    });
});
</script>

<h2>Front-end simulator</h2>
<form action="" method="" >
    <br />
    <textarea rows="10" cols="50" name="json_ajax" id="json_ajax">
{
modified: [{id:box1, etag:111, html:content}],
created: [{id:box1, etag:111, html:content1},{id:box2, etag:111, html:content}],
deleted: [{id:box1, etag:111, html:content}]
}
    </textarea>
    <input type="radio" id="isOwner" name="isOwner" value="true" checked>Owner <input type="radio" name="isOwner" id="isOwner" value="false">Contributor
    <br />
    <input type="button" id="submit" value="post via ajax" />
</form>

<%--<%--%>
    <%--}--%>
    <%--else--%>
        <%--response.sendRedirect("/login");--%>
<%--%>--%>

<%@ include file="/view/includes/static/footer.jsp" %>