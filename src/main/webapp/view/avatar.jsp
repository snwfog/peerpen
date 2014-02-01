<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<% Peer sessionPeer = (Peer)request.getAttribute("sessionUser"); %>

<div class="container">
        <div class="row">
            <div class="col-md-8">
                <div id="avatar-original-container">
                    <%--<img id="avatar-original" src="/assets/images/browser-icon-chrome.png"--%>
                         <%--name="avatar-original" alt="Image for Profile">--%>
                    <form enctype="multipart/form-data" class="dropzone" id="avatar-upload"
                          action="/peer/<%= sessionPeer.getId() %>/profile/avatar" method="post">
                        <input type="hidden" id="avatar-crop-x1" name="x1" value="">
                        <input type="hidden" id="avatar-crop-x2" name="x2" value="">
                        <input type="hidden" id="avatar-crop-y1" name="y1" value="">
                        <input type="hidden" id="avatar-crop-y2" name="y2" value="">
                        <input type="hidden" id="avatar-crop-width" name="width" value="">
                        <input type="hidden" id="avatar-crop-height" name="height" value="">


                        <input type="submit" value="Confirm" />
                    </form>
                </div>
            </div>
            <div class="col-md-4">
                <div id="avatar-preview-container">
                    <img id="avatar-preview" />
                </div>
            </div>
        </div>
</div>

<!-- avatar dragging part-->
<%--<script src="/assets/js/custom/resample.js"></script>--%>
<%--<script src="/assets/js/custom/avatar.coffee"></script>--%>
<%@ include file="/view/includes/static/footer.jsp" %>