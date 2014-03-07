<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>
<%@ include file="/view/includes/static/navbar.jsp" %>


<div class="register">
    <form class="form-inline" name="register"  action="/peer/<%=sessionUser.getId()%>/profile/full" method="post">
    <h1>Complete your profile</h1>
    <div class="row">
            <div class="col-md-6">
                <textarea class="form-control" style="resize:none;" name="description" rows="9" placeholder="Present your experience, skills and expertise"></textarea>
            </div>
            <div style="max-width:330px; margin-left:50%">
                <input type="date" class="form-control" name="dob" style="margin:10px 0" id="valid_dob" placeholder="Date of Birth" ><br/>
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
                </div><br/>
                <input type="text" class="form-control" name="country" style="margin:10px 0" placeholder="Country">
                <input type="text" class="form-control" name="industry" style="margin:10px 0" placeholder="Industry">
                <input type="text" class="form-control" name="yoe" style="margin:10px 0" id="valid_yoe" placeholder="Years of Experience" data-content="<div class='validation'>The years of experience must be in integer(s) format (eg. 12)</div>" data-html="true">
                <input type="text" class="form-control" name="personal_website" style="margin:10px 0" placeholder="Personal Website">
                <input type="hidden" class="form-control" name="peerId" value="<%=sessionUser.getId()%>">
                <button class="pull-right btn btn-primary" type="submit">Done</button>
            </div>
    </div>
    </form>
</div>

<%@ include file="/view/includes/static/footer.jsp" %>