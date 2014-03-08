<%@ page import="com.peerpen.model.Avatar" %>
<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>

<% Peer sessionPeer = (Peer) request.getAttribute( "sessionUser" ); %>

<link rel="stylesheet" href="/assets/css/lib/dropzone/basic.css" type="text/css">
<link rel="stylesheet" href="/assets/css/lib/dropzone/dropzone.css" type="text/css">

<div id="dropzone-container">
    <form enctype="multipart/form-data" class="dropzone" id="avatar-upload"
          action="/peer/<%= sessionPeer.getId() %>/profile/avatar/upload" method="post">
        <input type="hidden" id="avatar-original-width" name="original-width" value="">
        <input type="hidden" id="avatar-original-height" name="original-height" value="">
    </form>
</div>

<script src="/assets/js/lib/dropzone/dropzone.min.js"></script>
<script src="/assets/js/lib/dropzone/dropzone-amd-module.min.js"></script>
<script src="/assets/js/custom/upload.bare.js"></script>

<div class="wrap-container">

    <div class="img-circle avatar-thumb" id="avatar-preview-container">
        <img id="avatar-preview"
             src="<%= sessionPeer.getAvatar().getRelativeServletContextAvatarPathForSize( request, Avatar.Size.ORIGINAL )%>"
             alt="<%= sessionPeer.getFirstName() %>" width="200px"/>
    </div>

    <div id="avatar-original-container" class="avatar-main">
        <form action="/peer/<%= sessionPeer.getId() %>/profile/avatar" method="post">
            <input type="hidden" id="avatar-crop-x1" name="x1" value="">
            <input type="hidden" id="avatar-crop-x2" name="x2" value="">
            <input type="hidden" id="avatar-crop-y1" name="y1" value="">
            <input type="hidden" id="avatar-crop-y2" name="y2" value="">
            <input type="hidden" id="avatar-crop-width" name="width" value="">
            <input type="hidden" id="avatar-crop-height" name="height" value="">

            <div id="avatar-original-area" class="avatar-box">
                <img id="avatar" src="<%= sessionPeer.getAvatar().getServletContextAvatarPathForSize( request, Avatar.Size.ORIGINAL ) %>"
                     alt="<%= sessionPeer.getFirstName() %>"/>
            </div>

            <div class="avatar-buttons">
                <a id="upload-new-avatar" href="#" class="btn btn-info">Upload New Avatar</a>

                <button class="btn btn-large btn-success" type="submit">Accept</button>
            </div>

        </form>
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>