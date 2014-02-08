<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<% Peer sessionPeer = (Peer) request.getAttribute("sessionUser"); %>

<div class="container">
    <div class="row">
        <a href="/peer/<%= sessionPeer.getId() %>/profile/avatar/upload" class="btn btn-info">Upload New Avatar</a>
        <div class="col-md-8">
            <div id="avatar-original-container">
                <form  action="/peer/<%= sessionPeer.getId() %>/profile/avatar" method="post">
                    <input type="hidden" id="avatar-crop-x1" name="x1" value="">
                    <input type="hidden" id="avatar-crop-x2" name="x2" value="">
                    <input type="hidden" id="avatar-crop-y1" name="y1" value="">
                    <input type="hidden" id="avatar-crop-y2" name="y2" value="">
                    <input type="hidden" id="avatar-crop-width" name="width" value="">
                    <input type="hidden" id="avatar-crop-height" name="height" value="">

                    <div id="avatar-original-area">
                        <img id="avatar" src="/assets/images/peers/avatars/lg/grid.png" alt=""/>
                    </div>

                    <button class="btn btn-large btn-success" type="submit">Accept</button>

                </form>
            </div>
        </div>
        <div class="col-md-4">
            <div id="avatar-preview-container">
                <img id="avatar-preview" src="/assets/images/peers/avatars/lg/grid.png"/>
            </div>
        </div>
    </div>
</div>
<%@ include file="/view/includes/static/footer.jsp" %>