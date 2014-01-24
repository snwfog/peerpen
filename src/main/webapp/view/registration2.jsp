<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>


<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>
    </div>

</div>
<div class="register">
    <form class="form-inline" name="register" id ="additional" action="/additional" onsubmit="return validateProfile()" method="post">
    <h1>Complete your profile <button class="pull-right btn btn-primary" type="submit">Next (1/2)</button></h1>
    <div class="row">
            <div class="col-md-5">
                <textarea class="form-control" style="resize:none;" name="description" rows="9" placeholder="Present your experience, skills and expertise"></textarea>
            </div>
            <div style="max-width:330px; margin-left:50%">
                <input type="text" class="form-control" name="dob" style="margin:10px 0" id="valid_dob" placeholder="Date of Birth" data-content="<div class='validation'>Valid date required in the form of yyyy-MM-dd (eg. 2014-04-18)</div>" data-html="true">
                <div class="radio">
                    <label>
                        <input style="margin:10px 10px" type="radio" name="gender" id="optionsRadios1" value="Male" checked>
                        Male
                    </label>
                </div>
                <div class="radio">
                    <label>
                        <input style="margin:10px 10px" type="radio" name="gender" id="optionsRadios2" value="Female">
                        Female
                    </label>
                </div>
                <input type="text" class="form-control" name="country" style="margin:10px 0" placeholder="Country">
                <input type="text" class="form-control" name="industry" style="margin:10px 0" placeholder="Industry">
                <input type="text" class="form-control" name="yoe" style="margin:10px 0" id="valid_yoe" placeholder="Years of Experience" data-content="<div class='validation'>The years of experience must be in integer(s) format (eg. 12)</div>" data-html="true">
                <input type="text" class="form-control" name="personal_website" style="margin:10px 0" placeholder="Personal Website">
                <input type="hidden" class="form-control" name="id" value="2">
            </div>
    </div>
    </form>
</div>

<div class="page-wrap">
    <form action="/profile" name="myForm" id="form_description" method="post">
        <input type="hidden" name="x1" value="" />
        <input type="hidden" name="y1" value="" />
        <input type="hidden" name="x2" value="" />
        <input type="hidden" name="y2" value="" />
    </form>

    <form action="upload.do" method="post" enctype="multipart/form-data">
        <div  class="pull-right">
            <img id="photo" src="/assets/images/profile/256.jpg" style="float:left;" />
            <h3>Upload a picture through browsing...</h3>

            <input id="uploader" type="file" name="file" size="50" />
            <br />
            <input type="submit" value="Upload File" />
        </div>
        <div class="dravatar pull-left" style=" max-width:500px;">
            <h4>Drag & Drop picture <%=request.getAttribute("path")%></h4>
            <div class="dravatar-avatar-wrap">
                <img src="/assets/images/profile/256.jpg" name="avatar-image" id="profile-avatar" alt="Image for Profile">
            </div>
        </div>

    </form>
</div>

<!-- avatar dragging part-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="/assets/js/custom/resample.js"></script>
<script src="/assets/js/custom/avatar.js"></script>

<%@ include file="/view/includes/static/footer.jsp" %>