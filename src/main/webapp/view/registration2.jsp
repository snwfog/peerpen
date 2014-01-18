<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>


<%
    Peer peer = (Peer) session.getAttribute("user");
    if (peer != null)
    {
%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>
    </div>

</div>
<div class="register">
    <form class="form-inline" name="register" onsubmit="return validateProfile()" action="/registration2.do" method="post">
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
                <input type="hidden" class="form-control" name="id" value="<%= peer.getId()%>">
            </div>
    </div>
    </form>
</div>

<%
    }
    else
        response.sendRedirect("/login");
%>


<%@ include file="/view/includes/static/footer.jsp" %>