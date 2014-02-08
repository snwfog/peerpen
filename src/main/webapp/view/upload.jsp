<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<link rel="stylesheet" href="/assets/css/lib/dropzone/basic.css" type="text/css">
<link rel="stylesheet" href="/assets/css/lib/dropzone/dropzone.css" type="text/css">

<% Peer sessionPeer = (Peer) request.getAttribute("sessionUser"); %>

<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div id="avatar-original-container">
                <form enctype="multipart/form-data" class="dropzone" id="avatar-upload"
                      action="/peer/<%= sessionPeer.getId() %>/profile/avatar/upload" method="post">
                </form>
                <button id='avatar-upload-submit' class="btn btn-large btn-success" type="submit">Accept</button>
            </div>
        </div>
    </div>
</div>

<script src="/assets/js/lib/dropzone/dropzone.min.js"></script>
<script src="/assets/js/lib/dropzone/dropzone-amd-module.min.js"></script>
<script src="/assets/js/custom/upload.bare.js"></script>

<%@ include file="/view/includes/static/footer.jsp" %>