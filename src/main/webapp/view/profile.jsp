<%@ page import="com.peerpen.model.Peer" %>
<%@ include file="/view/includes/static/header.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
    $("#edit").click(function(){
        $(".peerProperties").attr("readonly",false);
    });
    $("#done").click(function(){
        $(".peerProperties").attr("readonly",true);
    });

});
</script>



<%
    Peer peer = (Peer) session.getAttribute("user");
    if (peer != null) {
%>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand">PeerPen</a>
        </div>

    </div>
</div>

<br>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-3">
            <div class="thumbnail">
                <img src="/assets/images/profile/pic.jpg" >
                <div class="caption">
                    <h4><%= peer.getFirstName() %> <%= peer.getLastName() %></h4>
                    <form action="/profile.do" method="post">
                        <table>
                            <tr><td>id</td><td><input type="text" class="peerId" name="id" value="<%= peer.getId().toString() %>" readonly></td></tr>
                            <tr><td>First</td><td><input type="text" class="peerProperties" name="first_name" value="<%= peer.getFirstName().toString() %>" readonly></td></tr>
                            <tr><td>Last</td><td><input type="text" class="peerProperties" name="last_name" value="<%= peer.getLastName().toString() %>" readonly></td></tr>
                            <tr><td>Username</td><td><input type="text" class="peerProperties" name="user_name" value="<%= peer.getUserName().toString() %>" readonly></td></tr>
                            <tr><td>Email</td><td><input type="text" class="peerProperties" name="email" value="<%= peer.getEmail().toString() %>" readonly></td></tr>
                            <tr><td>Points</td><td><input type="text" class="peerPoint" name="point" value="<%= Integer.parseInt(peer.getPoint().toString()) %>" readonly></td></tr>
                            <tr><td>Website</td><td><input type="text" class="peerProperties" name="personal_website" value="<%= peer.getPersonalWebsite().toString() %>" readonly></td></tr>
                        </table>
                        <a href="#" id="edit">edit</a> | <a href="#" id="done">done</a>
                        <input type="submit" value="Save" />
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<p align="center"> (<a href="/logout.do">logout</a>)</p>



<%
    } else {
        response.sendRedirect("/login");
    }
%>


<%@ include file="/view/includes/static/footer.jsp" %>